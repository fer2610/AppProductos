package com.ejercicios.appproductos.Model

import java.time.LocalDate

data class Producto(
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val fecha: LocalDate
)