package com.nagarro.statements.model;

public class Statement {

	  private Long ID;
	  private Long account_id;
	  private String datefield;
	  private String amount;
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

}
