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
	
	private void run(Programa _programa) {
		Programa p = _programa;
		aux.carga(p, memory);
		cpu.setContext(0, memorySize, 0);
		System.out.println("---------------------------------- programa carregado ");
		aux.dump(memory, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		cpu.run();
		aux.dump(memory, 0, 63);
	}

	public void init(Programa[] _programas) {
		for (Programa p : _programas) {
            run(p);
        }
	}
   
}