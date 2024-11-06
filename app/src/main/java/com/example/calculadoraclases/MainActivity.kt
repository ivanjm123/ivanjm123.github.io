package com.example.calculadoraclases

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

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



/**
 * Actividad principal que controla la calculadora, utilizando `Calculator` para la lógica
 * y `DisplayManager` para la interfaz.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var calculator: Calculator
    private lateinit var displayManager: DisplayManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tv_num1: TextView = findViewById(R.id.tv_num1)
        val tv_num2: TextView = findViewById(R.id.tv_num2)

        calculator = Calculator()
        displayManager = DisplayManager(tv_num1, tv_num2)

        val btnLimpiar: Button = findViewById(R.id.btn_clear)
        val btnBorra: Button = findViewById(R.id.btn_borrar)
        val btnPorc: Button = findViewById(R.id.btn_div100)
        val btnResult: Button = findViewById(R.id.btn_result)

        // Configuración de los listeners de los botones
        btnResult.setOnClickListener {
            val numero2 = displayManager.num2.toDoubleOrNull() ?: 0.0
            val result = calculator.calculate(numero2)
            displayManager.showResult(result)
        }

        btnLimpiar.setOnClickListener {
            displayManager.clearDisplay()
            calculator = Calculator() // Reinicia la calculadora
        }

        btnBorra.setOnClickListener {
            displayManager.deleteLastCharacter()
        }

        btnPorc.setOnClickListener {
            val temp = displayManager.num2.toDoubleOrNull() ?: 0.0
            displayManager.showResult(temp / 100)
        }
    }

    /**
     * Método para manejar los dígitos presionados.
     *
     * @param view La vista del botón presionado.
     */
    fun presionarDigito(view: View) {
        val buttonValue = when (view.id) {
            R.id.btn_num0 -> "0"
            R.id.btn_num1 -> "1"
            R.id.btn_num2 -> "2"
            R.id.btn_num3 -> "3"
            R.id.btn_num4 -> "4"
            R.id.btn_num5 -> "5"
            R.id.btn_num6 -> "6"
            R.id.btn_num7 -> "7"
            R.id.btn_num8 -> "8"
            R.id.btn_num9 -> "9"
            R.id.btn_decimal -> if (!displayManager.num2.contains(".")) if (displayManager.num2.isEmpty()) "0." else "." else ""
            else -> ""
        }
        displayManager.updateDisplay(buttonValue)
    }

    /**
     * Método para manejar las operaciones seleccionadas.
     *
     * @param view La vista del botón de operación presionado.
     */
    fun clicOperacion(view: View) {
        val numero = displayManager.num2.toDoubleOrNull() ?: return
        val (operationCode, operationSymbol) = when (view.id) {
            R.id.btn_suma -> 1 to "+"
            R.id.btn_resta -> 2 to "-"
            R.id.btn_mult -> 3 to "*"
            R.id.btn_div -> 4 to "/"
            else -> return
        }
        calculator.setOperation(numero, operationCode)
        displayManager.showOperation(displayManager.num2, operationSymbol)
    }
}
