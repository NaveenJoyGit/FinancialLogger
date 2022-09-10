package com.financialog.security;

import com.financialog.entity.FinancialLoggerUser;

public interface IAuthenticationFacade {

    FinancialLoggerUser getLoggedInUser();

}
