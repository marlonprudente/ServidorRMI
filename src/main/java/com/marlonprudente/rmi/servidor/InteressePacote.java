/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlonprudente.rmi.servidor;

import com.marlonprudente.rmi.interfaces.Cliente;

/**
 *
 * @author Marlon Prudente <marlon.oliveira at alunos.utfpr.edu.br>
 */
public class InteressePacote {
    String para;
    String de;
    Cliente cliente;
    String localizacao;
    Integer valorMaximo;
    Integer quantidadePessoas;
    
    public InteressePacote(String para, String de, String localizacaoHotel, Integer valorMaximo, Integer quantidadePessoas, Cliente cliente){
        this.para = para;
        this.de = de;
        this.localizacao = localizacaoHotel;
        this.cliente = cliente;
        this.valorMaximo = valorMaximo;
        this.quantidadePessoas = quantidadePessoas;
        
    }
    
}
