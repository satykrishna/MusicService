package com.validus.music.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validus.music.domain.Artist;



public interface ArtistRepository extends JpaRepository<Artist, Integer>{
	
	List<Artist> findAll();
	
	public Optional<Artist> findArtistById(Integer id);
	
	public Optional<Artist> findArtistByNameIgnoreCase(String name);
	
	public List<Artist> findAllArtistsByAlbums_id(int albumId);
	
	public List<Artist> findAllArtistsByAlbumsIgnoreCase_name(String albumName);
	
	public List<Artist> findAllArtistsByAlbums_yearReleased(int yearReleased);

	public Artist save(Artist newArtist);
	
	public void deleteById(Integer id);

	public List<Artist> findAllArtistsByAlbums_songs_id(int songId);
	
	public List<Artist> findAllArtistsByAlbumsIgnoreCase_songs_name(String songName);
	
	public List<Artist> findAllArtistsByAlbums_songs_track(int trackId);

}
