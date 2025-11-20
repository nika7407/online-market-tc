package com.solvd.onlinemarkettc;

import com.jayway.jsonpath.JsonPath;
import com.solvd.onlinemarkettc.domain.delivery.Address;
import com.solvd.onlinemarkettc.domain.delivery.Delivery;
import com.solvd.onlinemarkettc.domain.delivery.PickupAtPlace;
import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;
import com.solvd.onlinemarkettc.domain.finantialoperation.CheckOut;
import com.solvd.onlinemarkettc.domain.finantialoperation.OnlineShop;
import com.solvd.onlinemarkettc.domain.item.DiscountedItem;
import com.solvd.onlinemarkettc.domain.item.DiscountedItemDecorator;
import com.solvd.onlinemarkettc.domain.item.FoodProduct;
import com.solvd.onlinemarkettc.domain.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.domain.item.Service;
import com.solvd.onlinemarkettc.domain.item.factory.FactoryMaker;
import com.solvd.onlinemarkettc.domain.item.factory.FoodProductFactory;
import com.solvd.onlinemarkettc.domain.item.factory.NonPerishebleProductFactory;
import com.solvd.onlinemarkettc.domain.payment.DebitCard;
import com.solvd.onlinemarkettc.domain.payment.EventType;
import com.solvd.onlinemarkettc.domain.user.User;
import com.solvd.onlinemarkettc.service.BasketService;
import com.solvd.onlinemarkettc.service.DiscountedItemService;
import com.solvd.onlinemarkettc.service.FoodProductService;
import com.solvd.onlinemarkettc.service.NonPerishableProductService;
import com.solvd.onlinemarkettc.service.ServiceService;
import com.solvd.onlinemarkettc.service.ShopFacade;
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

        DebitCard GeneratedCard = new DebitCard(true, 1000.0);
        DebitCard customCard = new DebitCard(123456789L, true, 500.0);
        DebitCard.getEventHolder().notify(EventType.CARD_BLOCKED, "cardblocked");
        FoodProductFactory foodFactory = (FoodProductFactory) FactoryMaker.getFactory("food");
        NonPerishebleProductFactory nonFoodFactory = (NonPerishebleProductFactory) FactoryMaker.getFactory("nonfood");

        FoodProduct apple = foodFactory.create(0.5, "apple", null);
        FoodProduct banana = foodFactory.create(0.3, "banana", null);
        banana.setId(5L);

        NonPerishebleProduct soap = nonFoodFactory.create("soap", 6.2, "");
        NonPerishebleProduct shampoo = nonFoodFactory.create("shampoo", 3.5, "");
        shampoo.setId(2L);

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

        CheckOut.checkoutBasket(GeneratedCard, basket);

        //strategy
        PickupAtPlace adress2 = new PickupAtPlace();

        Delivery delivery = new Delivery(basket.getAddress(),basket);
        delivery.setAddressInterface(adress2);
        delivery.Deliver();

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

        Long id5 = foodProductService.createFoodProduct(apple);
        FoodProduct foodProduct = foodProductService.getFoodProductById(id5);
        log.info("food product from db by id name: {}", foodProduct.getName());

        FoodProduct foodProduct2 = foodProductService.getFoodProductByName("apple");
        log.info("food product from db by name, name: {}", foodProduct2.getName());

        foodProductService.getAllFoodProducts().forEach(food -> {
            log.info("food in list id {} and name {}", food.getId(), food.getName());
        });
        foodProductService.createFoodProduct(apple);
        foodProductService.deleteFoodProduct(3L);
        foodProductService.updateFoodProduct(banana);

        discountedItemService.getAllDiscountedItems();
        log.info("all discounted found");
        discountedItemService.createDiscountedItem(new DiscountedItem("some item", 10.5, "idk"));
        discountedItemService.createDiscountedItem(new DiscountedItem("some item", 10.5, "idk"));
        discountedItemService.deleteDiscountedItem(1L);
        discountedItemService.updateDiscountedItem(new DiscountedItem("NOTsome item", 1L, 10.5, "idk"));

        nonPerishableProductService.getAllNonPerishableProducts();
        log.info("found all non perish");
        Long id6 = nonPerishableProductService.createNonPerishableProduct(soap);
        nonPerishableProductService.getNonPerishableProductById(id6);
        nonPerishableProductService.deleteNonPerishableProduct(nonPerishableProductService.createNonPerishableProduct(soap));
        NonPerishebleProduct Notsoap = nonFoodFactory.create("soap", 1.2, "");

        nonPerishableProductService.createNonPerishableProduct(Notsoap);
        Notsoap.setName("NotSoap");
        Long id4 = nonPerishableProductService.createNonPerishableProduct(Notsoap);

        Service service = Service.builder().name("service")
                .cost(16.3)
                .description("idk")
                .serviceProvider("someguy")
                .build();

        var serviceid = serviceService.createService(service);
        serviceService.getAllServices();
        serviceService.getServiceById(serviceid);
        Service NOTservice = Service.builder().name("service")
                .cost(16.3)
                .description("idk")
                .serviceProvider("some guy")
                .build();

        DiscountedItem item = new DiscountedItem("dog Food", 1L, 20.0, "food");
        DiscountedItemDecorator discountedItemDecorator = new DiscountedItemDecorator(item);

        var newId = serviceService.createService(NOTservice);
        NOTservice.setName("NotService");
        NOTservice.setId(newId);
        serviceService.updateService(NOTservice);
        serviceService.deleteService(newId);

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

        ShopFacade shopFacade = new ShopFacade(foodProductService,userService,basketService);

        basketService.createBasketWithItems(basket3);
        log.info("created basket id {}", basket3.getId());

        Basket foundBasket = basketService.getBasketById(basket3.getId());
        log.info("found basket id {}", foundBasket.getId());

        basketService.getAllBaskets().forEach(b -> {
            log.info("basket in list id {} and cost {}", b.getId(), b.getSumCost());
        });

        basket.setId(basket3.getId());
        User user = new User("someUser", GeneratedCard, basket);
//        Long userId = userService.createUser(user);
//        User foundUser = userService.getUserById(userId);
//        userService.getAllUsers();
//        foundUser.setName("notSomeUser");
//        userService.deleteUser(userId);

        System.out.println("ended");
    }
}