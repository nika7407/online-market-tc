package com.solvd.onlinemarkettc.domain.item;

import com.solvd.onlinemarkettc.service.impl.ServiceServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiscountedItemDecorator extends DiscountedItem {

    private static final Logger log = LogManager.getLogger(DiscountedItemDecorator.class);
    private final DiscountedItem discountedItem;

    public DiscountedItemDecorator(DiscountedItem discountedItem) {
        this.discountedItem = discountedItem;
    }

    @Override
    public String getName() {
        return discountedItem.getName();
    }

    @Override
    public void setName(String name) {
        discountedItem.setName(name);
    }

    @Override
    public double getCost() {
        return discountedItem.getCost();
    }

    @Override
    public void setCost(double cost) {
        discountedItem.setCost(cost);
    }

    @Override
    public String getDescription() {
        return discountedItem.getDescription();
    }

    @Override
    public void setDescription(String description) {
        discountedItem.setDescription(description);
    }

    @Override
    public Long getId() {
        return discountedItem.getId();
    }

    @Override
    public void setId(Long id) {
        discountedItem.setId(id);
    }

    private void isItemDiscounted() {
        log.info("item: {} is discounted! quick buy it ", discountedItem.getName());
    }
}