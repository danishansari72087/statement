package com.nagarro.statements.resource;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.statements.dto.StatementDto;
import com.nagarro.statements.exception.InvalidRequestException;
import com.nagarro.statements.service.StatementService;
/**
 * @author 
 * Danish Ansari
 *
 */
@RestController
@RequestMapping("/statement")
public class StatementController {

	Logger logger = LoggerFactory.getLogger(StatementController.class);

	@Autowired
	StatementService statementService;

	@GetMapping("/search")
	public ResponseEntity<List<StatementDto>> search(@RequestParam  @NotBlank Long accId ,@RequestParam   @Nullable String searchType
			,@RequestParam @Nullable String fromDate,@RequestParam @Nullable String toDate
			,@RequestParam @Nullable String fromAmount,@RequestParam @Nullable String toAmount) throws InvalidRequestException{

		logger.debug("inside search method");
		List<StatementDto> statementDto=statementService.getStatements(accId,searchType,fromDate,toDate,fromAmount,toAmount);
		return new ResponseEntity<>(statementDto,HttpStatus.OK);
	}

}

