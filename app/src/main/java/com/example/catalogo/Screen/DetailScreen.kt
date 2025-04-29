package com.example.catalogo.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import coil.request.ImageRequest
import coil.compose.SubcomposeAsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    producto: Producto,
    onAgregarAlCarrito: () -> Unit,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle del Producto") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(producto.imagenUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Imagen del producto",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                },
                error = {
                    Text(
                        text = "Imagen no disponible",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.error // Corregido
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = producto.nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Precio: $${producto.precio}", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = producto.descripcion,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onVolver) {
                    Text("Volver")
                }

                Button(onClick = onAgregarAlCarrito) {
                    Text("Agregar al carrito")
                }
            }
        }
    }
}
