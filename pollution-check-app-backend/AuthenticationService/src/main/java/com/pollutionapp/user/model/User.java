package com.pollutionapp.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
	
    /*
	 * This class should have three fields (userId,password,cpassword. Out of these 
	 * five fields, the field userId should be the primary key. This class should
	 * also contain the getters and setters for the fields, along with the no-arg,
	 * parameterized constructor and toString method.
	 */
	@Id
	@Column(unique = true)
	private String email;
	private String name;
	private String password;
	private String confirmPassword;

}
