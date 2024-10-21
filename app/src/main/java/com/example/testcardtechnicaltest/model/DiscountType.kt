package com.example.testcardtechnicaltest.model

import kotlin.reflect.KClass

sealed class DiscountType

data class ComboDeal(val categories: List<KClass<out Product>>, val price: Int = 0,): DiscountType()
data class DiscountOnProduct(val productType: KClass<out Product>, val percentage: Double): DiscountType()
data object Buy2Get1Free: DiscountType()
data class DiscountOnBasket(val percentage: Double): DiscountType()