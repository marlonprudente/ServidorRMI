/*
 * Este sotfware foi feito para a UTFPR - Campus Curitiba;
 * O Código é livre para uso não comercial;
 * Desenvolvido através do Netbeans IDE.
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
