package mv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CPU {

	/**
	 * program counter
	 */
	private int pc;

	/**
	 * instruction register
	 */
	private Word ir;

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
	private Interrupts irpt;

	/**
	 * base e limite de acesso na memoria
	 */
	private int base;

	/**
	 * por enquanto toda memoria pode ser acessada pelo processo rodando ATE AQUI:
	 * contexto da CPU - tudo que precisa sobre o estado de um processo para executar
	 * nas proximas versoes isto pode modificar, e vai permitir salvar e restaurar um processo na CPU
	 */
	private int limite;

	
	/**
	 * 
	 * @param _memory uma nova instância de memória
	 */
	public CPU(Word[] _memory) {     		log.info("[ CPU ]    Setup: Starting procedure...");
		this.memory = _memory;				log.info("[ CPU ]    Setup: Acquired a memory");
		this.reg = new int[8];				log.info("[ CPU ]    Setup: Allocated area for registers");
	}


	/**
	 * no futuro esta funcao vai ter que ser expandida para setar contexto de execucao,
	 * agora,  setamos somente os registradores base, limite e pc (deve ser zero nesta 
	 * versao) reset da interrupcao registrada.
	 * @param _base
	 * @param _limite
	 * @param _pc
	 */
	public void setContext(int _base, int _limite, int _pc) {
											log.info("[ CPU ]    Setup: Setting context...");
		this.base = _base;
		this.limite = _limite - 1;
		this.pc = _pc;                                   
		this.irpt = Interrupts.noInterrupt;                         
	}



	/**
	 * Todo acesso a memoria tem que ser verificado
	 * @param e posição (int) de um endereço na memória
	 * @return 
	 */
	private boolean isLegal(int e) {
		boolean isLegal = true;

		if ((e < base) || (e > limite)) {                      //  valida se endereco 'e' na memoria ee posicao legal
			irpt = Interrupts.intEnderecoInvalido;             //  caso contrario ja liga interrupcao
			isLegal = false;
		};

		return isLegal;
	}



	/**
	 * Execucao da CPU supoe que o contexto da CPU, vide acima, esta devidamente setado
	 */
	public void run() {						log.debug("CPU is running...");
		while (true) { 					
			// FETCH
			if (isLegal(pc)) { 				log.debug("CPU: Program counter is legal");
				ir = memory[pc]; 			log.debug("CPU: busca posicao da memoria apontada por pc, guarda em ir");
											log.debug("CPU: Running instruction {}", ir.opc);
				switch (ir.opc) {

					// ----- J - Type Instructions ----- 

					case JMP: // PC ← k 
						pc = ir.p;
						break;

					case JMPI: // PC ← Rs
						pc = reg[ir.r1];
						break;

					case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1
						if (reg[ir.r2] > 0) {
							pc = reg[ir.r1];
							break;
						} else {
							pc++;
						};
						break;

					case JMPIL: // if Rc < 0 then PC ← Rs Else PC ← PC +1
						if (reg[ir.r2] < 0) {
							pc = reg[ir.r1];
						} else {
							pc++;
						};
						break;

					case JMPIE: // if Rc = 0 then PC ← Rs Else PC ← PC +1
						if (reg[ir.r2] == 0) {
							pc = reg[ir.r1];
						} else {
							pc++;
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
						reg[ir.r1] = reg[ir.r1] + ir.p;
						pc++;
						break;
					
					case SUBI: // Rd ← Rd – k
						reg[ir.r1] = reg[ir.r1] - ir.p;
						pc++;
						break;

					case LDI: // Rd ← k
						reg[ir.r1] = ir.p;
						pc++;
						break;

					case LDD: // Rd ← [A]
						reg[ir.r1] = memory[ir.p].p;
						pc++;
						break;

					case STD: // [A] ← Rs
						if (isLegal(ir.p)) {
							memory[ir.p].opc = Opcode.DADO;
							memory[ir.p].p = reg[ir.r1];
							pc++;
						};
						break;
					

					
					// ----- R2 - Type Instructions ----- 

					case ADD: // Rd ← Rd + Rs
						reg[ir.r1] = reg[ir.r1] + reg[ir.r2];
						pc++;
						break;

					case SUB: // Rd ← Rd - Rs
						reg[ir.r1] = reg[ir.r1] - reg[ir.r2];
						pc++;
						break;

					case MULT: // Rd ← Rd * Rs
						reg[ir.r1] = reg[ir.r1] * reg[ir.r2];
						pc++;
						break;

					case LDX: // Rd ← [Rs] 
						reg[ir.r1] = memory[ir.r2].p;
						pc++;
						break;

					case STX: // [Rd] ← Rs
						if (isLegal(ir.r1)) {
							memory[reg[ir.r1]].opc = Opcode.DADO;
							memory[reg[ir.r1]].p = reg[ir.r2];
							pc++;
						};
						break;


					// ----- R1 - Type Instructions ----- 

					case SWAP:
						int aux = reg[ir.r1];
						reg[ir.r1] = reg[ir.r2];
						reg[ir.r2] = aux;
						pc++;
						break;

					case STOP: //  para execucao
						irpt = Interrupts.intSTOP;
						break;



					// ----- DADO ----- 

					case DADO:
						if (isLegal(ir.r1)) {
							memory[ir.r1].opc = Opcode.DADO;
							memory[ir.r1].p = ir.p;
							pc++;
						};
						break;

					default:
						System.out.println("Instrução não reconhecida pelo switch no método run().");
						break;
				}
			}

			if (!(irpt == Interrupts.noInterrupt)) {
				log.warn("[ CPU ]    : Program was interrupted by [ {} ]", irpt);
				break;						// break sai do loop da cpu
			}
		}
	}

    /* END */

    private static Logger log = LoggerFactory.getLogger(CPU.class);

}
