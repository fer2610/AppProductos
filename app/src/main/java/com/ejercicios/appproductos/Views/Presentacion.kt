package com.ejercicios.appproductos.Views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ejercicios.appproductos.R

@Composable
fun presentacion(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
                .align(Alignment.TopCenter)
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
                text = "Presentación",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // Espacio para evitar la barra superior
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.fotopersonal),
                contentDescription = "Foto",
                modifier = Modifier
                    .size(180.dp)
                    .padding(1.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary) // Borde alrededor de la imagen
            )
            Text(
                text = "Fernando Torres",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Desarrollador",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )

            // Información de contacto envuelta en un Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                                       .align(Alignment.Center),
                    horizontalAlignment = Alignment.Start
                ) {
                    ContactRow(iconId = R.drawable.icon_telefono, text = "+526321058937")
                    ContactRow(iconId = R.drawable.icon_linkedin, text = "@fxrmrshm")
                    ContactRow(iconId = R.drawable.icon_correo, text = "a219210879@unison.mx")
                }
            }
        }
    }
}

@Composable
fun ContactRow(iconId: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp),
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = text, color = Color.White, fontSize = 15.sp)
    }
}
