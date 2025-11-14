package com.solvd.onlinemarkettc.service.impl;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;
import com.solvd.onlinemarkettc.domain.item.DiscountedItem;
import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.domain.item.Service;
import com.solvd.onlinemarkettc.persistence.impl.BasketRepositoryImpl;
import com.solvd.onlinemarkettc.persistence.impl.DiscountedItemRepositoryImpl;
import com.solvd.onlinemarkettc.persistence.impl.FoodProductRepositoryImpl;
import com.solvd.onlinemarkettc.persistence.impl.NonPerishableProductRepositoryImpl;
import com.solvd.onlinemarkettc.persistence.impl.ServiceRepositoryImpl;
import com.solvd.onlinemarkettc.service.BasketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BasketServiceImpl implements BasketService {

    private static final Logger log = LogManager.getLogger(BasketServiceImpl.class);
    private final BasketRepositoryImpl basketRepository;
    private final FoodProductRepositoryImpl foodProductRepository;
    private final NonPerishableProductRepositoryImpl nonPerishableProductRepository;
    private final DiscountedItemRepositoryImpl discountedItemRepository;
    private final ServiceRepositoryImpl serviceRepository;

    public BasketServiceImpl() {
        this.basketRepository = new BasketRepositoryImpl();
        this.foodProductRepository = new FoodProductRepositoryImpl();
        this.nonPerishableProductRepository = new NonPerishableProductRepositoryImpl();
        this.discountedItemRepository = new DiscountedItemRepositoryImpl();
        this.serviceRepository = new ServiceRepositoryImpl();
    }

    @Override
    public Basket createBasketWithItems(Basket basket) {
        log.info("create basket with items");

        Basket savedBasket = basketRepository.save(basket);
        Long basketId = savedBasket.getId();

        for (FoodProduct food : basket.getFoodProductList()) {
            basketRepository.addFoodProductToBasket(basketId, food.getId());
        }

        for (NonPerishebleProduct product : basket.getNonPerishebleProductList()) {
            basketRepository.addNonPerishableProductToBasket(basketId, product.getId());
        }

        for (DiscountedItem item : basket.getDiscountedItemList()) {
            basketRepository.addDiscountedItemToBasket(basketId, item.getId());
        }

        for (Service service : basket.getServiceList()) {
            basketRepository.addServiceToBasket(basketId, service.getId());
        }

        log.info("basket created successfully");
        return getBasketWithAllItems(basketId);
    }

    @Override
    public Optional<Basket> getBasketById(Long id) {
        log.debug("find basket by id");
        return basketRepository.findById(id);
    }

    public Basket getBasketWithAllItems(Long basketId) {
        log.debug("find basket items");

        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new RuntimeException("basket not found"));

        List<Long> foodProductIds = basketRepository.findFoodProductIdsByBasketId(basketId);
        for (Long foodId : foodProductIds) {
            foodProductRepository.findById(foodId).ifPresent(basket::addFoodProduct);
        }

        List<Long> nonPerishableProductIds = basketRepository.findNonPerishableProductIdsByBasketId(basketId);
        for (Long productId : nonPerishableProductIds) {
            nonPerishableProductRepository.findById(productId).ifPresent(basket::addProduct);
        }

        List<Long> discountedItemIds = basketRepository.findDiscountedItemIdsByBasketId(basketId);
        for (Long itemId : discountedItemIds) {
            discountedItemRepository.findById(itemId).ifPresent(basket::addDiscountedItem);
        }

        List<Long> serviceIds = basketRepository.findServiceIdsByBasketId(basketId);
        for (Long serviceId : serviceIds) {
            serviceRepository.findById(serviceId).ifPresent(basket::addService);
        }

        return basket;
    }

    @Override
    public List<Basket> getAllBaskets() {
        log.debug("get all baskets");
        return basketRepository.findAll();
    }

    @Override
    public Basket updateBasket(Basket basket) {
        log.info("update basket");
        return basketRepository.update(basket);
    }

    @Override
    public void deleteBasket(Long id) {
        log.info("delete basket");
        basketRepository.deleteById(id);
    }

    @Override
    public boolean basketExists(Long id) {
        return basketRepository.findById(id).isPresent();
    }

    public void addFoodProductToBasket(Long basketId, Long foodProductId) {
        log.info("add food product");
        basketRepository.addFoodProductToBasket(basketId, foodProductId);
    }

    public void addNonPerishableProductToBasket(Long basketId, Long productId) {
        log.info("add non perishable product");
        basketRepository.addNonPerishableProductToBasket(basketId, productId);
    }

    public void addDiscountedItemToBasket(Long basketId, Long itemId) {
        log.info("add discounted item");
        basketRepository.addDiscountedItemToBasket(basketId, itemId);
    }

    public void addServiceToBasket(Long basketId, Long serviceId) {
        log.info("add service");
        basketRepository.addServiceToBasket(basketId, serviceId);
    }
}