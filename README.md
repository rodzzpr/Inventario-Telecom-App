# Inventario Telecom App 📱

## 📝 Descripción del Proyecto
Esta aplicación móvil resuelve el descontrol en el flujo de inventario de campo en empresas de telecomunicaciones. Permite registrar salidas de material vinculadas a técnicos responsables, asegurando la trazabilidad y el monitoreo constante del stock de insumos críticos.

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Kotlin
* **Framework UI:** Jetpack Compose para interfaces modernas e interactivas
* **Arquitectura:** Programación Orientada a Objetos (POO)
* **Persistencia:** SQLite/Room (basado en el modelo físico diseñado en Enterprise Architect)[cite: 1]
* **Control de Versiones:** Git con flujo de trabajo basado en ramas

## 🚀 Instrucciones de Ejecución
1. **Clonar el repositorio:** `git clone https://github.com/rodzzpr/Inventario-Telecom-App.git`
2. **Abrir en Android Studio:** Se recomienda la versión Koala o superior.
3. **Sincronizar Gradle:** Esperar a que descarguen todas las dependencias.
4. **Ejecutar:** Seleccionar un emulador (API 30+) o dispositivo físico y presionar "Run".

## 📊 Modelo de Datos
El sistema se basa en un modelo relacional de 4 tablas principales:
* **Producto:** Catálogo maestro y stock actual.
* **Tecnico:** Registro de personal autorizado.
* **Retiro:** Registro transaccional de movimientos.
* **Insumocritico:** Parámetros de alerta para productos sensibles.
