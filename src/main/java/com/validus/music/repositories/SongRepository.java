package com.validus.music.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validus.music.domain.Song;


public interface SongRepository extends JpaRepository<Song, Integer> {

	public List<Song> findAllSongsById(int id);
	
	public Optional<Song> findSongById(int id);	
	
	public Optional<Song> findSongByNameIgnoreCase(String name);
	 
	public List<Song> findAll();
	
	public List<Song> findAllSongByTrack(int trackId);
	
	public Song save(Song newSong);
	
	public void deleteById(Integer id);
	
	
}
