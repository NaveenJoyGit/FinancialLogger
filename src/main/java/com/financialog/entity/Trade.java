package com.financialog.entity;

import com.financialog.enums.HoldStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int stockName;
    private double buyPrice;
    private Date buyDate;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private HoldStatus status;

}
