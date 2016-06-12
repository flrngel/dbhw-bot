package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.dao.User;

@Transactional
@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, String> {

//	@Query(value = "SELECT user.* WHERE user.username = userusage.user_id " + "AND user.username = :user "
//			+ "userusage.date BETWEEN :startDate AND :endDate ", nativeQuery = true)
//	public List<User> findByUsernameAndDate(@Param("user") String user, @Param("startDate") String startDate,
//			@Param("endDate") String endDate);
	
	@Query(value="SELECT * FROM user", nativeQuery = true)
	@Transactional
	List<User> findAllU();
}
