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
public class InteresseQuarto {
    String localizacao;
    Integer valorMaximo;
    Integer quantidadePessoas;
    Cliente cliente;
    
    public InteresseQuarto(String localizacao, Integer valorMaximo, Integer quantidadePessoas, Cliente cliente){
        this.cliente = cliente;
        this.localizacao = localizacao;
        this.quantidadePessoas = quantidadePessoas;
        this.valorMaximo = valorMaximo;
    }
    
}
