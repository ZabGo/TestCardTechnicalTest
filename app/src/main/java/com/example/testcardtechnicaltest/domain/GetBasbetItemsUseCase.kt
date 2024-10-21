package com.example.testcardtechnicaltest.domain

import com.example.testcardtechnicaltest.data.BasketRepository
import com.example.testcardtechnicaltest.model.Basket

class GetBasketItemsUseCase(private val basketRepository: BasketRepository) {
    suspend operator fun invoke(): Basket = basketRepository.getBasketItems()

}