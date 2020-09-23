package com.validus.music.services;

import java.util.List;
import java.util.Optional;

import com.validus.music.api.model.ArtistDTO;
import com.validus.music.domain.Artist;

public interface ArtistService {

	List<ArtistDTO> findAll();
	
	public ArtistDTO findArtistById(Integer id);
	
	public ArtistDTO findArtistByNameIgnoreCase(String name);
	
	public List<ArtistDTO> findAllArtistsByAlbums_id(int albumId);
	
	public List<ArtistDTO> findAllArtistsByAlbumsIgnoreCase_name(String albumName);
	
	public List<ArtistDTO> findAllArtistsByAlbums_yearReleased(int yearReleased);

	public ArtistDTO save(ArtistDTO newArtist);
	
	public void deleteById(Integer id);

	public List<ArtistDTO> findAllArtistsByAlbums_songs_id(int songId);
	
	public List<ArtistDTO> findAllArtistsByAlbums_songs_name(String songName);
	
	public List<ArtistDTO> findAllArtistsByAlbums_songs_track(int trackId);
	
	public ArtistDTO replace(ArtistDTO newArtist, int id);
	
}
