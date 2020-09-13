package mv;

public class Word {
    public Opcode opc; 	//
	public int r1; 		// indice do primeiro registrador da operacao (Rs ou Rd cfe opcode na tabela)
	public int r2; 		// indice do segundo registrador da operacao (Rc ou Rs cfe operacao)
	public int p; 		// parametro para instrucao (k ou A cfe operacao), ou o dado, se opcode = DADO

	public Word(Opcode _opc, int _r1, int _r2, int _p) {  
		opc = _opc;   r1 = _r1;    r2 = _r2;	p = _p;
	}

	public static boolean isEmpty(Word _word) {
		boolean isEmpty = true;

		if(_word == null) return isEmpty;

		boolean isOpcodeEmpty 		= ((_word.opc == Opcode.___) ? true : false);
		boolean isRegistrador1Empty = ((_word.r1  == -1) 		 ? true : false);
		boolean isRegistrador2Empty = ((_word.r2  == -1) 		 ? true : false);
		boolean isParametroEmpty 	= ((_word.p   == -1) 		 ? true : false);
		
		if(
			!isOpcodeEmpty			||			
			!isRegistrador1Empty	||
			!isRegistrador2Empty	||
			!isParametroEmpty		== true		// se qualquer um tiver algum conteúdo
		) {
			isEmpty = false; 					// então a Word não está vazia
		}

		return isEmpty;
	}

	public String toString() {
		return "[ " + opc + ", " + r1 + ", " + r2 + ", " + p + "  ] ";
	}
}
