/*
 * Este sotfware foi feito para a UTFPR - Campus Curitiba;
 * O Código é livre para uso não comercial;
 * Desenvolvido através do Netbeans IDE.
 */
package com.marlonprudente.rmi.servidor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marlon Prudente <marlon.oliveira at alunos.utfpr.edu.br>
 */
public class Hoteis {
    Integer id = -1;
    List<Quarto> quartos = new ArrayList<>();
    Integer valorQuarto;
    String localizacao;
    String nome;
    Integer quartosDisponiveis;
    Integer quantidadePessoasPorQuarto;
    
    public Hoteis(Integer id, String nome, String localizacao, Integer quartos, Integer pessoasPorQuarto, Integer valorQuarto){
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.quartosDisponiveis = quartos;
        this.valorQuarto = valorQuarto;
        this.quantidadePessoasPorQuarto = pessoasPorQuarto;
        for(int i = 0; i< quartos;i++){
            this.quartos.add(new Quarto(pessoasPorQuarto));
        }        
    }
    
    public String getLocalizacao(){
        return this.localizacao;
    }
    public void setLocalizacao(String novaLocalizacao){
        this.localizacao = novaLocalizacao;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String novoNome){
        this.nome = novoNome;
    }
    
    public Integer getQuartosDisponiveis(){
        int quantidade = 0;
        for(Quarto q : quartos){
            if(!q.ocupado){
                quantidade++;
            }
        }
        return quantidade;
    }
    
    public void setQuartosDisponiveis(Integer novaQuantidade){
        this.quartosDisponiveis = novaQuantidade;
    }
    
    public Integer getQuantidadePessoasDisponiveis(){
                int quantidade = 0;
        for(Quarto q : quartos){
            if(!q.ocupado){
                quantidade += q.quantidadePessoas;
            }
        }
        return quantidade;
    }
    
    public void setQuantidadePessoasDisponiveis(Integer novaQuantidade){
        this.quantidadePessoasPorQuarto = novaQuantidade;
    }
    
    public List<Quarto> getQuartos(){
        return quartos;
    }
    
    public void setQuartos(List<Quarto> novaLista){
        this.quartos = novaLista;
    }

    
}
