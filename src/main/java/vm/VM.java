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

package vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VM {

	public int memorySize;    
    public Word[] memory;     
    public CPU cpu;    
	public Aux aux;

	public VM(){									log.info("{} (Setup) Starting procedure . . .", VM.mark);
	
		aux = new Aux();							log.info("{} (Setup) Aux is set", VM.mark);

		memorySize = 128;							
		memory = new Word[memorySize];				log.info("{} (Setup) Starting new memory with size of {}", Aux.mark, memorySize);
		initMemory(memory);							log.info("{} (Setup) Memory is set", Aux.mark);

		cpu = new CPU(memory);						
		cpu.setContext(0, memorySize, 0);			log.info("{} (Setup) Context of CPU is set", CPU.mark);
													log.info("{} (Setup) CPU is set", CPU.mark);
		// aux.dump(memory);
	}
	

	/**
	 * Executa o conteúdo presente na memória (instruções e dados de um programa).
	 */
	private void run() {							log.info("{} Program is running . . .", VM.mark);
		// aux.dump(memory);
		cpu.run();
		// aux.dump(memory);
	}


	/**
	 * Inicia a máquina virtual carregando na memória e executando cada um dos programas.
	 * @param _programs a lista de programas que a mv deve executar
	 */
	public void init(Program[] _programs) {			log.info("{} Virtual Machine running . . .\n", VM.mark);
		// Para cada programa da lista...
		for (Program program : _programs) {
			/* Carrega na memoria... */
			aux.carga(program, memory);				log.info("{} Program successfully loaded", VM.mark);
			
			/* Executa o programa */				
            // run(program.processID);									log.info("{} Program ended\n", VM.mark);
            run();									log.info("{} Program ended\n", VM.mark);

			/* Sempre que der carga na memoria, aumentar o processID */
			// program.processID++;
        }
	}
	



	/**
	 * Inicia uma memória do zero preenchendo valores nulos em cada endereço.
	 * @param _memory instância de memória que ainda não possui conteúdo
	 */
	private void initMemory(Word[] _memory) {		log.info("{} (Setup) Assembling new memory...", Aux.mark);
		// Caso a memória não tenha conteúdo
		if(isMemoryEmpty(_memory)) {
			// Preenche a memória com conteúdo nulo
			Word blankWord = new Word(Opcode.___,-1,-1,-1);
			fillMemory(memory, blankWord);
		}
	}


	/**
	 * Sobrescreve todos os endereços da memória com a palavra recebida.
	 * @param _memory a memória da CPU
	 * @param _word	a palavra que será escrita em todos os endereços da memória
	 */
	private void fillMemory(Word[] _memory, Word _word) {
													log.info("{} (Setup) Filling memory addresses...", Aux.mark);
		/* Para cada endereço na memória... */
		for(int i = 0; i < _memory.length; i++) {
			Word blankWord = Word.copy(_word);
			/* Escrever a palavra em branco (sem conteúdo) */
			_memory[i] = blankWord; 
		};
	}
   


	private boolean isMemoryBlank(Word[] _memory) {	log.info("{} (Setup) Checking if memory is blank...", Aux.mark);
		boolean isMemoryBlank = false;

		for (int i = 0; i < _memory.length; i++) {
			/* Se o endereço da memória for 'blank' */
			if(Word.equals(_memory[i], new Word(Opcode.___,-1,-1,-1))) {
				isMemoryBlank = true;				log.debug("{} (Setup) Memory is blank", Aux.mark);
				break;
			}
		}
		
		return isMemoryBlank;
	}

	private boolean isMemoryEmpty(Word[] _memory) {	log.info("{} (Setup) Checking if memory is empty...", Aux.mark);
		boolean isMemoryEmpty = true;

		for (int i = 0; i < _memory.length; i++) {
			if(_memory[i] != null) {
				isMemoryEmpty = false;				log.debug("{} (Setup) Memory is empty", Aux.mark);
				break;
			}
		}
		
		return isMemoryEmpty;
	}

	/* END */
	
	public static String mark = "    [ VM ] :";

    private static Logger log = LoggerFactory.getLogger(CPU.class);

}