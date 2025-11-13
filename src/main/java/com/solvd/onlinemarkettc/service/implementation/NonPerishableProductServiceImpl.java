package com.solvd.onlinemarkettc.service.implementation;

import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.persistence.repository.NonPerishableProductRepository;
import com.solvd.onlinemarkettc.service.interfaces.NonPerishableProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class NonPerishableProductServiceImpl implements NonPerishableProductService {

    private static final Logger log = LogManager.getLogger(NonPerishableProductServiceImpl.class);
    private final NonPerishableProductRepository nonPerishableProductRepository;

    public NonPerishableProductServiceImpl() {
        this.nonPerishableProductRepository = new NonPerishableProductRepository();
    }

    public NonPerishebleProduct createNonPerishableProduct(NonPerishebleProduct product) {
        log.info("create product: {}", product.getName());
        return nonPerishableProductRepository.save(product);
    }

    public Optional<NonPerishebleProduct> getNonPerishableProductById(Long id) {
        log.debug("find product id: {}", id);
        return nonPerishableProductRepository.findById(id);
    }

    public Optional<NonPerishebleProduct> getNonPerishableProductByName(String name) {
        log.debug("find product name: {}", name);
        return nonPerishableProductRepository.findByName(name);
    }

    public List<NonPerishebleProduct> getAllNonPerishableProducts() {
        log.debug("get all products");
        return nonPerishableProductRepository.findAll();
    }

    public NonPerishebleProduct updateNonPerishableProduct(NonPerishebleProduct product) {
        log.info("update product id: {}", product.getId());
        return nonPerishableProductRepository.update(product);
    }

    public void deleteNonPerishableProduct(Long id) {
        log.info("delete product id: {}", id);
        nonPerishableProductRepository.deleteById(id);
    }

    public boolean nonPerishableProductExists(Long id) {
        return nonPerishableProductRepository.findById(id).isPresent();
    }

    public boolean nonPerishableProductExistsByName(String name) {
        return nonPerishableProductRepository.findByName(name).isPresent();
    }
}