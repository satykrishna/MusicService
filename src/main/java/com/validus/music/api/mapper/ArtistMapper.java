package com.validus.music.api.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.validus.music.api.model.ArtistDTO;
import com.validus.music.domain.Artist;

@Mapper(componentModel = "spring", uses = AlbumMapper.class)
public interface ArtistMapper {

	ArtistMapper INSTANCE = Mappers.getMapper(ArtistMapper.class);

	@Mapping(source = "albumDTO", target = "albums")
	Artist artistDTOtoArtist(ArtistDTO artistDTO);
	
	@InheritInverseConfiguration
	ArtistDTO artistToArtistDTO(Artist artist);
	


}
