package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var txtView: TextView;
    private var number: Double = 0.0
    private  var sign: String = ""
    private var dot: Boolean = false
    private var equals: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtView = findViewById(R.id.textView);
    }
    fun String.Trim(): String =  this.trimEnd('0').trimEnd('.')


    private fun executeOperation(secondNumber: Double) {
        when (sign) {

            "+" -> txtView.text = ((number + secondNumber).toString().Trim())
            "-" -> txtView.text = ((number - secondNumber).toString().Trim())
            "*" -> txtView.text = ((number * secondNumber).toString().Trim())
            "/" -> txtView.text = ((number / secondNumber).toString().Trim())
        }
    }

    fun dotBtn(btn: View){
        if (!dot && !txtView.text.contains('.')) {
            txtView.text = txtView.text.toString() + "."
            dot = true
        }
    }

    private fun dotChange() {
        dot = false

        if (equals) {
            txtView.text = "";
            equals = false;
        }
    }

    fun regularBtn(btn: View) {
        if (btn is Button) {
            dotChange()

            val current = txtView.text.toString().trimStart('0');
            val btnContent = btn.text.toString();

            val altered = current + btnContent;
            txtView.text = altered;
        }
    }


    fun operatorBtn(btn: View) {
        if (btn is Button) {
            sign = btn.text.toString();
            val txtView = txtView.text.toString();
            if (txtView.isNotEmpty()) {
                number = txtView.toDouble();
            }
            this.txtView.text = "";
        }
    }


    fun equalsBtn(btn: View) {
        equals = true;

        val oldOp = txtView.text.toString();

        var newOp = 0.0;

        if (oldOp.isNotEmpty()) {
            newOp = oldOp.toDouble();
        }
        executeOperation(newOp)
    }

    fun percentageBtn(btn: View){

        val result = txtView.text.toString().toDouble() / 100
        txtView.text = result.toString().Trim()
    }


    fun sqrt(btn: View){

        val result = sqrt(txtView.text.toString().toDouble())
        txtView.text = result.toString().Trim()
    }


    fun clearBtn(btn: View) {
        txtView.text = "";
        sign = "";
        number = 0.0;
    }

    fun reverseSignBtn(btn: View){

        val current = txtView.text.toString().toDouble()
        txtView.text = (current * -1).toString().Trim()
    }




}