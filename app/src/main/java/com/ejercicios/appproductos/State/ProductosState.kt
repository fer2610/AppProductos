package com.ejercicios.appproductos.State

import com.ejercicios.appproductos.Model.Producto

data class ProductoState(
    val productos: List<Producto> = listOf(),
    val estaCargando: Boolean = true
)