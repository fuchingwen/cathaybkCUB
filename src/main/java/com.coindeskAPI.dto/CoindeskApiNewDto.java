package com.coindeskAPI.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class CoindeskApiNewDto {
	
     @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss", timezone = "GMT+8")
     private String updateTime;

     List<CoinDeskBpiNew> coinDeskBpiNews;

  
     public void CoinDeskNew(String updateTime, CoinDeskBpiNew... coinDeskBpiNew) {
		this.updateTime = updateTime;		
		this.coinDeskBpiNews = new ArrayList<>(Arrays.asList(coinDeskBpiNew));	
     }

     public void CoinDeskNew(String updateTime, List<CoinDeskBpiNew> coinDeskBpiNews) {
		this.updateTime = updateTime;
		this.coinDeskBpiNews = coinDeskBpiNews;	
     }

     public static class CoinDeskBpiNew {

             private String code;
             private String chineseName;
             private String rate;

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
		public String getRate() {
			return rate;
		}
		public void setRate(String rate) {
			this.rate = rate;
		}

      }

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<CoinDeskBpiNew> getCoinDeskBpiNews() {
		return coinDeskBpiNews;
	}

	public void setCoinDeskBpiNews(List<CoinDeskBpiNew> coinDeskBpiNews) {
		this.coinDeskBpiNews = coinDeskBpiNews;
	}
	
}	
