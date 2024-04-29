![Logo](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/th5xamgrr6se0x5ro4g6.png)

# Conversor de Moneda
- ![Static Badge](https://img.shields.io/badge/Versi%C3%B3n-1.0--SNAPSHOT-blue?style=for-the-badge) 
- ![Legal Disclaimer](https://img.shields.io/badge/License-MIT-succes?style=for-the-badge&logoColor=white&color=blue)

Versión: **1.0.0**
Fecha de lanzamiento: **25 de abril 2024**

Este proyecto consiste en una aplicación Java que permite a los usuarios convertir cantidades de dinero de una moneda a otra. Utiliza una API de tasas de cambio para obtener las tasas actuales y realiza las conversiones en tiempo real. La aplicación también filtra las monedas disponibles para convertir, mostrando solo aquellas que son distintas de la moneda inicial seleccionada por el usuario.

## Descripción 📝

El Conversor de Moneda es una herramienta útil para realizar conversiones de moneda de manera rápida y precisa. La aplicación proporciona una interfaz fácil de usar que guía al usuario a través del proceso de selección de monedas y entrada de la cantidad a convertir. Luego, utiliza una API externa para obtener las tasas de cambio actualizadas y realiza la conversión de manera eficiente.

## Funcionalidades Extras 🚀

- Filtrado de Monedas: Solo se muestran las opciones de monedas válidas para la conversión, simplificando la selección para los usuarios.
- Historial de Conversiones: Se ha implementado un historial que muestra las últimas 5 conversiones realizadas, proporcionando a los usuarios una visión detallada de actividades anteriores.
- Soporte para una Amplia Gama de Monedas: La lista de monedas disponibles se ha expandido, ofreciendo a los usuarios aún más opciones para sus conversiones monetarias.
- Registros con Marca de Tiempo: Cada conversión en el historial ahora incluye una marca de tiempo, utilizando la Java Time API, lo que permite a los usuarios conocer la hora y la fecha exactas en que se realizó cada conversión.


## Pre-requisitos 📋

Para ejecutar esta aplicación, necesitarás tener instalado Java en tu sistema operativo. Además, asegúrate de tener acceso a Internet para poder realizar solicitudes a la API de tasas de cambio.

## Empezando 🏁

Para obtener una copia de este proyecto, simplemente clona este repositorio utilizando Git en tu terminal:

```bash
git clone https://github.com/Hernanencizo360/conversorDeMoneda.git
```

También puedes descargar el proyecto en formato ZIP haciendo clic en el botón "Code" en la parte superior de esta página y seleccionando "Download ZIP".

Una vez que tengas el proyecto en tu máquina local, puedes descomprimir y abrir el archivo en tu entorno de desarrollo integrado (IDE) preferido. 
Si estás utilizando IntelliJ IDEA, sigue estos pasos:

1. Abre IntelliJ IDEA.
2. Selecciona "File" en la barra de menú.
3. Haz clic en "Open" y navega hasta la ubicación donde descargaste el proyecto.
4. Si descargaste el proyecto en formato ZIP, descomprímelo primero. Puedes hacer esto haciendo clic derecho en el archivo ZIP y seleccionando la opción para extraer o descomprimir el archivo. Asegúrate de descomprimirlo en una ubicación fácilmente accesible en tu computadora.
5. Selecciona el directorio del proyecto descomprimido y haz clic en "Open".
6. Una vez que se haya cargado el proyecto en IntelliJ IDEA, podrás explorar el código fuente, ejecutar la aplicación, además realizar cualquier modificación que desees.

Si estás utilizando otro IDE o sistema operativo, los pasos para abrir el proyecto pueden variar.


## Construido Con 🛠️

- ![Java](https://img.shields.io/badge/java-17.0.11-blue.svg?style=for-the-badge&logo=openjdk&logoColor=black)
- ![Gson](https://img.shields.io/badge/Gson-2.10.1-succes?style=for-the-badge&logo=json&logoColor=red&color=succes)
- ![JUnit](https://img.shields.io/badge/JUnit%20Jupiter-RELEASE-success?style=for-the-badge&logo=junit5&logoColor=succes&color=red)
- ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)



## Hoja de Ruta 🗺️

Ideas, mejoras y actualizaciones futuras para el proyecto.

**1. Interfaz de Usuario**
- Navegación Intuitiva: Mejorar la interfaz de usuario para permitir una navegación más intuitiva, con opciones claras para avanzar y retroceder entre las alternativas de conversión.

- Ordenar Monedas por Valor: Implementar una función para ordenar las monedas disponibles según su valor en el mercado, lo que permite a los usuarios acceder rápidamente a las opciones más relevantes.

**2. Funcionalidades Adicionales**

- Guardar Preferencias del Usuario: Permitir que los usuarios guarden sus preferencias de conversión, como la moneda inicial y final predeterminada, para facilitar futuras conversiones.
- Opción de Cancelar: Agregar una opcion de cancelar durante toda la ejecución del programa, para permitir a los usuarios salir de la aplicación en cualquier momento sin necesidad de completar una conversión.

**3. Experiencia del Usuario**

- Gestión de Errores Mejorada: Implementar una gestión de errores mejorada que informe al usuario sobre cualquier problema que ocurra durante la ejecución del programa, ofreciendo soluciones o sugerencias para resolverlos.

**4. Optimizaciones de Rendimiento**

- Caché de Datos: Implementar una caché de datos local para almacenar temporalmente las tasas de cambio más utilizadas, reduciendo la dependencia de las solicitudes a la API externa y mejorando la velocidad de respuesta.

## Versionado 📌

Se uso [Git](https://git-scm.com) para el control de versiones. Puedes revisar el historial de commits en este repositorio para ver las diferentes versiones del proyecto.

## Soporte 🤝

Si tienes algún problema o sugerencia, por favor abre un problema [aquí](https://github.com/Hernanencizo360/conversorDeMoneda/issues).

## Autor ✒️

- **[Hernán Eladio Encizo](https://github.com/Hernanencizo360)** - _Trabajo inicial_

## Links de Interés 🔗

[![GitHub](https://img.shields.io/badge/GitHub-000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Hernanencizo360)
[![Telegram](https://img.shields.io/badge/Telegram-2CA5E0?style=for-the-badge&logo=telegram&logoColor=white)](https://t.me/hernanencizo360)
[![Instagram](https://img.shields.io/badge/Instagram-E4405F?style=for-the-badge&logo=instagram&logoColor=white)](https://www.instagram.com/hernanencizo360)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/hern%C3%A1n-encizo-b3b355229?original_referer=)


## Licencia 📄

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE.md](LICENSE.md) para más detalles.
