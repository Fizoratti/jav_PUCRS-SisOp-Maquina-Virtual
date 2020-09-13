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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VM {

	public int memorySize;    
    public Word[] memory;     
    public CPU cpu;    
	public Aux aux;

    public VM(){									log.info("[ VM ]     Setup: Starting procedure . . .");
		memorySize = 128;							
		memory = new Word[memorySize];				log.info("[ Memory ] Setup: Starting new memory with size of {}", memorySize);

		initMemory(memory);							log.info("[ Memory ] Setup: Memory is set");

													log.debug("Memory value in memory[0]: {}", memory[0]);
													log.debug("Memory value in memory[1]: {}", memory[1]);
													log.debug("Memory value in memory[2]: {}", memory[2]);
													log.debug("Memory value in memory[3]: {}", memory[3]);

		cpu = new CPU(memory);						
		cpu.setContext(0, memorySize, 0);			log.info("[ VM ]     Setup: Context of CPU is set");
													log.info("[ VM ]     Setup: CPU is set");

		aux = new Aux();							log.info("[ VM ]     Setup: Aux is set");
		// aux.dump(memory);
	}
	

	/**
	 * Executa o conteúdo presente na memória (instruções e dados de um programa).
	 */
	private void run() {							log.info("[ VM ]     : Program is running . . .");
		// aux.dump(memory);
		cpu.run();
		// aux.dump(memory);
	}


	/**
	 * Inicia a máquina virtual carregando na memória e executando cada um dos programas.
	 * @param _programas a lista de programas que a mv deve executar
	 */
	public void init(Programa[] _programas) {		log.info("[ VM ]     : Virtual Machine running . . .\n");
		// Para cada programa da lista...
		for (Programa programa : _programas) {
			/* Carrega na memoria... */
			aux.carga(programa, memory);			log.info("[ VM ]     : Program successfully loaded");
			/* Executa o programa */				
            run();									log.info("[ VM ]     : Program ended\n");
        }
	}
	



	/**
	 * Inicia uma memória do zero preenchendo valores nulos em cada endereço.
	 * @param _memory instância de memória que ainda não possui conteúdo
	 */
	private void initMemory(Word[] _memory) {		log.info("[ Memory ] Setup: Assembling new memory...");
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
		/* Para cada endereço na memória... */		log.info("[ Memory ] Setup: Filling memory addresses...");
		for(int i = 0; i < _memory.length; i++) {
			// Escrever a palavra		
			_memory[i] = _word; 
		};
	}
   


	private boolean isMemoryEmpty(Word[] _memory) {	log.info("[ Memory ] Setup: Checking if memory is empty...");
		boolean isMemoryEmpty = true;

		// for (Word word : _memory) {
		// 	if(word != null) {
		// 		isMemoryEmpty = false;
		// 		break;
		// 	}
		// }
		
		return isMemoryEmpty;
	}

    /* END */

    private static Logger log = LoggerFactory.getLogger(CPU.class);

}