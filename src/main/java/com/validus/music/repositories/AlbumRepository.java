
package com.validus.music.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validus.music.api.model.AlbumDTO;
import com.validus.music.api.model.SongDTO;
import com.validus.music.domain.Album;
import com.validus.music.domain.Song;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
	
 
	public List<Album> findAll();
	
	public Optional<Album> findAlbumById(@PositiveOrZero Integer id);
	
	public Optional<Album> findAlbumByNameIgnoreCase(@NotBlank String name);
	
	public List<Album> findAllAlbumsByYearReleased(@PositiveOrZero int yearReleased);
	
	public Album save(@Valid Album newAlbum);
	
	public void deleteById(@PositiveOrZero Integer id);
	
	public Optional<Album> findAlbumBySongs_nameIgnoreCase(@NotBlank String name);
	
	public Optional<Album> findAlbumBySongs_id(@PositiveOrZero int id);

	public List<Album> findAlbumBySongs_track(@PositiveOrZero int trackId);
	
	

}
