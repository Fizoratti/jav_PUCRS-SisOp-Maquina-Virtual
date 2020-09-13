// PUCRS, Escola Politécnica, Engenharia de Software
// Disciplina Sistemas Operacionais
// Prof. Fernando Luís Dotti
// Trabalho - Parte I
//
// Código fornecido pelo professor como uma forma de resolver o enunciado
// Este código compila e executa na VM o pequeno programa ao final, com somente tres instrucoes diferentes.
// Alem das definicoes dos elementos solicitados, os cuidados de acesso valido a memoria, instrucoes validas,
// interrupcoes, o ciclo de instrucao com as tres fases, ja estao contemplados.
// Pede-se estudar e enteder este codigo. Os alunos podem adotar ideias parecidas.   
// Falta implementar as demais instrucoes da CPU, assim como os programas solicitados.
// Este trabalho tem menos de 200 linhas de código.
// A VM completa, construida pelo professor, incluindo o programa P1, tem 234 linhas.

package mv;
import java.util.Arrays;

public class VM {

	private class Aux {
		public void dump(Word w) {
			System.out.print("[ "); 
			System.out.print(w.opc); System.out.print(", ");
			System.out.print(w.r1);  System.out.print(", ");
			System.out.print(w.r2);  System.out.print(", ");
			System.out.print(w.p);   System.out.println("  ] ");
		}

		public void dump(Word[] m, int ini, int fim) {
			for (int i = ini; i < fim; i++) {
				System.out.print(i); System.out.print(":  ");  dump(m[i]);
			}
		}

		public void carga(Programa _p, Word[] _m) {
			Word[] p = _p.read();
			for (int i = 0; i < p.length; i++) {
				_m[i].opc = p[i].opc;     
				_m[i].r1 = p[i].r1;     
				_m[i].r2 = p[i].r2;     
				_m[i].p = p[i].p;
			}
		}
	}
	
	public int tamMem;    
    public Word[] m;     
    public CPU cpu;    
    public Aux aux;

    public VM(){
		tamMem = 1024;
		m = new Word[tamMem]; // m ee a memoria
		for (int i=0; i<tamMem; i++) { m[i] = new Word(Opcode.___,-1,-1,-1); };
		cpu = new CPU(m);
		aux = new Aux();
	}	


	// -------------------------------------------- teste da VM ,  veja classe de programas
	public void test1(){
		Programa p = Programas.p0;
		aux.carga(p, m);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(m, 0, 15);
	}

	public void p1(){
		Programa p = Programas.p1;
		aux.carga(p, m);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(m, 0, 32);
	}

	public void p2(){
		Programa p = Programas.p2;
		aux.carga(p, m);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(m, 0, 62);
	}

	public void p3(){
		Programa p = Programas.p3;
		aux.carga(p, m);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(m, 0, 62);
	}

	public void p4(){
		Programa p = Programas.p4;
		aux.carga(p, m);
		cpu.setContext(0, tamMem - 1, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(m, 0, 62);
	}
   
}