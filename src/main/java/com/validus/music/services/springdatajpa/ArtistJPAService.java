package com.validus.music.services.springdatajpa;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.validus.music.api.mapper.ArtistMapper;
import com.validus.music.api.model.ArtistDTO;
import com.validus.music.repositories.ArtistRepository;
import com.validus.music.services.ArtistService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistJPAService implements ArtistService {

	private final ArtistRepository artistRepoistory;
	
	private final ArtistMapper artistMapper;

	@Override
	public List<ArtistDTO> findAllArtists() {
		log.info("find All artists: {} ", artistRepoistory.findAll() );
		return artistRepoistory.findAll().stream()
				.map(artistMapper::artistToArtistDTO).collect(toList());
	}
	
}
