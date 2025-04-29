package com.example.catalogo.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    productos: List<Producto>,
    onProductoClick: (Producto) -> Unit,
    onAgregarClick: () -> Unit,
    onCarritoClick: () -> Unit,
    total: Double
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Catálogo de Productos",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = onAgregarClick, containerColor = MaterialTheme.colorScheme.primary) {
                    Text("+", color = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                FloatingActionButton(onClick = onCarritoClick, containerColor = MaterialTheme.colorScheme.secondary) {
                    Text("🛒")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = 80.dp) // Espacio para no tapar el total
            ) {
                items(productos) { producto ->
                    ProductoItem(
                        producto = producto,
                        onClick = { onProductoClick(producto) }
                    )
                }
            }

            Divider()

            Text(
                text = "Total del carrito: $${"%.2f".format(total)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun ProductoItem(
    producto: Producto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(producto.imagenUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Imagen del producto",
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        CircularProgressIndicator()
                    }
                },
                error = {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                            .padding(4.dp)
                    ) {
                        Text(
                            "Imagen no disponible",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = producto.nombre,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Precio: $${producto.precio}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
