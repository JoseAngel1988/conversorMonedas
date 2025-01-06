El proyecto en mencion es un Conversor de Monedas que permite a los usuarios convertir una moneda base a una moneda objetivo utilizando tasas de cambio actualizadas a través de una API externa. El programa tiene una interfaz interactiva que permite al usuario realizar varias opciones:

Descripción General:
Realizar una Conversión: El usuario ingresa la moneda base (como USD) y la moneda objetivo (como EUR), así como la cantidad que desea convertir. El programa calcula el valor convertido y muestra la tasa de conversión. Además, guarda cada conversión en un historial.

Ver Historial de Conversiones: El usuario puede ver un listado de todas las conversiones que ha realizado previamente, mostrando la cantidad convertida, las monedas involucradas y la tasa de cambio en cada caso.

Salir: El usuario puede salir del programa en cualquier momento.

Características:
Utiliza una API para obtener las tasas de conversión de monedas en tiempo real.
Guarda un historial de las conversiones realizadas, permitiendo al usuario consultarlas más tarde.
Presenta un menú interactivo con opciones para realizar conversiones, ver el historial y salir.
Tecnologías Usadas:
Java con HttpClient para realizar solicitudes HTTP.
Gson para parsear las respuestas JSON de la API de tasas de cambio.
