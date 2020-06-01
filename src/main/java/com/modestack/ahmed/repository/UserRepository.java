package com.modestack.ahmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.modestack.ahmed.models.UserDto;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Integer> {

	@Query(value = "FROM UserDto u WHERE userName=:userName", nativeQuery = false)
	public UserDto fetchUserDetailsByUserName(@Param(value = "userName") String userName);
}
