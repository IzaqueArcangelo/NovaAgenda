package br.com.alura.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.alura.agenda.DAO.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        lista = (ListView) findViewById(R.id.lista);
        // listener de um item da lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // obtém um aluno da lista.
                Aluno aluno = (Aluno) lista.getItemAtPosition(position);
                Intent editarAluno = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                //colocar uma instância do aluno selecionado no intent
                editarAluno.putExtra("aluno", aluno);
                startActivity(editarAluno);
            }
        });

        Button adicionar = (Button) findViewById(R.id.novo_aluno);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move da activity atual para a activity desejada.
                Intent formulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(formulario);
            }
        });
        registerForContextMenu(lista);
    }

    private void carregaLIsta() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();
        //converte o array em view para que possa ser adicionado na listView
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLIsta();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) lista.getItemAtPosition(info.position);
                Toast.makeText(ListaAlunosActivity.this, "Deletar Aluno: " + aluno.getNome(), Toast.LENGTH_SHORT).show();
                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deletar(aluno);
                dao.close();
                carregaLIsta();
                return false;
            }
        });
    }
}
