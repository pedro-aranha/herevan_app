package com.projeto.projetotcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class MainCadastro extends AppCompatActivity {

    private EditText editNomeCad, editEmailCad, editSenhaCad, editSenhaConf;
    private Button btnCadastrar;

    //LEMBRAR SEMPRE DE ALTERAR O IP, CASO FOR NECESSÁRIO!!

    //private String HOST = "http://172.16.251.38/herevan_proj/cadastroAluno";

    private String HOST = "http://192.168.0.5/herevan_proj/cadastroAluno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_cadastro);

        editNomeCad = (EditText) findViewById(R.id.editNomeCad);
        editEmailCad = (EditText) findViewById(R.id.editEmailCad);
        editSenhaCad = (EditText) findViewById(R.id.editSenhaCad);
        editSenhaConf = (EditText) findViewById(R.id.editSenhaConf);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = editNomeCad.getText().toString();
                String email = editEmailCad.getText().toString();
                String senha = editSenhaCad.getText().toString();
                String confirma = editSenhaConf.getText().toString();

                String URL = HOST + "/cadastro_mobile.php";

                if(confirma.equals(senha)) {

                    if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                        Toast.makeText(MainCadastro.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                    }else {

                        Ion.with(MainCadastro.this)
                                .load(URL)
                                .setBodyParameter("us_nome", nome)
                                .setBodyParameter("us_email", email)
                                .setBodyParameter("us_senha", senha)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {

                                        try {
                                            //Toast.makeText(MainCadastro.this, "NOME: " + result.get("nome").getAsString(), Toast.LENGTH_LONG).show();
                                            String RETORNO = result.get("CADASTRO").getAsString();

                                            if(RETORNO.equals("Email_Erro"))
                                            {
                                                Toast.makeText(MainCadastro.this, "Ops! Este e-mail já está cadastrado" , Toast.LENGTH_LONG).show();
                                            }
                                            else if (RETORNO.equals("Sucesso"))
                                            {
                                                //Toast.makeText(MainCadastro.this, "CADASTRADO COM SUCESSO" , Toast.LENGTH_LONG).show();
                                                Intent abreDasboard = new Intent(MainCadastro.this, MainDashboard.class);
                                                startActivity(abreDasboard);
                                            }
                                            else
                                            {
                                                Toast.makeText(MainCadastro.this, "Ops! Deu erro :(" , Toast.LENGTH_LONG).show();
                                            }

                                        } catch (Exception erro) {
                                            Toast.makeText(MainCadastro.this, "Ocorreu um erro, " + erro, Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });
                    }
                } else {
                    Toast.makeText(MainCadastro.this, "As senhas não conferem", Toast.LENGTH_LONG).show();
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