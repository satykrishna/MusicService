package com.validus.music.api.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ArtistDTO {
	
	@JsonProperty("artist_id")
	private int id;
	
	@JsonProperty("artist_name")
	private String name;
	
	@JsonProperty("albums")
	private Set<AlbumDTO> albumDTO = new HashSet<>();
	
}
