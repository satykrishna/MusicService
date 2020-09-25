package com.validus.music.repositories;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.validus.music.domain.Album;
import com.validus.music.domain.Artist;
import com.validus.music.domain.Song;

@DataJpaTest
public class ArtistRepositoryTest {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Test
	@DisplayName("test find all Artists")
	public void testAllArtists() {
		List<Artist> Artists = artistRepository.findAll();
		assertNotNull(Artists);
	}

	@Test
	@DisplayName("test Existing Artist By Id")
	public void testArtistById() {
		int id = 1;
		Optional<Artist> ArtistOptional = artistRepository.findArtistById(id);
		assertNotNull(ArtistOptional.get());
		assertEquals(ArtistOptional.get().getId(), id);
	}

	@Test
	@DisplayName("test Artist by id that doesn't exist")
	public void testArtistByIdThatNotExists() {
		int id = -1;
		Optional<Artist> ArtistOptional = artistRepository.findArtistById(-1);
		assertEquals(ArtistOptional, Optional.empty());
		assertThatExceptionOfType(NoSuchElementException.class);
	}
	
	@Test
	@DisplayName("test Existing Artist By Name")
	public void testArtistByNameThatExists() {
		String name = "Muse";
		Optional<Artist> ArtistOptional = artistRepository.findArtistByNameIgnoreCase(name);
		assertNotNull(ArtistOptional.get());
		assertEquals(ArtistOptional.get().getName(), name);
	}

	@Test
	@DisplayName("test Artist by id that doesn't exist")
	public void testArtistByNameThatNotExists() {
		String name="";
		Optional<Artist> ArtistOptional = artistRepository.findArtistByNameIgnoreCase(name);
		assertEquals(ArtistOptional, Optional.empty());
		assertThatExceptionOfType(NoSuchElementException.class);

	}


	@Test
	@DisplayName("test new insert Artist ")
	public void testSave() {
		List<Song> songs = Arrays.asList(new Song(1, "song1"), new Song(2, "song2"));
		Album newAlbum = new Album("NewAlbum", 2015, songs);
		Set<Album> albums = new HashSet<>();
		albums.add(newAlbum);
		Artist newArtist = new Artist("artist1", albums);
		Artist saved = artistRepository.save(newArtist);
		assertNotNull(saved);
	}

	@Test
	@DisplayName("test delete by Id")
	public void testDeleteById() {
		long count = artistRepository.findAll().stream().count();
		List<Song> songs = Arrays.asList(new Song(1, "song1"), new Song(2, "song2"));
		Album newAlbum = new Album("NewAlbum", 2015, songs);
		Set<Album> albums = new HashSet<>();
		albums.add(newAlbum);
		Artist newArtist = new Artist("artist1", albums);
		Artist saved = artistRepository.save(newArtist);
		artistRepository.deleteById(saved.getId());
		assertEquals(count, artistRepository.findAll().stream().count());
	};


}
