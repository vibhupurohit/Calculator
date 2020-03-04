package com.example.calculator;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.calculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char PERCENTAGE = '%';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION;
    private double valueOne = Double.NaN;
    private double valueTwo;
    private DecimalFormat decimalFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        decimalFormat = new DecimalFormat("#.#####");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.btnDot.setOnClickListener(view -> binding.editText.setText(String.format("%s.", binding.editText.getText())));
        binding.btn0.setOnClickListener(view -> binding.editText.setText(String.format("%s0", binding.editText.getText())));
        binding.btn1.setOnClickListener(view -> binding.editText.setText(String.format("%s1", binding.editText.getText())));
        binding.btn2.setOnClickListener(view -> binding.editText.setText(String.format("%s2", binding.editText.getText())));
        binding.btn3.setOnClickListener(view -> binding.editText.setText(String.format("%s3", binding.editText.getText())));
        binding.btn4.setOnClickListener(view -> binding.editText.setText(String.format("%s4", binding.editText.getText())));
        binding.btn5.setOnClickListener(view -> binding.editText.setText(String.format("%s5", binding.editText.getText())));
        binding.btn6.setOnClickListener(view -> binding.editText.setText(String.format("%s6", binding.editText.getText())));
        binding.btn7.setOnClickListener(view -> binding.editText.setText(String.format("%s7", binding.editText.getText())));
        binding.btn8.setOnClickListener(view -> binding.editText.setText(String.format("%s8", binding.editText.getText())));
        binding.btn9.setOnClickListener(view -> binding.editText.setText(String.format("%s9", binding.editText.getText())));


        binding.btnPlus.setOnClickListener(view -> {
            computeCalculation();
            CURRENT_ACTION = ADDITION;
            binding.infoTextView.setText(String.format("%s+", decimalFormat.format(valueOne)));
            binding.editText.setText(null);
        });

        binding.btnMinus.setOnClickListener(view -> {
            computeCalculation();
            CURRENT_ACTION = SUBTRACTION;
            binding.infoTextView.setText(String.format("%s-", decimalFormat.format(valueOne)));
            binding.editText.setText(null);
        });

        binding.btnMultiply.setOnClickListener(view -> {
            computeCalculation();
            CURRENT_ACTION = MULTIPLICATION;
            binding.infoTextView.setText(String.format("%s*", decimalFormat.format(valueOne)));
            binding.editText.setText(null);
        });

        binding.btnDivision.setOnClickListener(view -> {
            computeCalculation();
            CURRENT_ACTION = DIVISION;
            binding.infoTextView.setText(String.format("%s/", decimalFormat.format(valueOne)));
            binding.editText.setText(null);
        });
        binding.btnPercent.setOnClickListener(view -> {
            computeCalculation();
            CURRENT_ACTION = PERCENTAGE;
            binding.infoTextView.setText(String.format("%s%%", decimalFormat.format(valueOne)));
            binding.editText.setText(null);
        });

        binding.btnEqual.setOnClickListener(view -> {
            computeCalculation();
            binding.infoTextView.setText(String.format("%s%s = %s", binding.infoTextView.getText().toString(), decimalFormat.format(valueTwo), decimalFormat.format(valueOne)));
            valueOne = Double.NaN;
            CURRENT_ACTION = '0';
        });
        binding.btnClear.setOnClickListener(view -> {
            if(binding.editText.getText().length() > 0) {
                CharSequence currentText = binding.editText.getText();
                binding.editText.setText(currentText.subSequence(0, currentText.length()-1));
            }
            else {
                valueOne = Double.NaN;
                valueTwo = Double.NaN;
                binding.editText.setText("");
                binding.infoTextView.setText("");
            }
        });
    }
    private void computeCalculation() {
        if(!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if(CURRENT_ACTION == ADDITION)
                valueOne = this.valueOne + valueTwo;
            else if(CURRENT_ACTION == SUBTRACTION)
                valueOne = this.valueOne - valueTwo;
            else if(CURRENT_ACTION == MULTIPLICATION)
                valueOne = this.valueOne * valueTwo;
            else if(CURRENT_ACTION == DIVISION)
                valueOne = this.valueOne / valueTwo;
            else if(CURRENT_ACTION == PERCENTAGE)
                valueOne = this.valueOne % valueTwo;
        }
        else {
            try {
                valueOne = Double.parseDouble(binding.editText.getText().toString());
            }
            catch (Exception e){
                /* Catch what you want */
            }
        }
    }
}