package com.ejercicios.appproductos.Views


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ejercicios.appproductos.R
import com.ejercicios.appproductos.ViewModels.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.ejercicios.appproductos.Dialogs.InfoDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioContent(navController: NavController, viewModel: ViewModel) {
    var nombre by remember { mutableStateOf(TextFieldValue()) }
    var precio by remember { mutableStateOf(TextFieldValue()) }
    var descripcion by remember { mutableStateOf(TextFieldValue()) }
    var fecha by remember { mutableStateOf(TextFieldValue()) }
    var showInfoDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_flecha), // Asegúrate de tener un icono para la flecha
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        // Validación simple
                        if (nombre.text.isNotBlank() && precio.text.isNotBlank() && descripcion.text.isNotBlank() && fecha.text.isNotBlank()) {
                            // Agregar producto al ViewModel
                            viewModel.addProduct(
                                nombre = nombre.text,
                                precio = precio.text.toDouble(),
                                descripcion = descripcion.text,
                                fecha = LocalDate.parse(fecha.text, DateTimeFormatter.ISO_LOCAL_DATE)
                            )

                            // Mostrar el diálogo de confirmación de éxito
                            showInfoDialog = true
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Agregar")
                }

                // Diálogo que se muestra una vez que se agrega el producto
                if (showInfoDialog) {
                    InfoDialog(
                        title = "Producto añadido",
                        message = "El producto ha sido añadido exitosamente.",
                        onDismiss = {
                            showInfoDialog = false // Ocultar el diálogo
                            navController.popBackStack() // Regresar a la pantalla de productos después de cerrar el diálogo
                        }
                    )
                }
            }
        }
    )
}