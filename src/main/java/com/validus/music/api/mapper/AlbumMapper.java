package com.validus.music.api.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.validus.music.api.model.AlbumDTO;
import com.validus.music.domain.Album;

@Mapper(componentModel = "spring", uses = SongMapper.class)
public interface AlbumMapper {

	AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

	@Mapping(source = "songsListDTO", target = "songs")
	Album albumDtoToAlbum(AlbumDTO albumDTO);

	@InheritInverseConfiguration
	AlbumDTO albumToAlbumDTO(Album album);

}
