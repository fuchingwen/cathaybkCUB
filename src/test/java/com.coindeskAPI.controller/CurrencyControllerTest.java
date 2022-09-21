package com.coindeskAPI.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.coindeskAPI.dto.CurrencyResponseDto;
import com.coindeskAPI.model.Currency;
import com.coindeskAPI.repository.CurrencyRepository;
import com.coindeskAPI.service.impl.CurrencyServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
@AutoConfigureMockMvc
public class CurrencyControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private CurrencyRepository currencyRepository;
	@MockBean
	private CurrencyServiceImpl currencyServiceImpl;

	public static final String coinDeskUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";
		
	private static final Logger logger = LoggerFactory.getLogger(CoinDeskControllerTest.class);

	public static final String JSON_UTF8 = "application/json;charset=UTF-8";
  
  private Map<String, Currency> currencies;
	
	private MockMvc mockMvc;
		
	
	@BeforeAll
	public void init() {
		logger.info("BEFORE ALL");
		
		Currency ntd = new Currency();
		ntd.setCode("NTD");
		ntd.setChineseName("新台幣");
		
		Currency usd = new Currency();
    usd.setCode("USD");		
    usd.setChineseName("美金");
		
		Currency gbp = new Currency();
		gbp.setCode("GBP");
    gbp.setChineseName("英鎊");
		
		Currency eur = new Currency();
		eur.setCode("EUR");
    eur.setChineseName("歐元");
		
		List<Currency> currencyList = new ArrayList<>();
		currencyList.add(ntd);
		currencyList.add(usd);
		currencyList.add(gbp);
		currencyList.add(eur);
		
		currencies = currencyList.stream().collect(Collectors.toMap(x -> x.getCode(), Function.identity()));

		mockMvc = MockMvcBuilders.standaloneSetup(new CurrencyController(currencyServiceImpl)).build();
	}
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new CurrencyController(currencyServiceImpl)).build();
	}
   
  /**
	 * 測試呼叫查詢所有幣別對應表資料API，並顯示其內容
	 * 
	 * @throws Exception
	 */
//@Test
	public void testFindAll() throws Exception {
		when(currencyServiceImpl.findAll()).thenReturn(new ArrayList<>(currencies.values()));
		mockMvc.perform(get("/currencies")).andDo(print())
			.andExpect(content().contentType(CurrencyControllerTest.JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.payload", notNullValue()));
	}

	/**
	 * 測試呼叫查詢單一幣別對應表資料API，並顯示其內容
	 * 
	 * @throws Exception
	 */
//@Test
	public void testFindByCode() throws Exception {

		when(currencyServiceImpl.findAll()).thenReturn(new ArrayList<>(currencies.values()));
		when(currencyServiceImpl.findByCode("NTD")).thenReturn(Optional.ofNullable(currencies.get("NTD")));

		mockMvc.perform(
				get("/currencies").
				param("code", "NTD").
				characterEncoding("utf-8"))
				.andDo(print())
				.andExpect(content().contentType(CurrencyControllerTest.JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.payload.code", equalTo("NTD")));
		
	}

	/**
	 * 測試呼叫新增幣別對應表資料API。
	 * 
	 * @throws Exception
	 */
//@Test
	public void testAdd() throws Exception {
		when(currencyServiceImpl.add(Mockito.any(Currency.class))).thenReturn(new Currency("CNY", "人民幣"));
		
		
		mockMvc.perform(
					post("/currencies").
					contentType(CurrencyControllerTest.JSON_UTF8).
					characterEncoding("utf-8").
					content(new ObjectMapper().writeValueAsString(new Currency("CNY", "人民幣")))).
			andDo(print()).
			andExpect(content().contentType(CurrencyControllerTest.JSON_UTF8)).
			andExpect(status().isOk()).
			andExpect(jsonPath("$.payload.code", equalTo("CNY")));		
	}
	
	/**
	 * 測試呼叫更新幣別對應表資料API，並顯示其內容。
	 * 
	 */
	@Test
	public void testUpdate() throws Exception {
		
		mockMvc.perform(
					put("/currencies").
					contentType(CurrencyControllerTest.JSON_UTF8).
					characterEncoding("utf-8").
					content(new ObjectMapper().writeValueAsString(new CurrencyResponseDto("NTD", "新台幣")))).
					
			andDo(print()).
			andExpect(content().contentType(CurrencyControllerTest.JSON_UTF8)).
			andExpect(status().isOk());
		
			verify(currencyServiceImpl, times(1)).update("NTD", "新台幣");
	}
    
	/**
	 * 測試呼叫刪除幣別對應表資料API。
	 * 
	 * @throws Exception
	 */
//@Test
	public void testDelete() throws Exception {

		mockMvc.perform(
					delete("/currencies/CNY").
					contentType(CurrencyControllerTest.JSON_UTF8).
					characterEncoding("utf-8")).
			andDo(print()).
			andExpect(content().contentType(CurrencyControllerTest.JSON_UTF8)).
			andExpect(status().isNoContent());
		
			verify(currencyServiceImpl, times(1)).delete("CNY");
	}
     
}
