package com.validus.music.services.springdatajpa;

import static com.validus.music.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.validus.music.api.mapper.ArtistMapper;
import com.validus.music.api.model.ArtistDTO;
import com.validus.music.controllers.Exception.ResourceNotFoundException;
import com.validus.music.domain.Artist;
import com.validus.music.repositories.ArtistRepository;

@DataJpaTest
public class ArtistJPAServiceUnitTest {

	@Mock
	private ArtistRepository artistRepository;

	@Mock
	private ArtistMapper artistMapper;

	@InjectMocks
	ArtistJPAService artistService;

	@Test
	@DisplayName("should save artist successfully")
	void testartistSave() {

		given(artistRepository.save(BDDMockito.any(Artist.class))).willReturn(createArtist());

		given(artistMapper.artistDTOtoArtist(BDDMockito.any(ArtistDTO.class))).willReturn(createArtist());

		given(artistMapper.artistToArtistDTO(BDDMockito.any(Artist.class))).willReturn(createArtistDTO());

		ArtistDTO saved = artistService.save(createArtistDTO());

		assertEquals(saved.getName(), "artist1");
		
		BDDMockito.verify(artistRepository).save(BDDMockito.any(Artist.class));
		
		BDDMockito.verify(artistRepository, times(1)).save(BDDMockito.any(Artist.class));
	}

	@Test
	@DisplayName("should find all artists")
	void testFindAll() {

		List<Artist> artists = createArtistList();
		
		List<ArtistDTO> artistDTOList = createArtistListDTO();
		
		Artist artist = createArtist();
		
		ArtistDTO artistDTO = createArtistDTO();
		
		BDDMockito.given(artistMapper.artistToArtistDTO(BDDMockito.any(Artist.class))).willReturn(artistDTO);
		
		given(artistRepository.findAll()).willReturn(artists);
		
		List<ArtistDTO> find = artistService.findAll();
		
		assertEquals(find, artistDTOList);
		
		BDDMockito.verify(artistRepository, times(2)).findAll();

	}
	
	@Test
	@DisplayName("should find artist by id")
	void testFindById() {
		
		BDDMockito.given(artistMapper.artistToArtistDTO(BDDMockito.any(Artist.class))).willReturn(createArtistDTO());
		
		given(artistRepository.findArtistById(BDDMockito.anyInt())).willReturn(Optional.of(createArtist()));
		
		ArtistDTO artistById = artistService.findArtistById(createArtist().getId());

		assertEquals(artistById, createArtistDTO());
	}
	
	@Test
	@DisplayName("shouldn't find artist by id")
	void testFindByIDThatNotExists() {
		
		
		given(artistRepository.findArtistById(anyInt())).willReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, ()-> {
			artistService.findArtistById(createArtistDTO().getId());
		});
		
		
	}
	
}
