package com.validus.music.services.springdatajpa;

import static com.validus.music.TestData.*;
import static com.validus.music.TestData.createSongDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.validus.music.api.mapper.SongMapper;
import com.validus.music.api.model.SongDTO;
import com.validus.music.controllers.Exception.ResourceNotFoundException;
import com.validus.music.domain.Song;
import com.validus.music.repositories.SongRepository;

@ExtendWith(MockitoExtension.class)
public class SongJPAServiceTest {

	@Mock
	private SongRepository songRepository;

	@Mock
	private SongMapper SongMapper;

	@InjectMocks
	SongJPAService SongService;

	@Test
	@DisplayName("should save Song successfully")
	void testSongSave() {

		BDDMockito.given(songRepository.save(BDDMockito.any(Song.class))).willReturn(createSong());

		BDDMockito.given(SongMapper.toSong(BDDMockito.any(SongDTO.class))).willReturn(createSong());

		BDDMockito.given(SongMapper.toSongDTO(BDDMockito.any(Song.class))).willReturn(createSongDTO());

		SongDTO saved = SongService.save(createSongDTO());

		assertEquals(saved.getName(), "Song1");
		
		BDDMockito.verify(songRepository).save(BDDMockito.any(Song.class));
		
		BDDMockito.verify(songRepository, times(1)).save(BDDMockito.any(Song.class));
	}

	@Test
	@DisplayName("should find all Songs")
	void testFindAll() {

		List<Song> Songs = createSongList();
		
		List<SongDTO> SongDTOList = createSongListDTO();
		
		Song Song = createSong();
		
		SongDTO SongDTO = createSongDTO();
		
		BDDMockito.given(SongMapper.toSongDTO(BDDMockito.any(Song.class))).willReturn(createSongDTO());

		given(songRepository.findAll()).willReturn(Songs);
		
		List<SongDTO> find = SongService.findAllSongs();
		
		assertEquals(find, SongDTOList);
		

	}
	
	@Test
	@DisplayName("should find Song by id")
	void testFindById() {
		
		BDDMockito.given(SongMapper.toSongDTO(BDDMockito.any(Song.class))).willReturn(createSongDTO());
		
		given(songRepository.findSongById(BDDMockito.anyInt())).willReturn(Optional.of(createSong()));
		
		SongDTO SongById = SongService.findSongById(createSong().getId());

		assertEquals(SongById, createSongDTO());
	}
	
	@Test
	@DisplayName("shouldn't find Song by id")
	void testFindByIDThatNotExists() {
		
		
		given(songRepository.findSongById(anyInt())).willReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, ()-> {
			SongService.findSongById(createSongDTO().getId());
		});
		
		
	}
	
}
