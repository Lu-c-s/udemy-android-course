package android.example.com.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern DOUBLE_PATTERN = Pattern.compile(
            "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
                    "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
                    "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
                    "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickConvert(View view){
        EditText valueField = findViewById(R.id.ValueEditText);


        if( !isFloat(valueField.getText().toString()) ){
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
            return;
        }

        double valueFieldInt = Double.parseDouble(valueField.getText().toString());

        double total = valueFieldInt * 3.45;

        Toast.makeText(this, "The converted value is " + Double.toString(total), Toast.LENGTH_SHORT).show();
    }

    public static boolean isFloat(String s){
        return DOUBLE_PATTERN.matcher(s).matches();
    }
}
