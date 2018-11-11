/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlonprudente.rmi.servidor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Marlon Prudente <marlon.oliveira at alunos.utfpr.edu.br>
 */
public class Quarto {
    Integer quantidadePessoas;
    Date dataLiberacao;
    boolean ocupado;
    
    public Quarto(Integer quantidadePessoas){
        this.quantidadePessoas = quantidadePessoas;
        ocupado = false;
    }
    
    public Integer getQuantidadePessoas(){
        return this.quantidadePessoas;
    }
    public void setQuantidadePessoas(Integer novaQuantidade){
        this.quantidadePessoas = novaQuantidade;
    }
    
    public Date getDataLiberacao(){
        return this.dataLiberacao;
    }
    public void setDataLiveracao(String novaData){
        try {
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
            this.dataLiberacao = df.parse(novaData);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }
   
    public boolean getOcupado(){
            return this.ocupado;
        }
    public void setOcupado(boolean ocupado){
        this.ocupado = ocupado;
    }

    
}
