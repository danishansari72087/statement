package com.nagarro.statements.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.statements.dao.StatementDao;
import com.nagarro.statements.dto.StatementDto;
import com.nagarro.statements.repository.StatementRepository;
/**
 * @author 
 * Danish Ansari
 *
 */
@Service
public class StatementDaoImpl implements StatementDao{

	Logger logger = LoggerFactory.getLogger(StatementDaoImpl.class);

	@Autowired
	StatementRepository statementRepository;


	@Override
	public List<StatementDto> getStatementsByDate(Long accId, String fromDate, String toDate) {
		logger.debug("inside getStatementsByDate method");
		return statementRepository.getStatementsByDate(accId,fromDate,toDate);
	}

	@Override
	public List<StatementDto> getStatementsByAmount(Long accId, String fromAmount, String toAmount) {
		logger.debug("inside getStatementsByAmount method");
		return statementRepository.getStatementsByAmount(accId,fromAmount,toAmount) ;
	}

}
