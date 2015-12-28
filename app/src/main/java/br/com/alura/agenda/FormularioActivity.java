package br.com.alura.agenda;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import br.com.alura.agenda.DAO.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;


public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            helper.preencheformulario(aluno);
        }
        /* remoção do comportamento do botão salvar
        Button botaosalvar = (Button) findViewById(R.id.formulario_salvar);

        botaosalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move do formulário para a lista de alunos.
                Toast.makeText(FormularioActivity.this, "Aluno Salvo", Toast.LENGTH_SHORT).show();
                *//** Existe o problema de pilha do android, pois o código abaixo cria uma nova activity
                 * e não utiliza a que já existe, sendo assim para obter o mesmo comportamento de voltar para
                 * a activity anterior também é possível utilizar o método finish();
                 *//*
                //Intent lista = new Intent(FormularioActivity.this, ListaAlunosActivity.class);
                //startActivity(lista);*//*
                finish(); // encerra a activity e retorna para a anterior.
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuPersonalizado = getMenuInflater();
        menuPersonalizado.inflate(R.menu.menu_formulario_ok, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Toast.makeText(FormularioActivity.this, "Aluno Salvo", Toast.LENGTH_SHORT).show();
                Aluno aluno = helper.getAluno();
                AlunoDAO dao = new AlunoDAO(FormularioActivity.this);
                if(aluno.getId()!=null){
                    dao.alterar(aluno);
                }else{
                    dao.insere(aluno);
                }
                dao.close();
                finish(); // encerra a activity e retorna para a anterior.
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
