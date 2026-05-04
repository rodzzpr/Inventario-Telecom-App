package com.equipo.inventariotelecomapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.equipo.inventariotelecomapp.data.InventarioRepository
import com.equipo.inventariotelecomapp.model.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithdrawalScreen(onBack: () -> Unit) {
    val productos = InventarioRepository.listaProductos
    var selectedProducto by remember { mutableStateOf<Producto?>(null) }
    var cantidad by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Retiro de Material",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Inventory,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Registrar Salida",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            // Selector de Producto
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedProducto?.nombre ?: "Seleccione un producto",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Producto") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    productos.forEach { producto ->
                        DropdownMenuItem(
                            text = { Text("${producto.nombre} (Stock: ${producto.stock})") },
                            onClick = {
                                selectedProducto = producto
                                expanded = false
                                mensajeError = null
                            }
                        )
                    }
                }
            }

            // Campo de Cantidad
            OutlinedTextField(
                value = cantidad,
                onValueChange = { 
                    if (it.all { char -> char.isDigit() }) {
                        cantidad = it
                        mensajeError = null
                    }
                },
                label = { Text("Cantidad a retirar") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                suffix = {
                    selectedProducto?.let {
                        Text(it.unidad)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )

            if (mensajeError != null) {
                Text(
                    text = mensajeError!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val cantInt = cantidad.toIntOrNull()
                    if (selectedProducto == null) {
                        mensajeError = "Por favor seleccione un producto"
                    } else if (cantInt == null || cantInt <= 0) {
                        mensajeError = "Ingrese una cantidad válida"
                    } else if (cantInt > selectedProducto!!.stock) {
                        mensajeError = "Stock insuficiente (Disponible: ${selectedProducto!!.stock})"
                    } else {
                        val exito = InventarioRepository.procesarRetiro(selectedProducto!!.id, cantInt)
                        if (exito) {
                            showSuccessDialog = true
                        } else {
                            mensajeError = "Error al procesar el retiro"
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Confirmar Retiro", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { 
                showSuccessDialog = false
                onBack()
            },
            title = { Text("Retiro Exitoso") },
            text = { Text("El movimiento ha sido registrado correctamente y el stock actualizado.") },
            confirmButton = {
                TextButton(onClick = { 
                    showSuccessDialog = false
                    onBack()
                }) {
                    Text("Aceptar")
                }
            }
        )
    }
}
