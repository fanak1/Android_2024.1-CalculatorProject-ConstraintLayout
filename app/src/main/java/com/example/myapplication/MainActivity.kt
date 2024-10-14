package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var prevFormula : String = "";
    var operatorNow : Int = 1;// 1 la dau cong tru, 2 la dau nhan chia
    var op1 : Int = 0;
    var op2 : Int = 0;
    var remain : Int = 0;
    var opRemain : String = "";
    var dotable : Boolean = true;
    var tempString : String = "";
    var tempString2 : String = "";

    lateinit var textResult: TextView;
    lateinit var upperTextResult: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var CEButton : Button = findViewById(R.id.CEButton);
        var CButton : Button = findViewById(R.id.CButton);
        var BSButton : Button = findViewById(R.id.BSButton);
        var DivideButton : Button = findViewById(R.id.DivideButton);
        var sevenButton : Button = findViewById(R.id.sevenButton);
        var eightButton : Button = findViewById(R.id.eightButton);
        var nineButton : Button = findViewById(R.id.nineButton);
        var multiplyButton : Button = findViewById(R.id.multiplyButton);
        var fourButton : Button = findViewById(R.id.fourButton);
        var fiveButton : Button = findViewById(R.id.fiveButton);
        var sixButton : Button = findViewById(R.id.sixButton);
        var substractButton : Button = findViewById(R.id.subtractButton);
        var oneButton : Button = findViewById(R.id.oneButton);
        var twoButton : Button = findViewById(R.id.twoButton);
        var threeButton : Button = findViewById(R.id.threeButton);
        var plusButton : Button = findViewById(R.id.plusButton);
        var negativeButton : Button = findViewById(R.id.negativeButton);
        var zeroButton : Button = findViewById(R.id.zeroButton);
        var dotButton : Button = findViewById(R.id.dotButton);
        var equalButton : Button = findViewById(R.id.equalButton);

        CEButton.setOnClickListener(this);
        CButton.setOnClickListener(this);
        BSButton.setOnClickListener(this);
        DivideButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        substractButton.setOnClickListener(this);
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        dotButton.setOnClickListener(this);
        equalButton.setOnClickListener(this);

        textResult = findViewById(R.id.calculation);
        upperTextResult = findViewById(R.id.calculation0);

    }

    override fun onClick(p0: View?){
        var id = p0?.id;
        when(id){
            R.id.oneButton -> {
                addDigit(1);
            }
            R.id.twoButton -> {
                addDigit(2);
            }
            R.id.threeButton -> {
                addDigit(3);
            }
            R.id.fourButton -> {
                addDigit(4);
            }
            R.id.fiveButton -> {
                addDigit(5);
            }
            R.id.sixButton -> {
                addDigit(6);
            }
            R.id.sevenButton -> {
                addDigit(7);
            }
            R.id.eightButton -> {
                addDigit(8);
            }
            R.id.nineButton -> {
                addDigit(9);
            }
            R.id.zeroButton -> {
                addDigit(0);
            }
            R.id.dotButton -> {
                addDot();
            }
            R.id.plusButton -> {
                perFormFormula("+");
            }
            R.id.subtractButton -> {
                perFormFormula("-");
            }
            R.id.multiplyButton -> {
                perFormFormula("*");
            }
            R.id.DivideButton -> {
                perFormFormula("/");
            }
            R.id.equalButton -> {
                perFormFormula("=");
            }
            R.id.CEButton -> {
                ClearEnd();
            }
            R.id.CButton -> {
                ClearAll();
            }
            R.id.BSButton -> {
                BackSpace();
            }
            R.id.negativeButton -> {
                Negative();
            }

        }
    }

    fun addDigit(c: Int) {

        if(operatorNow == 1){
            op1 = op1 * 10 + c;
            tempString2 = "$op1";
            textResult.text = "$op1";

        } else{
            if(operatorNow == 2){
                op2 = op2 * 10 + c;
                tempString2 = "$op2";
                textResult.text = "$op2"
            } else{
                ClearAll();
                op1 = c;
                operatorNow = 1;
                textResult.text = "$op1";
            }

        }
    }

    fun addDot(){
        textResult.text = "Chua co";
    }
    fun perFormFormula(formula: String){
        tempString2 += formula;
        tempString += tempString2;
        upperTextResult.text = tempString;
        tempString2 = "";
        when(formula){
            "+" -> {
                if(operatorNow == 1) {
                    prevFormula = "+";
                    operatorNow = 2;
                } else{
                    Calculate(prevFormula);
                    CalculateRemain(opRemain);
                    prevFormula = "+";
                    operatorNow = 2;
                    opRemain = "";
                    remain = 0;
                }
            }
            "-" -> {
                if(operatorNow == 1){
                    prevFormula = "-";
                    operatorNow = 2;

                } else{
                    Calculate(prevFormula);
                    CalculateRemain(opRemain);
                    prevFormula = "+";
                    operatorNow = 2;
                    opRemain = "";
                    remain = 0;
                }
            }
            "*" -> {
                if(operatorNow == 1){
                    prevFormula = "*";
                    operatorNow = 2;
                } else{
                    if(prevFormula == "+" || prevFormula == "-"){
                        opRemain = prevFormula;
                        remain = op1;
                        op1 = op2;
                        op2 = 0;
                        prevFormula = "*";
                    } else {
                        Calculate(prevFormula);
                        prevFormula = "*";
                    }
                }
            }
            "/" -> {
                if(operatorNow == 1){
                    prevFormula = "/";
                    operatorNow = 2;
                } else{
                    if(prevFormula == "+" || prevFormula == "-"){
                        opRemain = prevFormula;
                        remain = op1;
                        op1 = op2;
                        op2 = 0;
                        prevFormula = "/";
                    } else {
                        Calculate(prevFormula);
                        prevFormula = "/";
                    }
                }
            }
            "=" -> {
                Calculate(prevFormula);
                CalculateRemain(opRemain);
                operatorNow = 1;
                op2 = 0;
                remain = 0;
                opRemain = "";
                tempString = "$op1";
                upperTextResult.text = tempString;
            }
        }
        op2 = 0;
        textResult.text = "$op1";
    }

    fun Calculate(formula: String){
        when(formula){
            "+" -> {
                op1 = op1 + op2;

            }
            "-" -> {
                op1 = op1 - op2;

            }
            "*" -> {
                op1 = op1 * op2;

            }
            "/" -> {
                if (op2 == 0) {

                } else {
                    op1 = op1 / op2;
                }
            }
        }
    }

    fun Negative(){
        if(operatorNow == 1){
            op1 = -op1;
            tempString2 = "$op1";
            textResult.text = "$op1";
        } else {
            op2 = -op2;
            tempString2 = "$op2";
            textResult.text = "$op2";
        }
    }

    fun CalculateRemain(formula : String){
        when(formula){
            "+" -> {
                op1 = op1 + remain;
            }
            "-" -> {
                op1 = remain - op1;
            }

        }
    }

    fun ClearEnd(){
        if(operatorNow == 1){
            op1 = 0;
            textResult.text = "$op1";
        } else{
            op2 = 0;
            textResult.text = "$op2";
        }
        tempString2 = "";
    }

    fun ClearAll(){
        remain = 0;
        op1 = 0;
        op2 = 0;
        prevFormula = "";
        opRemain = "";
        operatorNow = 1;
        textResult.text = "$op1";
        tempString = "";
        tempString2 = "";
        upperTextResult.text = "0";
    }

    fun BackSpace(){
        if(operatorNow == 1){
            op1 /= 10;
            textResult.text = "$op1";
        } else{
            op2 /= 10;
            textResult.text = "$op2";
        }
    }
}