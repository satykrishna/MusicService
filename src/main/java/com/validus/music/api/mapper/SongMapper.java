package com.validus.music.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.validus.music.api.model.SongDTO;
import com.validus.music.domain.Song;

@Mapper
public interface SongMapper {
	
	SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

	SongDTO toSongDTO(Song song);
	
	Song toSong(SongDTO songDTO);
}
