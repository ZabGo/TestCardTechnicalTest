package com.example.testcardtechnicaltest.domain

import com.example.testcardtechnicaltest.data.BasketRepository
import com.example.testcardtechnicaltest.data.DiscountHandler
import com.example.testcardtechnicaltest.model.Buy2Get1Free
import com.example.testcardtechnicaltest.model.ComboDeal
import com.example.testcardtechnicaltest.model.DiscountOnBasket
import com.example.testcardtechnicaltest.model.DiscountOnProduct
import com.example.testcardtechnicaltest.model.DiscountType

class CheckoutUseCase(private val basketRepository: BasketRepository, private val discountHandler: DiscountHandler) {
    suspend operator fun invoke(discounts: List<DiscountType>) {
        var tempBasket = basketRepository.getBasketItems()

        discounts.forEach {
            tempBasket = when(it){
                is ComboDeal -> discountHandler.applyComboDeal(basket = tempBasket, discount = it)
                is Buy2Get1Free -> discountHandler.applyDiscountBuy2GetOneFree(basket = tempBasket)
                is DiscountOnBasket -> discountHandler.applyDiscountOnBasket(basket = tempBasket, it)
                is DiscountOnProduct -> discountHandler.applyDiscountOnProduct(basket = tempBasket, discount = it)
            }
        }

        basketRepository.checkoutBasket(tempBasket)
    }
}