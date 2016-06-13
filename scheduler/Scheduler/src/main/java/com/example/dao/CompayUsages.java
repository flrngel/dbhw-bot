package com.example.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
public class CompayUsages implements Serializable {
	@Id
	@GeneratedValue
	int id;

	@JsonBackReference
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "companies_id", nullable = false)
	Companies company_id;

	String keyword;
	int bid_price;
	
	String url;
	String image_url;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = MyDateSerializer.class)
	Date createdAt;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = MyDateSerializer.class)
	Date updatedAt;

	public CompayUsages(Companies company_id, String keyword, int bid_price, Date createdAt, Date updatedAt) {
		this.company_id = company_id;
		this.keyword = keyword;
		this.bid_price = bid_price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
}
