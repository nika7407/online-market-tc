package com.solvd.onlinemarkettc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.onlinemarkettc.finantialoperation.OnlineShop;

import java.io.File;
import java.io.IOException;

/**
 * json parser
 * json paths
 * <ul>
 *     <li>$.foodProducts[0].foodProduct.name</li>
 *     <li>$.nonFoodProducts[0].nonPerishebleProduct.cost</li>
 *     <li>$.users[0].name</li>
 *     <li>$.users[0].debitCard.moneyAmount</li>
 *     <li>$.users[0].basket.basketId</li>
 * </ul>
 */
public class Jparser {

    public OnlineShop parse(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), OnlineShop.class);
    }
}