package com.ArgenProgSpring.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ArgenProgSpring.demo.models.Sale;
import com.ArgenProgSpring.demo.repositories.IsaleRepository;

@Service
public class SaleServiceImpl implements ISaleService {

	@Autowired
	private IsaleRepository saleRepository;

	@Override
	public void saveSale(Sale sale) {
		saleRepository.save(sale);
	}

	@Override
	public List<Sale> getAllSales() {
		List<Sale> allSales = saleRepository.findAll();
		return allSales;
	}

	@Override
	public Sale getSaleByCode(Integer saleCode) {
		Sale sale = saleRepository.findById(saleCode).orElse(null);
		return sale;
	}

	@Override
	public void updateSale(Sale sale) {
		this.saveSale(sale);
	}

	@Override
	public void deleteSale(Integer saleCode) {
		saleRepository.deleteById(saleCode);
	}

}
