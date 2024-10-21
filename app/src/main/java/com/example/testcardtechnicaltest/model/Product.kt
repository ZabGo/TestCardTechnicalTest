package com.example.testcardtechnicaltest.model

sealed class Product(
    open val name: String,
    open val price: Int,
    open val quantity: Int,
){
    abstract fun copyWithUpdatedQuantity(updatedQuantity: Int): Product
}

data class Shoe(
    override val name: String,
    override val price: Int = 20,
    override val quantity: Int,
) : Product(name, price, quantity){

    override fun copyWithUpdatedQuantity(updatedQuantity: Int): Product =
        this.copy(quantity = updatedQuantity)

}

data class TShirt(
    override val name: String,
    override val price: Int = 10,
    override val quantity: Int,
) : Product(name, price, quantity) {
    override fun copyWithUpdatedQuantity(updatedQuantity: Int): Product =
        this.copy(quantity = updatedQuantity)
}

data class Trouser(
    override val name: String,
    override val price: Int = 50,
    override val quantity: Int,
) : Product(name, price, quantity) {
    override fun copyWithUpdatedQuantity(updatedQuantity: Int): Product =
        this.copy(quantity = updatedQuantity)
}

data class Sock(
    override val name: String,
    override val price: Int = 5,
    override val quantity: Int,
) : Product(name, price, quantity) {
    override fun copyWithUpdatedQuantity(updatedQuantity: Int): Product =
        this.copy(quantity = updatedQuantity)
}

data class Short(
    override val name: String,
    override val price: Int = 10,
    override val quantity: Int,
) : Product(name, price, quantity) {
    override fun copyWithUpdatedQuantity(updatedQuantity: Int): Product =
        this.copy(quantity = updatedQuantity)
}