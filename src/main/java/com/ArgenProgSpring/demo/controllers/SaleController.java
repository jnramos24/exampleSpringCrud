package com.ArgenProgSpring.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ArgenProgSpring.demo.models.Sale;
import com.ArgenProgSpring.demo.services.SaleServiceImpl;

@RestController
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	private SaleServiceImpl saleService;

	@PostMapping("/create")
	public ResponseEntity<String> createSale(@RequestBody Sale sale) {
		try {
			saleService.saveSale(sale);
			return new ResponseEntity<>("Venta creada", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("")
	public ResponseEntity<List<Sale>> getAllSales() {
		List<Sale> allsales = saleService.getAllSales();

		if (!allsales.isEmpty()) {
			return new ResponseEntity<>(allsales, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{saleCode}")
	public ResponseEntity<Sale> getSale(@PathVariable("saleCode") Integer saleCode) {
		Sale sale = saleService.getSaleByCode(saleCode);

		if (sale != null) {
			return new ResponseEntity<>(sale, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Sale> updateSale(@RequestBody Sale sale) {
		try {

			saleService.updateSale(sale);
			Sale saleUpdated = saleService.getSaleByCode(sale.getSaleCode());

			if (saleUpdated != null) {
				return new ResponseEntity<>(saleUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{saleCode}")
	public ResponseEntity<String> deleteSale(@PathVariable("saleCode") Integer saleCode) {
		try {
			Sale sale = saleService.getSaleByCode(saleCode);

			if (sale != null) {
				saleService.deleteSale(saleCode);
				return new ResponseEntity<>("Venta eliminada", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
