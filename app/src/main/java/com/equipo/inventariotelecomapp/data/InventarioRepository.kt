package com.equipo.inventariotelecomapp.data
import com.equipo.inventariotelecomapp.model.Producto
import com.equipo.inventariotelecomapp.model.Tecnico
import com.equipo.inventariotelecomapp.model.Retiro

// Clase encargada de la lógica de datos
object InventarioRepository {
    val listaProductos = mutableListOf(
        Producto(1, "Cable UTP Cat6", 500, "Metros"),
        Producto(2, "Conector RJ45", 1000, "Unidades"),
        Producto(3, "Router Dual Band", 25, "Unidades")
    )

    val listaTecnicos = mutableListOf(
        Tecnico(1, "Ricardo Prado", "Instalador"),
        Tecnico(2, "Alejandro Mendoza", "Fibra Óptica"),
        Tecnico(3, "Anthony Torres", "Soporte Redes")
    )

    val historialRetiros = mutableListOf<Retiro>()
}