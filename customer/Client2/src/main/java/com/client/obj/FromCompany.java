package com.client.obj;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonAutoDetect
public class FromCompany {
	@Id
	String keyword;
}
