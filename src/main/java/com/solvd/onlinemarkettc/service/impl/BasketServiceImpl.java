package com.solvd.onlinemarkettc.service.impl;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;
import com.solvd.onlinemarkettc.domain.item.DiscountedItem;
import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.domain.item.Service;
import com.solvd.onlinemarkettc.persistence.mybatisimpl.BasketRepositoryMybatisImpl;
import com.solvd.onlinemarkettc.service.BasketService;
import com.solvd.onlinemarkettc.service.DiscountedItemService;
import com.solvd.onlinemarkettc.service.FoodProductService;
import com.solvd.onlinemarkettc.service.NonPerishableProductService;
import com.solvd.onlinemarkettc.service.ServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BasketServiceImpl implements BasketService {

    private static final Logger log = LogManager.getLogger(BasketServiceImpl.class);
    // private final BasketRepositoryImpl basketRepository;
    private final BasketRepositoryMybatisImpl basketRepository;
    private final FoodProductService foodProductService;
    private final NonPerishableProductService nonPerishableProductService;
    private final DiscountedItemService discountedItemService;
    private final ServiceService serviceService;

    public BasketServiceImpl() {
        this.basketRepository = new BasketRepositoryMybatisImpl();
        this.foodProductService = new FoodProductServiceImpl();
        this.nonPerishableProductService = new NonPerishableProductServiceImpl();
        this.discountedItemService = new DiscountedItemServiceImpl();
        this.serviceService = new ServiceServiceImpl();
    }

    @Override
    public Basket createBasketWithItems(Basket basket) {
        log.info("create basket with items");
        basketRepository.save(basket);
        Long savedBasket = basket.getId();

        for (FoodProduct food : basket.getFoodProductList()) {
            basketRepository.addFoodProductToBasket(savedBasket, food.getId());
        }

        for (NonPerishebleProduct product : basket.getNonPerishebleProductList()) {
            basketRepository.addNonPerishableProductToBasket(savedBasket, product.getId());
        }

        for (DiscountedItem item : basket.getDiscountedItemList()) {
            basketRepository.addDiscountedItemToBasket(savedBasket, item.getId());
        }

        for (Service service : basket.getServiceList()) {
            basketRepository.addServiceToBasket(savedBasket, service.getId());
        }

        log.info("basket created successfully");
        return getBasketWithAllItems(savedBasket);
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
            foodProductService.getFoodProductById(foodId).ifPresent(basket::addFoodProduct);
        }

        List<Long> nonPerishableProductIds = basketRepository.findNonPerishableProductIdsByBasketId(basketId);
        for (Long productId : nonPerishableProductIds) {
            nonPerishableProductService.getNonPerishableProductById(productId).ifPresent(basket::addProduct);
        }

        List<Long> discountedItemIds = basketRepository.findDiscountedItemIdsByBasketId(basketId);
        for (Long itemId : discountedItemIds) {
            discountedItemService.getDiscountedItemById(itemId).ifPresent(basket::addDiscountedItem);
        }

        List<Long> serviceIds = basketRepository.findServiceIdsByBasketId(basketId);
        for (Long serviceId : serviceIds) {
            serviceService.getServiceById(serviceId).ifPresent(basket::addService);
        }

        return basket;
    }

    public List<Basket> getAllBaskets() {
        log.debug("get all baskets");
        return basketRepository.findAll();
    }

    public Long updateBasket(Basket basket) {
        log.info("update basket");
        return basketRepository.update(basket);
    }

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

    public Long save(Basket basket) {
        log.info("save basket");
        return basketRepository.save(basket);
    }

    public List<Long> findFoodProductIdsByBasketId(Long basketId) {
        return basketRepository.findFoodProductIdsByBasketId(basketId);
    }

    public List<Long> findNonPerishableProductIdsByBasketId(Long basketId) {
        return basketRepository.findNonPerishableProductIdsByBasketId(basketId);
    }

    public List<Long> findDiscountedItemIdsByBasketId(Long basketId) {
        return basketRepository.findDiscountedItemIdsByBasketId(basketId);
    }

    public List<Long> findServiceIdsByBasketId(Long basketId) {
        return basketRepository.findServiceIdsByBasketId(basketId);
    }
}