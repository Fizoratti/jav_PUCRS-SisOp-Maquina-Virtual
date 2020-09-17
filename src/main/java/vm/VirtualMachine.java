package vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import os.OperatingSystem;

public class VirtualMachine {

    private static VirtualMachine INSTANCE;

	// public int memorySize;    
	// public Word[] memory;
	public Memory memory;
	public CPU cpu;
	public OperatingSystem os;

	private VirtualMachine() {						log.info("{} {} Starting procedure . . .", Tag.VM, Tag.SETUP);
	
		// aux = new Aux();							log.info("{} {} "+Tag.green("Aux is set"), Tag.VM, Tag.SETUP);

		// Experimentando
		Memory.createMemory(1024);						log.info("{} {} Starting new memory with size of {}", Tag.MEMORY, Tag.SETUP, 1024);
		memory = Memory.get();
		setMemory(memory);							log.info("{} {} "+Tag.green("Memory is set"), Tag.MEMORY, Tag.SETUP);

		cpu = new CPU(memory);						
		cpu.setContext(0, memory.size, 0);			log.info("{} {} Context of CPU is set", Tag.CPU, Tag.SETUP);
													log.info("{} {} "+Tag.green("CPU is set"), Tag.CPU, Tag.SETUP);
		
		// memorySize = 128;							
		// memory = new Word[memorySize];				log.info("{} {} Starting new memory with size of {}", Tag.MEMORY, Tag.SETUP, memorySize);
		// initMemory(memory);							log.info("{} {} "+Tag.green("Memory is set"), Tag.MEMORY, Tag.SETUP);
		
		// cpu.setContext(0, memorySize, 0);			log.info("{} {} Context of CPU is set", Tag.CPU, Tag.SETUP);
		// cpu = new CPU(memory);						

		// aux.dump(memory);
	}
	

	/**
	 * Executa o conteúdo presente na memória (instruções e dados de um programa).
	 */
	private void run() {							log.info("{} Program is running . . .", Tag.VM);
		// aux.dump(memory);
		cpu.run();
		// aux.dump(memory);
	}


	/**
	 * Inicia a máquina virtual carregando na memória e executando cada um dos programas.
	 * @param _programs a lista de programas que a mv deve executar
	 */
	public void init(Program[] _programs) {			log.info("{} Virtual Machine running . . .\n", Tag.PROGRAM);
		// Para cada programa da lista...
		for (Program program : _programs) {
			/* Carrega na memoria... */
			Aux.carga(program, memory);				log.info("{} "+Tag.green("Program successfully loaded"), Tag.PROGRAM);
			
			/* Executa o programa */				
            // run(program.processID);									log.info("{} Program ended\n", Tag.VM);
            run();									log.info("{} "+Tag.red("Program ended")+"\n", Tag.PROGRAM);

			/* Sempre que der carga na memoria, aumentar o processID */
			// program.processID++;
        }
	}

	public void init(OperatingSystem _os) {			log.info("{} Virtual Machine running . . .\n", Tag.VM);
		this.os = _os;
		os.start();
	}



	public void stop() {							log.info("{} "+Tag.red("Virtual Machine stopped")+"\n", Tag.VM);
		
	}



	// /**
	//  * Inicia uma memória do zero preenchendo valores nulos em cada endereço.
	//  * @param _memory instância de memória que ainda não possui conteúdo
	//  */
	// private void initMemory(Word[] _memory) {		log.info("{} {} Assembling new memory...", Tag.MEMORY, Tag.SETUP);
	// 	// Caso a memória não tenha conteúdo
	// 	if(isMemoryEmpty(_memory)) {
	// 		// Preenche a memória com conteúdo nulo
	// 		fillMemory(memory, Word.BLANK);
	// 	}
	// }

	private void setMemory(Memory _memory) {
		if(isMemoryEmpty(_memory)) {
			fillMemory(_memory, Word.BLANK);
		}
	}


	/**
	 * Sobrescreve todos os endereços da memória com a palavra recebida.
	 * @param _memory a memória da CPU
	 * @param _word	a palavra que será escrita em todos os endereços da memória
	 */
	private void fillMemory(Memory _memory, Word _word) {
													log.info("{} {} Filling memory addresses...", Tag.MEMORY, Tag.SETUP);
		/* Para cada endereço na memória... */
		for(int i = 0; i < _memory.data.length; i++) {
			Word blankWord = Word.copy(_word);
			/* Escrever a palavra em branco (sem conteúdo) */
			_memory.data[i] = blankWord; 
		};
	}
   


	private boolean isMemoryBlank(Word[] _memory) {	log.info("{} {} Checking if memory is blank...", Tag.MEMORY, Tag.SETUP);
		boolean isMemoryBlank = false;

		for (int i = 0; i < _memory.length; i++) {
			/* Se o endereço da memória for 'blank' */
			if(Word.equals(_memory[i], new Word(Opcode.___,-1,-1,-1))) {
				isMemoryBlank = true;				log.debug("{} {} Memory is blank", Tag.MEMORY, Tag.SETUP);
				break;
			}
		}
		
		return isMemoryBlank;
	}

	private boolean isMemoryEmpty(Memory _memory) {	log.info("{} {} Checking if memory is empty...", Tag.MEMORY, Tag.SETUP);
		boolean isMemoryEmpty = true;

		for (int i = 0; i < _memory.data.length; i++) {
			if(_memory.data[i] != null) {
				isMemoryEmpty = false;				log.debug("{} {} Memory is empty", Tag.MEMORY, Tag.SETUP);
				break;
			}
		}
		
		return isMemoryEmpty;
	}




    public static void createVM() {
        if(INSTANCE == null)
            INSTANCE = new VirtualMachine();
    }


    public static VirtualMachine get() {
        return INSTANCE;
    }



	/* END */

    private static Logger log = LoggerFactory.getLogger("");

}