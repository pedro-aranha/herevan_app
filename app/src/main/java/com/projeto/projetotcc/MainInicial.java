package com.projeto.projetotcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainInicial extends AppCompatActivity {

    private Button btn_login;
    private Button btn_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_inicial);

        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent abreCad = new Intent(MainInicial.this, MainLogin.class);
                startActivity(abreCad);
            }
        });

        btn_cadastro = (Button) findViewById(R.id.btn_cadastro);

        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent abreCad = new Intent(MainInicial.this, MainCadastro.class);
                startActivity(abreCad);
            }
        });

    }

    //AQUI ESTÁ OS CÓDIGOS PARA ABRIR AS TELAS LOGIN E CADASTRO

    //TELA DE LOGIN

    //public void login (View View){

    //setContentView(R.layout.main_login);

    //}

    //TELA DE CADASTRO

    //public void cadastro (View View){

    //setContentView(R.layout.main_cadastro);
    //}
}
