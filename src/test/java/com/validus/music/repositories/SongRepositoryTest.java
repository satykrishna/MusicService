package com.validus.music.repositories;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.validus.music.domain.Song;
import com.validus.music.domain.Song;

@DataJpaTest
public class SongRepositoryTest {

	@Autowired
	private SongRepository songRepository;
	
	@Test
	@DisplayName("test find all Songs")
	public void testAllSongs() {
		List<Song> Songs = songRepository.findAll();
		assertNotNull(Songs);
	}

	@Test
	@DisplayName("test Existing Song By Id")
	public void testSongById() {
		int id = 1;
		Optional<Song> SongOptional = songRepository.findSongById(id);
		assertNotNull(SongOptional.get());
		assertEquals(SongOptional.get().getId(), id);
	}

	@Test
	@DisplayName("test Song by id that doesn't exist")
	public void testSongByIdThatNotExists() {
		int id = -1;
		Optional<Song> SongOptional = songRepository.findById(-1);
		assertEquals(SongOptional, Optional.empty());
		assertThatExceptionOfType(NoSuchElementException.class);
	}
	
	@Test
	@DisplayName("test Existing Song By Name")
	public void testSongByNameThatExists() {
		String name = "Intro";
		Optional<Song> SongOptional = songRepository.findSongByNameIgnoreCase(name);
		assertNotNull(SongOptional.get());
		assertEquals(SongOptional.get().getName(), name);
	}

	@Test
	@DisplayName("test Song by id that doesn't exist")
	public void testSongByNameThatNotExists() {
		String name="";
		Optional<Song> SongOptional = songRepository.findSongByNameIgnoreCase(name);
		assertEquals(SongOptional, Optional.empty());
		assertThatExceptionOfType(NoSuchElementException.class);

	}


	@Test
	@DisplayName("test new insert Song ")
	public void testSave() {
		Song newSong = new Song(1, "NewSong");
		Song saved = songRepository.save(newSong);
		assertNotNull(saved);
	}

	@Test
	@DisplayName("test delete by Id")
	public void testDeleteById() {
		long count = songRepository.findAll().stream().count();
		Song newSong = new Song(1, "NewSong");
		Song saved = songRepository.save(newSong);
		songRepository.deleteById(saved.getId());
		assertEquals(count, songRepository.findAll().stream().count());
	};

	
}
