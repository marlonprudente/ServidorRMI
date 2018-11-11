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
public class InteressePassagem {
    String de;
    String para;
    Integer valorMaximo;
    Cliente cliente;
    
    public InteressePassagem(String de, String para, Integer valorMaximo, Cliente cliente){
        this.cliente = cliente;
        this.de = de;
        this.para = para;
        this.valorMaximo = valorMaximo;
    }
    
}
