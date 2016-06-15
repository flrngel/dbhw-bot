package com.example.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.example.dao.Companies;
import com.example.dao.CompayUsages;

@Projection(name = "UserUsageProjection", types = CompayUsages.class)
public interface UserUsageProjection {

	int getUserUsageId();

	String getWord();

	int getPrice();

	Date getDate();

	Companies getUser();
}
