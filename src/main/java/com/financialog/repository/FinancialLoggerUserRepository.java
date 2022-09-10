package com.financialog.repository;

import com.financialog.entity.FinancialLoggerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinancialLoggerUserRepository extends JpaRepository<FinancialLoggerUser, Long> {

    Optional<FinancialLoggerUser> findByUsername(String userName);

}
