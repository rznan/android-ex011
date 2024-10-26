package br.com.renan.ex011.model;

/*
*@author:renan santos carvalho
*/
public class ContaEspecial extends ContaBancaria {
    private float limite;

    public ContaEspecial(String nomeCliente, int numConta, float saldo, float limite) {
        super(nomeCliente, numConta, saldo);
        this.limite = limite;
    }

    @Override
    public void sacar(float valor) throws Exception {
        if ((getSaldo() - valor) < -limite) {
            throw new Exception("Saldo insuficiente");
        }
        setSaldo(getSaldo() - valor);
    }

    public float getLimite() {
        return limite;
    }

    protected void setLimite(float limite) {
        this.limite = limite;
    }
}
