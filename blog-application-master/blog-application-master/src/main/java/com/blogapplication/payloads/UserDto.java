package com.blogapplication.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name;
	
	@NotEmpty(message = "Email should not be empty")
	@Email(message = "Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "password must be min of 3 chars and max of 10 chars !!")
//	@Pattern(regexp = )
	private String password;
	
	@NotEmpty
	private String about;
	
}
