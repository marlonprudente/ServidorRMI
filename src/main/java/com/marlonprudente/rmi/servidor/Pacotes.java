/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlonprudente.rmi.servidor;

import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author Marlon Prudente <marlon.oliveira at alunos.utfpr.edu.br>
 */
public class Pacotes {
    Integer pacoteId;
    Integer quantidadePessoas;
    Integer preco;
    Hoteis hotel;
    Passagem passagem;
    
    public Pacotes(Integer pacoteId, Integer passagemId, Integer hotelId, Integer quantidadePessoas, String para, String de, Integer valor, Date dataInicial,
            String nomeHotel, String localizacaoHotel, Integer pessoasPorQuarto ){
        
        try{
            passagem = new Passagem(passagemId, quantidadePessoas, para, de, dataInicial, valor);
            hotel = new Hoteis(hotelId, nomeHotel, localizacaoHotel, quantidadePessoas/pessoasPorQuarto, pessoasPorQuarto, valor);
            this.preco = valor;
            this.pacoteId = pacoteId;
            this.quantidadePessoas = quantidadePessoas;
            
        }catch(RemoteException e){
            System.out.println("Classe Pacotes: " + e);
        }
        
    }
    
    public Pacotes(Integer id, Passagem passagem, Hoteis hotel, Integer valor, Integer quantidadePessoas){
        this.pacoteId = id;
        this.passagem = passagem;
        this.hotel = hotel;
        this.quantidadePessoas = quantidadePessoas;
        this.preco = valor;
    }
    
}
