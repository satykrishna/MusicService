package com.validus.music.repositories;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.constraints.PositiveOrZero;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.validus.music.domain.Album;
import com.validus.music.domain.Song;

@DataJpaTest
public class AlbumRepositoryTest {

	/*
	 * public List<Album> findAll();
	 * 
	 * public Optional<Album> findAlbumById(@PositiveOrZero Integer id);
	 * 
	 * public Optional<Album> findAlbumByNameIgnoreCase(@NotBlank String name);
	 * 
	 * public List<Album> findAllAlbumsByYearReleased(@PositiveOrZero int
	 * yearReleased);
	 * 
	 * public Album save(@Valid Album newAlbum);
	 * 
	 * public void deleteById(@PositiveOrZero Integer id);
	 * 
	 * public Optional<Album> findAlbumBySongs_nameIgnoreCase(@NotBlank String
	 * name);
	 * 
	 * public Optional<Album> findAlbumBySongs_id(@PositiveOrZero int id);
	 * 
	 * public List<Album> findAlbumBySongs_track(@PositiveOrZero int trackId);
	 */

	@Autowired
	private AlbumRepository albumRepository;

	@Test
	@DisplayName("test find all albums")
	public void testAllAlbums() {
		List<Album> albums = albumRepository.findAll();
		assertNotNull(albums);
		assertEquals(4, albums.size());
	}

	@Test
	@DisplayName("test Existing Album By Id")
	public void testAlbumById() {
		int id = 1;
		Optional<Album> albumOptional = albumRepository.findAlbumById(id);
		assertNotNull(albumOptional.get());
		assertEquals(albumOptional.get().getId(), id);
	}

	@Test
	@DisplayName("test album by id that doesn't exist")
	public void testAlbumByIdThatNotExists() {
		int id = -1;
		Optional<Album> albumOptional = albumRepository.findAlbumById(-1);
		assertEquals(albumOptional, Optional.empty());
		assertThatExceptionOfType(NoSuchElementException.class);
	}
	
	@Test
	@DisplayName("test Existing Album By Name")
	public void testAlbumByNameThatExists() {
		String name = "Drones";
		Optional<Album> albumOptional = albumRepository.findAlbumByNameIgnoreCase(name);
		assertNotNull(albumOptional.get());
		assertEquals(albumOptional.get().getName(), name);
	}

	@Test
	@DisplayName("test album by id that doesn't exist")
	public void testAlbumByNameThatNotExists() {
		String name="";
		Optional<Album> albumOptional = albumRepository.findAlbumByNameIgnoreCase(name);
		assertEquals(albumOptional, Optional.empty());
		assertThatExceptionOfType(NoSuchElementException.class);

	}


	@Test
	@DisplayName("test new insert album ")
	public void testSave() {
		List<Song> songs = Arrays.asList(new Song(1, "song1"), new Song(2, "song2"));
		Album newAlbum = new Album("NewAlbum", 2015, songs);
		Album saved = albumRepository.save(newAlbum);
		assertNotNull(saved);
	}

	@Test
	@DisplayName("test delete by Id")
	public void testDeleteById() {
		long count = albumRepository.findAll().stream().count();
		List<Song> songs = Arrays.asList(new Song(1, "song1"), new Song(2, "song2"));
		Album newAlbum = new Album("NewAlbum", 2015, songs);
		Album saved = albumRepository.save(newAlbum);
		albumRepository.deleteById(saved.getId());
		assertEquals(count, albumRepository.findAll().stream().count());
	};
	
}
