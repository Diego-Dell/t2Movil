package com.example.t2movil;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText numeroText;
    TextView resultadoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeroText = findViewById(R.id.numero);
        resultadoView = findViewById(R.id.resultado);
        Button generarButton = findViewById(R.id.generar);

        generarButton.setOnClickListener(v -> generarSerie());
    }

    private void generarSerie() {
        String texto = numeroText.getText().toString().trim();

        if (texto.isEmpty()) {
            resultadoView.setText("Error: el campo no puede estar vacío.");
            return;
        }

        int n;

        try {
            n = Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            resultadoView.setText("Error: ingrese un valor numérico válido.");
            return;
        }

        if (n <= 0) {
            resultadoView.setText("Error: n debe ser mayor a 0.");
            return;
        }

        StringBuilder serie = new StringBuilder();
        double suma = 0;

        for (int i = 1; i <= n; i++) {
            double termino = (Math.pow(i, 2) + 1) / 2.0;

            if (i % 2 == 0) {
                termino = termino * -1;
            }

            suma += termino;
            serie.append(formatearNumero(termino));

            if (i < n) {
                serie.append(", ");
            }
        }

        resultadoView.setText(
                "Serie:\n" + serie.toString() +
                        "\n\nSumatoria: " + formatearNumero(suma)
        );
    }

    private String formatearNumero(double numero) {
        if (numero == (int) numero) {
            return String.valueOf((int) numero);
        }
        return String.valueOf(numero);
    }
}