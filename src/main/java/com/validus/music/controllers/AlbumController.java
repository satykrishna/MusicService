package com.validus.music.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.validus.music.api.model.AlbumDTO;
import com.validus.music.api.model.AlbumListDTO;
import com.validus.music.services.AlbumService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = AlbumController.BASE_URL)
@RequiredArgsConstructor
@Validated
@Slf4j
public class AlbumController {

	public static final String BASE_URL = "/api/album";

	private final AlbumService albumService;

	@GetMapping("/all")
	public AlbumListDTO findAllAlbums() {
		List<AlbumDTO> albums = albumService.findAllAlbums();
		return AlbumListDTO.builder().albums(albums).build();
	}

	@GetMapping("/id/{albumId}")
	public AlbumDTO findAlbumById(@PathVariable("albumId") @Positive Integer albumId) {
		return albumService.findAlbumById(albumId);
	}

	@GetMapping("/name/{albumName}")
	public AlbumDTO findAlbumByName(@PathVariable("albumName") @NotBlank @Size(min = 1) String albumName) {
		return albumService.findAlbumByNameIgnoreCase(albumName);
	}
	
	@GetMapping("/song/name/{songName}")
	public AlbumDTO findAlbumBySongName(@PathVariable("songName") @NotBlank @Size(min = 1) String songName) {
		return albumService.findAlbumBySongName(songName);
	}
	
	@GetMapping("/song/id/{id}")
	public AlbumDTO findAlbumBySongId(@PathVariable("id") @Positive Integer id) {
		return albumService.findAlbumBySongId(id);
	}
	
	@GetMapping("/song/track/{id}")
	public AlbumListDTO findAlbumByTrack(@PathVariable("id") @Positive Integer id) {
		List<AlbumDTO> albums = albumService.findAlbumsByTrack(id);
		return AlbumListDTO.builder().albums(albums).build();
	}
	

	@GetMapping("/yearReleased/{yearReleased}")
	public AlbumListDTO findAlbumsByYearReleased(@PathVariable("yearReleased") @Positive Integer yearReleased) {
		List<AlbumDTO> albums = albumService.findAllAlbumsByYearReleased(yearReleased);
		return AlbumListDTO.builder().albums(albums).build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlbumDTO createNewAlbum(@RequestBody @Valid AlbumDTO newAlbumDTO) {
		return albumService.save(newAlbumDTO);
	}

	@DeleteMapping("/id/{id}")
	public Map<String, Boolean> deleteAlbum(@PathVariable("id") @Positive Integer id) {
		albumService.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/update/{id}")
	public AlbumDTO replaceAlbum(@RequestBody @Valid AlbumDTO newAlbum, @PathVariable("id") @Positive Integer id) {
		return albumService.replaceAlbum(newAlbum, id);
	}

}
