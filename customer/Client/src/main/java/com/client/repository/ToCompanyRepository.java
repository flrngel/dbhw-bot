package com.client.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.client.obj.ToCompany;

@RepositoryRestResource
public interface ToCompanyRepository extends PagingAndSortingRepository<ToCompany, String>{

}
