package com.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table (name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "user_name", nullable = false, unique = true, length = 50)
    private String userName;
    @Column (name = "user_password", nullable = false, length = 50)
    private String password;
    @Column (name = "active")
    private boolean active;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "user_details_id")
    private UserDetails userDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
