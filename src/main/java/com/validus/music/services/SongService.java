package com.validus.music.services;

import java.util.List;

import com.validus.music.api.model.SongDTO;

public interface SongService {

//	public List<Song> findAll(Integer albumId);
//	
//	public List<Song> findAll(String albumName);
//	
//	public Song findSongsByName(String songName);
//	
//	public Song save(Song newSong);
//	
//	public Song findById(Integer songId);
//	
//	public void delete(Song song);
//	
//	public void delete(Song song, String albumName);
//	
//	public void deleteById(Integer songId);

//	public List<SongDTO> findSongs(Integer albumId);
	
	public SongDTO findSongById(Integer songId);
	
	public SongDTO findSongByName(String songName);
	
	public List<SongDTO> findSongsByTrack(int track);
	
	public List<SongDTO> findAllSongs();
	
	public SongDTO save(SongDTO newSong);
	
	public void deleteBySongId(Integer id);

	public SongDTO replaceSong(SongDTO newSong, Integer id);
	
}
