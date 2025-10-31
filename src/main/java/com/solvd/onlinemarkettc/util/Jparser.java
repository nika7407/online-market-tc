package com.solvd.onlinemarkettc.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.onlinemarkettc.finantialoperation.OnlineShop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * json parser
 * json paths
 * <ul>
 *     <li>$.foodProducts[0].foodProduct.name</li>
 *     <li>$.nonFoodProducts[0].nonPerishebleProduct.cost</li>
 *     <li>$.users[0].name</li>
 *     <li>$.users[0].debitCard.moneyAmount</li>
 *     <li>$.users[0].basket.id</li>
 * </ul>
 */
public class Jparser {

    public OnlineShop parse(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // use same date format as XML parser and model annotations
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        // be tolerant to extra fields
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(new File(path), OnlineShop.class);
    }
}