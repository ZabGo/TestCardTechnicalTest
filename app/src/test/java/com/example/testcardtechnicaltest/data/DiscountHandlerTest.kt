package com.example.testcardtechnicaltest.data

import com.example.testcardtechnicaltest.UnitTestParentClass
import com.example.testcardtechnicaltest.model.Basket
import com.example.testcardtechnicaltest.model.ComboDeal
import com.example.testcardtechnicaltest.model.DiscountOnBasket
import com.example.testcardtechnicaltest.model.DiscountOnProduct
import com.example.testcardtechnicaltest.model.Shoe
import com.example.testcardtechnicaltest.model.Short
import com.example.testcardtechnicaltest.model.Sock
import com.example.testcardtechnicaltest.model.TShirt
import com.example.testcardtechnicaltest.model.Trouser
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DiscountHandlerTest : UnitTestParentClass() {

    private lateinit var discountHandler: DiscountHandler
    private lateinit var basket: Basket

    @Before
    override fun setUp() {
        super.setUp()
        discountHandler = DiscountHandlerImpl()
        basket = Basket(products = products, amount = 0)
        basket.calculateTotalAmoutWithoutDiscount()
    }

    @Test
    fun `should apply discount ot the basket`() {
        val discount = 0.2
        val expectedDiscountedBasketAmount = basket.amount - (basket.amount * discount).toInt()
        val expectedBasket = discountHandler.applyDiscountOnBasket(basket = basket, discount = DiscountOnBasket(percentage = discount))
        assertEquals(expectedDiscountedBasketAmount, expectedBasket.amount)
    }

    @Test
    fun `should apply discount on a product only`() {
        val percentage = 0.2
        val discountOnShoes = ((percentage * shoe.price) * shoe.quantity).toInt()
        val expectedDiscountedBasketAmount = basket.amount - discountOnShoes
        val expectedBasket = discountHandler.applyDiscountOnProduct(
            basket = basket,
            discount = DiscountOnProduct(productType = Shoe::class, percentage = percentage)
        )
        assertEquals(expectedDiscountedBasketAmount, expectedBasket.amount)
    }

    @Test
    fun `should apply promotion buy 2 get 2 free`() {
        val expectedDiscountedBasketAmount = basket.amount - setupBuy2Get1FreeDiscount()
        val expectedBasket = discountHandler.applyDiscountBuy2GetOneFree(basket)
        assertEquals(expectedDiscountedBasketAmount, expectedBasket.amount)
    }

    @Test
    fun `should apply combo deal discount`() {
        val expectedDiscountedBasketAmount = basket.amount - setupComboDealDiscount()
        val expectedBasket = discountHandler.applyComboDeal(
            basket,
            ComboDeal(categories = listOf(Short::class, Trouser::class, TShirt::class), price = 80)
        )
        assertEquals(expectedDiscountedBasketAmount, expectedBasket.amount)
    }
}

fun setupBuy2Get1FreeDiscount(): Int {
    val test =
        ((sock.quantity / 3) * sock.price) + ((shoe.quantity / 3) * shoe.price) + ((tShirt.quantity / 3) * tShirt.price) + ((trouser.quantity / 3) * trouser.price) + ((short.quantity / 3) * short.price)
    println("test: $test")
    return test
}

fun setupComboDealDiscount(): Int {
    val unDiscountPrice = 100
    val numberOfDeal = 2
    val comboDealPrice = 80
    return (unDiscountPrice - comboDealPrice) * numberOfDeal
}

val sock = Sock(name = "Cotton Sock", price = 10, quantity = 10) //100
val shoe = Shoe(name = "Running Shoe", price = 10, quantity = 5) // 50
val tShirt = TShirt(name = "Graphic T-Shirt", price = 20, quantity = 3) // 60
val trouser = Trouser(name = "Slim Fit Trouser", price = 50, quantity = 2) // 100
val short = Short(name = "Denim Short", price = 30, quantity = 2)// 60

val products = listOf(sock, shoe, tShirt, trouser, short)