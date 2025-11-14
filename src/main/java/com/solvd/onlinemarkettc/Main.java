package com.solvd.onlinemarkettc;

import com.jayway.jsonpath.JsonPath;
import com.solvd.onlinemarkettc.domain.delivery.Address;
import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;
import com.solvd.onlinemarkettc.domain.finantialoperation.CheckOut;
import com.solvd.onlinemarkettc.domain.finantialoperation.OnlineShop;
import com.solvd.onlinemarkettc.domain.item.DiscountedItem;
import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.domain.item.Service;
import com.solvd.onlinemarkettc.domain.payment.DebitCard;
import com.solvd.onlinemarkettc.domain.user.User;
import com.solvd.onlinemarkettc.service.BasketService;
import com.solvd.onlinemarkettc.service.DiscountedItemService;
import com.solvd.onlinemarkettc.service.FoodProductService;
import com.solvd.onlinemarkettc.service.NonPerishableProductService;
import com.solvd.onlinemarkettc.service.ServiceService;
import com.solvd.onlinemarkettc.service.UserService;
import com.solvd.onlinemarkettc.service.impl.BasketServiceImpl;
import com.solvd.onlinemarkettc.service.impl.DiscountedItemServiceImpl;
import com.solvd.onlinemarkettc.service.impl.FoodProductServiceImpl;
import com.solvd.onlinemarkettc.service.impl.NonPerishableProductServiceImpl;
import com.solvd.onlinemarkettc.service.impl.ServiceServiceImpl;
import com.solvd.onlinemarkettc.service.impl.UserServiceImpl;
import com.solvd.onlinemarkettc.service.parser.Dparser;
import com.solvd.onlinemarkettc.service.parser.Jparser;
import com.solvd.onlinemarkettc.service.parser.Xparser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);
    private static final BasketService basketService = new BasketServiceImpl();
    private static final FoodProductService foodProductService = new FoodProductServiceImpl();
    private static final DiscountedItemService discountedItemService = new DiscountedItemServiceImpl();
    private static final NonPerishableProductService nonPerishableProductService = new NonPerishableProductServiceImpl();
    private static final ServiceService serviceService = new ServiceServiceImpl();
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws Exception {
        System.out.println("Program started");
        System.out.println("=== System.out ===");
        log.info("=== Logger INFO ===");
        log.error("=== Logger ERROR ===");
        log.debug("=== Logger DEBUG ===");
        FoodProduct apple = new FoodProduct(0.5, "apple");
        FoodProduct banana = new FoodProduct(0.3, "banana", 5L);

        NonPerishebleProduct soap = new NonPerishebleProduct("soap", 1.2, "");
        NonPerishebleProduct shampoo = new NonPerishebleProduct("shampoo", 3.5, "", 2L);

        ArrayList<FoodProduct> foodList = new ArrayList<>();
        foodList.add(apple);
        foodList.add(banana);

        ArrayList<NonPerishebleProduct> nonFoodList = new ArrayList<>();
        nonFoodList.add(soap);
        nonFoodList.add(shampoo);

        OnlineShop shop = new OnlineShop();
        log.info("online shop launched!");
        Basket basket = new Basket();
        basket.addFoodProduct(apple);
        DebitCard debitCard1 = new DebitCard(5555L, true, 10000);

        CheckOut.checkoutBasket(debitCard1, basket);

        String xmlPath = "src/main/java/resources/hierarchy.xml";
        Xparser parser = new Xparser();
        OnlineShop xmlShop = parser.unmarshal(xmlPath);
        log.info("xml hierarchy parsed!");

        OnlineShop Dshop = Dparser.parser(Path.of(xmlPath));
        log.info("Dshop is created using DOM");

        log.info(xmlShop.getFoodProducts().getFirst());

        Jparser jparser = new Jparser();
        OnlineShop jshop = jparser.parse("src/main/java/resources/hierarchy.json");

        String json = Files.readString(Paths.get("src/main/java/resources/hierarchy.json"));

        String name = JsonPath.read(json, "$.foodProducts[0].name");
        log.info("First food product: {}", name);

        Double cost = JsonPath.read(json, "$.nonFoodProducts[0].cost");
        log.info("First non-food cost: {}", cost);

        String firstName = JsonPath.read(json, "$.users[0].name");
        log.info("First user name: {}", firstName);

        Double money = JsonPath.read(json, "$.users[0].debitCard.moneyAmount");
        log.info("First user money: {}", money);

        Number id = JsonPath.read(json, "$.users[0].basket.id");
        Long basketId = (id == null) ? null : id.longValue();
        log.info("First basket ID: {}", basketId);

        //product service
        FoodProduct foodProduct = foodProductService.getFoodProductById(foodProductService.createFoodProduct(apple).getId())
                .orElseThrow(() -> new RuntimeException("food by id 1 not found"));
        log.info("food product from db by id name: {}", foodProduct.getName());

        FoodProduct foodProduct2 = foodProductService.getFoodProductByName("apple")
                .orElseThrow(() -> new RuntimeException("food by name apple not found"));
        log.info("food product from db by name, name: {}", foodProduct2.getName());

        foodProductService.getAllFoodProducts().forEach(food -> {
            log.info("food in list id {} and name {}", food.getId(), food.getName());
        });
        foodProductService.createFoodProduct(apple);
        foodProductService.deleteFoodProduct(3L);
        foodProductService.updateFoodProduct(banana);

        //Discounted Things
        discountedItemService.getAllDiscountedItems();
        log.info("all discounted found");
        discountedItemService.createDiscountedItem(new DiscountedItem("some item", 10.5, "idk"));
        discountedItemService.createDiscountedItem(new DiscountedItem("some item", 10.5, "idk"));
        discountedItemService.deleteDiscountedItem(1L);
        discountedItemService.updateDiscountedItem(new DiscountedItem("NOTsome item", 1L, 10.5, "idk"));

        //NonPerish
        nonPerishableProductService.getAllNonPerishableProducts();
        log.info("found all non perish");
        nonPerishableProductService.getNonPerishableProductById(nonPerishableProductService.createNonPerishableProduct(soap).getId());
        nonPerishableProductService.deleteNonPerishableProduct(nonPerishableProductService.createNonPerishableProduct(soap).getId());
        NonPerishebleProduct Notsoap = new NonPerishebleProduct("soap", 1.2, "");

        nonPerishableProductService.createNonPerishableProduct(Notsoap);
        Notsoap.setName("NotSoap");
        nonPerishableProductService.updateNonPerishableProduct(nonPerishableProductService.createNonPerishableProduct(Notsoap));

        //Service
        Service service = new Service("service", 16.3, "idk", "some guy");
        var serviceid = serviceService.createService(service).getId();
        serviceService.getAllServices();
        serviceService.getServiceById(serviceid);
        Service NOTservice = new Service("service", 16.3, "idk", "some guy");
        var newId = serviceService.createService(NOTservice).getId();
        NOTservice.setName("NotService");
        NOTservice.setId(newId);
        serviceService.updateService(NOTservice);
        serviceService.deleteService(newId);

        //Basket service
        List<FoodProduct> foodProductList = new ArrayList<>();
        List<NonPerishebleProduct> nonPerishableProductList = new ArrayList<>();
        List<DiscountedItem> discountedItemList = new ArrayList<>();
        List<Service> serviceList = new ArrayList<>();
        Address adrress2 = new Address("ifas", 1L, "10");

        Basket basket3 = new Basket(
                foodProductList,
                nonPerishableProductList,
                discountedItemList,
                serviceList,
                50.8,
                Date.from(Instant.now()),
                adrress2
        );

        basketService.createBasketWithItems(basket3);
        log.info("created basket id {}", basket3.getId());

        Basket foundBasket = basketService.getBasketById(basket3.getId())
                .orElseThrow(() -> new RuntimeException("basket not found"));
        log.info("found basket id {}", foundBasket.getId());

        basketService.getAllBaskets().forEach(b -> {
            log.info("basket in list id {} and cost {}", b.getId(), b.getSumCost());
        });

        //User
        basket.setId(basket3.getId());
        User user = new User("someUser", debitCard1, basket);
        Long userId = userService.createUser(user).getId();
        User foundUser = userService.getUserById(userId).orElseThrow();
        userService.getAllUsers();
        foundUser.setName("notSomeUser");
        userService.deleteUser(userId);

        System.out.println("ended");
    }
}