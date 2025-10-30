package com.solvd.onlinemarkettc.finantialoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.onlinemarkettc.delivery.Address;
import com.solvd.onlinemarkettc.item.DiscountedItem;
import com.solvd.onlinemarkettc.item.FoodProduct;
import com.solvd.onlinemarkettc.item.NonPerishebleProduct;
import com.solvd.onlinemarkettc.item.Service;
import com.solvd.onlinemarkettc.util.Generator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Basket {

    @XmlElement(name = "basketId")
    @JsonProperty("basketId")
    private String basketId;

    @XmlElementWrapper(name = "foodProductList")
    @XmlElement(name = "FoodProduct")
    @JsonProperty("foodProductList")
    private List<FoodProduct> foodProductList = new ArrayList<>();

    @XmlElementWrapper(name = "nonPerishebleProductList")
    @XmlElement(name = "NonPerishebleProduct")
    @JsonProperty("nonPerishebleProductList")
    private List<NonPerishebleProduct> nonPerishebleProductList = new ArrayList<>();

    @XmlElementWrapper(name = "discountedItemList")
    @XmlElement(name = "DiscountedItem")
    @JsonProperty("discountedItemList")
    private List<DiscountedItem> discountedItemList = new ArrayList<>();

    @XmlElementWrapper(name = "serviceList")
    @XmlElement(name = "Service")
    @JsonProperty("serviceList")
    private List<Service> serviceList = new ArrayList<>();

    @XmlElement(name = "sumCost")
    @JsonProperty("sumCost")
    private double sumCost = 0.0;

    @XmlElement(name = "date")
    @JsonProperty("date")
    private Date date;

    @XmlElement(name = "address")
    @JsonProperty("address")
    private Address address;


    public Basket() {
        this.basketId = Generator.numberGenerator();
        this.date = new Date();
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public void setFoodProductList(List<FoodProduct> foodProductList) {
        this.foodProductList = foodProductList;
    }

    public void setNonPerishebleProductList(List<NonPerishebleProduct> nonPerishebleProductList) {
        this.nonPerishebleProductList = nonPerishebleProductList;
    }

    public List<FoodProduct> getFoodProductList() {
        return foodProductList;
    }

    public void addFoodProduct(FoodProduct foodProduct) {
        foodProductList.add(foodProduct);
        calculateCost();
    }

    public void addProduct(NonPerishebleProduct product) {
        nonPerishebleProductList.add(product);
        calculateCost();
    }

    public List<NonPerishebleProduct> getNonPerishebleProductList() {
        return nonPerishebleProductList;
    }

    public double getSumCost() {
        return sumCost;
    }

    public void setSumCost(double sumCost) {
        this.sumCost = sumCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address adress) {
        this.address = adress;
    }

    public List<DiscountedItem> getDiscountedItemList() {
        return discountedItemList;
    }

    public void setDiscountedItemList(List<DiscountedItem> discountedItemList) {
        this.discountedItemList = discountedItemList;
    }

    public void addDiscountedItem(DiscountedItem item) {
        this.discountedItemList.add(item);
        calculateCost();
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public void addService(Service service) {
        this.serviceList.add(service);
        calculateCost();
    }

    private void calculateCost() {

        double totalCostOfFood = foodProductList.stream()
                .mapToDouble(FoodProduct::getCost)
                .sum();

        double totalCostOfProduct = nonPerishebleProductList.stream()
                .mapToDouble(NonPerishebleProduct::getCost)
                .sum();

        double totalCostOfDiscounted = discountedItemList.stream()
                .mapToDouble(DiscountedItem::getCost)
                .sum();

        double totalCostOfServices = serviceList.stream()
                .mapToDouble(Service::getCost)
                .sum();

        sumCost = totalCostOfFood + totalCostOfProduct + totalCostOfDiscounted + totalCostOfServices;
    }

    public String getId() {
        return basketId;
    }
}
