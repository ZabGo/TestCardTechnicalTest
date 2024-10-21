package com.example.testcardtechnicaltest.data

import com.example.testcardtechnicaltest.model.Basket
import com.example.testcardtechnicaltest.model.ComboDeal
import com.example.testcardtechnicaltest.model.DiscountOnBasket
import com.example.testcardtechnicaltest.model.DiscountOnProduct
import com.example.testcardtechnicaltest.model.Product
import com.example.testcardtechnicaltest.model.Shoe
import com.example.testcardtechnicaltest.model.Short
import com.example.testcardtechnicaltest.model.Sock
import com.example.testcardtechnicaltest.model.TShirt
import com.example.testcardtechnicaltest.model.Trouser

class DiscountHandlerImpl : DiscountHandler {

    override fun applyDiscountOnBasket(basket: Basket, discount: DiscountOnBasket): Basket {
        var totalDiscount = 0
        totalDiscount = (basket.amount * discount.percentage).toInt()

        return basket.copy(
            amount = basket.amount - totalDiscount
        )
    }

    override fun applyDiscountOnProduct(basket: Basket, discount: DiscountOnProduct): Basket {
        var totalDiscount = 0

        val updatedProducts = basket.products.map {
            if (it.quantity != 0 && discount.productType == it::class) {

                totalDiscount = ((it.price * discount.percentage) * it.quantity).toInt()
                it.copyWithUpdatedQuantity(it.quantity - 1)
            } else {
                it
            }
        }

        return basket.copy(products = updatedProducts, amount = basket.amount - totalDiscount)
    }

    override fun applyComboDeal(basket: Basket, discount: ComboDeal): Basket {
        var productsFullPrice = 0
        val numberOfComboDeal =
            getNumberOfComboDealAvailableInBasket(comboDeal = discount, products = basket.products)

        val updatedProducts = basket.products.map {
            if (discount.categories.contains(it::class)) {

                productsFullPrice += it.price

                it.copyWithUpdatedQuantity(updatedQuantity = it.quantity - 1)
            } else {
                it
            }
        }

        val totalDiscount = (productsFullPrice - discount.price) * numberOfComboDeal
        return basket.copy(products = updatedProducts, amount = basket.amount - totalDiscount)
    }

    override fun applyDiscountBuy2GetOneFree(basket: Basket): Basket {
        var totalDiscount = 0
        val updatedProduct = basket.products.map { product ->

            totalDiscount += getAmountSavedFromDiscount(
                quantity = product.quantity,
                price = product.price
            )

            product.copyWithUpdatedQuantity(getTheNumberOfProductAvailableForNewDiscount(
                product.quantity
            ))
        }
        return basket.copy(products = updatedProduct, amount = basket.amount - totalDiscount)
    }

    private fun getTheNumberOfProductAvailableForNewDiscount(numberOfProduct: Int) =
        numberOfProduct - (numberOfProduct - (numberOfProduct % 3))

    private fun getAmountSavedFromDiscount(quantity: Int, price: Int) = (quantity / 3) * price

    private fun getNumberOfComboDealAvailableInBasket(
        comboDeal: ComboDeal,
        products: List<Product>
    ): Int {
        val combo = comboDeal.categories.mapNotNull { comboItem ->
            products.find { product -> comboItem == product::class }
        }

        return if (combo.size == comboDeal.categories.size) {
            combo.minByOrNull { it.quantity }?.quantity ?: 0
        } else 0
    }
}