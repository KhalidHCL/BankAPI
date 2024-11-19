package com.hussain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hussain.entites.TransactionHistory;
@Repository
public interface TransationRepository extends JpaRepository<TransactionHistory,Long>{
	List<TransactionHistory> findByFromAccount(String fromAccount);
    List<TransactionHistory> findByToAccount(String toAccount);
}
