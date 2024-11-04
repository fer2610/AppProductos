package com.ejercicios.appproductos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ejercicios.appproductos.Model.Producto
import com.ejercicios.appproductos.ViewModels.ViewModel
import com.ejercicios.appproductos.Dialogs.ConfirmDialog
import java.time.LocalDate

@Composable
fun MainView(viewModel: ViewModel, modifier: Modifier = Modifier, navController: NavHostController) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Barra superior fuera del Column para ocupar todo el ancho
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
                .align(Alignment.TopCenter) // Alineación superior de la pantalla
        ) {
            IconButton(
                onClick = { navController.navigate("inicio") },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_flecha),
                    contentDescription = "Regresar",
                    tint = Color.White
                )
            }
            Text(
                text = "Productos",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 80.dp, horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Spacer(modifier = Modifier.height(16.dp))

        // Estado del viewModel.
        val estado = viewModel.estado

        if (estado.estaCargando) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.surface),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(estado.productos) { producto: Producto ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = producto.nombre + " ($" + producto.precio.toString() + " pesos)",
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = producto.descripcion ?: "Descripción no disponible",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        IconButton(
                            onClick = {
                                showDeleteDialog = true // Mostrar el diálogo cuando se presiona el botón
                            }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar producto")
                        }

                        // Mostrar el diálogo si showDeleteDialog es verdadero
                        if (showDeleteDialog) {
                            ConfirmDialog(
                                title = "Confirmar eliminación",
                                message = "¿Estás seguro de que deseas eliminar este producto?",
                                onConfirm = {
                                    viewModel.deleteProduct(producto) // Elimina el producto específico
                                    showDeleteDialog = false // Cierra el diálogo
                                },
                                onCancel = {
                                    showDeleteDialog = false // Cierra el diálogo sin eliminar
                                },
                                onDismiss = {
                                    showDeleteDialog = false // Cierra el diálogo si se hace clic fuera de él
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = { navController.navigate("formulario") },
            modifier = Modifier
                .size(75.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_mas),
                contentDescription = "Agregar producto",
                modifier = Modifier.size(32.dp),
                tint = Color.White
            )
        }
    }
}
}
