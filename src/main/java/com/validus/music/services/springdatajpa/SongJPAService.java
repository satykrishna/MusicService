package com.validus.music.services.springdatajpa;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.validus.music.api.mapper.SongMapper;
import com.validus.music.api.model.SongDTO;
import com.validus.music.controllers.Exception.ResourceNotFoundException;
import com.validus.music.domain.Song;
import com.validus.music.repositories.SongRepository;
import com.validus.music.services.SongService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongJPAService implements SongService {

	private final SongRepository songRepository;

	private final SongMapper songMapper;

	public SongDTO findSongById(Integer songId) {
		
		if(log.isDebugEnabled()) {
			log.debug("FindSongById-{}:  {}", songRepository.findSongById(songId));
		}
		
		return songRepository.findSongById(songId).map(songMapper::toSongDTO).orElseThrow(()-> new ResourceNotFoundException("Didn't Find Song with Id - " + songId));
	}

	@Override
	public SongDTO findSongByName(String songName) {
		
		if(log.isDebugEnabled()) {
			log.debug("FindSongByName-{}:  {}", songRepository.findSongByNameIgnoreCase(songName));
		}

		return songRepository.findSongByNameIgnoreCase(songName).map(songMapper::toSongDTO)
				.orElseThrow(()-> new ResourceNotFoundException("Didn't Find Song with Name - " + songName));
	}

	@Override
	public List<SongDTO> findSongsByTrack(int track) {
	
		if(log.isDebugEnabled()) {
			log.debug("FindSongByTrack-{}:  {}", songRepository.findAllSongByTrack(track));
		}
		
		return songRepository.findAllSongByTrack(track).stream().map(songMapper::toSongDTO).collect(toList());
	}

	@Override
	public List<SongDTO> findAllSongs() {
		
		if(log.isDebugEnabled()) {
			log.debug("FindAll-{}:  {}", songRepository.findAll());
		}
	
		
		return songRepository.findAll().stream().map(songMapper::toSongDTO).collect(toList());
	}

	@Override
	public SongDTO save(SongDTO newSongDTO) {
		Song song =  songRepository.save(songMapper.toSong(newSongDTO));
		return songMapper.toSongDTO(song);
	}

	@Override
	public void deleteBySongId(Integer id) {
		songRepository.findSongById(id).orElseThrow(()-> new ResourceNotFoundException("Didn't Find Song with Id - " + id));
		songRepository.deleteById(id);
	}

	@Override
	public SongDTO replaceSong(SongDTO newSongDTO, Integer id) {
		
		 Song newSong = songMapper.toSong(newSongDTO);
		 
		 return songRepository.findSongById(id).map(song -> {
			 song.setName(newSong.getName());
			 song.setTrack(newSong.getTrack());
			 return songMapper.toSongDTO(songRepository.save(song));
		 }).orElseGet(()-> {
			 newSong.setId(id);
			 return songMapper.toSongDTO(songRepository.save(newSong));
		 });
		 
		 
	}
}
