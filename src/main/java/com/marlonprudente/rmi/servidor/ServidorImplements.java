/*
 * Este sotfware foi feito para a UTFPR - Campus Curitiba;
 * O Código é livre para uso não comercial;
 * Desenvolvido através do Netbeans IDE.
 */
package com.marlonprudente.rmi.servidor;

import com.marlonprudente.rmi.interfaces.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Marlon Prudente <marlon.oliveira at alunos.utfpr.edu.br>
 */
public class ServidorImplements extends UnicastRemoteObject implements Servidor {
    
    List<InteressePacote> interessesPacote = new ArrayList<>();
    List<InteressePassagem> interessesPassagem = new ArrayList<>();
    List<InteresseQuarto> interessesQuarto = new ArrayList<>();
    
    List<Passagem> passagens = new ArrayList<>();
    List<Hoteis> hoteis = new ArrayList<>();
    List<Pacotes> pacotes = new ArrayList<>();
    
    
    protected ServidorImplements() throws RemoteException{
        super();
    }

    @Override
    public List<String> ConsultarPassagens() throws RemoteException {
        System.out.println(">Consultando todas as Passagens<");
        List<String> lista = new ArrayList<>();
        
        for(Passagem p : passagens){
            lista.add(p.id + "/para: " + p.getPara() + "/de: " + p.getDe() + "/dataPartida: " + p.getDataInicial().toString() + "/valor: " + p.getValor() + "/Acentos disponiveis: " + p.getPoltronas());
        }
        
        return lista;
    }
    @Override
    public List<String> ConsultarPassagens(String para, String de, Date dataInicial, Date dataRetorno, Integer acentos) throws RemoteException {
        System.out.println(">Consultando Passagens de ida e volta<");
        List<String> lista = new ArrayList<>();
        
        for(Passagem p : passagens){
            if(p.idaEVolta){
                if(p.getPara().equalsIgnoreCase(para) && p.getDe().equalsIgnoreCase(de) && p.getDataRetorno() == dataRetorno && p.getDataInicial() == dataInicial && p.poltronas >= acentos){
                    lista.add(p.id + "/para: " + p.getPara() + "/de: " + p.getDe() + "/dataPartida: " + p.getDataInicial().toString() + "/valor: " + p.getValor() + "/Acentos disponiveis: " + p.getPoltronas());
                }
            }
        }
        return lista;        
    }
    @Override
    public List<String> ConsultarPassagens(String para, String de, Date dataInicial, Integer acentos) throws RemoteException {
        System.out.println(">Consultando Passagens de ida<");
        List<String> lista = new ArrayList<>();
        
        for(Passagem p : passagens){
                if(p.getPara().equalsIgnoreCase(para) && p.getDe().equalsIgnoreCase(de) && p.getDataInicial() == dataInicial && p.poltronas >= acentos){
                    lista.add(p.id + "/para: " + p.getPara() + "/de: " + p.getDe() + "/dataPartida: " + p.getDataInicial().toString() + "/valor: " + p.getValor() + "/Acentos disponiveis: " + p.getPoltronas());
                }            
        }
        return lista;
    }

    @Override
    public List<String> ConsultarHoteis() throws RemoteException {
        System.out.println(">Consultando Hoteis<");
        List<String> lista = new ArrayList<>();
        
        for(Hoteis h : hoteis){
            lista.add(h.id + "/Nome: " + h.getNome() + "/Localização: " + h.getLocalizacao() + "/QuartosDisponiveis: " + h.getQuartosDisponiveis() + "/Pessoas por quarto: " + h.getQuantidadePessoasDisponiveis());
        }
        return lista;
    }

    @Override
    public List<String> ConsultarPacotes() throws RemoteException {
        System.out.println(">Consultando Pacotes<");
        List<String> lista = new ArrayList<>();
        
        for(Pacotes p : pacotes){
            lista.add(p.pacoteId + "/Para: " + p.passagem.para + "/De:" + p.passagem.de + "/Data: " + p.passagem.dataInicial.toString() + 
                    "/Localizacao Hotel: " + p.hotel.localizacao + "/Hotel Nome: " + p.hotel.nome + "/Capacidade de pessoas no Quarto: " + p.hotel.quantidadePessoasPorQuarto
            + "/Quartos Disponiveis: " + p.hotel.quartosDisponiveis + "/Valor: " + p.preco + "/QuantidadePessoas: " + p.quantidadePessoas);
        }
        return lista;
    }

    @Override
    public void AdicionarHotel(Integer id, String nome, String localizacao, Integer quartos, Integer pessoasPorQuarto, Integer preco) throws RemoteException {
        System.out.println(">Adicionando novo Hotel<");
        Hoteis novoHotel = new Hoteis(id, nome, localizacao, quartos, pessoasPorQuarto, preco);
        hoteis.add(novoHotel);
        
        for(InteresseQuarto iq : interessesQuarto){
            if(iq.valorMaximo >= preco && localizacao.equalsIgnoreCase(iq.localizacao) && pessoasPorQuarto*quartos >= iq.quantidadePessoas){
                System.out.println("Interesse em quarto encontrado");
                iq.cliente.Notificacao(id + "/Nome: " + nome + "/Localização: " + localizacao + "/Quartos: " + quartos + "/Valor: " + preco + 
                        " => Este quarto é de seu interesse!");
            }
        }
        //verificaInteressePacotes();
    }

