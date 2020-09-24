package com.validus.music.services.springdatajpa;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.validus.music.api.mapper.AlbumMapper;
import com.validus.music.api.model.AlbumDTO;
import com.validus.music.api.model.SongDTO;
import com.validus.music.domain.Album;
import com.validus.music.domain.Song;
import com.validus.music.repositories.AlbumRepository;

@ExtendWith(MockitoExtension.class)
class AlbumJPAServiceUnitTest {

	@Mock
	private AlbumRepository albumRepository;
	
	@Mock
	private AlbumMapper albumMapper;
	
	@InjectMocks
	AlbumJPAService albumService;
	
	@Mock
	private Set<SongDTO> songs;
	
	@BeforeEach
	public void initialize() {
	
	final SongDTO songDTO1 = new SongDTO();
		
		songDTO1.setName("song1");
		songDTO1.setTrack(1);
		
		final SongDTO songDTO2 = new SongDTO();
		
		songDTO2.setName("song2");
		songDTO2.setTrack(2);
		
		Set<SongDTO> songset = new HashSet<>();
		
		songset.add(songDTO2);
		
		songset.add(songDTO1);
	
		when(songs).thenReturn(songset);
	}
	
	
	@Test
	@DisplayName("should save album successfully")
	void shouldSaveAlbumSuccessfully() {
		
		final AlbumDTO albumDTO = new AlbumDTO();
		
		albumDTO.setName("album1");
		
		albumDTO.setYearReleased(2010);

		albumDTO.setSongsListDTO(songs);
		
		Album newAlbum = albumMapper.albumDtoToAlbum(albumDTO);
		
		given(albumRepository.findAlbumByNameIgnoreCase(newAlbum.getName())).willReturn(Optional.empty());
		
		given(albumRepository.save(newAlbum)).willAnswer(i->i.getArgument(0));
		
		AlbumDTO saved = albumService.save(albumDTO);
		
		assertThat(saved).isNotNull();
		
		verify(albumRepository).save(any(Album.class));
		
		
	}
 
}
