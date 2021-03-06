package com.example.helpdev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TelaInicialActivity extends AppCompatActivity {

    ImageView btnoticias,btcodigo,btperfil,btsair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        btnoticias = findViewById(R.id.imageView);
        btcodigo = findViewById(R.id.imageView2);
        btperfil = findViewById(R.id.imageView3);
        btsair = findViewById(R.id.imageView4);

        btnoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaInicialActivity.this, NoticiasActivity.class);
                startActivity(intent);
            }
        });

        btcodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente c = getIntent().getExtras().getParcelable("cliente");

                Integer cd = c.getCodigo();
                String nome = c.getNome();


                Cliente cliente = new Cliente(cd,nome);

                System.out.println(cd);
                System.out.println(nome);

                Intent it = new Intent(TelaInicialActivity.this, CodigoActivity.class);

                it.putExtra("cliente", cliente);

                startActivity(it);
            }
        });

        btperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cliente c = getIntent().getExtras().getParcelable("cliente");

                Integer cd = c.getCodigo();
                String nome = c.getNome();


                Cliente cliente = new Cliente(cd,nome);

                System.out.println(cd);

                Intent it = new Intent(TelaInicialActivity.this, PerfilActivity.class);

                it.putExtra("cliente", cliente);

                startActivity(it);

            }
        });

        btsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
