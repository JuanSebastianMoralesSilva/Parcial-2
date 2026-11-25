# Parcial-2
Universidad Distrital Francisco Jose de Caldas

Programacion Orientada a Objetos

Parcial 2

Creadores:
Juan Sebastian Morales Silva 20261007033
Juan David Cruz Becerra 20261007009

Descripcion general:
Es un sistema de gestion por consola desarrollado en java para la clase de programacion orientada a objetos, diseñado para administrar entrenadores pokemon, sus equipos, medallas y tiene el catalogo de pokemones der la primera generacion original (Kanto), aunque tambien se permite crear tanto pokemones como entrenadores inventados con ciertas caracteristicas modificables.
El programa permite alternar entre distintos entrenadores activos, consultar su informacion y realizar distintas operaciones.

Main:
Controla el flujo principal y el menu interactivo en consola, inicializando la Pokedex y los entrenadores base y gestionando las acciones sobre el entredador o pokemon seleccionado.

Trainer:
Modela a cada entrenadfor con nombre, id, lista de pokemones(ArrayList<Pokemon>) y coleccion de medallas obtenidas(ArrayList<Medal>).

Pokemon:
Respresenta a cada pokemon con nombre, id, nivel, referencia a su entrenador (Trainer) y una lista lista dinamica de tipos (ArrayList<type>) que admite 1 o 2 tipos.

Pokedex:
Contiene el metodo estatico "cargarPrimeraGeneracion()", que precarga los 151 pokemon iniciales Kanto con sus tipos correspondientes y sin entrenador asignado.

type:
Enumeracion (enum) que define los tipos elementales disponibles para los pokemones.

Medal:
Enumeracion (enum) que reune las medallas de gimnasio asignables a los entrenadores.

FUNCIONALIDADES PRINCIPALES

Gestion de entrenadores:
Cambiar el entrenador activo, registrar nuevos entrenadores y visualizar su equipo junto con sus medallas.

Administracion de equipos Pokemon:
Capturar pokemones clonandolos desde los 151 disponibles en la pokedex o crear pokemones personalizados asignando tipos, ademas que permite buscar cada pokemon por su id, actualizar su nombre o nivel y liberar o eliminar un pokemon del equipo.

Progresion de medallas:
Otorgar medallas del "enun Medal" al entrenador activo evitando duplicados.
