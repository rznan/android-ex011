package br.com.renan.ex011;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.com.renan.ex011.model.ContaBancaria;
import br.com.renan.ex011.model.ContaEspecial;
import br.com.renan.ex011.model.ContaPoupanca;

/*
*@author:renan santos carvalho
*/
public class MainActivity extends AppCompatActivity {


    private TextView cliente;
    private TextView conta;
    private TextView limiteEDtRendimento;
    private TextView valor;
    private TextView saldo;
    private TextView result;

    private RadioGroup rgTypes;
    private RadioButton rbEspecial;
    private RadioButton rbPoupanca;

    private Button btnDepositar;
    private Button btnSacar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        limiteEDtRendimento = findViewById(R.id.limiteEDtRendimento);
        valor = findViewById(R.id.valor);
        saldo = findViewById(R.id.saldo);
        result = findViewById(R.id.result);
        conta = findViewById(R.id.conta);
        cliente = findViewById(R.id.cliente);

        btnDepositar = findViewById(R.id.btnDepositar);
        btnSacar = findViewById(R.id.btnSacar);

        rgTypes = findViewById(R.id.rgTypes);
        rbEspecial = findViewById(R.id.rbEspecial);
        rbPoupanca = findViewById(R.id.rbPoupanca);

        rbEspecial.setChecked(true);
        rgTypes.setOnCheckedChangeListener((group, value) -> changeLabels());

        btnDepositar.setOnClickListener(e -> depositar());
        btnSacar.setOnClickListener(e -> sacar());

    }

    private void sacar() {
        float valor = Float.parseFloat(this.valor.getText().toString());
        ContaBancaria cb = viewToConta();
        String message;

        try {
            cb.sacar(valor);
            message = cb.toString();
        } catch (Exception e) {
            message = e.getMessage();
        }

        showResultMessage(message);
    }

    private void showResultMessage(String message) {
        this.result.setText(message);
    }

    private void depositar() {
        float valor = Float.parseFloat(this.valor.getText().toString());
        ContaBancaria cb = viewToConta();
        cb.depositar(valor);
        showResultMessage(cb.toString());
    }

    @NonNull
    private ContaBancaria viewToConta() {
        ContaBancaria cb;

        String cliente = this.cliente.getText().toString();
        int conta = Integer.parseInt(this.conta.getText().toString());
        float saldo = Float.parseFloat(this.saldo.getText().toString());

        if(rbEspecial.isChecked()) {
            float limite = Float.parseFloat(this.limiteEDtRendimento.getText().toString());
            cb = new ContaEspecial(cliente, conta, saldo, limite);
        }
        else {
            int diaRendimento = Integer.parseInt(this.limiteEDtRendimento.getText().toString());
            cb = new ContaPoupanca(cliente, conta, saldo, diaRendimento);
        }
        return cb;
    }

    private void changeLabels() {
        if(rbPoupanca.isChecked()) {
            limiteEDtRendimento.setHint(R.string.diaRendimento);
            limiteEDtRendimento.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        else {
            limiteEDtRendimento.setHint(R.string.limite);
            limiteEDtRendimento.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
    }
}