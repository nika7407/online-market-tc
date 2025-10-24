package com.solvd.onlinemarkettc.util;

import com.solvd.onlinemarkettc.delivery.Address;
import com.solvd.onlinemarkettc.finantialoperation.Basket;
import com.solvd.onlinemarkettc.finantialoperation.OnlineShop;
import com.solvd.onlinemarkettc.item.DiscountedItem;
import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.item.Service;
import com.solvd.onlinemarkettc.payment.DebitCard;
import com.solvd.onlinemarkettc.user.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Dparser {

    public static OnlineShop parser(Path xmlPath) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream inputStream = Files.newInputStream(xmlPath);
        Document document = builder.parse(inputStream);

        OnlineShop onlineShop = new OnlineShop();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        List<FoodProduct> foodProducts = new ArrayList<>();
        NodeList foodNodes = document.getElementsByTagName("FoodProduct");
        for (int i = 0; i < foodNodes.getLength(); i++) {
            Node foodNode = foodNodes.item(i);
            if (foodNode.getNodeType() == Node.ELEMENT_NODE) {
                Element foodElement = (Element) foodNode;

                FoodProduct foodProduct = new FoodProduct();
                foodProduct.setCost(Double.parseDouble(foodElement.getElementsByTagName("cost").item(0).getTextContent()));
                foodProduct.setExpirationDate(dateFormat.parse(foodElement.getElementsByTagName("expirationDate").item(0).getTextContent()));
                foodProduct.setName(foodElement.getElementsByTagName("name").item(0).getTextContent());
                foodProducts.add(foodProduct);
            }
        }
        onlineShop.setFoodProducts(foodProducts);

        List<NonPerishebleProduct> nonFoodProducts = new ArrayList<>();
        NodeList nonFoodNodes = document.getElementsByTagName("NonPerishebleProduct");
        for (int i = 0; i < nonFoodNodes.getLength(); i++) {
            Node nonFoodNode = nonFoodNodes.item(i);
            if (nonFoodNode.getNodeType() == Node.ELEMENT_NODE) {
                Element nonFoodElement = (Element) nonFoodNode;
                NonPerishebleProduct nonFoodProduct = new NonPerishebleProduct();

                nonFoodProduct.setName(nonFoodElement.getElementsByTagName("name").item(0).getTextContent());
                nonFoodProduct.setCost(Double.parseDouble(nonFoodElement.getElementsByTagName("cost").item(0).getTextContent()));
                nonFoodProduct.setDescription(nonFoodElement.getElementsByTagName("description").item(0).getTextContent());
                nonFoodProducts.add(nonFoodProduct);
            }
        }
        onlineShop.setNonFoodProduct(nonFoodProducts);

        List<DiscountedItem> discountedItems = new ArrayList<>();
        NodeList discountedNodes = document.getElementsByTagName("DiscountedItem");
        for (int i = 0; i < discountedNodes.getLength(); i++) {
            Node discountedNode = discountedNodes.item(i);
            if (discountedNode.getNodeType() == Node.ELEMENT_NODE) {
                Element discountedElement = (Element) discountedNode;
                DiscountedItem discountedItem = new DiscountedItem();

                discountedItem.setName(discountedElement.getElementsByTagName("name").item(0).getTextContent());
                discountedItem.setCost(Double.parseDouble(discountedElement.getElementsByTagName("cost").item(0).getTextContent()));
                discountedItem.setDescription(discountedElement.getElementsByTagName("description").item(0).getTextContent());
                discountedItems.add(discountedItem);
            }
        }
        onlineShop.setDiscountList(discountedItems);

        List<Service> services = new ArrayList<>();
        NodeList serviceNodes = document.getElementsByTagName("Service");
        for (int i = 0; i < serviceNodes.getLength(); i++) {
            Node serviceNode = serviceNodes.item(i);
            if (serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serviceElement = (Element) serviceNode;
                Service service = new Service();

                service.setName(serviceElement.getElementsByTagName("name").item(0).getTextContent());
                service.setCost(Double.parseDouble(serviceElement.getElementsByTagName("cost").item(0).getTextContent()));
                service.setDescription(serviceElement.getElementsByTagName("description").item(0).getTextContent());
                service.setServiceProvider(serviceElement.getElementsByTagName("serviceProvider").item(0).getTextContent());
                services.add(service);
            }
        }
        onlineShop.setServiceList(services);

        List<User> users = new ArrayList<>();
        NodeList userNodes = document.getElementsByTagName("User");
        for (int i = 0; i < userNodes.getLength(); i++) {
            Node userNode = userNodes.item(i);
            if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                Element userElement = (Element) userNode;
                User user = new User();
                user.setName(userElement.getElementsByTagName("name").item(0).getTextContent());
                user.setUserId(userElement.getElementsByTagName("userId").item(0).getTextContent());

                Element debitCardElement = (Element) userElement.getElementsByTagName("debitCard").item(0);
                DebitCard debitCard = new DebitCard();

                debitCard.setCardNumber(debitCardElement.getElementsByTagName("cardNumber").item(0).getTextContent());
                debitCard.setActive(Boolean.parseBoolean(debitCardElement.getElementsByTagName("active").item(0).getTextContent()));
                debitCard.setMoneyAmount(Double.parseDouble(debitCardElement.getElementsByTagName("moneyAmount").item(0).getTextContent()));
                user.setDebitCard(debitCard);

                Element basketElement = (Element) userElement.getElementsByTagName("basket").item(0);
                Basket basket = new Basket();

                basket.setBasketId(basketElement.getElementsByTagName("basketId").item(0).getTextContent());
                basket.setDate(dateFormat.parse(basketElement.getElementsByTagName("date").item(0).getTextContent()));

                Element addressElement = (Element) basketElement.getElementsByTagName("address").item(0);
                Address address = new Address();

                address.setDeliveryAdress(addressElement.getElementsByTagName("deliveryAdress").item(0).getTextContent());
                address.setUserId(addressElement.getElementsByTagName("userId").item(0).getTextContent());
                basket.setAddress(address);

                List<FoodProduct> basketFoodProducts = new ArrayList<>();
                NodeList basketFoodNodes = basketElement.getElementsByTagName("FoodProduct");
                for (int j = 0; j < basketFoodNodes.getLength(); j++) {
                    Element basketFoodElement = (Element) basketFoodNodes.item(j);
                    FoodProduct foodProduct = new FoodProduct();
                    foodProduct.setCost(Double.parseDouble(basketFoodElement.getElementsByTagName("cost").item(0).getTextContent()));
                    foodProduct.setExpirationDate(dateFormat.parse(basketFoodElement.getElementsByTagName("expirationDate").item(0).getTextContent()));
                    foodProduct.setName(basketFoodElement.getElementsByTagName("name").item(0).getTextContent());
                    basketFoodProducts.add(foodProduct);
                }
                basket.setFoodProductList(basketFoodProducts);

                List<NonPerishebleProduct> basketNonFoodProducts = new ArrayList<>();
                NodeList basketNonFoodNodes = basketElement.getElementsByTagName("NonPerishebleProduct");
                for (int j = 0; j < basketNonFoodNodes.getLength(); j++) {
                    Element basketNonFoodElement = (Element) basketNonFoodNodes.item(j);
                    NonPerishebleProduct nonFoodProduct = new NonPerishebleProduct();
                    nonFoodProduct.setName(basketNonFoodElement.getElementsByTagName("name").item(0).getTextContent());
                    nonFoodProduct.setCost(Double.parseDouble(basketNonFoodElement.getElementsByTagName("cost").item(0).getTextContent()));
                    nonFoodProduct.setDescription(basketNonFoodElement.getElementsByTagName("description").item(0).getTextContent());
                    basketNonFoodProducts.add(nonFoodProduct);
                }
                basket.setNonPerishebleProductList(basketNonFoodProducts);

                user.setBasket(basket);
                users.add(user);
            }
        }
        onlineShop.setUsers(users);

        return onlineShop;
    }
}