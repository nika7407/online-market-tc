package com.solvd.onlinemarkettc.domain.item;

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

    private Service(Builder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.cost = builder.cost;
        this.description = builder.description;
        this.serviceProvider = builder.serviceProvider;
    }

    public Service(String name, double cost, String description, String serviceProvider) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.serviceProvider = serviceProvider;
    }

    public static class Builder {
        private String name;
        private Long id;
        private double cost;
        private String description = "";
        private String serviceProvider;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder cost(double cost) {
            this.cost = cost;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder serviceProvider(String serviceProvider) {
            this.serviceProvider = serviceProvider;
            return this;
        }

        public Service build() {
            return new Service(this);
        }
    }

    public static Builder builder() {
        return new Builder();
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