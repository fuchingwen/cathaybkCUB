package com.coineskAPI.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tpisoftware.cfh.apistore.repository.CurrencyRepository;
import com.tpisoftware.cfh.apistore.service.impl.CurrencyServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
@AutoConfigureMockMvc
public class CoindeskControllerTest { 

    	  @Autowired
	  private WebApplicationContext webApplicationContext;
    	  @Autowired
    	  private CurrencyRepository currencyRepository;
    	  @MockBean
    	  private CurrencyServiceImpl currencyServiceImpl;

    	  private MockMvc mvc;

	  public static final String coinDeskUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";
	
	  private static final Logger logger = LoggerFactory.getLogger(CoinDeskControllerTest.class);


   	  /**
    	   * 測試呼叫coindesk API，並顯示其內容。
 	   * 
 	   */
          @Test
	  public void contextLoads() throws Exception {
		String uri = "/coindesk/getCoindeskApi";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		int status = result.getResponse().getStatus();

		System.out.println(result.getResponse().getContentAsString());
		Assert.assertEquals("錯誤",200,status);
	  }

   	 /**
    	  * 測試呼叫資料轉換的API，並顯示其內容。
    	  * 
    	  */
//	  @Test
	  public void newCoinDeskAPILoads() throws Exception {
		String uri = "/coindesk/getNewCoinDeskAPI";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		String jsonString = result.getResponse().getContentAsString();
                int status = result.getResponse().getStatus();

		System.out.println("jsonString = " + jsonString);
		Assert.assertEquals("錯誤", 200, status);
	}    

}
