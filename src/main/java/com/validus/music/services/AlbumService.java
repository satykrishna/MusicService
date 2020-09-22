package com.validus.music.services;

import java.util.List;

import com.validus.music.api.model.AlbumDTO;

public interface AlbumService {

	public List<AlbumDTO> findllAlbums();
	
	public AlbumDTO findAlbumById(Integer id);
	
	public AlbumDTO findAlbumByNameIgnoreCase(String name);
	
	public List<AlbumDTO> findAllAlbumsByYearReleased(int yearReleased);
	
	public AlbumDTO save(AlbumDTO newAlbum);
	
	public void deleteById(Integer id);
	
	public AlbumDTO replaceAlbum(AlbumDTO newAlbum, Integer id);

}
