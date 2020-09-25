package com.validus.music.services.controller.it;

import static com.validus.music.TestData.testArtistByIdData;
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
import com.validus.music.api.model.ArtistDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistsControllerTestIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private final static String ALBUM_URL = "/api/artist/";

	@Test
	@DisplayName("create artist")
	public void addArtist() throws Exception {
		
		ArtistDTO artistDTO = TestData.createArtistDTO();

		HttpEntity<ArtistDTO> entity = new HttpEntity<ArtistDTO>(artistDTO, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(ALBUM_URL), HttpMethod.POST, entity,
				String.class);

		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		ArtistDTO strToArtistDTO = objectMapper.readValue(json, ArtistDTO.class);

		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

		assertEquals(artistDTO.getName(), strToArtistDTO.getName());

//		assertEquals(artistDTO.getSongsListDTO(), strToArtistDTO.getSongsListDTO());
	}

	@Test
	@DisplayName("testartistbyid")
	public void testArtistById() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String url = createURLWithPort("/api/artist/id/1");

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		ArtistDTO strToArtist = objectMapper.readValue(json, ArtistDTO.class);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);

		assertEquals(testArtistByIdData().getId(), strToArtist.getId());

		assertEquals(testArtistByIdData().getName(), strToArtist.getName());

	}

	@Test
	@DisplayName("testartistbyname")
	public void testArtistByName() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String url = createURLWithPort("/api/artist/name/Muse");

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		ArtistDTO strToArtist = objectMapper.readValue(json, ArtistDTO.class);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);

		assertEquals(testArtistByIdData().getId(), strToArtist.getId());

		assertEquals(testArtistByIdData().getName(), strToArtist.getName());

	}
	
	@Test
	@DisplayName("testArtistDelete")
	public void testArtistDelete() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		String url = createURLWithPort("/api/artist/name/Muse");

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String json = response.getBody();

		ObjectMapper objectMapper = new ObjectMapper();

		ArtistDTO strToArtist = objectMapper.readValue(json, ArtistDTO.class);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);

		assertEquals(testArtistByIdData().getId(), strToArtist.getId());

		assertEquals(testArtistByIdData().getName(), strToArtist.getName());

	}

	

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/music-service" + uri;
	}
}
