/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Esta clase representa una clase que evalúa expresiones en notación polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();

        try {
            // 1. Escanear los valores de la expresión de izquierda a derecha
            for (int i = 0; i < expresion.size(); i++) {
                String s = expresion.get(i);
                if (!isOperator(s)) {
                    int number = Integer.parseInt(s);
                    pila.add(number);
                }else{
                    // Sacar los dos operandos.
                    int op1 = pila.pop();
                    int op2 = pila.pop();

                    // Evaluar la expresión.
                    switch(s)
                    {
                        case "+": pila.push(op2 + op1);
                            break;
                        case "-": pila.push(op2 - op1);
                            break;
                        case "*": pila.push(op2 * op1);
                            break;
                        case "/": pila.push(op2 / op1);
                            break;
                        case "%": pila.push(op2 % op1);
                            break;
                        case "^": pila.push((int)Math.pow(op2, op1));
                            break;

                    }
                }
            }
            return pila.pop();
        }catch(EmptyStackException e){
            System.out.println("No hay suficientes argumentos en la expresión " + e.getMessage() + "\n" + e.getStackTrace());
            return -1;
        }catch (Exception e){
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
            return -1;
        }
    }

    private static boolean isOperator(String ch) {
        return ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/") || ch.equals("%") || ch.equals("^");
    }

    /**
     * Programa principal
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("> ");
        String linea = teclado.nextLine();

        try {
            List<String> expresion = Token.dividir(linea);
            System.out.println(evaluarPostFija(expresion));
        }
        catch (Exception e) {
            System.err.printf("Error grave en la expresión: %s", e.getMessage());
        }
    }
}
