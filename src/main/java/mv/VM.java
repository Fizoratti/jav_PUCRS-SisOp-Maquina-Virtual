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
		initMemory(memory);
		System.out.println("Memory memory[0]: " + memory[0]);
		System.out.println("Memory memory[1]: " + memory[1]);
		System.out.println("Memory memory[2]: " + memory[2]);
		System.out.println("Memory memory[3]: " + memory[3]);
		cpu = new CPU(memory);
		aux = new Aux();
		aux.dump(memory);
	}
	

	/**
	 * Executa o conteúdo presente na memória (instruções e dados de um programa).
	 */
	private void run() {
		aux.dump(memory);
		cpu.run();
		aux.dump(memory);
	}


	/**
	 * Inicia a máquina virtual carregando na memória e executando cada um dos programas.
	 * @param _programas a lista de programas que a mv deve executar
	 */
	public void init(Programa[] _programas) {
		cpu.setContext(0, memorySize, 0);
		// Para cada programa da lista...
		for (Programa programa : _programas) {
			// Carrega na memoria...
			aux.carga(programa, memory);
			// Executa o programa
            run();
        }
	}
	



	/**
	 * Inicia uma memória do zero preenchendo valores nulos em cada endereço.
	 * @param _memory instância de memória que ainda não possui conteúdo
	 */
	private void initMemory(Word[] _memory) {
		// Caso a memória não tenha conteúdo
		if(isMemoryEmpty(_memory))
			// Preenche a memória com conteúdo nulo
			fillMemory(memory, new Word(Opcode.___,-1,-1,-1));
	}


	/**
	 * Sobrescreve todos os endereços da memória com a palavra recebida.
	 * @param _memory a memória da CPU
	 * @param _word	a palavra que será escrita em todos os endereços da memória
	 */
	private void fillMemory(Word[] _memory, Word _word) {
		// Para cada endereço na memória...
		for(int i = 0; i < _memory.length; i++) {
			// Escrever a palavra		
			_memory[i] = _word; 
		};
	}
   


	private boolean isMemoryEmpty(Word[] _memory) {
		boolean isMemoryEmpty = true;

		// for (Word word : _memory) {
		// 	if(word != null) {
		// 		isMemoryEmpty = false;
		// 		break;
		// 	}
		// }
		
		return isMemoryEmpty;
	}


}