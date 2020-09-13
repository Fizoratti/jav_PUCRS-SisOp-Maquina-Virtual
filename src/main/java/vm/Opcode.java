package vm;

public enum Opcode {
    DADO, ___,		    // se memoria nesta posicao tem um dado, usa DADO, se nao usada é NULO
    JMP, JMPI, JMPIG, JMPIL, JMPIE, 	// J - Type Instructions 
        JMPIM, JMPIGM, JMPILM, JMPIEM, 		
    ADDI, SUBI, LDI, LDD, STD,      	// I - Type Instructions
    ADD, SUB, MULT, LDX, STX, 			// R2 - Type Instructions
    SWAP, STOP;							// R1 - Type Instructions
}
