package com.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Field("user_name")
	public String username;
	@Field("password")
	public String password;
	@Field("first_name")
	public String firstName;
	@Field("last_name")
	public String lastName;
	@Field("mail")
	public String mailId;
	@Field("mobile")
	public String mobileNumber;
}
