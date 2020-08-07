package com.piggybank.piggybank.controllers;


import com.piggybank.piggybank.models.Coin;
import com.piggybank.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {

    @Autowired
    CoinRepository coinrepos;

    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity <?> listAllCoins() {
        List<Coin> allCoins = new ArrayList<>();
        coinrepos.findAll().iterator().forEachRemaining(allCoins::add);
        for (Coin c : allCoins) {
            if (c.getQuantity() > 1) {
                System.out.println(c.getQuantity() + " " + c.getNameplural());
            } else {
                System.out.println(c.getQuantity() + " " +  c.getName());
            }
        }
        double totalCoins = 0;
        for (Coin c : allCoins) {
            totalCoins += (c.getValue() * c.getQuantity());
        }
        System.out.println("The piggy bank holds " + totalCoins);
        return new ResponseEntity<>("Status OK", HttpStatus.OK);
    }

    @GetMapping(value = "/money/{amount}", produces={"application/json"})
    public ResponseEntity<?> removeMoney (@PathVariable double amount) {
        List<Coin> coinList = new ArrayList<>();
        coinrepos.findAll().iterator().forEachRemaining(coinList::add);
        coinList.sort((c1, c2) -> Double.compare( c2.getValue(), c1.getValue()));
        double totalCoins = 0;
        for (Coin c : coinList) {
            totalCoins += (c.getValue() * c.getQuantity());
        }

        if (amount > totalCoins) {
            System.out.println("Money not available");
        }

        double tempAmount;

        for (Coin c : coinList) {
            tempAmount = amount;
            if (tempAmount > (c.getValue() * c.getQuantity())) {

            }
        }


        System.out.println( "The piggy bank holds $" + totalCoins);
        return new ResponseEntity<>("Status OK", HttpStatus.OK);
    }
}
