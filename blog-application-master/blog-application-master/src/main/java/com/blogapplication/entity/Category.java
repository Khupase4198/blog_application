package com.blogapplication.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId; 
	
	@Column(name = "title", length = 100, nullable = true)
	private String categoryTitle;

	@Column(name = "description")
	private String categoryDescription;
	// fetch = fetchType.LAZY (parent nikale child na nikle)
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> post = new ArrayList<>();
}
