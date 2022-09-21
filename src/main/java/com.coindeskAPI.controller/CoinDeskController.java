package com.coindeskAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coindeskAPI.dto.CurrencyResponseDto;
import com.coindeskAPI.service.impl.CurrencyServiceImpl;


@RestController
@RequestMapping("/coindesk")
public class CoinDeskController {

	@Autowired
	private CurrencyServiceImpl currencyServiceImpl;
	

  @RequestMapping(value = "/getCoindeskApi", produces = "application/json; charset=utf-8")
	public ResponseEntity<?> getCoindeskApiI() throws Exception {

      return ResponseEntity.ok(CurrencyResponseDto.success(currencyServiceImpl.getCoindeskApi()));
	}

	@RequestMapping(value = "/getNewCoinDeskAPI", produces = "application/json; charset=utf-8")
	public ResponseEntity<?> getNewCoinDeskAPI() throws Exception {

      return ResponseEntity.ok(CurrencyResponseDto.success(currencyServiceImpl.getNewCoinDeskAPI()));
	}
	
	
}
