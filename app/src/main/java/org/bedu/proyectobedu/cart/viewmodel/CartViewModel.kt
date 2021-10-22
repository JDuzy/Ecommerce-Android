package org.bedu.proyectobedu.cart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.bedu.proyectobedu.cart.model.CartItem
import org.bedu.proyectobedu.cart.repository.CartItemRepository
import org.bedu.proyectobedu.cart.repository.CartItemRepository.Companion.insertCartItem
import org.bedu.proyectobedu.home.model.Product

class CartViewModel: ViewModel() {

    val cartItems = MutableLiveData<MutableList<CartItem>>()

    init {
        postAll()
    }

    fun addCartItem(product: Product){
        val foundItem = CartItemRepository.findCartItemByProduct(product)
        if (foundItem != null){
            CartItemRepository.updateAmount(foundItem, foundItem.amount + 1)
        }
        else{
            cartItems.value?.add(insertCartItem(product, 1))
        }
        postAll()
    }

    fun removeCartItem(product: Product){
        val foundItem = CartItemRepository.findCartItemByProduct(product)

        foundItem?.let {
            if (foundItem.amount == 1)
                CartItemRepository.remove(foundItem)
            else
                CartItemRepository.updateAmount(foundItem, foundItem.amount - 1)
            postAll()
        }
    }


    private fun postAll() {
        val list: MutableList<CartItem> = CartItemRepository.findAll().toMutableList()
        cartItems.postValue(list)
    }

    fun findAll(): MutableList<CartItem> {
        return CartItemRepository.findAll().toMutableList()
    }

    fun deleteAll() {
        CartItemRepository.deleteAll()
    }

    fun isCartEmpty(): Boolean{
        return cartItems.value?.let { it.size == 0 } ?: true
    }



}
