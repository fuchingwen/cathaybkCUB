package com.coindeskAPI.dto;


public class CurrencyResponseDto<T> {

	public static final String SUCCESS_CODE = "0000";
	
	public static final String ERROR_CODE = "1111";

	private Object data;

  private T payload;
	
	private String code;
	
	private String message;

        
  private CurrencyResponseDto(T payload, String code, String message) {
		this.payload = payload;
    this.code = code;
		this.message = message;
	}

    public CurrencyResponseDto(String string, String string2) {
		//For Test TODO Auto-generated constructor stub
	}

	public static <T> CurrencyResponseDto<?> success(T t) {
		return new CurrencyResponseDto<T>(t, CurrencyResponseDto.SUCCESS_CODE, "操作成功");
	}
	
	public static <T> CurrencyResponseDto<?> success() {
		return new CurrencyResponseDto<T>(null, CurrencyResponseDto.SUCCESS_CODE, "操作成功");
	}	
	
	public static <T> CurrencyResponseDto<?> error(T t, String message) {
		return new CurrencyResponseDto<T>(t, CurrencyResponseDto.ERROR_CODE, message);
	}	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

  public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
