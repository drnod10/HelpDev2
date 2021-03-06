package com.example.helpdev2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    Button btcadastrar, btcancelar;
    EditText ednome,edapelido,edemail,edsenha,edsenha2,edtelefone;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btcancelar = findViewById(R.id.btcancelarperfil);
        btcadastrar = findViewById(R.id.btcadastro);
        ednome = (EditText) findViewById(R.id.nomeperfil);
        edapelido = (EditText) findViewById(R.id.apelidoperfil);
        edemail = (EditText) findViewById(R.id.emailperfil);
        edsenha = (EditText) findViewById(R.id.edsenha);
        edtelefone = findViewById(R.id.edtelefone);
        edsenha2 = findViewById(R.id.edsenha2);

        try {
            db = openOrCreateDatabase("banco_dados", Context.MODE_PRIVATE, null);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String nome = ednome.getText().toString();
                String apelido = edapelido.getText().toString();
                String email = edemail.getText().toString();
                String senha = edsenha.getText().toString();
                String telefone = edtelefone.getText().toString();
                String senha2 = edsenha2.getText().toString();
                try {
                    if ( senha.equals(senha2) ) {
                        if (!nome.equals("") && !apelido.equals("") && !email.equals("") && !senha.equals("") && !telefone.equals("") && !senha2.equals("")) {
                            db.execSQL("insert into usuarios(nome, apelido, email, telefone,senha) values ('" + nome + "','" + apelido + "','" + email + "','" + telefone + "','" + senha + "')");
                            AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastroActivity.this);
                            dialogo.setTitle("Aviso");
                            dialogo.setMessage("Usuario Cadastrado com Sucesso !")
                                    .setNeutralButton("OK", null)
                                    .show();
                        }
                        else{
                            AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastroActivity.this);
                            dialogo.setTitle("Aviso");
                            dialogo.setMessage("Preencha os Campos para cadastrar !")
                                    .setNeutralButton("OK", null)
                                    .show();

                        }

                    }else {
                        AlertDialog.Builder dialogo = new AlertDialog.Builder(CadastroActivity.this);
                        dialogo.setTitle("ERRO");
                        dialogo.setMessage("Senhas Diferentes digite novamente !")
                                .setNeutralButton("OK", null)
                                .show();

                    }

                } catch (Exception e) {
                    System.out.println("Erro ao Cadastrar Dados!");
                }
            }
        });

        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
