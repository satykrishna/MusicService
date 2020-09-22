package com.validus.music.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongListDTO {

	private List<SongDTO> songs;
}
