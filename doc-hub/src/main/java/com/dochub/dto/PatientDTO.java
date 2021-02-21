package com.dochub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
}
