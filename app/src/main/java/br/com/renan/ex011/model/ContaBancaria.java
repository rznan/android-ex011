package br.com.renan.ex011.model;

import androidx.annotation.NonNull;

/*
*@author:renan santos carvalho
*/
public abstract class ContaBancaria {
    private String nomeCliente;
    private int    numConta;
    private float  saldo;

    public ContaBancaria(String nomeCliente, int numConta, float saldo) {
        this.nomeCliente = nomeCliente;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    public void sacar(float valor) throws Exception {
        if (this.saldo < valor) {
            throw new Exception("Saldo insuficiente");
        }
        this.saldo -= valor;
    }

    public void depositar(float valor) {
        this.saldo += valor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    protected void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getNumConta() {
        return numConta;
    }

    protected void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public float getSaldo() {
        return saldo;
    }

    protected void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @NonNull
    @Override
    public String toString() {
        return "nome   =" + getNomeCliente() + "\n" +
               "numero =" + getNumConta() + "\n" +
               "saldo  =" + getSaldo();
    }
}
