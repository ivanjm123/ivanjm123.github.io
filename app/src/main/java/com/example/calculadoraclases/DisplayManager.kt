package com.example.calculadoraclases

import android.widget.TextView

/**
 * Clase para gestionar la actualización de los TextViews en la interfaz de usuario.
 */
class DisplayManager(private val tv_num1: TextView, private val tv_num2: TextView) {

    // Almacena la entrada actual como texto
    var num2: String = ""

    /**
     * Actualiza el contenido de `tv_num2`, agregando el valor del botón pulsado.
     *
     * @param buttonValue El valor del botón que se ha pulsado.
     */
    fun updateDisplay(buttonValue: String) {
        num2 += buttonValue
        tv_num2.text = num2
    }

    /**
     * Limpia ambos TextViews y reinicia los valores de entrada.
     */
    fun clearDisplay() {
        tv_num1.text = ""
        tv_num2.text = ""
        num2 = ""
    }

    /**
     * Muestra el resultado en `tv_num2`.
     *
     * @param result El resultado de la operación.
     */
    fun showResult(result: Double) {
        tv_num2.text = result.toString()
    }

    /**
     * Muestra el primer operando y el operador seleccionado en `tv_num1`.
     *
     * @param num El primer operando.
     * @param operation El operador en formato de texto.
     */
    fun showOperation(num: String, operation: String) {
        tv_num1.text = "$num $operation"
        num2 = ""
        tv_num2.text = ""
    }

    /**
     * Elimina el último carácter de la entrada actual.
     */
    fun deleteLastCharacter() {
        if (num2.isNotEmpty()) {
            num2 = num2.substring(0, num2.length - 1)
            tv_num2.text = num2
        }
    }
}
