package com.example.testcardtechnicaltest.data

import com.example.testcardtechnicaltest.model.Basket
import com.example.testcardtechnicaltest.model.ComboDeal
import com.example.testcardtechnicaltest.model.DiscountOnBasket
import com.example.testcardtechnicaltest.model.DiscountOnProduct

interface DiscountHandler {
    fun applyDiscountOnBasket(basket: Basket, discount: DiscountOnBasket): Basket
    fun applyComboDeal(basket: Basket, discount: ComboDeal): Basket
    fun applyDiscountOnProduct(basket: Basket, discount: DiscountOnProduct): Basket
    fun applyDiscountBuy2GetOneFree(basket: Basket): Basket
}