package com.blogapplication.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private int categoryId;
	
	@NotEmpty(message = "category title cannot be null / empty")
	private String categoryTitle;
	
	@NotEmpty(message = "category description can not be null / empty")
	private String categoryDescription;
}
