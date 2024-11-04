package drawable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ejercicios.appproductos.ViewModels.ViewModel

@Composable
fun MainView(viewModel: ViewModel, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Barra superior con ícono de regresar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            IconButton(
                onClick = { /* Acción de retroceso, sin funcionalidad */ },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_flecha), // Agrega tu icono de flecha
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

        // Espacio entre el título y el contenido
        Spacer(modifier = Modifier.height(16.dp))

        // Estado del viewModel.
        val estado = viewModel.estado

        // Carga.
        if (estado.estaCargando) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            // Mostrar los productos.
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.surface),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(estado.productos) { producto ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = producto.nombre + " ($" + producto.precio.toString() + " pesos)",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = producto.descripcion,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        // Botón de "más"
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Acción de continuación, sin funcionalidad */ },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_mas), // Icono de más
                contentDescription = "Continuar",
                tint = Color.White
            )
        }
    }
}


