package Projeto;

import java.io.PrintWriter;

public class Grafo {
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	int adj[][]; // matriz de adjacência
    private String rotulos[]; // rotulos dos vertices
    private int lenRotulos; // quantidade de rotulos

    public Grafo( int n) {
	    this.n = n;
	    this.m = 0; 
	    this.adj = new int [n][n];
        this.rotulos = new String[n];

		for(int i = 0; i< n; i++)
			for(int j = 0; j< n; j++)
				this.adj[i][j]=0;	
        
        this.visitados = new int[n];
	}

    public String getRotulo(int i){
        return rotulos[i];
    }

    public void setRotulos(String[] rotulosNovo, int len){
        for (int i=0;i<len;i++)
            rotulos[i] = rotulosNovo[i];
        lenRotulos = len;
    }
    public int getN(){
        return n;
    }
    public int getM(){
        return m;
    }

    public void insereA(int v, int w, int valor) {
        // Insere uma aresta no grafo caso não tivesse uma
	    if(adj[v][w] == 0 ){
	        adj[v][w] = valor;
            adj[w][v] = valor;
	        m++; 
	    }
	}
    public void removeA(int v, int w) {
        // Remove uma aresta existente do grafo
	    if(adj[v][w] != 0 ){
	        adj[v][w] = 0;
            adj[w][v] = 0;
	        m--; 
	    }
	}
    public void show() {
        // Mostra os rotulos dos vertices
        for (int i=0;i<lenRotulos;i++){
            System.out.println(rotulos[i]);
        }
        // Mostra o grafo em forma de matriz de adjacência
	    System.out.println("Quantidade de vértices: " + n );
	    System.out.println("Quantidade de arestas: " + m );
	    for( int i=0; i < n; i++){
	    	System.out.print("\n");
	        for( int w=0; w < n; w++)
	            if(adj[i][w] != 0)
	            	System.out.print("Adj[" + i + "," + w + "]= " + adj[i][w] + " ");
	            else System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo." );   
	}

    public void inserirRotulo(String nome){ // Insere um rotulo no vetor rotulos
        rotulos[lenRotulos] = nome;
        lenRotulos++;
    }
    private void removeRotulo(int indice){ // Remove um rotulos do vetor rotulos
        for (int i=indice;i<n-1;i++)
			rotulos[i] = rotulos[i+1];
        lenRotulos--;
    }

    public Grafo inserirVertice(String nome){
        // Cria um novo grafo com um vértice a mais e copia as informações do outro
        Grafo grafo = new Grafo(n+1);
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (adj[i][j] != 0){
                    grafo.insereA(i, j, adj[i][j]);
                }
            }
        }
        // Copia os rotulos para o novo grafo e adiciona o do novo vertice
        grafo.setRotulos(rotulos, lenRotulos);
        grafo.inserirRotulo(nome);
        return grafo;
    }


    public void removeVertice(int v){
        // Remove um vertice do grafo
		int cont=0;
		for(int i=0;i<n;i++) // Conta a quantidade de arestas relacionadas àquele vertice
			if (adj[v][i] != 0)
				cont++;

        // Muda a posição dos vertices da matriz para 'cobrir o buraco' da remoção do vertice
		for (int i=v;i<n-1;i++)
			adj[i] = adj[i+1];

		for (int i=v;i<n-1;i++)
			for (int j=0;j<n;j++)
				adj[j][i] = adj[j][i+1];
		
        // Atualiza a quantidade de arestas e vertices
		n--;
		m-=cont;

        removeRotulo(v); // Remove o rotulo referente ao vertice removido 
	}

    private int visitados[];
	private int cont;
	private void recur(int i){ // Função recursiva que percorre todos os vertices a partir de um, em profundidade
		for (int j=0;j<n;j++){
			if (adj[i][j] != 0){
				boolean flag = true;
				for (int k=0;k<cont;k++){
					if (visitados[k] == j){
						flag = false;
						break;
					}
				}
				if (flag){
					visitados[cont] = j;
					cont++;
					recur(j);
				}
			}
		}
	}
    public int conexo(){
        // Verifica usando a função recursiva se todos os vertices são atingiveis a partir do primeiro, 
        // caso sim, retorna que o grafo é conexo
		for (int i=0;i<n;i++)
			visitados[i] = 0;
		cont = 1;
		recur(0);
		if (cont == n)
			return 0;
		return 1;
    }

    public void escrever(PrintWriter arquivo){
        // A partir dos rotulos e da matriz de adjacência, escreve no arquivo todas as informações do grafo
        arquivo.println("2");
        arquivo.println(n);
        for (int i=0;i<n;i++){
            arquivo.println(rotulos[i]);
        }
        arquivo.println(m);
        for (int i=0;i<n;i++){
            for (int j=i;j<n;j++){
                if (adj[i][j] != 0){
                    arquivo.print(i);
                    arquivo.print(" ");
                    arquivo.print(j);
                    arquivo.print(" ");
                    arquivo.println(adj[i][j]);
                }
            }
        }
    }
}
