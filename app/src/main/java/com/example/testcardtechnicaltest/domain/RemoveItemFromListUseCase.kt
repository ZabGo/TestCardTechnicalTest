package com.example.testcardtechnicaltest.domain

import com.example.testcardtechnicaltest.data.BasketRepository
import com.example.testcardtechnicaltest.model.Product

class RemoveItemFromListUseCase(private val repository: BasketRepository) {
    suspend operator fun invoke(product: Product): List<Product> = repository.removeItem(product)
}