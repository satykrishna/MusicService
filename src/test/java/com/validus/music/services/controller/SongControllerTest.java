package com.validus.music.services.controller;

import static com.validus.music.TestData.createSongDTO;
import static com.validus.music.TestData.createSongListDTO;
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
import com.validus.music.api.model.SongDTO;
import com.validus.music.controllers.SongsController;
import com.validus.music.controllers.Exception.ResourceNotFoundException;
import com.validus.music.services.SongService;

@WebMvcTest(controllers = SongsController.class)
public class SongControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SongService songService;

	private List<SongDTO> songDTOList;

	private ObjectMapper objMapper;

	@BeforeEach
	void setup() {
		this.songDTOList = createSongListDTO();
		this.objMapper = new ObjectMapper();
	}

	@Test
	@DisplayName("fetch all songs")
	void fetchAllSongs() throws Exception {

		BDDMockito.given(songService.findAllSongs()).willReturn(songDTOList);

		this.mockMvc.perform(get("/api/song/all")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(songDTOList.size())));
	}

	@Test
	@DisplayName("fetch song by songId")
	void fetchSongById() throws Exception {

		int songId = 1;

		SongDTO song = createSongDTO();

		song.setId(1);

		BDDMockito.given(songService.findSongById(BDDMockito.anyInt())).willReturn(song);

		this.mockMvc.perform(get("/api/song/id/{id}", songId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.song_id", is(song.getId())));
	}

	@Test
	@DisplayName("Not song by not existing songId")
	void fetchSongByIdNotFound() throws Exception {

		int songId = 3;

		SongDTO song = createSongDTO();

		song.setId(1);

		BDDMockito.given(songService.findSongById(BDDMockito.anyInt())).willThrow(ResourceNotFoundException.class);

		this.mockMvc.perform(get("/api/song/id/{id}", songId)).andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Not song by not negative songId")
	void fetchSongByNegativeIdNotFound() throws Exception {

		int songId = -2;

		SongDTO song = createSongDTO();

		song.setId(1);

		this.mockMvc.perform(get("/api/song/id/{id}", songId)).andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("create song")
	void createSong() throws Exception {

		SongDTO song = createSongDTO();

		BDDMockito.given(songService.save(BDDMockito.any(SongDTO.class))).will(i -> i.getArgument(0));

		this.mockMvc.perform(
				post("/api/song").contentType(MediaType.APPLICATION_JSON).content(objMapper.writeValueAsString(song)))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("delete song")
	void delete() throws Exception {

		SongDTO song = createSongDTO();

		song.setId(1);

		BDDMockito.given(songService.findSongById(BDDMockito.anyInt())).willReturn(song);
		BDDMockito.doNothing().when(songService).deleteBySongId(BDDMockito.anyInt());

		BDDMockito.given(songService.save(BDDMockito.any(SongDTO.class))).will(i -> i.getArgument(0));

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/song/id/{id}", song.getId()))
				.andExpect(status().isOk());
	}

}
