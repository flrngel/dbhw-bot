package com.example.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.dao.serialization.MyDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Items {
	@Id
	@GeneratedValue
	int id;

	String title;
	String url;
	String image;
	String description;
	String keywords;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = MyDateSerializer.class)
	Date creaetAt;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = MyDateSerializer.class)
	Date updateAt;
}
