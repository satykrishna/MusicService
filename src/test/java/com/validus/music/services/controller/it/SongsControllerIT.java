package com.validus.music.services.controller.it;

import static com.validus.music.TestData.testSongByIdData;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.validus.music.api.model.SongDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongsControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private final static String SONG_URL = "/api/song/";

	@Test
	@DisplayName("create song")
	public void addSong() throws Exception {

		SongDTO songDTO = new SongDTO();

		songDTO.setName("song_new");

		songDTO.setTrack(1);

		HttpEntity<SongDTO> entity = new HttpEntity<SongDTO>(songDTO, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(SONG_URL), HttpMethod.POST, entity,
				String.class);
		
		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		
		SongDTO strToSongDTO = objectMapper.readValue(json, SongDTO.class);

		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		
		assertEquals(songDTO.getName(), strToSongDTO.getName());

		

	}

	@Test
	@DisplayName("testsongbyid")
	public void testASongById() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String url = createURLWithPort("/api/song/id/1");

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		SongDTO strToSongDTO = objectMapper.readValue(json, SongDTO.class);

		assertEquals(testSongByIdData().getId(), strToSongDTO.getId());

		assertEquals(testSongByIdData().getName(), strToSongDTO.getName());

	}

	@Test
	@DisplayName("testsongbyname")
	public void testASongByName() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String url = createURLWithPort("/api/song/name/Dead Inside");

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		SongDTO strToSongDTO = objectMapper.readValue(json, SongDTO.class);

		assertEquals(testSongByIdData().getId(), strToSongDTO.getId());

		assertEquals(testSongByIdData().getName(), strToSongDTO.getName());

	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/music-service" + uri;
	}
}
