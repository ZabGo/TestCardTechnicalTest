package com.example.testcardtechnicaltest.model


data class Basket(
    val products: List<Product>,
    var amount: Int
){
    fun calculateTotalAmoutWithoutDiscount(){
        products.forEach {
            amount += it.price * it.quantity
        }
        println("Total amount of the basket without any discount: $amount")
    }
}