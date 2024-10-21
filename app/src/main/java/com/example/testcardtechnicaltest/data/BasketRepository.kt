package com.example.testcardtechnicaltest.data

import com.example.testcardtechnicaltest.model.Basket
import com.example.testcardtechnicaltest.model.Product

interface BasketRepository {
    suspend fun getBasketItems(): Basket
    suspend fun addItem(product: Product)
    suspend fun removeItem(product: Product): List<Product>
    suspend fun checkoutBasket(basket: Basket): Basket
}