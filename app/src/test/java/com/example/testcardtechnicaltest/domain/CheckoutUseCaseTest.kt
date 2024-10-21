package com.example.testcardtechnicaltest.domain

import com.example.testcardtechnicaltest.UnitTestParentClass
import com.example.testcardtechnicaltest.data.BasketRepository
import com.example.testcardtechnicaltest.data.DiscountHandler
import com.example.testcardtechnicaltest.data.DiscountHandlerImpl
import com.example.testcardtechnicaltest.data.products
import com.example.testcardtechnicaltest.model.Basket
import com.example.testcardtechnicaltest.model.Buy2Get1Free
import com.example.testcardtechnicaltest.model.ComboDeal
import com.example.testcardtechnicaltest.model.DiscountOnProduct
import com.example.testcardtechnicaltest.model.Shoe
import com.example.testcardtechnicaltest.model.Short
import com.example.testcardtechnicaltest.model.TShirt
import com.example.testcardtechnicaltest.model.Trouser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CheckoutUseCaseTest : UnitTestParentClass() {

    @RelaxedMockK
    private lateinit var repository: BasketRepository

    @RelaxedMockK
    private lateinit var discountHandler: DiscountHandler

    @InjectMockKs
    private lateinit var useCase: CheckoutUseCase

    private lateinit var basket: Basket

    @Before
    override fun setUp() {
        super.setUp()
        basket = Basket(products = products, amount = 0)
        basket.calculateTotalAmoutWithoutDiscount()
    }

    @Test
    fun `should apply combo deal discount`() = runTest {
        val discounts = listOf(
            ComboDeal(
                categories = listOf(
                    Short::class,
                    Trouser::class,
                    TShirt::class
                ), price = 80
            ), Buy2Get1Free, DiscountOnProduct(productType = Shoe::class, percentage = 0.3)
        )
        coEvery { repository.getBasketItems() } returns Basket(
            products = listOf(),
            amount = 0
        )

        coEvery {
            discountHandler.applyComboDeal(
                basket = any(), any()
            )
        } returns Basket(products = listOf(), amount = 0)

        coEvery { discountHandler.applyDiscountBuy2GetOneFree(basket = any()) } returns Basket(
            products = listOf(),
            amount = 0
        )

        coEvery {
            discountHandler.applyDiscountOnProduct(
                basket = any(), discount = any()
            )
        } returns Basket(products = listOf(), amount = 0)

        useCase(discounts = discounts)

        coVerifySequence {
            discountHandler.applyComboDeal(
                basket = any(),
                discount = any()
            )
            discountHandler.applyDiscountBuy2GetOneFree(basket = any())
            discountHandler.applyDiscountOnProduct(
                basket = any(),
                discount = any()
            )
        }
    }
}