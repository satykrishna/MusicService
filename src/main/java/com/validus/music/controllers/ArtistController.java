package com.validus.music.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.validus.music.api.model.ArtistListDTO;
import com.validus.music.services.ArtistService;

import lombok.RequiredArgsConstructor;

//@RestController
//@RequestMapping(ArtistController.BASE_URL) 
@RequiredArgsConstructor
public class ArtistController {

	public static final String BASE_URL = "/api/artists";
	
	private final ArtistService artistService;
	
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArtistListDTO getAllArtists(){
        return  ArtistListDTO.builder().artists(artistService.findAllArtists()).build();
    }
}

