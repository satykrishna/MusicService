package com.validus.music.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.validus.music.api.model.ArtistDTO;
import com.validus.music.domain.Artist;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

	ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

	ArtistDTO artistToArtistDTO(Artist artist);
	
	Artist artistDTOtoArtist(ArtistDTO artistDTO);

}
