package com.coindeskAPI.dto;


public class CurrencyRequestDto {
	
	private String code;
	
	private String chineseName;

  
	public CurrencyRequestDto() {}
	
	public CurrencyRequestDto(String code, String chineseName) {
		this.code = code;
		this.chineseName = chineseName;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	
}
