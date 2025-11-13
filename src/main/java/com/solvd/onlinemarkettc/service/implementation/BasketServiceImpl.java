package com.solvd.onlinemarkettc.service.implementation;

import com.solvd.onlinemarkettc.domain.finantialoperation.Basket;
import com.solvd.onlinemarkettc.persistence.repository.BasketRepository;
import com.solvd.onlinemarkettc.service.interfaces.BasketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BasketServiceImpl implements BasketService {

    private static final Logger log = LogManager.getLogger(BasketServiceImpl.class);
    private final BasketRepository basketRepository;

    public BasketServiceImpl() {
        this.basketRepository = new BasketRepository();
    }

    public Basket createBasketWithItems(Basket basket) {
        log.info("create basket items");
        return basketRepository.saveWithItems(basket);
    }

    public Optional<Basket> getBasketById(Long id) {
        log.debug("find basket id: {}", id);
        return basketRepository.findById(id);
    }

    public Basket getBasketWithAllItems(Long basketId) {
        log.debug("find basket items: {}", basketId);
        return basketRepository.findBasketWithAllItems(basketId);
    }

    public List<Basket> getAllBaskets() {
        log.debug("get all baskets");
        return basketRepository.findAll();
    }

    public Basket updateBasket(Basket basket) {
        log.info("update basket id: {}", basket.getId());
        return basketRepository.update(basket);
    }

    public void deleteBasket(Long id) {
        log.info("delete basket id: {}", id);
        basketRepository.deleteById(id);
    }

    public boolean basketExists(Long id) {
        return basketRepository.findById(id).isPresent();
    }
}