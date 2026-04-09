package com.api.constant;

public enum Model {
	NEXUS_2_Blue(1), GALAXY(2);

	int code;

	Model(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
