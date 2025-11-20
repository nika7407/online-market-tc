package com.solvd.onlinemarkettc.domain.item.factory;

//abstract factory example
public class FactoryMaker   {

    public static Object getFactory(String choice) {
        Object factory = null;
        switch (choice) {
            case "food":
                factory = new FoodProductFactory();
                break;
            case "nonfood":
                factory = new NonPerishebleProductFactory();
                break;
            default:
                break;
        }
        return factory;
    }
}