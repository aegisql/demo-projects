package com.aegisql.demo;

public class UserInfo {

	private final int gender;
	private final String name;
	private final String patronymic;
	private final String email;
	public UserInfo(int gender, String name, String patronymic, String email) {
		super();
		this.gender = gender;
		this.name = name;
		this.patronymic = patronymic;
		this.email = email;
	}
	public int getGender() {
		return gender;
	}
	public String getName() {
		return name;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [gender=").append(gender).append(", name=").append(name).append(", patronymic=")
				.append(patronymic).append(", email=").append(email).append("]");
		return builder.toString();
	}
	
}
