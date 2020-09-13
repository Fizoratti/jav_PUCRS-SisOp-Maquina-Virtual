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

public class VM {

	public int memorySize;    
    public Word[] memory;     
    public CPU cpu;    
    public Aux aux;

    public VM(){
		memorySize = 1024;
		memory = new Word[memorySize];
		for (int i=0; i<memorySize; i++) { 
			memory[i] = new Word(Opcode.___,-1,-1,-1); 
		};
		cpu = new CPU(memory);
		aux = new Aux();
	}	


	// -------------------------------------------- teste da VM ,  veja classe de programas
	public void test1(){
		Programa p = Programas.p0;
		aux.carga(p, memory);
		cpu.setContext(0, memorySize, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(memory, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(memory, 0, 15);
	}

	public void p1(){
		Programa p = Programas.p1;
		aux.carga(p, memory);
		cpu.setContext(0, memorySize, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(memory, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(memory, 0, 32);
	}

	public void p2(){
		Programa p = Programas.p2;
		aux.carga(p, memory);
		cpu.setContext(0, memorySize, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(memory, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(memory, 0, 62);
	}

	public void p3(){
		Programa p = Programas.p3;
		aux.carga(p, memory);
		cpu.setContext(0, memorySize, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(memory, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(memory, 0, 62);
	}

	public void p4(){
		Programa p = Programas.p4;
		aux.carga(p, memory);
		cpu.setContext(0, memorySize, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(memory, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(memory, 0, 62);
	}
   
}