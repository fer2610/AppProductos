package com.ejercicios.appproductos.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ejercicios.appproductos.R

@Composable
fun InicioScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Center-align all items vertically
    ) {
        // Logo and text centered
        Column(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_logo), // Logo icon
                contentDescription = "Logo de la Empresa",
                modifier = Modifier.size(120.dp) // Logo size
            )
            Text(
                text = "NewGreenLand",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }

        // Centered buttons
        Column(
            modifier = Modifier
                .width(300.dp)
                .padding(top = 40.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Button(
                onClick = { navController.navigate("productos") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Productos")
            }

            Button(
                onClick = { navController.navigate("presentacion") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Presentación")
            }
        }
    }
}
