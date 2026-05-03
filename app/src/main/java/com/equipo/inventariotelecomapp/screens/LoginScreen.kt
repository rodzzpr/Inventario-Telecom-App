package com.equipo.inventariotelecomapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.equipo.inventariotelecomapp.data.InventarioRepository

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Inventario Telecom",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo de Usuario (ID/Username del Técnico)
        OutlinedTextField(
            value = usuario,
            onValueChange = {
                usuario = it
                isError = false
            },
            label = { Text("Usuario") }, // Cambiado a "Usuario" para ser más técnico
            placeholder = { Text("Ej: cashprofe") }, // Placeholder actualizado
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                isError = false
            },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            singleLine = true
        )

        // Mensaje de error dinámico
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp).align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // Validación de campos vacíos
                if (usuario.isBlank() || password.isBlank()) {
                    errorMessage = "Por favor, completa todos los campos"
                    isError = true
                    return@Button
                }

                // Validación contra el repositorio usando el campo 'username'
                val tecnicoEncontrado = InventarioRepository.listaTecnicos.find {
                    it.username.equals(usuario, ignoreCase = true) && it.password == password
                }

                if (tecnicoEncontrado != null) {
                    onLoginSuccess(tecnicoEncontrado.nombre)
                } else {
                    errorMessage = "Usuario o contraseña incorrectos"
                    isError = true
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Iniciar Sesión", fontWeight = FontWeight.Bold)
        }
    }
}