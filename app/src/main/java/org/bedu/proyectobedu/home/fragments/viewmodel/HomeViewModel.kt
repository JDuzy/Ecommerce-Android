package org.bedu.proyectobedu.home.fragments.viewmodel

import androidx.lifecycle.ViewModel
import org.bedu.proyectobedu.home.model.Product
import org.bedu.proyectobedu.home.repositories.ProductRepository

class HomeViewModel: ViewModel() {

    fun findAllProducts(): List<Product> {
        return ProductRepository.findAll()
    }

}