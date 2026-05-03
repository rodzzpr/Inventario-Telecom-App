package com.equipo.inventariotelecomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.equipo.inventariotelecomapp.screens.InventoryScreen
import com.equipo.inventariotelecomapp.ui.theme.InventarioTelecomAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventarioTelecomAppTheme {
                InventoryScreen()
            }
        }
    }
}