package com.solvd.onlinemarkettc.service.impl;

import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.persistence.mybatisimpl.NonPerishableProductRepositoryMybatisImpl;
import com.solvd.onlinemarkettc.service.NonPerishableProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class NonPerishableProductServiceImpl implements NonPerishableProductService {

    private static final Logger log = LogManager.getLogger(NonPerishableProductServiceImpl.class);
    // private final NonPerishableProductRepositoryImpl nonPerishableProductRepository;
    private final NonPerishableProductRepositoryMybatisImpl nonPerishableProductRepository;

    public NonPerishableProductServiceImpl() {
        this.nonPerishableProductRepository = new NonPerishableProductRepositoryMybatisImpl();
    }

    @Override
    public Long createNonPerishableProduct(NonPerishebleProduct product) {
        log.info("create product: {}", product.getName());
        return nonPerishableProductRepository.save(product);
    }

    @Override
    public Optional<NonPerishebleProduct> getNonPerishableProductById(Long id) {
        log.debug("find product id: {}", id);
        return nonPerishableProductRepository.findById(id);
    }

    @Override
    public Optional<NonPerishebleProduct> getNonPerishableProductByName(String name) {
        log.debug("find product name: {}", name);
        return nonPerishableProductRepository.findByName(name);
    }

    @Override
    public List<NonPerishebleProduct> getAllNonPerishableProducts() {
        log.debug("get all products");
        return nonPerishableProductRepository.findAll();
    }

    @Override
    public Long updateNonPerishableProduct(NonPerishebleProduct product) {
        log.info("update product id: {}", product.getId());
        return nonPerishableProductRepository.update(product);
    }

    @Override
    public void deleteNonPerishableProduct(Long id) {
        log.info("delete product id: {}", id);
        nonPerishableProductRepository.deleteById(id);
    }

    @Override
    public boolean nonPerishableProductExists(Long id) {
        return nonPerishableProductRepository.findById(id).isPresent();
    }

    @Override
    public boolean nonPerishableProductExistsByName(String name) {
        return nonPerishableProductRepository.findByName(name).isPresent();
    }
}