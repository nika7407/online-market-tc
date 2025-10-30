package com.solvd.onlinemarkettc.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "address")
public class Address {


    @XmlElement(name = "deliveryAddress")
    @JsonProperty("deliveryAddress")
    private String deliveryAddress;

    @XmlElement(name = "deliveryAddressId")
    @JsonProperty("deliveryAddressId")
    private String deliveryAddressId;


    @XmlElement(name = "userId")
    @JsonProperty("userId")
    private String userId;

    public Address() {
    }

    public Address(String deliveryAdress, String userId) {
        this.deliveryAddress = deliveryAdress;
        this.userId = userId;
    }

    // старые методы (с опечаткой) оставлены для совместимости
    public String getDeliveryAdress() {
        return deliveryAddress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAddress = deliveryAdress;
    }

    // новые корректные методы, которые использует парсер
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
