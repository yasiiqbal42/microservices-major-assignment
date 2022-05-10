package com.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table (name = "users_details")
public class UserDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column (name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column (name = "email", nullable = false, unique = true, length = 50)
    private String email;
    @Column (name = "phone_number", length = 15)
    private String phoneNumber;

    @OneToOne(mappedBy = "userDetails")
	@JsonIgnore 
    private User user;

	public UserDetails() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
