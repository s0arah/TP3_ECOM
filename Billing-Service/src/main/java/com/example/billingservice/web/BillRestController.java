package com.example.billingservice.web;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.repositories.BillRepository;
import com.example.billingservice.repositories.ProductItemRepository;
import com.example.billingservice.service.CustomarRestClient;
import com.example.billingservice.service.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {


    @Autowired
private BillRepository billRepository;
    @Autowired
private ProductItemRepository productItemRepository;
    @Autowired
private CustomarRestClient customerRestClient;
    @Autowired
private ProductRestClient productRestClient;

@GetMapping( "/fullBill/{id}")
public Bill bill(@PathVariable  Long id){
    Bill bill=billRepository.findById(id).get();
    bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
    bill.getProductItems().forEach(productItem->{
        productItem.setProduct(productRestClient.findProductById(productItem.getProductId()));
    });
    return bill;

}

}
