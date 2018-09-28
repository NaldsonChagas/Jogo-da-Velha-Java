package br.com.naldson.jogodavelha.jogo;

import java.util.Scanner;

public class BaseJogo {
	private char jogador1, jogador2;
	private char[] quadrantes = new char[9];
	private char jogadorAtivo;
	
	public void inicia() {
		System.out.println("Bem vindo ao jogo da velha");
		
		inicializaQuadrantes();
		escolheEntreXouO();
		desenhaBase();
		
		int x = 0;
		while (!jogoAcabou()) {
			if (x%2 == 0) {
				jogadorAtivo = jogador1;
				System.out.println("Vez de jogador 1");
			}
			else {
				jogadorAtivo = jogador2;
				System.out.println("Vez de jogador 2");
			}
			Scanner scanner = new Scanner(System.in);
			System.out.println("Escolha um número disponível");
			int numeroEscolhido = scanner.nextInt();
			
			if (!entradaInvalida(numeroEscolhido)) continue; 
			
			quadrantes[numeroEscolhido] = jogadorAtivo;
			
			desenhaBase();
			x++;
		}
		
	}
	
	private boolean entradaInvalida(int numeroEscolhido) {
		if (numeroEscolhido > 8 && numeroEscolhido < 0) {
			System.out.println("Entrada inválida. Escoha um número disponível");
			return false;
		}
		if (quadrantes[numeroEscolhido] == 'X' || quadrantes[numeroEscolhido] == 'O') {
			System.out.println("Local não disponível. Escolha um local válido");
			return false;
		}
		return true;
	}
	
	public boolean jogoAcabou() {
		if (temVencedorHorizontal()) return true;
		if (temVencedorDiagonal()) return true;
		if (temVencedorVertical()) return true;
		if (semVencedor()) return true;
		return false;
	}

	private boolean semVencedor() {
		for (int i = 0; i < quadrantes.length; i++) { 
			if (quadrantes[i] != 'X' || quadrantes[i] != 'O') {
				 return false;
			}
		}
		System.out.println("O jogo acabou sem vencedores");
		return true;
	}

	private boolean temVencedorVertical() {
		char valor;
		for (int i=0; i<3; i++) {
			for (int n=0; n < 2; n++) {
				valor = n == 0 ? 'X' : 'O';
				if (quadrantes[i] == valor && quadrantes[i+3] == valor && quadrantes[i+3+3] == valor) {
					anunciaVencedor();
					return true;
				}
			}
		}
		return false;
	}

	private boolean temVencedorDiagonal() {
		char valor;
		for (int i=0; i<2; i++) {
			valor = i == 0 ? 'X' : 'O';
			if ((quadrantes[0] == valor && quadrantes[4] == valor && quadrantes[8] == valor) ||
					(quadrantes[2] == valor && quadrantes[4] == valor && quadrantes[6] == valor)) {
				anunciaVencedor();
				return true;
			}
		}
		return false;
	}

	private boolean temVencedorHorizontal() {
		int x = 0;
		char valor;
		for (int i = 0; i < 3; i++) {
			for (int n=0; n < 2; n++) {
				valor = n == 0 ? 'X' : 'O';
				if (quadrantes[i+x] == valor && quadrantes[i+1+x] == valor && quadrantes[i+2+x] == valor) {
					anunciaVencedor();
					return true;
				}
			}
			x += 2;
		}
		return false;
	}
	
	private void anunciaVencedor() {
		if (jogador1 == 'X') System.out.println("O jogador 1 venceu. Parabéns!");
		else System.out.println("O jogador 2 venceu. Parabéns!");
	}

	private void escolheEntreXouO() {
		System.out.println("Jogador 1, escolha entre X (x) ou O (o)");
		Scanner scanner = new Scanner(System.in);
		
		jogador1 = scanner.next().toUpperCase().charAt(0);
		if ((jogador1 != 'x' && jogador1 == 'o') ||
				(jogador1 != 'o' && jogador1 == 'x')) {
			System.out.println("Esolha apenas entre X ou O");
			escolheEntreXouO();
		}
		jogador2 = (jogador1 == 'X' ? 'O' : 'X');
	}
	
	public void desenhaBase() {
		int x = 0;
		for (int i = 0; i < 3; i++) {
			System.out.println("   "+quadrantes[i+x]+"   |   "+quadrantes[i+x+1]+"   |   "+quadrantes[i+x+2]);
			if (i==0)
				System.out.println("--------------------");
			if (i==1)
				System.out.println("--------------------");
			
			x+=2;
		}
	}
	
	private void inicializaQuadrantes() {
		for (int i=0; i < quadrantes.length; i++) 
			quadrantes[i] = (char) (i+'0');
	}
}
