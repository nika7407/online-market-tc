package com.solvd.onlinemarkettc.service.parser;

import com.solvd.onlinemarkettc.domain.finantialoperation.OnlineShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * xml parser for {@link OnlineShop}.
 * uses JAXB to read XML and validate it against schema.xsd.
 * logs all xml validation errors.
 * Example XPath queries
 * <ul>
 *     <li>/OnlineShop/foodProducts/ObjectAmount/FoodProduct/name</li>
 *     <li>/OnlineShop/nonFoodProduct/ObjectAmount/NonPerishebleProduct/name</li>
 *     <li>/OnlineShop/users/User/name</li>
 *     <li>/OnlineShop/users/User/debitCard/moneyAmount</li>
 *     <li>/OnlineShop/users/User/basket/foodProductList/FoodProduct</li>
 * </ul>
 */
public class Xparser {

    private static final Logger log = LogManager.getLogger(Xparser.class);

    public OnlineShop unmarshal(String path) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(OnlineShop.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("src/main/java/resources/schema.xsd"));
            unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(event -> {
                log.error("xml error: {}", event.getMessage());
                return true;
            });

        } catch (Exception e) {
            throw new IOException("Error loading XML schema: " + e.getMessage(), e);
        }
        return (OnlineShop) unmarshaller.unmarshal(new FileReader(path));
    }
}
