package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.dao.Companies;

@Transactional
@RepositoryRestResource
public interface CompaniesRepository extends PagingAndSortingRepository<Companies, String> {
	@Query(value="SELECT * FROM user", nativeQuery = true)
	@Transactional
	List<Companies> findAllU();
	
	@Transactional
	Companies findByEmail(@Param("email") String email);
}
