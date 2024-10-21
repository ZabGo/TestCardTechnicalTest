package com.example.testcardtechnicaltest.domain

import com.example.testcardtechnicaltest.UnitTestParentClass
import com.example.testcardtechnicaltest.data.BasketRepository
import com.example.testcardtechnicaltest.model.Basket
import com.example.testcardtechnicaltest.model.Shoe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Test


class AddItemToBasketUseCaseTest: UnitTestParentClass() {

    @RelaxedMockK
    private lateinit var repository: BasketRepository

    @InjectMockKs
    private lateinit var useCase: AddItemToBasketUseCase

    @Test
    fun `should add an item to the basket`() = runTest {
        val shoe =  Shoe(name = "", quantity = 10, price = 0)
        coEvery {repository.getBasketItems()} returns Basket(products = listOf(), amount = 0)

        useCase(shoe)
        coVerify{repository.addItem(shoe)}
    }
}