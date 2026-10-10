package com.uniajc;

public class CuentaCorriente extends Cuenta {
    protected float sobregiro;

    public CuentaCorriente(float saldo, float tasa) {
        super(saldo, tasa);
    }

    public void consignatr(float cantidad) {
        super.consignar(cantidad);
    }

    public void retirar(float cantidad) {
        super.retirar(cantidad);
    }

    public void extractoMensual() { }

    public void imprimir() { }
}
