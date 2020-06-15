package com.example.helpdev2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ComentarioPessoalActivity extends AppCompatActivity {

    Button btcancelar, btpostar;
    SQLiteDatabase db, tp;
    TextView comentpessoal, escrevacomentario;

    int indice, codigo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_pessoal);

        btcancelar = findViewById(R.id.cancelarcomentariop);
        btpostar = findViewById(R.id.postarcomentariop);
        comentpessoal = findViewById(R.id.comentariospessoal);
        escrevacomentario = findViewById(R.id.escrevacomentariop);
        final Cliente c = getIntent().getExtras().getParcelable("cliente");
        final IDClass d = getIntent().getExtras().getParcelable("id_user");



        db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists " +
                "comentarios(id integer primary key " +
                "autoincrement, nome_comentador text not null, coment text " +
                "not null, id_user_postagem integer not null)");
        System.out.println("Banco de Dados Criado com Sucesso!");

        System.out.println(c.getCodigo());
        System.out.println(c.getNome());
        System.out.println(d.getCodigo());


            comentpessoal.setText("");
            MostraComentario(d.getCodigo());

            btcancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });


            btpostar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Integer id_post = d.getCodigo();
                    String nm = c.getNome();
                    String cm = escrevacomentario.getText().toString();

                    System.out.println(nm);
                    System.out.println(cm);
                    System.out.println(id_post);

                    db.execSQL("insert into comentarios(nome_comentador, coment,id_user_postagem) values " +
                            "('" + nm + "','" + cm + "','" + id_post + "')");

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(ComentarioPessoalActivity.this);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Comentário Postado com Sucesso !")
                            .setNeutralButton("OK", null)
                            .show();
                    comentpessoal.setText("");
                    MostraComentario(d.getCodigo());
                    escrevacomentario.setText("");
                }
            });
        }
        public void MostraComentario (int val) {

            final Cursor res = db.rawQuery("select nome_comentador,coment from comentarios WHERE id_user_postagem =" + val, null);
            ArrayList<String> coments = new ArrayList();
            if (res.getCount() > 0) {

                res.moveToFirst();

                while (res.moveToNext()) {
                    int a = 0;
                    int b = 1;
                    coments.add(res.getString(a) + ":" + res.getString(b));
                    a++;
                    a++;
                    b++;
                    b++;
                }
            }
            for (int i = 0; i < coments.size(); i++) {

                comentpessoal.setText(comentpessoal.getText()+" "+coments.get(i) + "\n");
                comentpessoal.setMovementMethod(new ScrollingMovementMethod());

            }


        }
    }

