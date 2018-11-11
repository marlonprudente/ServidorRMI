/*
 * Este sotfware foi feito para a UTFPR - Campus Curitiba;
 * O Código é livre para uso não comercial;
 * Desenvolvido através do Netbeans IDE.
 */
package com.marlonprudente.rmi.servidor;

import com.marlonprudente.rmi.interfaces.Servidor;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Marlon Prudente <marlon.oliveira at alunos.utfpr.edu.br>
 */
public class Main {
    int idIndex = 0;
    
    public static void main(String[] args) {
        Servidor servidor = null;
        try{
            servidor = new ServidorImplements();
        }catch(RemoteException e){
            System.out.println("Main: " + e);
        }
        
        try{
            Registry servicoNomesRMI = LocateRegistry.createRegistry(2000);
            servicoNomesRMI.rebind("servidor", servidor);
            
            Integer id, poltronas,quartos, pessoasPorQuarto, valor = null;
            String de, para, localizacao, nomeHotel = null;
            Date dataIda = null, dataVolta = null;
            
            servidor.AdicionarPassagem(1, 50, "SP", "CWB", Date.from(Instant.now()), 50);
            servidor.AdicionarHotel(15, "MãeJoanaHotel", "SP", 10, 3, 50);
            
            while(true){
                System.out.println("1 - Adicionar Passagem");
                System.out.println("2 - Adicionar Hotel");
                System.out.println("3 - Adicionar Pacote de Viagens");
                System.out.println("4 - Visualizar todas as passagens");
                System.out.println("5 - Visualizar todos os Hoteis");
                System.out.println("6 - Visualizar todos os pacotes");
                Scanner scanner = new Scanner(System.in);
                Integer op = scanner.nextInt();
                String input;
                switch(op){
                    case 1:
                        System.out.println("Digite o id da viagem: ");
                        input = scanner.next();
                        id = Integer.valueOf(input);                        
                        System.out.println("Digite a quantidade de poltronas: ");
                        input = scanner.next();
                        poltronas = Integer.valueOf(input);
                        System.out.println("Digite o local de partida: ");
                        input = scanner.next();
                        de = input;
                        System.out.println("Digite o destino da viagem: ");
                        input = scanner.next();
                        para = input;
                        System.out.println("Digite a data de viagem (dd/mm/yyyy)");
                        input = scanner.next();
                        try {
                            DateFormat df = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
                            dataIda = df.parse(input);
                            } catch (ParseException e) {
                            System.out.println("Erro ao Inserir data de viagem: " + e);
                            }
                        System.out.println("Digite o valor da passagem: ");
                        input = scanner.next();
                        valor = Integer.valueOf(input);
                        System.out.println("Inserindo passagem...");
                        servidor.AdicionarPassagem(id, poltronas, para, de, dataIda, valor);
                        break;
                    case 2:
                        System.out.println("Digite o id do Hotel: ");
                        input = scanner.next();
                        id = Integer.valueOf(input);
                        System.out.println("Digite o nome do Hotel: ");
                        input = scanner.next();
                        nomeHotel = input;
                        System.out.println("Digite a localidade do Hotel: ");
                        input = scanner.next();
                        localizacao = input;
                        System.out.println("Digite a quantidade de quartos: ");
                        input = scanner.next();
                        quartos = Integer.valueOf(input);
                        System.out.println("Digite a quantidade de pessoas por quarto: ");
                        input = scanner.next();
                        pessoasPorQuarto = Integer.valueOf(input);
                        System.out.println("Digite o valor: ");
                        input = scanner.next();
                        valor = Integer.valueOf(input);
                        System.out.println("Inserindo Hotel...");
                        servidor.AdicionarHotel(id, nomeHotel, localizacao, quartos, pessoasPorQuarto, valor);
                        break;
                    case 3:
                        Integer passagemId, hotelId;
                        System.out.println("Digite o id do pacote: ");
                        input = scanner.next();
                        id = Integer.valueOf(input);
                        System.out.println("Digite o id da Passagem: ");
                        input = scanner.next();
                        passagemId = Integer.valueOf(input);
                        System.out.println("Digite o id do hotel: ");
                        input = scanner.next();
                        hotelId = Integer.valueOf(input);
                        System.out.println("Digite a quantidade de pessoas: ");
                        input = scanner.next();
                        poltronas = Integer.valueOf(input);
                        System.out.println("Digite o valor do Pacote: ");
                        input = scanner.next();
                        valor = Integer.valueOf(input);
                        System.out.println("Inserindo pacote...");
                        servidor.AdicionarPacote(id, passagemId, hotelId, poltronas, valor);
                        break;
                    case 4:
                        System.out.println("Lista de passagens: ");
                        for(String passagem : servidor.ConsultarPassagens()){
                            System.out.println(passagem);
                        }
                        break;
                    case 5:
                        System.out.println("Lista de hoteis: ");
                        for(String hotel : servidor.ConsultarHoteis()){
                            System.out.println(hotel);
                        }
                        break;
                    case 6:
                        System.out.println("Lista de pacotes: ");
                        for(String pacote : servidor.ConsultarPacotes()){
                            System.out.println(pacote);
                        }
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
                
            }
            
            
            
            
            
        }catch(RemoteException e){
            System.out.println("Main: " + e);
        }
        
    }
}
