package com.financialog.repository;

import com.financialog.entity.FinancialLoggerUser;
import com.financialog.entity.Stock;
import com.financialog.entity.TradeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeDetailsRepository extends JpaRepository<TradeDetails, Long> {

    @Query("select SUM(tradeValue) from TradeDetails td where user = :user")
    Optional<Float> getTotalTradeCapital(FinancialLoggerUser user);

    @Query("select td from TradeDetails td join td.stockId s where s.code = :stockCode")
    List<TradeDetails> findByStockCode(String stockCode);

    List<TradeDetails> findByUser(FinancialLoggerUser user);

    List<TradeDetails> findByTradeStatusAndUser(String tradeStatus, FinancialLoggerUser user);

}
