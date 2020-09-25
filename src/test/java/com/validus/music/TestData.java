package com.validus.music;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.validus.music.api.model.AlbumDTO;
import com.validus.music.api.model.ArtistDTO;
import com.validus.music.api.model.SongDTO;
import com.validus.music.domain.Album;
import com.validus.music.domain.Artist;
import com.validus.music.domain.Song;

public class TestData {
	
	public static ArtistDTO testArtistByIdData() {
		ArtistDTO artistDTO = new ArtistDTO();
		artistDTO.setName("Muse");
		return artistDTO;
	}

	public static AlbumDTO testAlbumByIdData() {
		return new AlbumDTO(1, "Drones", 2015, emptySet());
	}
	
	public static SongDTO testSongByIdData() {
		return new SongDTO(1, 1, "Dead Inside");		
	}

	public static List<ArtistDTO> createArtistListDTO() {
		return asList(createArtistDTO());
	}

	public static List<Artist> createArtistList() {
		return asList(createArtist());
	}

	public static Artist createArtist() {
		Artist artistDTO = new Artist();
		artistDTO.setName("artist1");
		artistDTO.setAlbums(createAlbumList().stream().collect(toSet()));
		return artistDTO;
	}

	
	public static ArtistDTO createArtistDTO() {
		ArtistDTO artistDTO = new ArtistDTO();
		artistDTO.setName("artist1");
		artistDTO.setAlbumDTO(createAlbumListDTO().stream().collect(toSet()));
		return artistDTO;
	}
	
	public static SongDTO createSongDTO() {
		SongDTO songDTO = new SongDTO();
		songDTO.setName("Song1");
		songDTO.setTrack(2);
		return songDTO;
	}
	
	public static Song createSong() {
		Song songDTO = new Song();
		songDTO.setName("Song1");
		songDTO.setTrack(2);
		return songDTO;
	}
	
	public static List<SongDTO> createSongListDTO() {
		return asList(createSongDTO());
	}

	public static List<Song> createSongList() {
		return asList(createSong());
	}

	
	public static List<AlbumDTO> createAlbumListDTO() {
		return asList(createAlbumDTO());
	}
	
	public static AlbumDTO createAlbumDTO() {
		
		final SongDTO songDTO1 = new SongDTO();

		songDTO1.setName("song1");
		songDTO1.setTrack(1);
		
		final SongDTO songDTO2 = new SongDTO();

		songDTO2.setName("song2");
		songDTO2.setTrack(2);
		
		Set<SongDTO> songsDTO = new HashSet<>();
		
		songsDTO.add(songDTO2);
		songsDTO.add(songDTO1);
		

		final AlbumDTO albumDTO = new AlbumDTO();

		albumDTO.setName("album1");

		albumDTO.setYearReleased(2010);
		
		albumDTO.setSongsListDTO(songsDTO);
		
		return albumDTO;
	}
	
	public static List<Album> createAlbumList() {
		return asList(createAlbum());
	}
	
	public static Album createAlbumWithId() {
		
		Album album = createAlbum();
		album.setId(1);
		
		return album;
	}

	
	public static AlbumDTO createAlbumDTOWithId() {
		
		AlbumDTO albumDTO = createAlbumDTO();
		
		albumDTO.setId(1);
		
		return albumDTO;
	}
	
	
	public static Album createAlbum() {
		
		final Song songDTO1 = new Song();

		songDTO1.setName("song1");
		songDTO1.setTrack(1);
		
		final Song songDTO2 = new Song();

		songDTO2.setName("song2");
		songDTO2.setTrack(2);
		
		Set<Song> songsDTO = new HashSet<>();
		
		songsDTO.add(songDTO2);
		songsDTO.add(songDTO1);
		

		final Album albumDTO = new Album();

		albumDTO.setName("album1");

		albumDTO.setYearReleased(2010);
		
		albumDTO.setSongs(songsDTO.stream().collect(toList()));
		
		return albumDTO;


	}
	
	
}