    @Override
    public void AdicionarPassagem(Integer id, Integer acentosDisponiveis, String para, String de, Date dataVoo, Integer preco) throws RemoteException {
        System.out.println(">Adicionando nova Passagem<");
        Passagem novaPassagem = new Passagem(id, acentosDisponiveis, para, de, dataVoo, preco);
        passagens.add(novaPassagem);
        
        for(InteressePassagem ip : interessesPassagem){
            if(ip.valorMaximo >= preco && ip.para.equalsIgnoreCase(para) && ip.de.equalsIgnoreCase(de)){
                System.out.println("Interesse em passagem encontrado");
                ip.cliente.Notificacao(id + "/Para: " + para + "/De: " + de + "/Data: " + dataVoo.toString() + "/Valor: " + preco + " => Esta passagem é de seu interesse!");
            }
        }
        //verificaInteressePacotes();
    }

    @Override
    public void AdicionarPacote(Integer id, Integer passagemId, Integer quantidadePessoas, String para, String de, Integer preco, Date dataVoo, Integer hotelId, String hotelNome, String HotelLocalizacao, Integer pessoasPorQuarto) throws RemoteException {
       System.out.println(">Adicionando novo Pacote<");
       Pacotes pacote = new Pacotes(id, passagemId, hotelId, quantidadePessoas, para, de, preco, dataVoo, hotelNome, HotelLocalizacao, pessoasPorQuarto);
       pacotes.add(pacote);
       verificaInteressePacotes(preco);
    }

    @Override
    public void AdicionarPacote(Integer id, Integer passagemId, Integer hotelId, Integer quantidadePessoas, Integer preco) throws RemoteException {
        System.out.println(">Adicionando novo Pacote<");
        Passagem passagemExistente = null;
        Hoteis hotelExistente = null;
        for(Passagem p : passagens){
            if(p.id == passagemId){
                passagemExistente = p;
            }
        }
        for(Hoteis h : hoteis){
            if(h.id == hotelId){
                hotelExistente = h;
            }
        }
        Pacotes pacote = new Pacotes(id,passagemExistente, hotelExistente, quantidadePessoas, preco);
        pacotes.add(pacote);
        verificaInteressePacotes(preco);
        
    }

