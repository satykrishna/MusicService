package com.validus.music.services.controller.it;

import static com.validus.music.TestData.testAlbumByIdData;
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
import com.validus.music.TestData;
import com.validus.music.api.model.AlbumDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlbumControllerTestIT {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private final static String ALBUM_URL = "/api/album/";

	@Test
	@DisplayName("create album")
	public void addAlbum() throws Exception {
		
		AlbumDTO albumDTO = TestData.createAlbumDTO();

		HttpEntity<AlbumDTO> entity = new HttpEntity<AlbumDTO>(albumDTO, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(ALBUM_URL), HttpMethod.POST, entity,
				String.class);

		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		AlbumDTO strToAlbumDTO = objectMapper.readValue(json, AlbumDTO.class);

		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

		assertEquals(albumDTO.getName(), strToAlbumDTO.getName());

//		assertEquals(albumDTO.getSongsListDTO(), strToAlbumDTO.getSongsListDTO());
	}

	@Test
	@DisplayName("testalbumbyid")
	public void testAlbumById() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String url = createURLWithPort("/api/album/id/1");

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		AlbumDTO strToAlbum = objectMapper.readValue(json, AlbumDTO.class);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);

		assertEquals(testAlbumByIdData().getId(), strToAlbum.getId());

		assertEquals(testAlbumByIdData().getName(), strToAlbum.getName());

	}

	@Test
	@DisplayName("testalbumbyname")
	public void testAlbumByName() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String url = createURLWithPort("/api/album/name/Drones");

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		AlbumDTO strToAlbum = objectMapper.readValue(json, AlbumDTO.class);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);

		assertEquals(testAlbumByIdData().getId(), strToAlbum.getId());

		assertEquals(testAlbumByIdData().getName(), strToAlbum.getName());


	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/music-service" + uri;
	}
}
