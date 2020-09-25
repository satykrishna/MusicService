package com.validus.music.services.controller;

import static com.validus.music.TestData.createAlbumDTO;
import static com.validus.music.TestData.createAlbumListDTO;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.validus.music.api.model.AlbumDTO;
import com.validus.music.controllers.AlbumController;
import com.validus.music.controllers.Exception.ResourceNotFoundException;
import com.validus.music.services.AlbumService;

@WebMvcTest(controllers = AlbumController.class)
public class AlbumControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AlbumService albumService;

	private List<AlbumDTO> albumDTOList;

	private ObjectMapper objMapper;

	@BeforeEach
	void setup() {
		this.albumDTOList = createAlbumListDTO();
		this.objMapper = new ObjectMapper();
	}

	@Test
	@DisplayName("fetch all albums")
	void fetchAllAlbums() throws Exception {

		BDDMockito.given(albumService.findAllAlbums()).willReturn(albumDTOList);

		this.mockMvc.perform(get("/api/album/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(albumDTOList.size())));
	}

	@Test
	@DisplayName("fetch album by albumId")
	void fetchAlbumById() throws Exception {

		int albumId = 1;

		AlbumDTO album = createAlbumDTO();

		album.setId(1);

		BDDMockito.given(albumService.findAlbumById(BDDMockito.anyInt())).willReturn(album);

		this.mockMvc.perform(get("/api/album/id/{id}", albumId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.album_id", is(album.getId())));
	}

	@Test
	@DisplayName("Not album by not existing albumId")
	void fetchAlbumByIdNotFound() throws Exception {

		int albumId = 3;

		AlbumDTO album = createAlbumDTO();

		album.setId(1);

		BDDMockito.given(albumService.findAlbumById(BDDMockito.anyInt())).willThrow(ResourceNotFoundException.class);

		this.mockMvc.perform(get("/api/album/id/{id}", albumId)).andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Not album by not negative albumId")
	void fetchAlbumByNegativeIdNotFound() throws Exception {

		int albumId = -2;

		AlbumDTO album = createAlbumDTO();

		album.setId(1);

		this.mockMvc.perform(get("/api/album/id/{id}", albumId)).andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("create album")
	void createAlbum() throws Exception {

		AlbumDTO album = createAlbumDTO();

		BDDMockito.given(albumService.save(BDDMockito.any(AlbumDTO.class))).will(i -> i.getArgument(0));

		this.mockMvc.perform(
				post("/api/album").contentType(MediaType.APPLICATION_JSON).content(objMapper.writeValueAsString(album)))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("delete album")
	void delete() throws Exception {

		AlbumDTO album = createAlbumDTO();

		album.setId(1);

		BDDMockito.given(albumService.findAlbumById(BDDMockito.anyInt())).willReturn(album);
		BDDMockito.doNothing().when(albumService).deleteById(BDDMockito.anyInt());

		BDDMockito.given(albumService.save(BDDMockito.any(AlbumDTO.class))).will(i -> i.getArgument(0));

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/album/id/{id}", album.getId()))
				.andExpect(status().isOk());
	}

}
