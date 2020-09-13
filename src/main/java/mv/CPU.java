package mv;

public class CPU {
	
	private int pc; 			// ... composto de program counter,
	private Word ir; 			// instruction register,
	private int[] reg;       	// registradores da CPU
	private Word[] memory;		// CPU acessa MEMORIA, guarda referencia 'memory' a ela. memoria nao muda. é sempre a mesma.
	private Interrupts irpt; 	// durante instrucao, interrupcao pode ser sinalizada
	private int base;   		// base e limite de acesso na memoria
	private int limite; // por enquanto toda memoria pode ser acessada pelo processo rodando
						// ATE AQUI: contexto da CPU - tudo que precisa sobre o estado de um processo
						// para executar
						// nas proximas versoes isto pode modificar, e vai permitir salvar e restaurar
						// um processo na CPU

				
	

	public CPU(Word[] _memory) {     		// ref a MEMORIA passada na criacao da CPU
		this.memory = _memory; 				// usa o atributo 'memory' para acessar a memoria.
		this.reg = new int[8]; 				// aloca o espaço dos registradores
	}



	public void setContext(int _base, int _limite, int _pc) {		// no futuro esta funcao vai ter que ser 
		this.base = _base;                                          // expandida para setar TODO contexto de execucao,
		this.limite = _limite - 1;									// agora,  setamos somente os registradores base,
		this.pc = _pc;                                              // limite e pc (deve ser zero nesta versao)
		this.irpt = Interrupts.noInterrupt;                         // reset da interrupcao registrada
		System.out.println("---------------------------------- programa carregado ");
	}



	private boolean isLegal(int e) {                             // todo acesso a memoria tem que ser verificado
		if ((e < base) || (e > limite)) {                      //  valida se endereco 'e' na memoria ee posicao legal
			irpt = Interrupts.intEnderecoInvalido;             //  caso contrario ja liga interrupcao
			return false;
		};
		return true;
	}



	public void run() { 				// execucao da CPU supoe que o contexto da CPU, vide acima, esta devidamente setado
		System.out.println("---------------------------------- após execucao ");
		while (true) { 					// ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
			// FETCH
			if (isLegal(pc)) { 			// pc valido
				ir = memory[pc]; 		// busca posicao da memoria apontada por pc, guarda em ir
										// EXECUTA INSTRUCAO NO ir
				switch (ir.opc) { 		// DADO,JMP,JMPI,JMPIG,JMPIL,JMPIE,ADDI,SUBI,ANDI,ORI,LDI,LDD,STD,ADD,SUB,MULT,LDX,STX,SWAP,STOP;


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

			// verifica int - agora simplesmente para programa em qualquer caso
			if (!(irpt == Interrupts.noInterrupt)) {
				System.out.print("Interrupcao ");
				System.out.println(irpt);
				break; // break sai do loop da cpu
			}
		}
	}
}
