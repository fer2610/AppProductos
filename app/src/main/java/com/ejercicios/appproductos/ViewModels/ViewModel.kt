package com.ejercicios.appproductos.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.ejercicios.appproductos.Model.Producto
import com.ejercicios.appproductos.State.ProductoState
import java.time.LocalDate

class ViewModel : ViewModel() {

    var productos: MutableList<Producto> = mutableStateListOf()
    var estado by mutableStateOf(ProductoState())
        private set

    init {
        viewModelScope.launch {
            // Espera 2 segundos.
            delay(2000)

            // Cargar los datos.
            estado = estado.copy(
                productos = listOf(
                    Producto("Taza de ceramica", "Taza artesanal", 50.0, LocalDate.now()),
                    Producto("Planta artificial", "Planta tipo arbusto", 70.0, LocalDate.now()),
                    Producto("Jarron", "Jarron verde", 50.0, LocalDate.now()),
                ),
                estaCargando = false
            )
        }
    }

    fun addProduct(nombre: String, precio: Double, descripcion: String, fecha: LocalDate) {
        val nuevoProducto = Producto(nombre, descripcion, precio, fecha)
        productos.add(nuevoProducto)
        estado = estado.copy(productos = productos.toList()) // Copia actualizada
    }

    fun deleteProduct(producto: Producto) {
        productos.remove(producto)
        estado = estado.copy(productos = productos.toList()) // Actualiza el estado con la lista actualizada
    }

    fun editProduct(producto: Producto, nombre: String, precio: Double, descripcion: String, fecha: LocalDate) {
        val productoIndex = productos.indexOf(producto)
        if (productoIndex != -1) {
            productos[productoIndex] = producto.copy(
                nombre = nombre,
                descripcion = descripcion,
                precio = precio,
                fecha = fecha
            )
            estado =
                estado.copy(productos = productos.toList()) // Actualiza el estado con la lista editada
        }
    }
}