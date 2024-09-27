// Integrantes:
// João Pedro Souza 10400720
// Pedro Nomura 10401616
// Victor Vaglieri 10400787

// Historico
// 21/09/2024 - Victor: Criação do arquivo grafo.txt e modelagem do grafo
// 24/09/2024 - Pedro: Criação do menu, opções 1, 3, 4, 5, 6, 8, 9, 10 feitas, criação da classe Grafo com 
// metodos para as opções do menu
// 26/09/2024 - Pedro: Opções 2 e 7, metodos da classe Grafo relacionados a rotulos, relatório e repositorio do github


// FALTA:
// opcao 2
// historico
// print dos teste opcao 2
// colocar tudo no github




package Projeto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        // Declaração de variáveis usadas posteriormente
        Scanner scanner = new Scanner(System.in);
        int escolha;
        Grafo grafo = new Grafo(1);
        boolean check = false; // Variavel que impede que o usuario chame algumas opções antes do grafo ser criado
        
        while (true){ // Loop que deixa o usuario escolher uma opção do menu até desejar sair
            System.out.println("Relação entre Jogos"); 
            System.out.println("Escolha uma opção: ");
            System.out.println("1) Ler dados do arquivo texto");
            System.out.println("2) Gravar dados no arquivo texto");
            System.out.println("3) Inserir vértice no grafo");
            System.out.println("4) Inserir aresta no grafo");
            System.out.println("5) Remover vértice do grafo");
            System.out.println("6) Remover aresta do grafo");
            System.out.println("7) Mostrar conteúdo do arquivo");
            System.out.println("8) Mostrar grafo");
            System.out.println("9) Apresentar conexidade do grafo");
            System.out.println("10) Encerrar aplicação");
            
            escolha = scanner.nextInt();
            scanner.nextLine();
            while(escolha < 1 || escolha > 10){ // Loop que impede o usuario de inserir uma opção inválida
                System.out.println("Opção inválida, digite uma das opções do menu");
                escolha = scanner.nextInt();
                scanner.nextLine();
            }
            if (escolha == 1){
                try{
                    // Cria o grafo a partir do conteúdo do arquivo grafo.txt
                    Scanner scannerT = new Scanner(new File("Projeto\\\\grafo.txt"));
                    scannerT.nextLine();
                    int numV = Integer.parseInt(scannerT.nextLine()); // Quantidade de vertices do grafo
                    grafo = new Grafo(numV);
                    for (int i=0;i<numV;i++){ // Leitura dos rotulos dos vertices
                        grafo.inserirRotulo(scannerT.nextLine());
                    }
                    int numA = Integer.parseInt(scannerT.nextLine()); // Quantidade de arestas do grafo
                    while (scannerT.hasNextLine()){
                        String linha = scannerT.nextLine();
                        String vetLinha[] = linha.split(" ");
                        // Insere as arestas lidas do arquivo no grafo
                        grafo.insereA(Integer.parseInt(vetLinha[0]), Integer.parseInt(vetLinha[1]), Integer.parseInt(vetLinha[2]));
                    }
                    System.out.println("Grafo criado com sucesso a partir do arquivo");
                    check = true;
                    scannerT.close();
                }
                catch(IOException e){
                    System.out.println("Não foi possivel ler o arquivo");
                }
            }
            if (escolha == 2){
                 try{
                    // Escreve no arquivo grafo.txt as informações do grafo em memória
                    PrintWriter arquivo = new PrintWriter(new FileWriter("Projeto\\\\\\\\grafo.txt"));
                    grafo.escrever(arquivo);
                    arquivo.close();
                    System.out.println("Gravação no arquivo feita com sucesso");
                 }
                 catch(IOException e){
                    System.out.println("Não foi possivel escrever no arquivo");
                 }
                
            }
            if (escolha == 3 && check){
                // Insere um novo vertice no grafo
                System.out.println("Digite o nome do jogo: ");
                String nome = scanner.nextLine();
                grafo = grafo.inserirVertice(nome);

                System.out.println("Vertice adicionado com sucesso");
            }
            if (escolha == 4 && check){
                // Insere uma nova aresta no grafo
                System.out.println("Digite o primeiro vertice da aresta: ");
                int v1 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite o segundo vertice da aresta: ");
                int v2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite o valor da aresta: ");
                int a = scanner.nextInt();
                scanner.nextLine();
                grafo.insereA(v1, v2, a);
                System.out.println("Aresta inserida com sucesso");
            }
            if (escolha == 5 && check){ 
                // Remove um vertice do grafo
                System.out.println("Digite o vertice: ");
                int v = scanner.nextInt();
                scanner.nextLine();
                grafo.removeVertice(v);
                System.out.println("Vertice removido com sucesso");
            }
            if (escolha == 6 && check){
                // Remove uma aresta do grafo
                System.out.println("Digite o primeiro vertice da aresta: ");
                int v1 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite o segundo vertice da aresta: ");
                int v2 = scanner.nextInt();
                scanner.nextLine();
                grafo.removeA(v1, v2);
                System.out.println("Aresta removida com sucesso");
            }
            if (escolha == 7){
                try{
                    // Lê o arquivo e printa as informações dele
                    Scanner scannerT = new Scanner(new File("Projeto\\\\grafo.txt"));
                    System.out.print("Tipo do grafo: ");
                    String tipo = scannerT.nextLine();
                    System.out.println(tipo);                    
                    System.out.print("Quantidade de vertices: ");
                    int v = scannerT.nextInt();
                    scannerT.nextLine();
                    System.out.println(v);
                    System.out.println("Vertices(nomes dos jogos): ");
                    for (int i=0;i<v;i++){
                        System.out.println(scannerT.nextLine());
                    }
                    System.out.print("Quantidade de arestas: ");
                    System.out.println(scannerT.nextLine());
                    System.out.println("Arestas: ");
                    while (scannerT.hasNextLine()){
                        String linha = scannerT.nextLine();
                        String vetLinha[] = linha.split(" ");
                        System.out.println("Aresta conectando o vertice " + vetLinha[0] + " com o " + vetLinha[1] + " e peso " + vetLinha[2]);
                    }
                    System.out.println("Fim do arquivo");
                    scannerT.close();
                }
                catch(IOException e){
                    System.out.println("Não foi possivel ler o arquivo");
                }
            }
            if (escolha == 8 && check){
                // Mostra o conteúdo do grafo em forma de matriz de adjacência e os rotulos dos vertices
                grafo.show();
            }
            if (escolha == 9 && check){
                // Mostra a conexidade do grafo
                if (grafo.conexo() == 0)
                    System.out.println("Grafo é conexo");
                else
                    System.out.println("Grafo é não conexo");

            }
            if (escolha == 10){
                // Encerra o programa
                System.out.println("Programa encerrado");
                break;
            }
        }
    }
}
