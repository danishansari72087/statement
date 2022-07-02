package com.nagarro.statements.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nagarro.statements.constant.Constants;
import com.nagarro.statements.dao.StatementDao;
import com.nagarro.statements.dto.StatementDto;
import com.nagarro.statements.exception.InvalidRequestException;
import com.nagarro.statements.security.JwtRequestFilter;
import com.nagarro.statements.service.StatementService;
import com.nagarro.statements.util.HashUtil;

/**
 * @author 
 * Danish Ansari
 *
 */


@Service
public class StatementServiceImpl implements StatementService{

	Logger logger = LoggerFactory.getLogger(StatementServiceImpl.class);

	@Autowired
	StatementDao statementDao;

	@Override
	public List<StatementDto> getStatements(Long accId,String searchType, String fromDate, String toDate,String fromAmount, String toAmount) throws InvalidRequestException{
		logger.debug("inside getStatements method");
		String role=JwtRequestFilter.role;
		List<StatementDto> statementsList;

		if(Constants.BY_DATE.equals(searchType)) {

			if(Constants.ROLE_ADMIN.equals(role)) {
				if( Objects.nonNull(fromDate) && Objects.nonNull(toDate)) {
					toDate=toDate.split("\\.")[2]+toDate.split("\\.")[1]+toDate.split("\\.")[0];
					fromDate=fromDate.split("\\.")[2]+fromDate.split("\\.")[1]+fromDate.split("\\.")[0];

					statementsList=statementDao.getStatementsByDate(accId,fromDate,toDate);
					statementsList=statementsList.stream()
					.peek(statement-> statement.setAccount_number(HashUtil.getMd5(statement.getAccount_number()))).collect(Collectors.toList());
				}else {
					throw new InvalidRequestException("fromDate and toDate is mandatory", HttpStatus.BAD_REQUEST.value());
				}

			}else {
				throw new InvalidRequestException(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED.value());
			}

		}else if(Constants.BY_AMOUNT.equals(searchType)) {

			if(Constants.ROLE_ADMIN.equals(role)) {
				if(  Objects.nonNull(fromAmount) && Objects.nonNull(toAmount)) {
					statementsList=statementDao.getStatementsByAmount(accId,fromAmount,toAmount);
					statementsList=statementsList.stream()
					.peek(statement-> statement.setAccount_number(HashUtil.getMd5(statement.getAccount_number()))).collect(Collectors.toList());

				}else {
					throw new InvalidRequestException("fromAmount and toAmount is mandatory", HttpStatus.BAD_REQUEST.value());
				}
			}else {
				throw new InvalidRequestException(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED.value());

			}

		}else {

			if(Constants.ROLE_ADMIN.equals(role) || Constants.ROLE_USER.equals(role)) {
				Date myDate = new Date(System.currentTimeMillis());
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Calendar cal = Calendar.getInstance();
				cal.setTime(myDate);
				cal.add(Calendar.MONTH,-3);

				String currentDate=dateFormat.format(myDate);
				String pastDate =dateFormat.format(cal.getTime());

				statementsList=statementDao.getStatementsByDate(accId,pastDate,currentDate);
				statementsList=statementsList.stream()
				.peek(statement-> statement.setAccount_number(HashUtil.getMd5(statement.getAccount_number()))).collect(Collectors.toList());

			}else {
				throw new InvalidRequestException(Constants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED.value());
			}
		}

		return statementsList;
	}

}
