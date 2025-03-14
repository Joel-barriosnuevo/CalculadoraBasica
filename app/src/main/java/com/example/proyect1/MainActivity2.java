package com.example.proyect1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edt_num1, edt_num2;
    TextView text_result;
    Spinner operaciones_spinner;
    Button btn_calcular;

    TextView txt_signo;


    @Override

    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (position == 0) {
            return;
        }

        String operacion = operaciones_spinner.getSelectedItem().toString().trim();
        Log.d("SpinnerSelection", "Selected operation: " + operacion);

        switch (operacion) {
            case "Suma":
                txt_signo.setText("+");
                break;
            case "Resta":
                txt_signo.setText("-");
                break;
            case "Multiplicación":
                txt_signo.setText("×");
                break;
            case "División":
                txt_signo.setText("÷");
                break;
            default:
                txt_signo.setText("?");
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        edt_num1 = findViewById(R.id.edt_num1);
        edt_num2 = findViewById(R.id.edt_num2);
        text_result = findViewById(R.id.txt_result);
        operaciones_spinner = findViewById(R.id.operaciones_spinner);
        btn_calcular = findViewById(R.id.btn_calcular);
        txt_signo = findViewById(R.id.txt_signo);


        operaciones_spinner.setOnItemSelectedListener(this);

        btn_calcular.setOnClickListener(v -> calcularResultado());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        btn_calcular.setVisibility(View.GONE);
    }

    private void calcularResultado() {
        try {
            int num1 = Integer.parseInt(edt_num1.getText().toString());
            int num2 = Integer.parseInt(edt_num2.getText().toString());
            double num1Double = Double.parseDouble(edt_num1.getText().toString());
            double num2Double = Double.parseDouble(edt_num2.getText().toString());
            String operacion = operaciones_spinner.getSelectedItem().toString();
            int resultado = 0;
            double resultadoDouble = 0.0;
            boolean isDivision = false;


            switch (operacion) {
                case "Suma":
                    resultado = num1 + num2;

                    break;
                case "Resta":
                    resultado = num1 - num2;

                    break;
                case "Multiplicación":
                    resultado = num1 * num2;

                    break;
                case "División":
                    if (num2Double != 0) {
                        resultadoDouble = num1Double / num2Double;
                        isDivision = true;
                    } else {
                        Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
                default:
                    Toast.makeText(this, "Operación no válida", Toast.LENGTH_SHORT).show();
                    return;
            }
            if (isDivision){
                text_result.setText(String.valueOf(resultadoDouble));
            }else{
                text_result.setText(String.valueOf(resultado));
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese números válidos", Toast.LENGTH_SHORT).show();
        }
    }
}
