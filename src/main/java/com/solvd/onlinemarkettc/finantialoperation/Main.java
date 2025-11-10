package com.solvd.onlinemarkettc.finantialoperation;

import com.jayway.jsonpath.JsonPath;
import com.solvd.onlinemarkettc.delivery.Address;
import com.solvd.onlinemarkettc.item.DiscountedItem;
import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.item.Service;
import com.solvd.onlinemarkettc.payment.DebitCard;
import com.solvd.onlinemarkettc.persistence.BasketRepository;
import com.solvd.onlinemarkettc.persistence.DiscountedItemRepository;
import com.solvd.onlinemarkettc.persistence.FoodProductRepository;
import com.solvd.onlinemarkettc.persistence.NonPerishableProductRepository;
import com.solvd.onlinemarkettc.persistence.ServiceRepository;
import com.solvd.onlinemarkettc.persistence.UserRepository;
import com.solvd.onlinemarkettc.user.User;
import com.solvd.onlinemarkettc.util.Dparser;
import com.solvd.onlinemarkettc.util.Jparser;
import com.solvd.onlinemarkettc.util.Xparser;
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
    private static final BasketRepository basketRepository = new BasketRepository();
    ;
    private static final FoodProductRepository foodProductRepository = new FoodProductRepository();
    private static final DiscountedItemRepository discountedItemRepository = new DiscountedItemRepository();
    private static final NonPerishableProductRepository nonPerishableProductRepository = new NonPerishableProductRepository();
    private static final ServiceRepository serviceRepository = new ServiceRepository();
    private static final UserRepository userRepository = new UserRepository();

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

        //product repo
        FoodProduct foodProduct = foodProductRepository.findById(foodProductRepository.save(apple).getId())
                .orElseThrow(() -> new RuntimeException("food by id 1 not found"));
        log.info("food product from db by id name: {}", foodProduct.getName());

        FoodProduct foodProduct2 = foodProductRepository.findByName("apple")
                .orElseThrow(() -> new RuntimeException("food by name apple not found"));
        log.info("food product from db by name, name: {}", foodProduct2.getName());

        foodProductRepository.findAll().forEach(food -> {
            log.info("food in list id {} and name {}", food.getId(), food.getName());
        });
        foodProductRepository.save(apple);
        foodProductRepository.deleteById(3L);
        foodProductRepository.update(banana);

        //Discounted Things repo
        discountedItemRepository.findAll();
        log.info("all discounted found");
        discountedItemRepository.save(new DiscountedItem("some item", 10.5, "idk"));
        discountedItemRepository.save(new DiscountedItem("some item", 10.5, "idk"));
        discountedItemRepository.deleteById(1L);
        discountedItemRepository.update(new DiscountedItem("NOTsome item", 1L, 10.5, "idk"));

        //NonPerish
        nonPerishableProductRepository.findAll();
        log.info("found all non perish");
        nonPerishableProductRepository.findById(nonPerishableProductRepository.save(soap).getId());
        nonPerishableProductRepository.deleteById(nonPerishableProductRepository.save(soap).getId());
        NonPerishebleProduct Notsoap = new NonPerishebleProduct("soap", 1.2, "");

        nonPerishableProductRepository.save(Notsoap);
        Notsoap.setName("NotSoap");
        nonPerishableProductRepository.update(nonPerishableProductRepository.save(Notsoap));

        //Service
        Service service = new Service("service", 16.3, "idk", "some guy");
        var serviceid = serviceRepository.save(service).getId();
        serviceRepository.findAll();
        serviceRepository.findById(serviceid);
        Service NOTservice = new Service("service", 16.3, "idk", "some guy");
        var newId = serviceRepository.save(NOTservice).getId();
        NOTservice.setName("NotService");
        NOTservice.setId(newId);
        serviceRepository.update(NOTservice);
        serviceRepository.deleteById(newId);

        //Basket repo
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

        basketRepository.saveWithItems(basket3);
        log.info("created basket id {}", basket3.getId());

        Basket foundBasket = basketRepository.findById(basket3.getId())
                .orElseThrow(() -> new RuntimeException("basket not found"));
        log.info("found basket id {}", foundBasket.getId());

        basketRepository.findAll().forEach(b -> {
            log.info("basket in list id {} and cost {}", b.getId(), b.getSumCost());
        });

        //User
        basket.setId(basket3.getId());
        User user = new User("someUser", debitCard1, basket);
        Long userId = userRepository.save(user).getId();
        User foundUser = userRepository.findById(userId).orElseThrow();
        userRepository.findAll();
        foundUser.setName("notSomeUser");
        userRepository.deleteById(userId);

        System.out.println("ended");
    }
}