package com.solvd.onlinemarkettc.service.impl;

import com.solvd.onlinemarkettc.domain.item.DiscountedItem;
import com.solvd.onlinemarkettc.persistence.mybatisimpl.DiscountedItemRepositoryMybatisImpl;
import com.solvd.onlinemarkettc.service.DiscountedItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DiscountedItemServiceImpl implements DiscountedItemService {

    private static final Logger log = LogManager.getLogger(DiscountedItemServiceImpl.class);
    //private final DiscountedItemRepositoryImpl discountedItemRepository;
    private final DiscountedItemRepositoryMybatisImpl discountedItemRepository;

    public DiscountedItemServiceImpl() {
        this.discountedItemRepository = new DiscountedItemRepositoryMybatisImpl();
    }

    @Override
    public Long createDiscountedItem(DiscountedItem item) {
        log.info("create item: {}", item.getName());
        discountedItemRepository.save(item);
        return item.getId();
    }

    @Override
    public DiscountedItem getDiscountedItemById(Long id) {
        log.debug("find item id: {}", id);
        return discountedItemRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public DiscountedItem getDiscountedItemByName(String name) {
        log.debug("find item name: {}", name);
        return discountedItemRepository.findByName(name).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<DiscountedItem> getAllDiscountedItems() {
        log.debug("get all items");
        return discountedItemRepository.findAll();
    }

    @Override
    public Long updateDiscountedItem(DiscountedItem item) {
        log.info("update item id: {}", item.getId());
        discountedItemRepository.update(item);
        return item.getId();
    }

    @Override
    public void deleteDiscountedItem(Long id) {
        log.info("delete item id: {}", id);
        discountedItemRepository.deleteById(id);
    }

    @Override
    public boolean discountedItemExists(Long id) {
        return discountedItemRepository.findById(id).isPresent();
    }

    @Override
    public boolean discountedItemExistsByName(String name) {
        return discountedItemRepository.findByName(name).isPresent();
    }
}