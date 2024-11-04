package com.ejercicios.appproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.ejercicios.appproductos.ViewModels.ViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ejercicios.appproductos.Views.InicioScreen
import com.ejercicios.appproductos.Views.presentacion
import com.ejercicios.appproductos.ui.theme.AppProductosTheme
import com.ejercicios.appproductos.Views.FormularioContent


class MainActivity : ComponentActivity() {

    // Método que es llamado al crear la activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instancia del viewModel.
        val viewModel: ViewModel = ViewModel()

        // Establecer el contenido de la aplicación.
        setContent {
            Box {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    MainView(viewModel, modifier = Modifier.padding(innerPadding), navController)
                }
            AppProductosTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavigationGraph() // Llama a tu función de navegación
                }
            }
            }
        }
    }
}
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val viewModel: ViewModel = ViewModel()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { InicioScreen(navController) } // Pantalla de inicio
        composable("presentacion") { presentacion(navController) } // Pantalla de presentación
        composable("productos") { MainView(viewModel, navController = navController) } // Pantalla de productos
        composable("formulario") { FormularioContent(navController, viewModel) } // Pantalla de formulario
    }
}


