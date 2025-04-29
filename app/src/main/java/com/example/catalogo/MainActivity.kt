// Ruta: com.example.catalogo.MainActivity.kt
package com.example.catalogo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.example.catalogo.Screen.Navigation
import com.example.catalogo.Screen.Navigation
import com.example.catalogo.Screen.Producto
import com.example.catalogo.ui.theme.CatalogoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatalogoTheme {
                // Lista vacía inicial de productos
                val productos = remember { mutableStateListOf<Producto>() }


                val carrito = remember { mutableStateListOf<Producto>() }
                val total by derivedStateOf { carrito.sumOf { it.precio } }


                val navController = rememberNavController()


                Navigation(
                    navController = navController,
                    productos = productos,
                    onAddProducto = { producto ->
                        productos.add(producto)
                    },
                    onAddToCart = { producto ->
                        carrito.add(producto)
                    },
                    carrito = carrito,
                    total = total
                )
            }
        }
    }
}
