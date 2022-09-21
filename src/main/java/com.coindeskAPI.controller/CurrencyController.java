package com.coindeskAPI.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coindeskAPI.model.Currency;
import com.coindeskAPI.dto.CurrencyResponseDto;
import com.coindeskAPI.dto.CurrencyRequestDto;
import com.coindeskAPI.service.impl.CurrencyServiceImpl;


@RestController
@RequestMapping("/currencies")
public class CurrencyController {
        
    @Autowired
    private CurrencyServiceImpl currencyServiceImpl;
        

	  public CurrencyController() {
		}
	
    public CurrencyController(CurrencyServiceImpl currencyServiceImpl2) {
		// For Test TODO Auto-generated constructor stub
	  }

	  @RequestMapping(value = "/findAll", method = RequestMethod.GET)
	  public ResponseEntity<?> findAll() {
        List<Currency> result = currencyServiceImpl.findAll();

		   return CollectionUtils.isNotEmpty(result) ? ResponseEntity.ok(CurrencyResponseDto.success(result))
				  : new ResponseEntity<CurrencyResponseDto<?>>(CurrencyResponseDto.success(), HttpStatus.NOT_FOUND);
	  }
	
	  @RequestMapping(value = "/{code}", method = RequestMethod.GET)	
	  public ResponseEntity<?> findByCode(@RequestParam(value = "code") String code) {
       Optional<Currency> result = Optional.empty();
		   if (StringUtils.isNotBlank(code)) {
               result = currencyServiceImpl.findByCode(code);			
		   } 

		   return result.isPresent() ? ResponseEntity.ok(CurrencyResponseDto.success(result.get()))
				  : new ResponseEntity<CurrencyResponseDto<?>>(CurrencyResponseDto.success(), HttpStatus.NOT_FOUND);
	  }

	  @RequestMapping(method = RequestMethod.POST)
	  public ResponseEntity<?> add(@RequestBody CurrencyRequestDto dto) {
		
		   return ResponseEntity.ok(CurrencyResponseDto.success(currencyServiceImpl.add(currencyServiceImpl.addDtoToEntity(dto))));
	  }

    @RequestMapping(method = RequestMethod.PUT)
	  public ResponseEntity<?> update(@RequestBody CurrencyRequestDto dto) {
       currencyServiceImpl.update(dto.getCode(), dto.getChineseName());

		   return ResponseEntity.ok(CurrencyResponseDto.success(null));
	  }

	  @RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	  public ResponseEntity<?> delete(@RequestBody CurrencyRequestDto dto) throws Exception {
		   currencyServiceImpl.delete(dto.getCode());

		   return new ResponseEntity<CurrencyResponseDto<?>>(CurrencyResponseDto.success(null), HttpStatus.NO_CONTENT);
	  }
	
}
