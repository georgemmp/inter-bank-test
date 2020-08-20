package com.george.test.inter.repositories;

import org.springframework.stereotype.Repository;

import com.george.test.inter.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
