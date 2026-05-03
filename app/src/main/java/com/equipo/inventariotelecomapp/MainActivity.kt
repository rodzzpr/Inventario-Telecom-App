package com.equipo.inventariotelecomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.equipo.inventariotelecomapp.screens.InventoryScreen
import com.equipo.inventariotelecomapp.screens.ProductDetailScreen // Asegúrate de crear esta pantalla
import com.equipo.inventariotelecomapp.ui.theme.InventarioTelecomAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventarioTelecomAppTheme {
                // 1. Creamos el controlador de navegación
                val navController = rememberNavController()

                // 2. Definimos el Host con las rutas
                NavHost(
                    navController = navController,
                    startDestination = "inventory" // Pantalla de inicio
                ) {
                    // Ruta de la Lista
                    composable("inventory") {
                        InventoryScreen(onNavigateToDetail = { productId ->
                            navController.navigate("detail/$productId")
                        })
                    }

                    // Ruta del Detalle (recibe un argumento ID)
                    composable(
                        route = "detail/{productId}",
                        arguments = listOf(navArgument("productId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("productId") ?: 0
                        ProductDetailScreen(
                            productId = id,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}