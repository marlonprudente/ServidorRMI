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
