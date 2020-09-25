package com.validus.music.services.controller;

import static com.validus.music.TestData.createArtistDTO;
import static com.validus.music.TestData.createArtistListDTO;
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
import com.validus.music.api.model.ArtistDTO;
import com.validus.music.controllers.ArtistController;
import com.validus.music.controllers.Exception.ResourceNotFoundException;
import com.validus.music.services.ArtistService;

@WebMvcTest(controllers = ArtistController.class)
public class ArtistControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArtistService artistService;

	private List<ArtistDTO> artistDTOList;

	private ObjectMapper objMapper;

	@BeforeEach
	void setup() {
		this.artistDTOList = createArtistListDTO();
		this.objMapper = new ObjectMapper();
	}

	@Test
	@DisplayName("fetch all artists")
	void fetchAllArtists() throws Exception {

		BDDMockito.given(artistService.findAll()).willReturn(artistDTOList);

		this.mockMvc.perform(get("/api/artist/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(artistDTOList.size())));
	}

	@Test
	@DisplayName("fetch artist by artistId")
	void fetchArtistById() throws Exception {

		int artistId = 1;

		ArtistDTO artist = createArtistDTO();

		artist.setId(1);

		BDDMockito.given(artistService.findArtistById(BDDMockito.anyInt())).willReturn(artist);

		this.mockMvc.perform(get("/api/artist/id/{id}", artistId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.artist_id", is(artist.getId())));
	}

	@Test
	@DisplayName("Not artist by not existing artistId")
	void fetchArtistByIdNotFound() throws Exception {

		int artistId = 3;

		ArtistDTO artist = createArtistDTO();

		artist.setId(1);

		BDDMockito.given(artistService.findArtistById(BDDMockito.anyInt())).willThrow(ResourceNotFoundException.class);

		this.mockMvc.perform(get("/api/artist/id/{id}", artistId)).andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Not artist by not negative artistId")
	void fetchArtistByNegativeIdNotFound() throws Exception {

		int artistId = -2;

		ArtistDTO artist = createArtistDTO();

		artist.setId(1);

		this.mockMvc.perform(get("/api/artist/id/{id}", artistId)).andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("create artist")
	void createArtist() throws Exception {

		ArtistDTO artist = createArtistDTO();

		BDDMockito.given(artistService.save(BDDMockito.any(ArtistDTO.class))).will(i -> i.getArgument(0));

		this.mockMvc.perform(
				post("/api/artist").contentType(MediaType.APPLICATION_JSON).content(objMapper.writeValueAsString(artist)))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("delete artist")
	void delete() throws Exception {

		ArtistDTO artist = createArtistDTO();

		artist.setId(1);

		BDDMockito.given(artistService.findArtistById(BDDMockito.anyInt())).willReturn(artist);
		BDDMockito.doNothing().when(artistService).deleteById(BDDMockito.anyInt());

		BDDMockito.given(artistService.save(BDDMockito.any(ArtistDTO.class))).will(i -> i.getArgument(0));

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/artist/id/{id}", artist.getId()))
				.andExpect(status().isOk());
	}

}
