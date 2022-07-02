package com.nagarro.statements.service;

import java.util.List;

import com.nagarro.statements.dto.StatementDto;
import com.nagarro.statements.exception.InvalidRequestException;
/**
 * @author 
 * Danish Ansari
 *
 */
public interface StatementService {

	public List<StatementDto> getStatements(Long accId, String searchType,String fromDate, String toDate,String fromAmount, String toAmount) throws InvalidRequestException;

}
