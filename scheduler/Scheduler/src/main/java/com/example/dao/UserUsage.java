package com.example.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.example.dao.serialization.MyDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserUsage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	int userUsageId;

	String word;
	int price;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = MyDateSerializer.class)
	Date date;

	@JsonBackReference
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "user", nullable = false)
	User user;

	public UserUsage(String word, Date date, int price, User user) {
		this.word = word;
		this.date = date;
		this.price = price;
		this.user = user;
	}
}
