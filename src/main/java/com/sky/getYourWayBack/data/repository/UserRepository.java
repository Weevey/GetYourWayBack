package com.sky.getYourWayBack.data.repository;

import com.sky.getYourWayBack.data.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
