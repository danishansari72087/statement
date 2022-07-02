package com.nagarro.statements.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nagarro.statements.dto.StatementDto;

/**
 * @author 
 * Danish Ansari
 *
 */

@Repository
public class StatementRepository  {

	Logger logger = LoggerFactory.getLogger(StatementRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;	


	public List<StatementDto> getStatementsByDate(Long id, String fromDate, String toDate) {
		logger.debug("inside getStatementsByDate method");
		return jdbcTemplate.query("SELECT s.ID,s.account_id, s.datefield,s.amount,a.account_number  from Statement s join Account a ON s.account_id=a.ID where a.ID=? and Format(Replace(s.datefield,'.','/'),'yyyyMMdd')>=? and Format(Replace(s.datefield,'.','/'),'yyyyMMdd')<=?  ",BeanPropertyRowMapper.newInstance(StatementDto.class),new Object[] {id,fromDate,toDate});
	}

	public List<StatementDto> getStatementsByAmount(Long id, String fromAmount, String toAmount) {
		logger.debug("inside getStatementsByAmount method");
		return jdbcTemplate.query("SELECT s.ID,s.account_id,s.datefield,s.amount,a.account_number  from Statement s join Account a ON s.account_id=a.ID where a.ID=? and Val(s.amount)>= ? and Val(s.amount)<= ?",BeanPropertyRowMapper.newInstance(StatementDto.class),new Object[] {id,fromAmount,toAmount});
	}

}
