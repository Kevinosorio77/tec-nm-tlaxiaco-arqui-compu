
package com.mycompany.mavenproject1;

import java.util.Scanner;

public class BaseConverter {

    // Método para convertir un número decimal a binario
    public static String decimalToBinary(int number) {
        return convertToBase(number, 2);
    }

    // Método para convertir un número decimal a octal
    public static String decimalToOctal(int number) {
        return convertToBase(number, 8);
    }

    // Método para convertir un número decimal a hexadecimal
    public static String decimalToHexadecimal(int number) {
        return convertToBase(number, 16);
    }

    // Método genérico para convertir un número decimal a cualquier base
    private static String convertToBase(int number, int base) {
        if (number == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        int remainder;
        while (number > 0) {
            remainder = number % base;
            if (remainder < 10) {
                result.append(remainder);  // Para binario y octal
            } else {
                result.append((char) ('A' + (remainder - 10)));  // Para hexadecimal
            }
            number /= base;
        }
        return result.reverse().toString();  // Invertir el resultado
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueConversion = true;

        while (continueConversion) {
            // Solicitar el número al usuario
            System.out.print("Introduce un número en decimal: ");
            int number = scanner.nextInt();

            // Solicitar la base de conversión al usuario
            System.out.println("Elige la base a la que quieres convertir:");
            System.out.println("1. Binario");
            System.out.println("2. Octal");
            System.out.println("3. Hexadecimal");
            int option = scanner.nextInt();

            // Convertir el número según la base seleccionada
            switch (option) {
                case 1:
                    System.out.println("Número en binario: " + decimalToBinary(number));
                    break;
                case 2:
                    System.out.println("Número en octal: " + decimalToOctal(number));
                    break;
                case 3:
                    System.out.println("Número en hexadecimal: " + decimalToHexadecimal(number));
                    break;
                default:
                    System.out.println("Opción no válida");
            }

            // Preguntar si desea realizar otra conversión
            System.out.print("¿Deseas realizar otra conversión? (s/n): ");
            String response = scanner.next();

            // Decidir si continuar o salir
            if (!response.equalsIgnoreCase("s")) {
                continueConversion = false;
                System.out.println("Saliendo del programa...");
            }
        }

        scanner.close();
    }
}