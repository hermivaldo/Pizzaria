package br.com.hermivaldo.pizzaria;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.from_username)
    TextInputLayout username;
    @BindView(R.id.from_password)
    TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        username.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validarNome();

            }
        });

        password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validaSenha();
            }
        });
    }


    @OnClick(R.id.from_conect)
    public void conectar(View v) {

        if (validarNome() && validaSenha()){
            Intent intent =  new Intent(this, MainActivity.class);
            intent.putExtra("USUARIO", username.getEditText().getText().toString());
            startActivity(intent);
        }

    }

    private boolean validarNome() {
        username.setError(null);
        if (username.getEditText().getText().toString().isEmpty()) {
            username.setError("Usuário não foi preenchido");
            return false;
        }
        return true;
    }

    private boolean validaSenha(){
        password.setError(null);
        if (password.getEditText().getText().toString().isEmpty()) {
            password.setError("Senha não foi preenchida");
            return false;
        }
        return true;
    }


}
