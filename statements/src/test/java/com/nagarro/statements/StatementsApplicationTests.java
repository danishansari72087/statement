package com.nagarro.statements;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.nagarro.statements.dao.StatementDao;
import com.nagarro.statements.dto.StatementDto;
import com.nagarro.statements.exception.InvalidRequestException;
import com.nagarro.statements.security.JwtRequestFilter;
import com.nagarro.statements.service.impl.StatementServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StatementsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	StatementDao statementDao;

	@InjectMocks
	StatementServiceImpl statementServiceImpl;

	@DisplayName("JUnit test for getStatementsByDate method")
	@Test
	public void getStatementsByDate1() throws InvalidRequestException{
		JwtRequestFilter.role="ROLE_ADMIN";
		List<StatementDto> response= statementServiceImpl.getStatements(1L, "BY_DATE", "15.10.2019", "15.10.2023", null, null);
		assertThat(response).isNotNull();
	}
}
