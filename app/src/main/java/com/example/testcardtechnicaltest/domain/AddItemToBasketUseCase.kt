package com.example.testcardtechnicaltest.domain

import com.example.testcardtechnicaltest.data.BasketRepository
import com.example.testcardtechnicaltest.model.Product

class AddItemToBasketUseCase(private val basketRepository: BasketRepository) {
    suspend operator fun invoke(product: Product) = basketRepository.addItem(product)
}