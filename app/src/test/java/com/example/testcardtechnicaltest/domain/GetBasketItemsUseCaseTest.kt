package com.example.testcardtechnicaltest.domain

import com.example.testcardtechnicaltest.UnitTestParentClass
import com.example.testcardtechnicaltest.data.BasketRepository
import com.example.testcardtechnicaltest.model.Basket
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Test
import kotlinx.coroutines.test.runTest

class GetBasketItemsUseCaseTest: UnitTestParentClass() {

    @RelaxedMockK
    private lateinit var repository: BasketRepository

    @InjectMockKs
    private lateinit var useCase: GetBasketItemsUseCase

    override fun setUp() {
        super.setUp()

    }

    @Test
    fun `should get all the items of the basket`() {
//        coEvery { repository.getBasketItems() } returns
    }

    @Test
    fun `should get an empty basket`() = runTest {
        coEvery { repository.getBasketItems() } returns Basket(products = listOf(), amount = 0)

        useCase()

        coEvery { repository.getBasketItems() }
    }
}