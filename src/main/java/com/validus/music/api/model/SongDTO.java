package com.validus.music.api.model;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName("song") 
public class SongDTO {

	@JsonProperty("song_id")
	private int id;

	@Positive
	@JsonProperty("track_id")
	private int track;

    @Size(min=2, message="Song should have atleast 2 characters")
    @JsonProperty("song_name")
	private String name;
}
