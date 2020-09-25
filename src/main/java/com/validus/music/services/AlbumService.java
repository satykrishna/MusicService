package com.validus.music.services;

import java.util.List;

import javax.validation.constraints.Positive;

import com.validus.music.api.model.AlbumDTO;

public interface AlbumService {

	public List<AlbumDTO> findAllAlbums();
	
	public AlbumDTO findAlbumById(Integer id);
	
	public AlbumDTO findAlbumByNameIgnoreCase(String name);
	
	public List<AlbumDTO> findAllAlbumsByYearReleased(int yearReleased);
	
	public AlbumDTO save(AlbumDTO newAlbum);
	
	public void deleteById(Integer id);
	
	public AlbumDTO replaceAlbum(AlbumDTO newAlbum, Integer id);
	
	public AlbumDTO findAlbumBySongName(String songName);
	
	public AlbumDTO findAlbumBySongId(int id);
	
	public List<AlbumDTO> findAlbumsByTrack(int trackId);
	
//	public List<AlbumDTO> findAlbumByArtistsIgnoreCase_name(String name);

//	public List<AlbumDTO> findAlbumByArtists_id(@Positive Integer artistId);

}
