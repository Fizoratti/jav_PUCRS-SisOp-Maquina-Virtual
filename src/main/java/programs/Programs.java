package programs;

import vm.Program;
import vm.Word;
import vm.Opcode;

public class Programs {

    // Programa Teste / Programa Mínimo
    public static Program p0 = new Program(new Word[] {
        new Word(Opcode.LDI, 0, -1, 999),           
        new Word(Opcode.STD, 0, -1, 10),
        new Word(Opcode.STD, 0, -1, 11),
        new Word(Opcode.STD, 0, -1, 12),
        new Word(Opcode.STD, 0, -1, 13),
        new Word(Opcode.STD, 0, -1, 14),
        new Word(Opcode.STOP, -1, -1, -1)
    });


    /* 
        ALGORITMO: 
            10 primeiros números da sequência de Fibonacci.
    */
    public static Program p1 = new Program(new Word[] {
        new Word(Opcode.LDI, 0, -1, 0),
        new Word(Opcode.STD, 0, -1, 20),
        new Word(Opcode.LDI, 1, -1, 1),
        new Word(Opcode.STD, 1, -1, 21),
        new Word(Opcode.LDI, 7, -1, 22),
        new Word(Opcode.LDI, 5, -1, 6),
        new Word(Opcode.LDI, 6, -1, 31),
        new Word(Opcode.LDI, 2, -1, 0),
        new Word(Opcode.ADD, 2, 0, -1),
        new Word(Opcode.LDI, 0, -1, 0),
        new Word(Opcode.ADD, 0, 1, -1),
        new Word(Opcode.ADD, 1, 2, -1),
        new Word(Opcode.STX, 7, 1, -1),
        new Word(Opcode.ADDI, 7, -1, 1),
        new Word(Opcode.SUB, 6, 7, -1),
        new Word(Opcode.JMPIG, 5, 6, -1),
        new Word(Opcode.STOP, -1, -1, -1)
    });


    /*  
        ALGORITMO:
            Escrever N números da sequência de Fibonacci a partir de um valor na memória.
            Escreve números de maneira crescente.
    */
    public static Program p2 = new Program(new Word[] {
        new Word(Opcode.LDI, 0, -1, -1),
        new Word(Opcode.LDI, 1, -1, 0),
        new Word(Opcode.LDI, 2, -1, 1),
        new Word(Opcode.LDI, 3, -1, 50),
        new Word(Opcode.LDX, 4, 3, -1),
        new Word(Opcode.LDI, 5, -1, 18),
        new Word(Opcode.LDI, 6, -1, 32),
        new Word(Opcode.JMPIL, 5, 4, -1),
        new Word(Opcode.JMPIE, 6, 4, -1),
        new Word(Opcode.STX, 3, 1, -1),
        new Word(Opcode.ADDI, 3, -1, 1),
        new Word(Opcode.SUBI, 4, -1, 1),
        new Word(Opcode.JMPIE, 6, 4, -1),
        new Word(Opcode.JMP, -1, -1, 20),
        new Word(Opcode.STX, 3, 0, -1),
        new Word(Opcode.JMP, -1, -1, 32),
        new Word(Opcode.LDI, 0, -1, 0),
        new Word(Opcode.LDI, 1, -1, 1),
        new Word(Opcode.LDI, 5, -1, 23),
        new Word(Opcode.LDI, 2, -1, 0),
        new Word(Opcode.ADD, 2, 1, -1),
        new Word(Opcode.LDI, 0, -1, 0),
        new Word(Opcode.ADD, 0, 1, -1),
        new Word(Opcode.ADD, 1, 2, -1),
        new Word(Opcode.STX, 3, 1, -1),
        new Word(Opcode.ADDI, 3, -1, 1),
        new Word(Opcode.SUBI, 4, -1, 1),
        new Word(Opcode.JMPIG, 5, 4, -1),
        new Word(Opcode.STOP, -1, -1, -1)
    });


    /*
        ALGORITMO:
            Escrever o fatorial de um valor pré determinado na memória.
    */
    public static Program p3 = new Program(new Word[] {
        new Word(Opcode.DADO, 50, -1, 10),
        
        new Word(Opcode.LDI, 0, -1, -1),
        new Word(Opcode.LDI, 1, -1, 1),
        new Word(Opcode.LDI, 2, -1, 50),
        new Word(Opcode.LDX, 3, 2, -1),
        new Word(Opcode.LDI, 4, -1, 11),
        new Word(Opcode.LDI, 5, -1, 13),
        new Word(Opcode.LDI, 6, -1, 25),
        new Word(Opcode.JMPIL, 4, 3, -1),
        new Word(Opcode.JMPIE, 5, 3, -1),
        new Word(Opcode.JMP, -1, -1, 15),
        new Word(Opcode.STX, 2, 0, -1),
        new Word(Opcode.JMP, -1, -1, 24),
        new Word(Opcode.STX, 2, 1, -1),
        new Word(Opcode.JMP, -1, -1, 24),
        new Word(Opcode.LDI, 4, -1, 0),
        new Word(Opcode.ADD, 4, 3, -1),
        new Word(Opcode.SUBI, 3, -1, 1),
        new Word(Opcode.STX, 2, 1, -1),
        new Word(Opcode.JMPIE, 6, 3, -1),
        new Word(Opcode.LDI, 0, -1, 21),
        new Word(Opcode.MULT, 4, 3, -1),
        new Word(Opcode.STX, 2, 4, -1),
        new Word(Opcode.SUB, 3, -1, 1),
        new Word(Opcode.JMPIG, 0, 3, -1),
        new Word(Opcode.STOP, -1, -1, -1)
    });


    /*
        ALGORITMO:
            Bubble sort.
    */
    public static Program p4 = new Program(new Word[] {
        new Word(Opcode.DADO, 0, 0, 5),
        new Word(Opcode.DADO, 1, 0, 3),
        new Word(Opcode.DADO, 2, 0, 4),
        new Word(Opcode.DADO, 3, 0, 1),
        new Word(Opcode.DADO, 4, 0, 2),

        new Word(Opcode.LDI, 3, -1, 6),
        new Word(Opcode.LDI, 2, -1, 5),
        new Word(Opcode.LDI, 0, -1, 1),
        new Word(Opcode.LDI, 1, -1, 1),
        new Word(Opcode.JMPIL, 4, 2, -1),
        new Word(Opcode.STOP, -1, -1, -1),
        new Word(Opcode.LDX, 3, 0, -1),
        new Word(Opcode.LDX, 4, 1, -1),
        new Word(Opcode.SUB, 3, 4, -1),
        new Word(Opcode.LDI, 4, -1, 12),
        new Word(Opcode.JMPIG, 4, 3, -1),
        new Word(Opcode.JMP, -1, -1, 4),
        new Word(Opcode.LDX, 3, 1, -1),
        new Word(Opcode.SWAP, 3, 4, -1),
        new Word(Opcode.STX, 0, 3, -1),
        new Word(Opcode.STX, 1, 4, -1),
        new Word(Opcode.ADDI, 0, -1, 1),
        new Word(Opcode.ADDI, 1, -1, 1),
        new Word(Opcode.JMP, -1, -1, 4)
    });

    /* END */

}