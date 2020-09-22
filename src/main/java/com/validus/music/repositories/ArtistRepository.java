package com.validus.music.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validus.music.domain.Artist;



public interface ArtistRepository{
	
	
	List<Artist> findAll();
}
