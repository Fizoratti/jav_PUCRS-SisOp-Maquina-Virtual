package vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import os.MemoryManager;

public class CPU {

	private int programCounter;

	private Word instructionRegister;

	/**
	 * registradores da CPU
	 */
	private int[] reg;

	/**
	 * CPU acessa MEMORIA, guarda referencia 'memory' a ela. memoria nao muda. é sempre a mesma.
	 */
	private Word[] memory;

	/**
	 * Durante a execução de uma instrucao, uma interrupcao pode ser sinalizada
	 */
	private Interrupts interruption;

	/**
	 * base de acesso na memoria
	 */
	private int base;

	/**
	 * por enquanto toda memoria pode ser acessada pelo processo rodando ATE AQUI:
	 * contexto da CPU - tudo que precisa sobre o estado de um processo para executar
	 * nas proximas versoes isto pode modificar, e vai permitir salvar e restaurar um processo na CPU
	 */
	private int limit;

	private MemoryManager gm;

	/**
	 * 
	 * @param _memory uma nova instância de memória
	 */
	public CPU(Word[] _memory) {     		log.info("{} {} Starting procedure...", Tag.CPU, Tag.SETUP);
		memory = _memory;					log.info("{} {} Acquired a memory", Tag.CPU, Tag.SETUP);
		reg = new int[8];					log.info("{} {} Allocated area for registers", Tag.CPU, Tag.SETUP);
	}
	public CPU(Memory _memory) {     		log.info("{} {} Starting procedure...", Tag.CPU, Tag.SETUP);
		memory = _memory.data;					log.info("{} {} Acquired a memory", Tag.CPU, Tag.SETUP);
		reg = new int[8];					log.info("{} {} Allocated area for registers", Tag.CPU, Tag.SETUP);
	}


	/**
	 * no futuro esta funcao vai ter que ser expandida para setar contexto de execucao,
	 * agora,  setamos somente os registradores base, limite e pc (deve ser zero nesta 
	 * versao) reset da interrupcao registrada.
	 * @param _base 
	 * @param _limite o tamanho limite máximo da memória
	 * @param _pc
	 */
	public void setContext(int _base, int _limite, int _pc) {
											log.info("{} {} Setting context...", Tag.CPU, Tag.SETUP);
		base = _base;
		limit = _limite - 1;
		programCounter = _pc;                                   
		interruption = Interrupts.None;                         
	}



	/**
	 * Todo acesso a memoria tem que ser verificado
	 * @param e posição (int) de um endereço na memória
	 * @return 
	 */
	private boolean isLegal(int e) {
		boolean isLegal = true;

		if ((e < base) || (e > limit)) {                      //  valida se endereco 'e' na memoria ee posicao legal
			interruption = Interrupts.InvalidAddress;             //  caso contrario ja liga interrupcao
			isLegal = false;
		};

		return isLegal;
	}

	// public int traduzirEnderecoMemoria(int pc, int processID) {
	// 	return (gm.tabPaginas.get(processID)[(pc / gm.tamPag)] * gm.tamFrame) + (pc % gm.tamPag);
	// }

