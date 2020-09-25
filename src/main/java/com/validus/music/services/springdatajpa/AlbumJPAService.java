package com.validus.music.services.springdatajpa;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.stereotype.Service;

import com.validus.music.api.mapper.AlbumMapper;
import com.validus.music.api.model.AlbumDTO;
import com.validus.music.controllers.Exception.ResourceNotFoundException;
import com.validus.music.domain.Album;
import com.validus.music.repositories.AlbumRepository;
import com.validus.music.services.AlbumService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumJPAService implements AlbumService {

	private final AlbumRepository albumRepository;
	
	private final AlbumMapper albumMapper;
	
	@Override
	public List<AlbumDTO> findAllAlbums() {
		
//		log.debug("albums: {}", albumRepository.findAll());
		
		 return albumRepository.findAll().stream().map(albumMapper::albumToAlbumDTO).collect(toList());
	}

	@Override
	public AlbumDTO findAlbumById(Integer id) {
		log.debug("album: {}", albumRepository.findAlbumById(id));

		return albumRepository.findAlbumById(id).map(albumMapper::albumToAlbumDTO).orElseThrow(()-> new ResourceNotFoundException("couldn't find Album by Id " + id));
	}

	@Override
	public AlbumDTO findAlbumByNameIgnoreCase(String name) {
		return albumRepository.findAlbumByNameIgnoreCase(name).map(albumMapper::albumToAlbumDTO).orElseThrow(()-> new ResourceNotFoundException("couldn't find Album by Name " + name));
	}

	@Override
	public List<AlbumDTO> findAllAlbumsByYearReleased(int yearReleased) {
		return albumRepository.findAllAlbumsByYearReleased(yearReleased).stream().map(albumMapper::albumToAlbumDTO).collect(toList());
	}

	@Override
	public AlbumDTO save(AlbumDTO newAlbum) {
		Album album =  albumRepository.save(albumMapper.albumDtoToAlbum(newAlbum));
		return albumMapper.albumToAlbumDTO(album);
	}

	@Override
	public void deleteById(Integer id) {
		albumRepository.findAlbumById(id).orElseThrow(()-> new ResourceNotFoundException("Didn't Find Album with Id - " + id));
		albumRepository.deleteById(id);
	}

	@Override
	public AlbumDTO replaceAlbum(AlbumDTO newAlbumDto, Integer id) {
		
	 Album newAlbum = albumMapper.albumDtoToAlbum(newAlbumDto);
		 
		 return albumRepository.findAlbumById(id).map(album -> {
			 album.setName(newAlbum.getName());
			 album.setYearReleased(newAlbum.getYearReleased());
			 album.setSongs(newAlbum.getSongs());
			 return albumMapper.albumToAlbumDTO(albumRepository.save(album));
		 }).orElseGet(()-> {
			 newAlbum.setId(id);
			 return albumMapper.albumToAlbumDTO(albumRepository.save(newAlbum));
		 });
	}

	@Override
	public AlbumDTO findAlbumBySongName(String songName) {
 		return albumRepository.findAlbumBySongs_nameIgnoreCase(songName).map(albumMapper::albumToAlbumDTO).orElseThrow(()-> new ResourceNotFoundException("couldn't find Album by song name " + songName));
	}

	@Override
	public AlbumDTO findAlbumBySongId(int id) {
 		return albumRepository.findAlbumBySongs_id(id).map(albumMapper::albumToAlbumDTO).orElseThrow(()-> new ResourceNotFoundException("couldn't find Album by song name " + id));
	}

	@Override
	public List<AlbumDTO> findAlbumsByTrack(int trackId) {
		return albumRepository.findAlbumBySongs_track(trackId).stream().map(albumMapper::albumToAlbumDTO).collect(toList());
	}

	
}
