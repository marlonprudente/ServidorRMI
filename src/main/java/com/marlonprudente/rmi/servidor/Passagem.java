/*
 * Este sotfware foi feito para a UTFPR - Campus Curitiba;
 * O Código é livre para uso não comercial;
 * Desenvolvido através do Netbeans IDE.
 */
package com.marlonprudente.rmi.servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Marlon Prudente <marlon.oliveira at alunos.utfpr.edu.br>
 */
public class Passagem extends UnicastRemoteObject {

    Integer id = -1;
    String de = "";
    String para = "";
    boolean idaEVolta = false;
    Date dataInicial;
    Date dataRetorno;
    Integer valor;
    Integer poltronas;

    public Passagem(Integer id, Integer quantidadePessoas, String para, String de, Date dataInicial, Date dataRetorno, Integer valor) throws RemoteException {
        this.id = id;
        this.idaEVolta = true;
        this.dataInicial = dataInicial;
        this.dataRetorno = dataRetorno;
        this.de = de;
        this.para = para;
        this.poltronas = quantidadePessoas;
        this.valor = valor;

    }

    public Passagem(Integer id, Integer quantidadePessoas, String para, String de, Date dataInicial, Integer valor) throws RemoteException {
        this.id = id;
        this.poltronas = quantidadePessoas;
        this.de = de;
        this.para = para;
        this.dataInicial = dataInicial;
        this.valor = valor;
    }

    public boolean getPassagemIdaEVolta() {
        return this.idaEVolta;
    }

    public void setPassagemIdaEVolta(boolean passagemIdaEVolta) {
        this.idaEVolta = passagemIdaEVolta;
    }

    public Date getDataRetorno() {
        return this.dataRetorno;
    }

    public void setDataRetorno(String data) {
        try {
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
            this.dataRetorno = df.parse(data);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer novoId) {
        this.id = novoId;
    }

    public Integer getValor() {
        return this.valor;
    }

    public void setValor(Integer novoValor) {
        this.valor = novoValor;
    }

    public String getDe() {
        return this.de;
    }

    public void setDe(String novaCidadeOrigem) {
        this.de = novaCidadeOrigem;
    }

    public String getPara() {
        return this.para;
    }

    public void setPara(String novaCidadeDestino) {
        this.para = novaCidadeDestino;
    }

    public Date getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(String novaData) {
        try {
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
            this.dataInicial = df.parse(novaData);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    public Integer getPoltronas() {
        return this.poltronas;
    }

    public void setPoltronas(Integer novasPoltronas) {
        this.poltronas = novasPoltronas;
    }
}
