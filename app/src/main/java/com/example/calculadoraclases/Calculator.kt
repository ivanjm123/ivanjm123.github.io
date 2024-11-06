/**
 * Clase que realiza operaciones matemáticas básicas: suma, resta, multiplicación y división.
 */
class Calculator {

    // Almacena el primer operando
    var numero1: Double = 0.0
    // Almacena la operación seleccionada
    var op: Int = 0

    /**
     * Realiza la operación seleccionada sobre dos números.
     *
     * @param numero2 El segundo número para la operación.
     * @return El resultado de la operación.
     */
    fun calculate(numero2: Double): Double {
        return when (op) {
            1 -> numero1 + numero2
            2 -> numero1 - numero2
            3 -> numero1 * numero2
            4 -> if (numero2 != 0.0) numero1 / numero2 else Double.NaN
            else -> 0.0
        }
    }

    /**
     * Establece el primer operando y el tipo de operación.
     *
     * @param numero El primer número para la operación.
     * @param operation El tipo de operación (1: suma, 2: resta, 3: multiplicación, 4: división).
     */
    fun setOperation(numero: Double, operation: Int) {
        numero1 = numero
        op = operation
    }
}