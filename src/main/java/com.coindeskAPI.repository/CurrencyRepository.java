package com.coindeskAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coindeskAPI.model.Currency;


@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {

  Optional<Currency> findByCode(String code);
	
	void deleteByCode(String code);

}
