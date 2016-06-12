package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.dao.UserUsage;
import com.example.repository.projection.UserUsageProjection;

@Transactional
@RepositoryRestResource(excerptProjection = UserUsageProjection.class)
public interface UserUsageRepository extends PagingAndSortingRepository<UserUsage, Integer> {
	// @RestResource(path = "findByUsername")
	// @Query(value = "SELECT * FROM user WHERE user = :username")
	// List<UserUsage> findByUsername(@Param("username") String username);

	@Query(value = "select * from user_usage where date between :start and :end", nativeQuery = true)
	List<UserUsage> findByDatesBetween(@Param("start") String start, @Param("end") String end);
	
	@Query(value = "select * from user_usage where user = :user date between :start and :end", nativeQuery = true)
	List<UserUsage> findByUserAndDatesBetween(@Param("user") String user,  @Param("start") String start, @Param("end") String end);
}
