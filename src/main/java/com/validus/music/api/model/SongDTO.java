package com.validus.music.api.model;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonRootName("song") 
public class SongDTO {

	private int id;

	@Positive
	private int track;

    @Size(min=2, message="Song should have atleast 2 characters")
	private String name;
}
