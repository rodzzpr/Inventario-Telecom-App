package com.equipo.inventariotelecomapp.data

import com.equipo.inventariotelecomapp.model.Movimiento
import com.equipo.inventariotelecomapp.model.Producto
import com.equipo.inventariotelecomapp.model.Tecnico

object InventarioRepository {
    val listaTecnicos = mutableListOf(
        Tecnico(1, "Ricardo Prado", "rprado", "Instalador", "admin123"),
        Tecnico(2, "Alejandro Zarruk", "azarruk", "Fibra Óptica", "zarruk2026"),
        Tecnico(3, "Anthony Flores", "aflores", "Soporte Redes", "flores99")
    )

    val listaProductos = mutableListOf(
        Producto(101, "Cable UTP Cat6", 150, "mts", "Cable de red de alta velocidad"),
        Producto(102, "Router Mikrotik", 15, "uds", "Router para gestión de red"),
        Producto(103, "Switch PoE 24p", 8, "uds", "Switch con alimentación PoE"),
        Producto(104, "Conector RJ45", 500, "uds", "Conector para cable UTP"),
        Producto(105, "Fibra Óptica ADSS", 25, "mts", "Fibra para tendido aéreo")
    )

    val listaMovimientos = mutableListOf<Movimiento>()

    fun procesarRetiro(productoId: Int, cantidadARetirar: Int, nombreTecnico: String): Boolean {
        val producto = listaProductos.find { it.id == productoId }
        if (producto != null && producto.stock >= cantidadARetirar) {
            producto.stock -= cantidadARetirar

            val nuevoMovimiento = Movimiento(
                id = listaMovimientos.size + 1,
                tecnico = nombreTecnico,
                producto = producto.nombre,
                cantidad = cantidadARetirar,
                fecha = "04/05/2026"
            )
            listaMovimientos.add(0, nuevoMovimiento)
            return true
        }
        return false
    }
}
