//package br.com.bento.serv_cadastro_endereco.service;
//
//import br.com.bento.serv_cadastro_endereco.domain.ConsultaCep;
//import br.com.bento.serv_cadastro_endereco.domain.model.dto.EnderecoDTO;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class gerarCEP {
//
//    Scanner leitura = new Scanner(System.in);
//    ConsultaCep consultaCep = new ConsultaCep();
//
//        System.out.println("Digite um número de CEP para consulta:");
//    var cep = leitura.nextLine();
//
//        try {
//        EnderecoDTO novoEndereco = consultaCep.buscaEndereco(cep);
//        System.out.println(novoEndereco);
//        GeradorDeArquivo gerador = new GeradorDeArquivo();
//        gerador.salvaJson(novoEndereco);
//    } catch (RuntimeException | IOException e) {
//        System.out.println(e.getMessage());
//        System.out.println("Finalizando a aplicação");
//    }
//
//}
//
//
//
