package com.example.service;

import com.example.dto.ShopSearchDto;
import com.example.model.Shop;
import com.example.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    /**
     * Create shop
     *
     * @param shop : shop
     *
     * @return Shop
     */
    @Transactional
    public Shop shopCreate(Shop shop) {
        return shopRepository.save(shop);
    }

    /**
     * Get shops list
     *
     * @param dto      : dto
     * @param pageable : pageable
     *
     * @return Page<Shop>
     */
    @Transactional
    public Page<Shop> getShops(ShopSearchDto dto, Pageable pageable) {
        return shopRepository.findAll(dto, pageable);
    }

}
