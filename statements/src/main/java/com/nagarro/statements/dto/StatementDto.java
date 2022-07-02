package com.nagarro.statements.dto;

import org.springframework.stereotype.Component;
/**
 * @author 
 * Danish Ansari
 *
 */
@Component
public class StatementDto {


	private Long ID;
	private Long account_id;
	private String datefield;
	private String amount;
	private String account_number;



	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	public String getDatefield() {
		return datefield;
	}
	public void setDatefield(String datefield) {
		this.datefield = datefield;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "StatementDto [ID=" + ID + ", account_id=" + account_id + ", datefield=" + datefield + ", amount="
				+ amount + "]";
	}

}
 