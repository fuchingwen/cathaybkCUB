package com.coindeskAPI.enums;


public enum DatePatterns {
    
    DATE_TIME_SLASH("yyyy/MM/dd HH:mm:ss");
  
	
    private String value;

	
    private DatePatterns(String value) {
        this.value = value;
    }

    public String code() {
        return this.name();
    }

    public String value() {
        return this.value;
    }
}
