package com.example.catalogo.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(
    navController: NavHostController,
    productos: List<Producto>,
    onAddProducto: (Producto) -> Unit,
    onAddToCart: (Producto) -> Unit,
    carrito: List<Producto>,
    total: Double
) {
    NavHost(navController = navController, startDestination = "catalogo") {
        composable("catalogo") {
            MainScreen(
                productos = productos,
                onProductoClick = { producto ->
                    navController.navigate("detalle/${producto.id}")
                },
                onAgregarClick = {
                    navController.navigate("agregar")
                },
                onCarritoClick = {
                    navController.navigate("carrito")
                },
                total = total
            )
        }

        composable("agregar") {
            AddProductScreen(
                onGuardar = { nuevoProducto ->
                    onAddProducto(nuevoProducto)
                    navController.popBackStack()
                },
                onCancelar = {
                    navController.popBackStack()
                }
            )
        }

        composable("detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            val producto = productos.find { it.id == id }

            if (producto != null) {
                DetailScreen(
                    producto = producto,
                    onAgregarAlCarrito = {
                        onAddToCart(producto)
                    },
                    onVolver = {
                        navController.popBackStack()
                    }
                )
            } else {
                ErrorScreen(mensaje = "Producto no encontrado") {
                    navController.popBackStack()
                }
            }
        }

        composable("carrito") {
            CartScreen(
                carrito = carrito,
                total = total,
                onFinalizar = {
                    navController.popBackStack()
                },
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun ErrorScreen(mensaje: String, onVolver: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = mensaje)
        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}