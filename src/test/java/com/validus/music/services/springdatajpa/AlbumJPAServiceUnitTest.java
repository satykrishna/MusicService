package com.validus.music.services.springdatajpa;

import static com.validus.music.TestData.createAlbum;
import static com.validus.music.TestData.createAlbumDTO;
import static com.validus.music.TestData.createAlbumList;
import static com.validus.music.TestData.createAlbumListDTO;
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

import com.validus.music.api.mapper.AlbumMapper;
import com.validus.music.api.model.AlbumDTO;
import com.validus.music.controllers.Exception.ResourceNotFoundException;
import com.validus.music.domain.Album;
import com.validus.music.repositories.AlbumRepository;

@ExtendWith(MockitoExtension.class)
class AlbumJPAServiceUnitTest {

	@Mock
	private AlbumRepository albumRepository;

	@Mock
	private AlbumMapper albumMapper;

	@InjectMocks
	AlbumJPAService albumService;

	@Test
	@DisplayName("should save album successfully")
	void testAlbumSave() {

		given(albumRepository.save(BDDMockito.any(Album.class))).willReturn(createAlbum());

		given(albumMapper.albumDtoToAlbum(BDDMockito.any(AlbumDTO.class))).willReturn(createAlbum());

		given(albumMapper.albumToAlbumDTO(BDDMockito.any(Album.class))).willReturn(createAlbumDTO());

		AlbumDTO saved = albumService.save(createAlbumDTO());

		assertEquals(saved.getName(), "album1");
		
		BDDMockito.verify(albumRepository).save(BDDMockito.any(Album.class));
		
		BDDMockito.verify(albumRepository, times(1)).save(BDDMockito.any(Album.class));
	}

	@Test
	@DisplayName("should find all Albums")
	void testFindAll() {

		List<Album> albums = createAlbumList();
		
		List<AlbumDTO> albumDTOList = createAlbumListDTO();
		
		Album album = createAlbum();
		
		AlbumDTO albumDTO = createAlbumDTO();
		
		BDDMockito.given(albumMapper.albumToAlbumDTO(BDDMockito.any(Album.class))).willReturn(albumDTO);
		
		given(albumRepository.findAll()).willReturn(albums);
		
		List<AlbumDTO> find = albumService.findAllAlbums();
		
		assertEquals(find, albumDTOList);
		
		BDDMockito.verify(albumRepository, times(1)).findAll();

	}
	
	@Test
	@DisplayName("should find album by id")
	void testFindById() {
		
		BDDMockito.given(albumMapper.albumToAlbumDTO(BDDMockito.any(Album.class))).willReturn(createAlbumDTO());
		
		given(albumRepository.findAlbumById(BDDMockito.anyInt())).willReturn(Optional.of(createAlbum()));
		
		AlbumDTO albumById = albumService.findAlbumById(createAlbum().getId());

		assertEquals(albumById, createAlbumDTO());
	}
	
	@Test
	@DisplayName("shouldn't find album by id")
	void testFindByIDThatNotExists() {
		
		
		given(albumRepository.findAlbumById(anyInt())).willReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, ()-> {
			albumService.findAlbumById(createAlbumDTO().getId());
		});
		
		
	}
	

}
