package org.sist.sb06_sbb7.user;

import lombok.Getter;

@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN"),	USER("ROLE_USER");

	UserRole(String value) {
		this.value = value;
	}
	
	private String value;
	
	
}
