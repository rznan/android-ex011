package br.com.renan.ex011.model;

/*
*@author:renan santos carvalho
*/
public class ContaPoupanca extends ContaBancaria {
    private int diaDeRendimento;

    public ContaPoupanca(String nomeCliente, int numConta, float saldo, int diaDeRendimento) {
        super(nomeCliente, numConta, saldo);
        this.diaDeRendimento = diaDeRendimento;
    }

    public void calcularNovoSaldo() {
        float TAXA = 1.10f;
        setSaldo(super.getSaldo() * TAXA);
    }

    public int getDiaDeRendimento() {
        return diaDeRendimento;
    }

    protected void setDiaDeRendimento(int diaDeRendimento) {
        this.diaDeRendimento = diaDeRendimento;
    }

    @Override
    public float getSaldo() {
        calcularNovoSaldo();
        return super.getSaldo();
    }
}

