package com.solvd.onlinemarkettc.service;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;
import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.domain.user.User;

public class ShopFacade {

    private final FoodProductService foodService;
    private final UserService userService;
    private final BasketService basketService;

    public ShopFacade(FoodProductService foodService,
                      UserService userService,
                      BasketService basketService
    ) {
        this.foodService = foodService;
        this.userService = userService;
        this.basketService = basketService;
    }

    public void addProductToUserBasket(Long userId, Long productId) {
        User user = userService.getUserById(userId);
        FoodProduct product = foodService.getFoodProductById(productId);
        basketService.getBasketById(user.getBasket().getId()).addFoodProduct(product);

    }

}
