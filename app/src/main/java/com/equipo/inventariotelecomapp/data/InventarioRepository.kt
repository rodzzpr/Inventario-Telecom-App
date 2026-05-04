package com.equipo.inventariotelecomapp.data

import com.equipo.inventariotelecomapp.model.Producto
import com.equipo.inventariotelecomapp.model.Tecnico
import com.equipo.inventariotelecomapp.model.Retiro

// Clase encargada de la lógica de datos
object InventarioRepository {
    val listaProductos = mutableListOf(
        Producto(
            id = 1,
            nombre = "Cable UTP Cat6",
            stock = 500,
            unidad = "Metros",
            descripcion = "Cable de red de alto rendimiento diseñado para transmisiones de datos a velocidades de hasta 1 Gbps. Ideal para instalaciones de infraestructura interna y racks de telecomunicaciones."
        ),
        Producto(
            id = 2,
            nombre = "Conector RJ45",
            stock = 1000,
            unidad = "Unidades",
            descripcion = "Conectores modulares estándar para terminación de cables Cat6. Cuentan con contactos bañados en oro para asegurar la mejor conductividad y evitar la corrosión."
        ),
        Producto(
            id = 3,
            nombre = "Router Dual Band",
            stock = 25,
            unidad = "Unidades",
            descripcion = "Enrutador inalámbrico de doble banda (2.4GHz y 5GHz) con tecnología MU-MIMO. Proporciona una cobertura extendida y estabilidad de conexión para múltiples dispositivos simultáneos."
        )
    )

    val listaTecnicos = mutableListOf(
        Tecnico(1, "Ricardo Prado", "rprado", "Instalador", "admin123"),
        Tecnico(2, "Alejandro Zarruk", "azarruk", "Fibra Óptica", "zarruk2026"),
        Tecnico(3, "Anthony Flores", "aflores","Soporte Redes", "flores99")
    )

    val historialRetiros = mutableListOf<Retiro>()

    fun procesarRetiro(productoId: Int, cantidad: Int): Boolean {
        val producto = listaProductos.find { it.id == productoId }
        return if (producto != null && producto.stock >= cantidad) {
            producto.stock -= cantidad
            historialRetiros.add(
                Retiro(
                    id = historialRetiros.size + 1,
                    productoId = productoId,
                    tecnicoId = 1, // ID por defecto del técnico actual
                    cantidad = cantidad,
                    fecha = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault()).format(java.util.Date())
                )
            )
            true
        } else {
            false
        }
    }
}