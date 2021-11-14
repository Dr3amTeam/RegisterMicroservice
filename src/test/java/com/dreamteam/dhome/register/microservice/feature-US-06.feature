Feature: Mostrar el telefono del trabajador
  Scenario: El cliente necesita información del trabajador
    Given que el cliente desea conocer el número telefónico del trabajador
    When el cliente ingrese en la sección de trabajador
    Then muestra sus datos personales.
