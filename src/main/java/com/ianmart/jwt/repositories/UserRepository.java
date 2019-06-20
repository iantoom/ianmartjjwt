package com.ianmart.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ianmart.jwt.domains.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailIgnoreCase(String username);

}
