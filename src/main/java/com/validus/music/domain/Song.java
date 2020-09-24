package com.validus.music.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
@ToString
public class Song extends BaseModel {

    @Column
	@Positive
	private int track;
	
	@Column
	@NotEmpty
    @Size(min=2, message="Song should have atleast 2 characters")
	private String name;

	
}
