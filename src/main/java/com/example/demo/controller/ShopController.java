package com.example.demo.controller;

import com.example.demo.entity.Shop;
import com.example.demo.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

    @Autowired
    ShopMapper shopMapper;

    @GetMapping("/getShop/" )
    public Shop getOne(@RequestParam(value = "uid", required = true) Long uid){
        Shop shop = shopMapper.getOne(uid);
        return shop;
    }
}
