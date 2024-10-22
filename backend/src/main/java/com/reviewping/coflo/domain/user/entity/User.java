package com.reviewping.coflo.domain.user.entity;

import com.reviewping.coflo.domain.user.enums.Provider;
import com.reviewping.coflo.domain.user.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String profileImageUrl;

	@Column(nullable = false, unique = true)
	private String oauth2Id;

	@Column(nullable = false)
	private Provider provider;

	@Column(nullable = false)
	private Role role;

	@Builder
	public User(String username, String profileImageUrl, String oauth2Id, Provider provider,
		Role role) {
		this.username = username;
		this.profileImageUrl = profileImageUrl;
		this.oauth2Id = oauth2Id;
		this.provider = provider;
		this.role = role;
	}
}
