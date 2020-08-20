package com.george.test.inter.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.george.test.inter.domain.SingleDigit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface SingleDigitRepository extends JpaRepository<SingleDigit, Integer> {
	
	@Query("SELECT sd FROM SingleDigit sd INNER JOIN sd.user u WHERE u.id = :userId")
	@Transactional(readOnly=true)
	List<SingleDigit> findUserSingleDigits(Integer userId);
}
