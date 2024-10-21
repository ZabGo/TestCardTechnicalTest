package com.example.testcardtechnicaltest.domain

import com.example.testcardtechnicaltest.UnitTestParentClass
import com.example.testcardtechnicaltest.data.BasketRepository
import com.example.testcardtechnicaltest.model.Shoe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoveItemFromListUseCaseTest: UnitTestParentClass() {


    @RelaxedMockK
    private lateinit var repository: BasketRepository

    @InjectMockKs
    private lateinit var useCase: RemoveItemFromListUseCase

    @Test
    fun `should remove an item form basket`() = runTest{
        val shoe =  Shoe(name = "", quantity = 10, price = 0)
        coEvery { repository.removeItem(product = shoe) } returns listOf()
        useCase(shoe)

        coVerify { repository.removeItem(shoe) }
    }
}