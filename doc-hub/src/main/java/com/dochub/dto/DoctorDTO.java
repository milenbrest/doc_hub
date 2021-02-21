package com.dochub.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DoctorDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String education;
	private String qualification;
	private String info;
}
