package com.validus.music.services.springdatajpa;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.validus.music.api.mapper.ArtistMapper;
import com.validus.music.api.model.ArtistDTO;
import com.validus.music.controllers.Exception.ResourceNotFoundException;
import com.validus.music.domain.Album;
import com.validus.music.domain.Artist;
import com.validus.music.repositories.ArtistRepository;
import com.validus.music.services.ArtistService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistJPAService implements ArtistService {

	private final ArtistRepository artistRepoistory;

	private final ArtistMapper artistMapper;

	@Override
	public List<ArtistDTO> findAll() {
		log.info("find All artists: {} ", artistRepoistory.findAll());
		return artistRepoistory.findAll().stream().map(artistMapper::artistToArtistDTO).collect(toList());
	}

	@Override
	public ArtistDTO findArtistById(Integer id) {
		return artistRepoistory.findArtistById(id).map(artistMapper::artistToArtistDTO)
				.orElseThrow(() -> new ResourceNotFoundException("couldn't find Artist by Id " + id));
	}

	@Override
	public ArtistDTO findArtistByNameIgnoreCase(String name) {
		return artistRepoistory.findArtistByNameIgnoreCase(name).map(artistMapper::artistToArtistDTO)
				.orElseThrow(() -> new ResourceNotFoundException("couldn't find Artist by Name " + name));
	}

	@Override
	public List<ArtistDTO> findAllArtistsByAlbums_id(int albumId) {
		return artistRepoistory.findAllArtistsByAlbums_id(albumId).stream().
				map(artistMapper::artistToArtistDTO).
				collect(toList());
			
	}

	@Override
	public List<ArtistDTO> findAllArtistsByAlbumsIgnoreCase_name(String albumName) {
		return artistRepoistory.findAllArtistsByAlbumsIgnoreCase_name(albumName).stream().map(artistMapper::artistToArtistDTO).collect(toList());
	}

	@Override
	public List<ArtistDTO> findAllArtistsByAlbums_yearReleased(int yearReleased) {
		return artistRepoistory.findAllArtistsByAlbums_yearReleased(yearReleased).stream().map(artistMapper::artistToArtistDTO).collect(toList());
	}

	@Override
	public ArtistDTO save(ArtistDTO newArtist) {
		Artist artist = artistRepoistory.save(artistMapper.artistDTOtoArtist(newArtist));
		return artistMapper.artistToArtistDTO(artist);
	}

	@Override
	public void deleteById(Integer id) {
		artistRepoistory.findArtistById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Didn't Find Artist with Id - " + id));
		artistRepoistory.deleteById(id);
	}

	@Override
	public List<ArtistDTO> findAllArtistsByAlbums_songs_id(int songId) {
		return artistRepoistory.findAllArtistsByAlbums_songs_id(songId).stream().map(artistMapper::artistToArtistDTO).collect(toList());
	}

	@Override
	public List<ArtistDTO> findAllArtistsByAlbums_songs_name(String songName) {
		return artistRepoistory.findAllArtistsByAlbumsIgnoreCase_songs_name(songName).stream().map(artistMapper::artistToArtistDTO).collect(toList());
	}

	@Override
	public List<ArtistDTO> findAllArtistsByAlbums_songs_track(int trackId) {
		return artistRepoistory.findAllArtistsByAlbums_songs_track(trackId).stream().map(artistMapper::artistToArtistDTO).collect(toList());
	}

	@Override
	public ArtistDTO replace(ArtistDTO newArtistDTO, int id) {

		Artist newArtist = artistMapper.artistDTOtoArtist(newArtistDTO);
		
		return artistRepoistory.findArtistById(id).map(a  -> {
			a.setName(newArtist.getName());
			a.setAlbums(newArtist.getAlbums());
			return artistMapper.artistToArtistDTO(artistRepoistory.save(a));
		}).orElseGet(()-> {
			newArtist.setId(id);
			return artistMapper.artistToArtistDTO(artistRepoistory.save(newArtist));
		});

	}

}