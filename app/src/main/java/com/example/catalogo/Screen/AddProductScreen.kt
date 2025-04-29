package com.example.catalogo.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    onGuardar: (Producto) -> Unit,
    onCancelar: () -> Unit
) {
    var nombre by remember { mutableStateOf(TextFieldValue()) }
    var precioTexto by remember { mutableStateOf(TextFieldValue()) }
    var descripcion by remember { mutableStateOf(TextFieldValue()) }
    var imagenUrl by remember { mutableStateOf(TextFieldValue()) }

    var error by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agregar Producto", textAlign = TextAlign.Center) })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nuevo Producto",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = precioTexto,
                    onValueChange = { precioTexto = it },
                    label = { Text("Precio") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = imagenUrl,
                    onValueChange = { imagenUrl = it },
                    label = { Text("URL de Imagen") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                if (error) {
                    Text(
                        text = "Todos los campos son obligatorios y el precio debe ser válido",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onCancelar,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Cancelar")
                    }

                    Button(
                        onClick = {
                            val nombreVal = nombre.text.trim()
                            val descripcionVal = descripcion.text.trim()
                            val imagenVal = imagenUrl.text.trim()
                            val precioVal = precioTexto.text.trim()
                            val precioDouble = precioVal.toDoubleOrNull()

                            if (
                                nombreVal.isNotEmpty() &&
                                descripcionVal.isNotEmpty() &&
                                imagenVal.isNotEmpty() &&
                                precioDouble != null && precioDouble > 0
                            ) {
                                onGuardar(
                                    Producto(
                                        id = (0..1000000).random(),
                                        nombre = nombreVal,
                                        precio = precioDouble,
                                        descripcion = descripcionVal,
                                        imagenUrl = imagenVal
                                    )
                                )
                                error = false
                            } else {
                                error = true
                            }
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}
