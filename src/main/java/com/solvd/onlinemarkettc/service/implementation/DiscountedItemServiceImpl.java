package com.solvd.onlinemarkettc.service.implementation;

import com.solvd.onlinemarkettc.domain.item.DiscountedItem;
import com.solvd.onlinemarkettc.persistence.repository.DiscountedItemRepository;
import com.solvd.onlinemarkettc.service.interfaces.DiscountedItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class DiscountedItemServiceImpl implements DiscountedItemService {

    private static final Logger log = LogManager.getLogger(DiscountedItemServiceImpl.class);
    private final DiscountedItemRepository discountedItemRepository;

    public DiscountedItemServiceImpl() {
        this.discountedItemRepository = new DiscountedItemRepository();
    }

    public DiscountedItem createDiscountedItem(DiscountedItem item) {
        log.info("create item: {}", item.getName());
        return discountedItemRepository.save(item);
    }

    public Optional<DiscountedItem> getDiscountedItemById(Long id) {
        log.debug("find item id: {}", id);
        return discountedItemRepository.findById(id);
    }

    public Optional<DiscountedItem> getDiscountedItemByName(String name) {
        log.debug("find item name: {}", name);
        return discountedItemRepository.findByName(name);
    }

    public List<DiscountedItem> getAllDiscountedItems() {
        log.debug("get all items");
        return discountedItemRepository.findAll();
    }

    public DiscountedItem updateDiscountedItem(DiscountedItem item) {
        log.info("update item id: {}", item.getId());
        return discountedItemRepository.update(item);
    }

    public void deleteDiscountedItem(Long id) {
        log.info("delete item id: {}", id);
        discountedItemRepository.deleteById(id);
    }

    public boolean discountedItemExists(Long id) {
        return discountedItemRepository.findById(id).isPresent();
    }

    public boolean discountedItemExistsByName(String name) {
        return discountedItemRepository.findByName(name).isPresent();
    }
}