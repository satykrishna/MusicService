package com.validus.music.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	//TODO- ADD CREATE AND UPDATETIMESTAMP
}
