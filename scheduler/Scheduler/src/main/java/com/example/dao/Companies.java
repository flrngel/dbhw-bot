package com.example.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.example.dao.serialization.MyDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Companies implements Serializable {
	@Id
	@GeneratedValue
	int id;

	@JsonIgnore
	String password;
	@Email
	String email;
	String name;
	String hook_url;
	String api_token;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = MyDateSerializer.class)
	Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = MyDateSerializer.class)
	Date updateAt;
}
