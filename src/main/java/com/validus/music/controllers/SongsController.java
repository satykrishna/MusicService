package com.validus.music.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validus.music.api.model.SongDTO;
import com.validus.music.api.model.SongListDTO;
import com.validus.music.services.SongService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SongsController.BASE_URL)
@RequiredArgsConstructor
@Validated
@Slf4j
public class SongsController {

	public static final String BASE_URL = "/api/song";

	private final SongService songService;

	@GetMapping("/all")
	public SongListDTO findAllSongs() {
		List<SongDTO> songs = songService.findAllSongs();
		return SongListDTO.builder().songs(songs).build();
	}

	@GetMapping("/id/{songId}")
	public SongDTO findSongById(@PathVariable("songId") @Positive int songId) {
		return songService.findSongById(songId);
	}

	@GetMapping("/name/{songName}")
	public SongDTO findSongByName(@PathVariable("songName") @Valid @NotBlank @Size(min = 1) String songName) {
		return songService.findSongByName(songName);
	}

	@GetMapping("/track/{trackId}")
	public SongListDTO findSongsByTrack(@PathVariable("trackId") @Positive int trackId) {
		List<SongDTO> songs = songService.findSongsByTrack(trackId);
		return SongListDTO.builder().songs(songs).build();
	}
	
	@PostMapping
	public SongDTO createNewSong(@RequestBody @Valid SongDTO newSongDTO) {
		return songService.save(newSongDTO);
	}
	
	@DeleteMapping("/id/{id}")
	 public Map<String, Boolean> deleteEmployee(@PathVariable("id") @Positive int id) {
		  songService.deleteBySongId(id);
		  Map<String, Boolean> response = new HashMap<>();
		  response.put("deleted", Boolean.TRUE);
		  return response;
	  }
	
	 @PutMapping("/update/{id}")
	 public SongDTO replaceSong(@RequestBody @Valid SongDTO newSong, @PathVariable("id") @Positive int id) {
		 	return songService.replaceSong(newSong, id);
	  }

}
