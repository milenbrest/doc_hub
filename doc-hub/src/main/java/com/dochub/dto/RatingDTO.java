package com.dochub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDTO {
	private Long id;
	private int rate;
	private String comment;
	private Long patient;
	private Long doctor;
}
