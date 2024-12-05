/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;

 

// Importaciones necesarias para la creación de interfaces gráficas y manejo de eventos
import javax.swing.*;         // Librería para crear interfaces gráficas en Java (Swing).
import java.awt.*;           // Librería para manejar elementos de diseño (layouts, componentes gráficos).
import java.awt.event.*;     // Librería para manejar eventos (como clics de botones).
import java.util.*;          // Librería para colecciones como Map y ArrayList.

/**+
 * Clase principal para simular la administración de memoria de una CPU
 * con interfaz gráfica.
 */
//EQUIPO DE KEVIN OSORIO APARICIO Y JORGE LUIS MATRA
public class SimulacionMemoriaCPUConInterfaz {
    // Mapa para almacenar instrucciones del programa.
    // Clave: Número de línea, Valor: Instrucción.
    private static Map<Integer, String> programa = new HashMap<>();
    
    // Mapa para almacenar variables y sus valores.
    // Clave: Identificador de variable, Valor: Valor actual.
    private static Map<Integer, Integer> variables = new HashMap<>();

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Simulación de Administración de Memoria en la CPU");
        // Configurar la operación al cerrar la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Establecer el tamaño de la ventana
        frame.setSize(600, 500);
        // Configurar el diseño principal de la ventana
        frame.setLayout(new BorderLayout());

        // Crear el panel principal con diseño vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Crear el panel de entrada con diseño de dos filas
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        // Área de texto para las instrucciones del programa
        JTextArea programaArea = new JTextArea(5, 40);
        programaArea.setBorder(BorderFactory.createTitledBorder("Instrucciones (Escribe 'fin' para terminar)"));
        // Área de texto para las variables iniciales
        JTextArea variablesArea = new JTextArea(3, 40);
        variablesArea.setBorder(BorderFactory.createTitledBorder("Variables iniciales (id valor)"));
        // Agregar áreas de texto con barras de desplazamiento al panel de entrada
        inputPanel.add(new JScrollPane(programaArea));
        inputPanel.add(new JScrollPane(variablesArea));

        // Crear un botón para ejecutar el programa
        JButton ejecutarButton = new JButton("Ejecutar Programa");
        ejecutarButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el botón

        // Crear el área de salida, con un título y solo lectura
        JTextArea salidaArea = new JTextArea(10, 40);
        salidaArea.setEditable(false);
        salidaArea.setBorder(BorderFactory.createTitledBorder("Salida"));

        // Añadir los componentes al panel principal
        mainPanel.add(inputPanel);               // Panel de entrada
        mainPanel.add(ejecutarButton);           // Botón de ejecución
        mainPanel.add(new JScrollPane(salidaArea)); // Área de salida con barra de desplazamiento

        // Añadir el panel principal a la ventana
        frame.add(mainPanel, BorderLayout.CENTER);

        // Configurar la acción del botón "Ejecutar Programa"
        ejecutarButton.addActionListener(e -> {
            // Limpiar las estructuras de datos
            programa.clear();
            variables.clear();

            // Cargar las instrucciones del área de texto al mapa `programa`
            String[] instrucciones = programaArea.getText().split("\n");
            for (int i = 0; i < instrucciones.length; i++) {
                if (instrucciones[i].equalsIgnoreCase("fin")) break; // Finalizar si encuentra "fin"
                programa.put(i, instrucciones[i]); // Guardar cada instrucción con su índice
            }

            // Cargar las variables iniciales del área de texto al mapa `variables`
            String[] definicionesVariables = variablesArea.getText().split("\n");
            for (String definicion : definicionesVariables) {
                String[] partes = definicion.split(" "); // Separar id y valor
                if (partes.length == 2) {
                    int id = Integer.parseInt(partes[0]);       // Convertir id a entero
                    int valor = Integer.parseInt(partes[1]);   // Convertir valor a entero
                    variables.put(id, valor);                  // Guardar variable y su valor
                }
            }

            // Crear un StringBuilder para construir la salida
            StringBuilder salida = new StringBuilder();
            salida.append("Ejecutando programa...\n");

            // Ejecutar las instrucciones del programa
            for (int i = 0; i < programa.size(); i++) {
                String instruccion = programa.get(i);
                String[] partes = instruccion.split(" ");

                // Manejar cada tipo de instrucción
                switch (partes[0].toLowerCase()) {
                    case "cargar":
                        int valor = Integer.parseInt(partes[1]);
                        int variableCargar = Integer.parseInt(partes[2]);
                        variables.put(variableCargar, valor); // Asignar valor a una variable
                        break;

                    case "sumar":
                        int var1 = Integer.parseInt(partes[1]);
                        int var2 = Integer.parseInt(partes[2]);
                        int varResultado = Integer.parseInt(partes[3]);
                        variables.put(varResultado, variables.getOrDefault(var1, 0) + variables.getOrDefault(var2, 0));
                        // Sumar dos variables y guardar el resultado
                        break;

                    case "imprimir":
                        int variableImprimir = Integer.parseInt(partes[1]);
                        salida.append("Resultado: ").append(variables.getOrDefault(variableImprimir, 0)).append("\n");
                        // Imprimir el valor de una variable
                        break;

                    default:
                        salida.append("Instrucción no reconocida: ").append(partes[0]).append("\n");
                        // Manejar instrucciones no reconocidas
                }
            }

            // Mostrar el estado final de las variables
            salida.append("\nEstado final de las variables:\n");
            for (Map.Entry<Integer, Integer> entry : variables.entrySet()) {
                salida.append("Variable ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }

            // Mostrar la salida en el área de texto
            salidaArea.setText(salida.toString());
        });

        // Hacer visible la ventana
        frame.setVisible(true);
    }
    
    
    //pasos a seguir en fin primer cuadro cargar 5 1     , cargar 10 2 ,   sumar 1 2 3   ,   imprimir 3  ,    fin            
    // otro cuadro 10, 20 ,30
}
