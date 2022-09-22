package com.coindeskAPI.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.coindeskAPI.model.Currency;
import com.coindeskAPI.repository.CurrencyRepository;
import com.coindeskAPI.dto.CoindeskApiResponseDto;
import com.coindeskAPI.dto.CoindeskApiRequestDto;
import com.coindeskAPI.dto.CurrencyRequestDto;
import com.coindeskAPI.dto.CoindeskApiNewDto;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


@Service
@Transactional
public class CurrencyServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(RemindBindingServiceImpl.class);

	@Autowired
	private CurrencyRepository currencyRepository;

   	@Value("${coindesk_url:https://api.coindesk.com/v1/bpi/currentprice.json}")
	private String coinDeskUrl;

    	@Autowired
    	private RestTemplate restTemplate;


    	public CoindeskApiResponseDto getCoindeskApi() throws Exception {
		String jsonString = this.getUrlData(coinDeskUrl, "UTF8");

		CoindeskApiResponseDto coindeskApiResponseDto = new CoindeskApiResponseDto();
		coindeskApiResponseDto = new Gson().fromJson(jsonString, CoindeskApiResponseDto.class);
             
		return coindeskApiResponseDto;
	}
    
        public CoindeskApiNewDto getNewCoinDeskAPI() throws Exception {
		
                CoindeskApiRequestDto data = restTemplate.getForObject(coinDeskUrl, CoindeskApiRequestDto.class);

                CoindeskApiNewDto coindeskApiNewDto = new CoindeskApiNewDto();
                Date now = new Date();
                coindeskApiNewDto.setUpdateTime(now);
                Map<String, CoindeskApiRequestDto.Bpi> bpis = data.getBpi();
                for (String key : bpis.keySet()) {
                	CoindeskApiRequestDto.Bpi coinDeskBpi = bpis.get(key);
                	Optional<Currency> currencyEntity = currencyRepository.findByCode(coinDeskBpi.getCode());
                	CoindeskApiNewDto.CoinDeskBpiNew coinDeskBpiNewVo = new CoindeskApiNewDto.CoinDeskBpiNew();
                	coinDeskBpiNewVo.setCode(currencyEntity.isPresent() ? currencyEntity.get().getCode() : coinDeskBpi.getCode());
                	coinDeskBpiNewVo.setChineseName(currencyEntity.isPresent() ? currencyEntity.get().getChineseName() : coinDeskBpi.getCode());
                	coinDeskBpiNewVo.setRate(coinDeskBpi.getRate());

                	coindeskApiNewDto.getCoinDeskBpiNews().add(coinDeskBpiNewVo);
                }
                return coindeskApiNewDto;
	     }	

        public Currency addDtoToEntity(CurrencyRequestDto dto) {
		Currency entity = new Currency();
		entity.setChineseName(dto.getChineseName());
		entity.setCode(dto.getCode());
		return entity;
	}

	@Transactional
	public Currency add(Currency entity) {
		return currencyRepository.save(entity);
	}

	@Transactional
	public Currency update(String code, String chineseName) {
		Optional<Currency> currencyEntityOptional = currencyRepository.findByCode(code);
		Currency currencyEntity = null;
		if (currencyEntityOptional.isPresent()) {
			currencyEntity = currencyEntityOptional.get();
			currencyEntity.setChineseName(chineseName);		
			
		} else {
			currencyEntity = new Currency();
			currencyEntity.setCode(code);
			currencyEntity.setChineseName(chineseName);
		}
		
		return currencyRepository.save(currencyEntity);
	}
	
	@Transactional
	public void delete(String code) throws Exception {
                Optional<Currency> currencyEntityOptional = currencyRepository.findByCode(code);
                if (currencyEntityOptional.isPresent()) {
                        currencyRepository.deleteByCode(code);		
			
		} else {
			throw new Exception("刪除的資料不存在!!");
		}
	}

    	public List<Currency> findAll() {
		return currencyRepository.findAll();
	}   
     
	public Optional<Currency> findByCode(String code) {
		return currencyRepository.findByCode(code);
	}

	
	public String getUrlData(String urlStr, String CodeStr) throws Exception {
		String urlString = "";
		URL url = new URL(urlStr);
		URLConnection urlConnection = url.openConnection();
		HttpURLConnection connection = null;
		if (urlConnection instanceof HttpURLConnection) {
			connection = (HttpURLConnection) urlConnection;
		} else {
			throw new Exception("請輸入URL");
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), CodeStr));
		String current;
		while ((current = in.readLine()) != null) {
			urlString += current;
		}
		return urlString;
	}
	
}
