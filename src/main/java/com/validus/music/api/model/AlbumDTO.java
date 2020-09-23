package com.validus.music.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AlbumDTO {
	
	@JsonProperty("album_id")
	private int id;

	@JsonProperty("album_name")
    @Size(min=2, message="album name should have atleast 2 characters")
	private String name;

	@Positive
	private int yearReleased;
	
	@JsonProperty("songs")
	private Set<SongDTO> songsListDTO = new HashSet<>();
	
	
}
