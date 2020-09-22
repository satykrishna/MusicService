package com.validus.music.api.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlbumListDTO {

	private List<AlbumDTO> albums;
	
}
