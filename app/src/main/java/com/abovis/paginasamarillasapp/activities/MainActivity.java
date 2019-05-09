package com.abovis.paginasamarillasapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abovis.paginasamarillasapp.R;
import com.abovis.paginasamarillasapp.adapters.PaginasAdapter;
import com.abovis.paginasamarillasapp.models.Paginas;
import com.abovis.paginasamarillasapp.repositories.PaginasRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.paginalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PaginasAdapter adapter = new PaginasAdapter(this);

        List<Paginas> paginas = PaginasRepository.getPaginas();
        adapter.setPaginas(paginas);

        recyclerView.setAdapter(adapter);
    }
}
