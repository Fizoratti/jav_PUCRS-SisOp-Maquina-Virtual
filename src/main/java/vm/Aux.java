package vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Aux {

    public static void carga(Program _program, Word[] _memory) {
                                            log.info("{} Loading program . . .", Tag.PROGRAM);
        Word[] program = _program.read();

        for (int i = 0; i < program.length; i++) {
            _memory[i].opc = program[i].opc;     
            _memory[i].r1 = program[i].r1;     
            _memory[i].r2 = program[i].r2;     
            _memory[i].p = program[i].p;
        }

        // para alocar programas, utilizar 2 vezes o tamanho do programa (para dados)
        // calcular quantidade de frames que o programa precisa, verificar se o programa cabe
        // alocar, fazer carga
        // verificar se frame esta ocupado, 
    }


    public static void carga(Program _program, Memory _memory) {
        Word[] program = _program.read();
        for (int i = 0; i < program.length; i++) {
            _memory.data[i] = program[i];
        }
    }

    /* END */

    private static Logger log = LoggerFactory.getLogger("");

}
