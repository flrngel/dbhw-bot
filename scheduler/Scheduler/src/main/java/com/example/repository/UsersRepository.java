package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.dao.Users;

@Transactional
@RepositoryRestResource
public interface UsersRepository extends PagingAndSortingRepository<Users, String> {
	@Query(value="SELECT * FROM user", nativeQuery = true)
	@Transactional
	List<Users> findAllU();
	
	@Transactional
	Users findByEmail(@Param("email") String email);
}
