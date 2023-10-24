package com.ArgenProgSpring.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ArgenProgSpring.demo.models.Sale;

@Service
public interface ISaleService {
	
	public void saveSale(Sale sale);
	
	public List<Sale> getAllSales();
	
	public Sale getSaleByCode(Integer saleCode);
	
	public void updateSale(Sale sale);
	
	public void deleteSale(Integer saleCode);

}
