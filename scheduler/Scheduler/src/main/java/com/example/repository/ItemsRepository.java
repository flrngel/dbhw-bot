package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.dao.Items;

@RepositoryRestResource
public interface ItemsRepository extends PagingAndSortingRepository<Items, Integer>{

}
