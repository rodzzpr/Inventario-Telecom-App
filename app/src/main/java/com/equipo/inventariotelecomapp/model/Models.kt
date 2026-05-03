package com.equipo.inventariotelecomapp.model

// Definición de las entidades usando POO
data class Producto(
    val id: Int,
    val nombre: String,
    var stock: Int,
    val unidad: String
)

data class Tecnico(
    val id: Int,
    val nombre: String,
    val especialidad: String
)

data class Retiro(
    val id: Int,
    val productoId: Int,
    val tecnicoId: Int,
    val cantidad: Int,
    val fecha: String
)

data class InsumoCritico(
    val id: Int,
    val productoId: Int,
    val alertaStockMinimo: Int
)