package com.solvd.onlinemarkettc.domain.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;

public class PickupAtPlace implements AddressInterface {

    private String deliveryAddress;

    private Long id;

    private String userId;

    public PickupAtPlace() {
    }

    public PickupAtPlace(String deliveryAddress, Long id, String userId) {
        this.deliveryAddress = deliveryAddress;
        this.id = id;
        this.userId = userId;
    }

    public PickupAtPlace(String deliveryAdress, String userId) {
        this.deliveryAddress = deliveryAdress;
        this.userId = userId;
    }

    @Override
    public String getDeliveryAddress() {
        return "there is none pickup will be at the shop";
    }

    @Override
    public void setDeliveryAddress(String deliveryAddress) {
        return;
    }

    @Override
    public Long getId() {
        return 0L;
    }

    @Override
    public void setId(Long id) {

    }

    @Override
    public String getUserId() {
        return "";
    }

    @Override
    public void setUserId(String userId) {

    }
}
