package com.dongseo.card.model.card;

public class CardVO {
	private String id;
	private String USER_USER_ID;
	private String CARD_NAME;
	private String CARD_ATTRIBUTE;
	private String CARD_CONTENT;
	private String CARD_BANK_CARD_BANK_NAME;
	private String CARD_NUMBER;
	private String CARD_CVC;
	private String CARD_NICKNAME;
	private String CARD_VALIDITY;
	private String CARD_IMG;
	private String CARD_CARD_NAME;
	private String STORE_NAME;
	private String STORE_STORE_NAME;
	private String STORE_ATTRIBUTE;
	private int STORE_CARD_PERSENT_SALE;
	private int STORE_CARD_CASH_SALE;
	private int STORE_CARD_WAY;
	private int result;
	
	


	
	public CardVO(String cARD_NUMBER, String cARD_CVC, String cARD_NICKNAME, String cARD_VALIDITY, String cARD_IMG,
			String cARD_CARD_NAME, int result) {
		super();
		CARD_NUMBER = cARD_NUMBER;
		CARD_CVC = cARD_CVC;
		CARD_NICKNAME = cARD_NICKNAME;
		CARD_VALIDITY = cARD_VALIDITY;
		CARD_IMG = cARD_IMG;
		CARD_CARD_NAME = cARD_CARD_NAME;
		this.result = result;
	}

	public CardVO(String cARD_CARD_NAME, String cARD_NUMBER, String cARD_CVC, String cARD_NICKNAME, String cARD_VALIDITY,
			String uSER_USER_ID) {
		super();
		USER_USER_ID = uSER_USER_ID;
		CARD_NUMBER = cARD_NUMBER;
		CARD_CVC = cARD_CVC;
		CARD_NICKNAME = cARD_NICKNAME;
		CARD_VALIDITY = cARD_VALIDITY;
		CARD_CARD_NAME = cARD_CARD_NAME;
	}

	public CardVO() {
		super();
	}
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSTORE_CARD_PERSENT_SALE() {
		return STORE_CARD_PERSENT_SALE;
	}

	public void setSTORE_CARD_PERSENT_SALE(int sTORE_CARD_PERSENT_SALE) {
		STORE_CARD_PERSENT_SALE = sTORE_CARD_PERSENT_SALE;
	}

	public int getSTORE_CARD_CASH_SALE() {
		return STORE_CARD_CASH_SALE;
	}

	public void setSTORE_CARD_CASH_SALE(int sTORE_CARD_CASH_SALE) {
		STORE_CARD_CASH_SALE = sTORE_CARD_CASH_SALE;
	}

	public int getSTORE_CARD_WAY() {
		return STORE_CARD_WAY;
	}

	public void setSTORE_CARD_WAY(int sTORE_CARD_WAY) {
		STORE_CARD_WAY = sTORE_CARD_WAY;
	}

	public String getSTORE_STORE_NAME() {
		return STORE_STORE_NAME;
	}

	public void setSTORE_STORE_NAME(String sTORE_STORE_NAME) {
		STORE_STORE_NAME = sTORE_STORE_NAME;
	}

	public String getSTORE_ATTRIBUTE() {
		return STORE_ATTRIBUTE;
	}

	public void setSTORE_ATTRIBUTE(String sTORE_ATTRIBUTE) {
		STORE_ATTRIBUTE = sTORE_ATTRIBUTE;
	}

	public String getSTORE_NAME() {
		return STORE_NAME;
	}

	public void setSTORE_NAME(String sTORE_NAME) {
		STORE_NAME = sTORE_NAME;
	}

	

	public String getUSER_USER_ID() {
		return USER_USER_ID;
	}
	public void setUSER_USER_ID(String uSER_USER_ID) {
		USER_USER_ID = uSER_USER_ID;
	}
	public String getCARD_NAME() {
		return CARD_NAME;
	}
	public void setCARD_NAME(String cARD_NAME) {
		CARD_NAME = cARD_NAME;
	}
	public String getCARD_ATTRIBUTE() {
		return CARD_ATTRIBUTE;
	}
	public void setCARD_ATTRIBUTE(String cARD_ATTRIBUTE) {
		CARD_ATTRIBUTE = cARD_ATTRIBUTE;
	}
	public String getCARD_CONTENT() {
		return CARD_CONTENT;
	}
	public void setCARD_CONTENT(String cARD_CONTENT) {
		CARD_CONTENT = cARD_CONTENT;
	}
	public String getCARD_BANK_CARD_BANK_NAME() {
		return CARD_BANK_CARD_BANK_NAME;
	}
	public void setCARD_BANK_CARD_BANK_NAME(String cARD_BANK_CARD_BANK_NAME) {
		CARD_BANK_CARD_BANK_NAME = cARD_BANK_CARD_BANK_NAME;
	}
	public String getCARD_NUMBER() {
		return CARD_NUMBER;
	}
	public void setCARD_NUMBER(String cARD_NUMBER) {
		CARD_NUMBER = cARD_NUMBER;
	}
	public String getCARD_CVC() {
		return CARD_CVC;
	}
	public void setCARD_CVC(String cARD_CVC) {
		CARD_CVC = cARD_CVC;
	}
	public String getCARD_NICKNAME() {
		return CARD_NICKNAME;
	}
	public void setCARD_NICKNAME(String cARD_NICKNAME) {
		CARD_NICKNAME = cARD_NICKNAME;
	}
	public String getCARD_VALIDITY() {
		return CARD_VALIDITY;
	}
	public void setCARD_VALIDITY(String cARD_VALIDITY) {
		CARD_VALIDITY = cARD_VALIDITY;
	}
	public String getCARD_IMG() {
		return CARD_IMG;
	}
	public void setCARD_IMG(String cARD_IMG) {
		CARD_IMG = cARD_IMG;
	}
	public String getCARD_CARD_NAME() {
		return CARD_CARD_NAME;
	}
	public void setCARD_CARD_NAME(String cARD_CARD_NAME) {
		CARD_CARD_NAME = cARD_CARD_NAME;
	}

	@Override
	public String toString() {
		return "CardVO [USER_USER_ID=" + USER_USER_ID + ", CARD_NAME=" + CARD_NAME + ", CARD_ATTRIBUTE="
				+ CARD_ATTRIBUTE + ", CARD_CONTENT=" + CARD_CONTENT + ", CARD_BANK_CARD_BANK_NAME="
				+ CARD_BANK_CARD_BANK_NAME + ", CARD_NUMBER=" + CARD_NUMBER + ", CARD_CVC=" + CARD_CVC
				+ ", CARD_NICKNAME=" + CARD_NICKNAME + ", CARD_VALIDITY=" + CARD_VALIDITY + ", CARD_IMG=" + CARD_IMG
				+ ", CARD_CARD_NAME=" + CARD_CARD_NAME + ", STORE_NAME=" + STORE_NAME + ", STORE_STORE_NAME="
				+ STORE_STORE_NAME + ", STORE_ATTRIBUTE=" + STORE_ATTRIBUTE + ", STORE_CARD_PERSENT_SALE="
				+ STORE_CARD_PERSENT_SALE + ", STORE_CARD_CASH_SALE=" + STORE_CARD_CASH_SALE + ", STORE_CARD_WAY="
				+ STORE_CARD_WAY + ", result=" + result + "]";
	}
	
}
