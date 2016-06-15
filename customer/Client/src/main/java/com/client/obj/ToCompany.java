package com.client.obj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ToCompany {
	@Id
	@JsonIgnore
	String keyword;
	
	int price;
	@Column(length = 1337)
	String image_url;
	@Column(length = 1337)
	String url;
	
	@Transient
	String apiToken;
	
	boolean bid;
}
