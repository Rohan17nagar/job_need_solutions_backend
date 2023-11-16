package com.jobneedsolutions.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDetailsDto {
	
	private long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date dob;
	private String gender;
	private String role;
}