    @Override
    public synchronized boolean VenderPassagem(Integer id, Integer acentos) throws RemoteException {
        System.out.println(">Vendendo Passagem " + id + "com " + acentos + " poltronas.<");
        for(Passagem p : passagens){
            if(p.id.equals(id)){
                if(p.poltronas < acentos){
                    return false;
                }
                p.poltronas = p.poltronas - acentos;
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean VenderQuartos(Integer id, Integer quantidadePessoas, Date dataCheckIn, Date dataCheckOut) throws RemoteException {
        System.out.println("Vendendo quartos no hotel de id" + id + " /DataCheckIn: " + dataCheckIn.toString() + " /DataCheckOut: " + dataCheckOut.toString());
        int quantidade = quantidadePessoas;
        for(Hoteis h : hoteis){
            if(h.getQuantidadePessoasDisponiveis() >= quantidadePessoas && h.id == id){
                for(Quarto q: h.getQuartos()){
                    if(q.ocupado){
                        quantidade -= q.quantidadePessoas;
                    }
                    if(quantidade <= 0){
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean VenderPacote(Integer passagemId, Integer hotelId, Integer quantidadePessoas, Date dataInicial, Date dataFinal) throws RemoteException {
        Pacotes remover = null;
        for(Pacotes p : pacotes){
            if(p.passagem.id.equals(passagemId)  && p.hotel.id.equals(hotelId)){
                remover = p;
            }
        }
        if(remover != null){
            System.out.println("Vendendo pacote!");
            VenderPassagem(passagemId, quantidadePessoas);
            VenderQuartos(hotelId, quantidadePessoas, dataInicial, dataFinal);
            pacotes.remove(remover);
        }


        return true;
    }

    @Override
    public boolean RegistrarInteressePassagem(String para, String de, Integer valorMaximo, String cliente) throws RemoteException {
        System.out.println("Registrando interesse em passagem!");
        Registry referenciaServicoNomes = LocateRegistry.getRegistry(2000);
        Cliente novoCliente = null;
        try{
            novoCliente = (Cliente)referenciaServicoNomes.lookup(cliente);
        }catch(NotBoundException e){
            System.out.println("RegistrarInteressePassagem: " + e);
        }
        interessesPassagem.add(new InteressePassagem(de, para, valorMaximo, novoCliente));
        return true;
    }

    @Override
    public boolean RemoverInteressePassagem(String para, String de, Integer valorMaximo, String cliente) throws RemoteException {
        System.out.println("Removendo interesse em passagem!");
        InteressePassagem ip = null;
        
        for(InteressePassagem intP : interessesPassagem){
            if(intP.para.equalsIgnoreCase(para) && intP.de.equalsIgnoreCase(de) && intP.valorMaximo.equals(valorMaximo)){
                ip = intP;
            }
        }
        if(ip == null){
            return false;
        }else{
            System.out.println("Interesse em passagem removido.");
            interessesPassagem.remove(ip);
            return true;
        }
    }

    @Override
    public boolean RegistrarInteresseQuarto(String localizacao, Integer quantidadePessoas, Integer valorMaximo, String cliente) throws RemoteException {
        System.out.println("Registrando interesse em quarto!");
        Registry referenciaServicoNomes = LocateRegistry.getRegistry(2000);
        Cliente novoCliente = null;
        try{
            novoCliente = (Cliente)referenciaServicoNomes.lookup(cliente);
        }catch(NotBoundException e){
            System.out.println("RegistrarInteresseQuarto: " + e);
        }
        interessesQuarto.add(new InteresseQuarto(localizacao, valorMaximo, quantidadePessoas, novoCliente));
        return true;
    }

    @Override
    public boolean RemoverInteresseQuarto(String localizacao, Integer quantidadePessoas, Integer valorMaximo, String cliente) throws RemoteException {
        System.out.println("Remover interesse em Quarto!");
        InteresseQuarto intQ = null;
        for(InteresseQuarto iq : interessesQuarto){
            if(iq.localizacao.equalsIgnoreCase(localizacao) && iq.quantidadePessoas == quantidadePessoas && iq.valorMaximo == valorMaximo){
                System.out.println("Removido interesse em quarto!");
                intQ = iq;                
            }
        }
        if(intQ == null){
            return false;
        }else{
            interessesQuarto.remove(intQ);
            return true;
        }        
    }

    @Override
    public boolean RegistrarInteressePacote(String para, String de, String localizacao, Integer valorMaximo, Integer quantidadePessoas, String cliente) throws RemoteException {
        System.out.println("Registrando interesse em pacote!");
        Registry referenciaServicoNomes = LocateRegistry.getRegistry(2000);
        Cliente novoCliente = null;
        try{
            novoCliente = (Cliente)referenciaServicoNomes.lookup(cliente);
        }catch(NotBoundException e){
            System.out.println("RegistrarInteressePacote: " + e);
        }
        interessesPacote.add( new InteressePacote(para, de, localizacao, valorMaximo, quantidadePessoas, novoCliente));
        verificaInteressePacotes(valorMaximo);
        return true;
    }

    @Override
    public boolean RemoverInteressePacote(String para, String de, String localizacao, Integer valorMaximo, Integer quantidadePessoas) throws RemoteException {
        System.out.println("Removendo Interesse em Pacote!");
        InteressePacote intPack = null;
        for(InteressePacote ip : interessesPacote){
            if(ip.para.equalsIgnoreCase(para) && ip.de.equalsIgnoreCase(de) && ip.localizacao.equalsIgnoreCase(localizacao) && ip.valorMaximo == valorMaximo && ip.quantidadePessoas == quantidadePessoas){
                intPack = ip;
            }
        }
        if(intPack == null){
            return false;
        }else{
            interessesPacote.remove(intPack);
            return true;
        }
    }
    
    public void verificaInteressePacotes(int valorMaximo) throws RemoteException {

        boolean encontreiPassagens = false;
        boolean encontreiHotel = false;
        InteressePacote remover = null;
        for (InteressePacote ip : interessesPacote) {

            for (Passagem p : passagens) {
                if (ip.para.equalsIgnoreCase(p.para) && ip.de.equalsIgnoreCase(p.de)) {
                    encontreiPassagens = true;
                }
            }
            for (Hoteis h : hoteis) {
                if (ip.localizacao.equalsIgnoreCase(h.localizacao) && ip.quantidadePessoas <= h.getQuantidadePessoasDisponiveis()) {
                    encontreiHotel = true;
                }
            }
            if (encontreiPassagens && encontreiHotel) {
                remover = ip;
            }
        }
        if (remover != null) {

            if (remover.valorMaximo <= valorMaximo) {
                remover.cliente.Notificacao("Pacote de seu interesse foi encontrado! - Para: " + remover.para + "/Valor:" + valorMaximo);
                interessesPacote.remove(remover);
            }

        }
    }
    
    public void removePassagem(Passagem p){
        System.out.println("Removendo passagem de ID: " + p.id);
        passagens.remove(p);
    }
    public void removePacote(Pacotes p){
        System.out.println("Removendo pacote de ID: " + p.pacoteId);
        pacotes.remove(p);
    }
    public void removeHotel(Hoteis h){
        System.out.println("Removendo hotel de ID: " + h.id);
        hoteis.remove(h);
    }
}
