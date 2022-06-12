package com.pollutionapp.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
	

	@Id
	@Column(unique = true)
	private String email;
	private String name;
	private String password;
	private String confirmPassword;

}