	/**
	 * Execucao da CPU supoe que o contexto da CPU, vide acima, esta devidamente setado
	 */
	public void run() {						log.debug("CPU is running...");
		while (true) {
			if (isLegal(programCounter)) { 	
											log.debug("CPU: Program counter is legal");
				// instructionRegister = memory[traduzirEnderecoMemoria(ir.p, processID)]; 			
											log.debug("CPU: busca posicao da memoria apontada por pc, guarda em ir");
				instructionRegister = memory[programCounter]; 			
											log.debug("CPU: busca posicao da memoria apontada por pc, guarda em ir");
											log.debug("CPU: Running instruction {}", instructionRegister.opc);
				switch (instructionRegister.opc) {

					// ----- J - Type Instructions ----- 

					case JMP: // pc ← p
						programCounter = instructionRegister.p;
						break;

					case JMPI: // pc ← r1
						programCounter = reg[instructionRegister.r1];
						break;

					case JMPIG: // If r2 > 0 Then pc ← r1 Else pc ← pc +1
						if (reg[instructionRegister.r2] > 0) {
							programCounter = reg[instructionRegister.r1];
							break;
						} else {
							programCounter++;
						};
						break;

					case JMPIL: // if r2 < 0 then pc ← Rs Else PC ← PC +1
						if (reg[instructionRegister.r2] < 0) {
							programCounter = reg[instructionRegister.r1];
						} else {
							programCounter++;
						};
						break;

					case JMPIE: // if Rc = 0 then PC ← Rs Else PC ← PC +1
						if (reg[instructionRegister.r2] == 0) {
							programCounter = reg[instructionRegister.r1];
						} else {
							programCounter++;
						};
						break;

					case JMPIM: // PC ← [A]
						break;

					case JMPIGM: // if Rc > 0 then PC ← [A] Else PC ← PC +1
						break;
					
					case JMPILM: // if Rc < 0 then PC ← [A] Else PC ← PC +1
						break;

					case JMPIEM: // if Rc = 0 then PC ← [A] Else PC ← PC +1
						break;
					
					

					// ----- I - Type Instructions ----- 

					case ADDI: // Rd ← Rd + k
						reg[instructionRegister.r1] = reg[instructionRegister.r1] + instructionRegister.p;
						programCounter++;
						break;
					
					case SUBI: // Rd ← Rd – k
						reg[instructionRegister.r1] = reg[instructionRegister.r1] - instructionRegister.p;
						programCounter++;
						break;

					case LDI: // Rd ← k
						reg[instructionRegister.r1] = instructionRegister.p;
						programCounter++;
						break;

					case LDD: // Rd ← [A]
						reg[instructionRegister.r1] = memory[instructionRegister.p].p;
						programCounter++;
						break;

					case STD: // [A] ← Rs
						if (isLegal(instructionRegister.p)) {
							memory[instructionRegister.p].opc = Opcode.DADO;
							memory[instructionRegister.p].p = reg[instructionRegister.r1];
							programCounter++;
						};
						break;
					

					
					// ----- R2 - Type Instructions ----- 

					case ADD: // Rd ← Rd + Rs
						reg[instructionRegister.r1] = reg[instructionRegister.r1] + reg[instructionRegister.r2];
						programCounter++;
						break;

					case SUB: // Rd ← Rd - Rs
						reg[instructionRegister.r1] = reg[instructionRegister.r1] - reg[instructionRegister.r2];
						programCounter++;
						break;

					case MULT: // Rd ← Rd * Rs
						reg[instructionRegister.r1] = reg[instructionRegister.r1] * reg[instructionRegister.r2];
						programCounter++;
						break;

					case LDX: // Rd ← [Rs] 
						reg[instructionRegister.r1] = memory[instructionRegister.r2].p;
						programCounter++;
						break;

					case STX: // [Rd] ← Rs
						if (isLegal(instructionRegister.r1)) {
							memory[reg[instructionRegister.r1]].opc = Opcode.DADO;
							memory[reg[instructionRegister.r1]].p = reg[instructionRegister.r2];
							programCounter++;
						};
						break;


					// ----- R1 - Type Instructions ----- 

					case SWAP:
						int aux = reg[instructionRegister.r1];
						reg[instructionRegister.r1] = reg[instructionRegister.r2];
						reg[instructionRegister.r2] = aux;
						programCounter++;
						break;

					case STOP: //  para execucao
						interruption = Interrupts.Stop;
						break;



					// ----- DADO ----- 

					case DADO:
						if (isLegal(instructionRegister.r1)) {
							memory[instructionRegister.r1].opc = Opcode.DADO;
							memory[instructionRegister.r1].p = instructionRegister.p;
							programCounter++;
						};
						break;

					default:
						System.out.println("Instrução não reconhecida pelo switch no método run().");
						break;
				}
			}

			if (!(interruption == Interrupts.None)) {
				log.warn("{} Program was interrupted by [ {} ]", Tag.CPU, interruption);
				break;						// break sai do loop da cpu
			}
		}
	}

	/* END */

    private static Logger log = LoggerFactory.getLogger(CPU.class);

}
