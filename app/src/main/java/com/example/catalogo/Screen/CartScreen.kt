package com.example.catalogo.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    carrito: List<Producto>,
    total: Double,
    onFinalizar: () -> Unit,
    onVolver: () -> Unit
) {
    var mostrarConfirmacion by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Carrito de Compras") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(carrito) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = producto.nombre,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text("Precio: $${producto.precio}")
                        }
                    }
                }
            }

            Divider()

            Text(
                text = "Total a pagar: $${"%.2f".format(total)}",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.End
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onVolver) {
                    Text("Volver")
                }

                Button(onClick = { mostrarConfirmacion = true }) {
                    Text("Finalizar Compra")
                }
            }
        }
    }

    if (mostrarConfirmacion) {
        AlertDialog(
            onDismissRequest = { mostrarConfirmacion = false },
            title = { Text("Compra Finalizada") },
            text = { Text("¡Gracias por tu compra!") },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarConfirmacion = false
                        onFinalizar()
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}
