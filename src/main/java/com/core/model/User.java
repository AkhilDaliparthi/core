package com.core.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private String id;
	@NonNull
	@Field("username")
	private String username;
	@NonNull
	@Field("password")
	private String password;
	@Field("first_name")
	private String firstName;
	@Field("last_name")
	private String lastName;
	@Field("email")
	private String emailId;
	@Field("mobile")
	private String mobileNumber;
	@Field("created_at")
	private LocalDateTime createdAt;
	@Field("updated_at")
	private LocalDateTime updatedAt;
}
