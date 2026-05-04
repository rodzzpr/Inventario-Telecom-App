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
import com.equipo.inventariotelecomapp.screens.ProductDetailScreen
import com.equipo.inventariotelecomapp.screens.LoginScreen
import com.equipo.inventariotelecomapp.screens.WithdrawalScreen
import com.equipo.inventariotelecomapp.ui.theme.InventarioTelecomAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventarioTelecomAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") {
                        LoginScreen(onLoginSuccess = { _ ->
                            navController.navigate("inventory") {
                                popUpTo("login") { inclusive = true }
                            }
                        })
                    }

                    composable("inventory") {
                        InventoryScreen(
                            onNavigateToDetail = { productId ->
                                navController.navigate("detail/$productId")
                            },
                            onNavigateToWithdrawal = {
                                navController.navigate("withdrawal")
                            },
                            onLogout = {
                                navController.navigate("login") {
                                    popUpTo("inventory") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("withdrawal") {
                        WithdrawalScreen(
                            onBack = { navController.popBackStack() }
                        )
                    }

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