package com.api.constant;

public enum Plateform {

	FST(3),
	FRONT_DESK(2);
	int code;
	Plateform(int code){
		this.code=code;
	}
 public int getCode() {
	 return code;
 }
}
