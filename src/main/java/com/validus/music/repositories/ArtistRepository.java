package com.validus.music.repositories;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validus.music.domain.Artist;



public interface ArtistRepository extends JpaRepository<Artist, Integer>{
	
	List<Artist> findAll();
	
	public Optional<Artist> findArtistById(@PositiveOrZero Integer id);
	
	public Optional<Artist> findArtistByNameIgnoreCase(@NotBlank String name);
	
	public List<Artist> findAllArtistsByAlbums_id(@PositiveOrZero int albumId);
	
	public List<Artist> findAllArtistsByAlbumsIgnoreCase_name(@NotBlank String albumName);
	
	public List<Artist> findAllArtistsByAlbums_yearReleased(@PositiveOrZero int yearReleased);

	public Artist save(Artist newArtist);
	
	public void deleteById(@PositiveOrZero Integer id);

	public List<Artist> findAllArtistsByAlbums_songs_id(@PositiveOrZero int songId);
	
	public List<Artist> findAllArtistsByAlbumsIgnoreCase_songs_name(@NotEmpty String songName);
	
	public List<Artist> findAllArtistsByAlbums_songs_track(@PositiveOrZero int trackId);

}
