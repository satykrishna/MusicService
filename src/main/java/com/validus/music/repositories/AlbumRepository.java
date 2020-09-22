
package com.validus.music.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validus.music.api.model.SongDTO;
import com.validus.music.domain.Album;
import com.validus.music.domain.Song;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
	
 
	public List<Album> findAll();
	
	public Optional<Album> findAlbumById(Integer id);
	
	public Optional<Album> findAlbumByNameIgnoreCase(String name);
	
	public List<Album> findAllAlbumsByYearReleased(int yearReleased);
	
	public Album save(Album newAlbum);
	
	public void deleteById(Integer id);
	

}
