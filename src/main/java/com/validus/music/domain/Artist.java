package com.validus.music.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.validus.music.domain.Album.AlbumBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by jt on 6/13/17.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
@ToString
public class Artist extends BaseModel {

	@Column
	@NotEmpty
	private String name;

	
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)  
    @JoinTable(name="artist_albums", joinColumns=@JoinColumn(name="artist_id"), inverseJoinColumns=@JoinColumn(name="albums_id"))  
    private Set<Album> albums = new HashSet<>();

}
