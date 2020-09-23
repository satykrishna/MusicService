
package com.validus.music.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

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
public class Album extends BaseModel {

	@Column
	@NotEmpty
	@NotBlank
    @Size(min=2, message="Album should have atleast 2 characters")
	private String name;

	@Column
	@NotEmpty
	@Positive
	private int yearReleased;

	@OneToMany(targetEntity = Song.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "albumId", referencedColumnName = "id")
	private List<Song> songs = new ArrayList<>();
	
}
