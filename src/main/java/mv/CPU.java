package mv;

public class CPU {
    					// característica do processador: contexto da CPU ...
		private int pc; 			// ... composto de program counter,
		private Word ir; 			// instruction register,
		private int[] reg;       	// registradores da CPU
		private Interrupts irpt; 	// durante instrucao, interrupcao pode ser sinalizada
		private int base;   		// base e limite de acesso na memoria
		private int limite; // por enquanto toda memoria pode ser acessada pelo processo rodando
							// ATE AQUI: contexto da CPU - tudo que precisa sobre o estado de um processo
							// para executar
							// nas proximas versoes isto pode modificar, e vai permitir salvar e restaurar
							// um processo na CPU

		private Word[] m;   // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. é sempre a mesma.
						
		public CPU(Word[] _m) {     // ref a MEMORIA passada na criacao da CPU
			m = _m; 				// usa o atributo 'm' para acessar a memoria.
			reg = new int[8]; 		// aloca o espaço dos registradores
		}

		public void setContext(int _base, int _limite, int _pc) {  // no futuro esta funcao vai ter que ser 
			base = _base;                                          //expandida para setar TODO contexto de execucao,
			limite = _limite - 1;									   // agora,  setamos somente os registradores base,
			pc = _pc;                                              // limite e pc (deve ser zero nesta versao)
			irpt = Interrupts.noInterrupt;                         // reset da interrupcao registrada
		}

		private boolean legal(int e) {                             // todo acesso a memoria tem que ser verificado
			if ((e < base) || (e > limite)) {                      //  valida se endereco 'e' na memoria ee posicao legal
				irpt = Interrupts.intEnderecoInvalido;             //  caso contrario ja liga interrupcao
				return false;
			};
			return true;
		}

		public void run() { 				// execucao da CPU supoe que o contexto da CPU, vide acima, esta devidamente setado
			while (true) { 				// ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
				// FETCH
				if (legal(pc)) { 		// pc valido
					ir = m[pc]; 		// busca posicao da memoria apontada por pc, guarda em ir
										// EXECUTA INSTRUCAO NO ir
					switch (ir.opc) { 	// DADO,JMP,JMPI,JMPIG,JMPIL,JMPIE,ADDI,SUBI,ANDI,ORI,LDI,LDD,STD,ADD,SUB,MULT,LDX,STX,SWAP,STOP;


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
							reg[ir.r1] = m[ir.p].p;
							pc++;
							break;

						case STD: // [A] ← Rs
						    if (legal(ir.p)) {
							    m[ir.p].opc = Opcode.DADO;
							    m[ir.p].p = reg[ir.r1];
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
							reg[ir.r1] = m[ir.r2].p;
							pc++;
							break;

						case STX: // [Rd] ← Rs
						    if (legal(ir.r1)) {
								m[reg[ir.r1]].opc = Opcode.DADO;
							    m[reg[ir.r1]].p = reg[ir.r2];
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
							if (legal(ir.r1)) {
								m[ir.r1].opc = Opcode.DADO;
								m[ir.r1].p = ir.p;
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
