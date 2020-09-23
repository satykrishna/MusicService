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
import com.validus.music.api.model.ArtistDTO;
import com.validus.music.api.model.ArtistListDTO;
import com.validus.music.services.ArtistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ArtistController.BASE_URL) 
@RequiredArgsConstructor
@Validated
public class ArtistController {

	public static final String BASE_URL = "/api/artist";
	
	private final ArtistService artistService;
	
	@GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ArtistListDTO getAllArtists(){
        return  ArtistListDTO.builder().artists(artistService.findAll()).build();
    }
	
	@GetMapping("/id/{artistId}")
	public ArtistDTO findArtistById(@PathVariable("artistId") @Positive Integer artistId) {
		return artistService.findArtistById(artistId);
	}

	@GetMapping("/name/{artistName}")
	public ArtistDTO findArtisByName(@PathVariable("artistName") @NotBlank @Size(min = 1) String artistName) {
		return artistService.findArtistByNameIgnoreCase(artistName);
	}
	
	@GetMapping("/album/name/{albumName}")
	public ArtistListDTO findArtistBysAlbumName(@PathVariable("albumName") @NotBlank @Size(min = 1) String albumName) {
		List<ArtistDTO> artists = artistService.findAllArtistsByAlbumsIgnoreCase_name(albumName);
		return ArtistListDTO.builder().artists(artists).build();
	}
	
	@GetMapping("/album/id/{albumId}")
	public ArtistListDTO findArtistsByAlbumId(@PathVariable("albumId") @Positive Integer albumId) {
		List<ArtistDTO> artists = artistService.findAllArtistsByAlbums_id(albumId);
		return ArtistListDTO.builder().artists(artists).build();
	}

	@GetMapping("/album/yearReleased/{yearReleased}")
	public ArtistListDTO findArtistBysAlbumYearReleased(@PathVariable("yearReleased") @Positive Integer yearReleased) {
		List<ArtistDTO> artists = artistService.findAllArtistsByAlbums_yearReleased(yearReleased);
		return ArtistListDTO.builder().artists(artists).build();
	}

	@GetMapping("/song/id/{songId}")
	public ArtistListDTO findArtistsBySongId(@PathVariable("songId") @Positive Integer songId) {
		List<ArtistDTO> artists = artistService.findAllArtistsByAlbums_songs_id(songId);
		return ArtistListDTO.builder().artists(artists).build();
	}
	
	@GetMapping("/song/name/{songName}")
	public ArtistListDTO findArtistBySongName(@PathVariable("songName") @NotBlank @Size(min = 1) String songName) {
		List<ArtistDTO> artists = artistService.findAllArtistsByAlbumsIgnoreCase_name(songName);
		return ArtistListDTO.builder().artists(artists).build();
	}
	
	@GetMapping("/song/track/{songTrack}")
	public ArtistListDTO findArtistsBySongTrack(@PathVariable("songTrack") @Positive Integer songTrack) {
		List<ArtistDTO> artists = artistService.findAllArtistsByAlbums_songs_id(songTrack);
		return ArtistListDTO.builder().artists(artists).build();
	}
	
	@PostMapping
	public ArtistDTO createNewArtist(@RequestBody @Valid ArtistDTO newArtistDTO) {
		return artistService.save(newArtistDTO);
	}

	@DeleteMapping("/id/{id}")
	public Map<String, Boolean> deleteArtist(@PathVariable("id") @Positive Integer id) {
		artistService.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/update/{id}")
	public ArtistDTO replaceArtist(@RequestBody @Valid ArtistDTO newArtistDTO, @PathVariable("id") @Positive Integer id) {
		return artistService.replace(newArtistDTO, id);
	}



}

