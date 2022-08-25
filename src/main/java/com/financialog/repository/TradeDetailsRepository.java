package com.financialog.repository;

import com.financialog.entity.TradeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeDetailsRepository extends JpaRepository<TradeDetails, Long> {
}
