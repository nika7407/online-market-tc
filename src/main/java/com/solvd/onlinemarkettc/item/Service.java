package com.solvd.onlinemarkettc.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlRootElement(name = "Service")
public class Service {

    @XmlElement(name = "name")
    @JsonProperty("name")
    private String name;

    @XmlElement(name = "id")
    @JsonProperty("id")
    private Long id;

    @XmlElement(name = "cost")
    @JsonProperty("cost")
    private double cost;

    @XmlElement(name = "description")
    @JsonProperty("description")
    private String description = "";

    @XmlElement(name = "serviceProvider")
    @JsonProperty("serviceProvider")
    private String serviceProvider;

    public Service() {
    }

    public Service(String name, double cost, String description, String serviceProvider) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.serviceProvider = serviceProvider;
    }

    public Service(String name, Long id, double cost, String description, String serviceProvider) {
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.description = description;
        this.serviceProvider = serviceProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
