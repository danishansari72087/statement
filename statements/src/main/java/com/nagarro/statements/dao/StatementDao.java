package com.nagarro.statements.dao;

import java.util.List;

import com.nagarro.statements.dto.StatementDto;

public interface StatementDao {

	List<StatementDto> getStatementsByDate(Long accId, String fromDate, String toDate);

	List<StatementDto> getStatementsByAmount(Long accId, String fromAmount, String toAmount);


}
