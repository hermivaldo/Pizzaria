package br.com.hermivaldo.pizzaria;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.hermivaldo.pizzaria.model.Pedido;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.from_loginuser)
    TextView loginuser;

    // sabores pizza
    @BindView(R.id.cbA)
    CheckBox atum;
    @BindView(R.id.cbB)
    CheckBox bacon;
    @BindView(R.id.cbF)
    CheckBox frango;
    @BindView(R.id.cbQ)
    CheckBox queijo;

    @BindView(R.id.rdTamanho)
    RadioGroup rgTamanho;

    @BindView(R.id.spPagamentos)
    Spinner spPagamentos;

    String usuario;
    Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.pedido = new Pedido();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            this.usuario = getIntent().getStringExtra("USUARIO");
            loginuser.setText(this.usuario);
        }

        bacon.setOnCheckedChangeListener(this);
        queijo.setOnCheckedChangeListener(this);
        frango.setOnCheckedChangeListener(this);
        atum.setOnCheckedChangeListener(this);

    }

    public String getTamanhoSele() {

        return ((RadioButton) findViewById(rgTamanho.getCheckedRadioButtonId())).getText().toString();

    }

    @OnClick(R.id.btPedido)
    public void fecharPedido() {

        pedido.setCliente(this.usuario);
        pedido.setTamanho(this.getTamanhoSele());
        pedido.setFormaPagamento(spPagamentos.getSelectedItem().toString());


        Intent intent = new Intent(this, ConfirmarPedidoActivity.class);
        Bundle bundle = new Bundle();

        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            pedido.addSabor(buttonView.getText().toString());
        } else {
            pedido.removerSabor(buttonView.getText().toString());
        }
    }
}
