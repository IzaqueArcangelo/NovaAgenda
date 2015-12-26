package br.com.alura.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.alura.agenda.DAO.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        Button adicionar = (Button) findViewById(R.id.novo_aluno);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move da activity atual para a activity desejada.
                Intent formulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(formulario);
            }
        });
    }

    private void carregaLIsta() {
        AlunoDAO dao = new AlunoDAO(this);

        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();


        ListView lista = (ListView) findViewById(R.id.lista);
        //converte o array em view para que possa ser adicionado na listView
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);

        lista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLIsta();
    }
}
