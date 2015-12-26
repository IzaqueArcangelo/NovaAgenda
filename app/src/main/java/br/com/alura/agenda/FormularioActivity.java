package br.com.alura.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.LineNumberInputStream;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Button botaosalvar = (Button) findViewById(R.id.formulario_salvar);

        botaosalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move do formulário para a lista de alunos.
                Toast.makeText(FormularioActivity.this, "Aluno Salvo", Toast.LENGTH_SHORT).show();
                /** Existe o problema de pilha do android, pois o código abaixo cria uma nova activity
                 * e não utiliza a que já existe, sendo assim para obter o mesmo comportamento de voltar para
                 * a activity anterior também é possível utilizar o método finish();
                 */
                //Intent lista = new Intent(FormularioActivity.this, ListaAlunosActivity.class);
                //startActivity(lista);/*
                finish(); // encerra a activity e retorna para a anterior.
            }
        });
    }

}
