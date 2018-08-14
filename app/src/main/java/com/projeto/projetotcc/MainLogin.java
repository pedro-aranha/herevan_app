package com.projeto.projetotcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainLogin extends AppCompatActivity {

    private EditText editEmailLogar, editSenhaLogar;
    private Button btnEntrar;
    private TextView txtCadastro;

    //LEMBRAR SEMPRE DE ALTERAR O IP, CASO FOR NECESSÁRIO!!

    //private String HOST = "http://172.16.251.38/herevan_proj/login"; //"http://127.0.0.1:80/herevan_proj/login";

    private String HOST = "http://192.168.0.5/herevan_proj/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        editEmailLogar = (EditText) findViewById(R.id.editEmailLogar);
        editSenhaLogar = (EditText) findViewById(R.id.editSenhaLogar);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        txtCadastro = (TextView) findViewById(R.id.txtCadastro);

        txtCadastro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent abreCad = new Intent(MainLogin.this, MainCadastro.class);
                startActivity(abreCad);
            }
        });

        btnEntrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmailLogar.getText().toString();
                String senha = editSenhaLogar.getText().toString();

                String URL = HOST + "/login_mobile.php";

                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(MainLogin.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                }else {

                    Ion.with(MainLogin.this)
                            .load(URL)
                            .setBodyParameter("us_email", email)
                            .setBodyParameter("us_senha", senha)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {

                                    try {
                                        //Toast.makeText(MainCadastro.this, "NOME: " + result.get("nome").getAsString(), Toast.LENGTH_LONG).show();
                                        String RETORNO = result.get("LOGIN").getAsString();

                                        if(RETORNO.equals("Erro"))
                                        {
                                            Toast.makeText(MainLogin.this, "Ops! E-mail ou senha incorretos" , Toast.LENGTH_LONG).show();
                                        }
                                        else if (RETORNO.equals("Sucesso"))
                                        {
                                            //Toast.makeText(MainLogin.this, "CADASTRADO COM SUCESSO" , Toast.LENGTH_LONG).show();
                                            Intent abreDasboard = new Intent(MainLogin.this, MainDashboard.class);
                                            startActivity(abreDasboard);
                                        }
                                        else
                                        {
                                            Toast.makeText(MainLogin.this, "Ops! Deu erro :(" , Toast.LENGTH_LONG).show();
                                        }

                                    } catch (Exception erro) {
                                        Toast.makeText(MainLogin.this, "Ocorreu um erro na aplicação! Verifique, " + erro, Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}
