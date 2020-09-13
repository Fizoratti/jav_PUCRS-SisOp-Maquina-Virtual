package vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Aux {

    public void dump(Word _word) {          log.info("{} Dumping memory . . .", Aux.mark);
        System.out.println(_word.toString());
    }

    public void dump(Word[] _memory) {      log.info("{} Dumping memory . . .", Aux.mark);
                                            log.debug("Memory value in memory[0]: {}", _memory[0]);
                                            log.debug("Memory value in memory[1]: {}", _memory[1]);
                                            log.debug("Memory value in memory[2]: {}", _memory[2]);
                                            log.debug("Memory value in memory[3]: {}", _memory[3]);

        // int memorySize = _memory.length;
        // for (int i = 0; i < memorySize; i++) {
        //     if(!Word.isEmpty(_memory[i])) {                                           // caso a Word tenha conteúdo
        //         System.out.print(i); System.out.print(":  ");  dump(_memory[i]);  // exibe o conteúdo da Word
        //     }
        // }

        if(_memory[0] == null) return;
        int index = 0;
        for (Word word : _memory) {
            if(Word.isEmpty(word)) {                               // caso a Word tenha conteúdo
                System.out.print(index + ":  ");  dump(word);       // exibe o conteúdo da Word
            }
            index++;
        }
    }

    public void dump(Word[] m, int ini, int fim) {
                                            log.info("{} Dumping memory . . .", Aux.mark);
        for (int i = ini; i < fim; i++) {
            System.out.print(i); System.out.print(":  ");  dump(m[i]);
        }
    }

    public void carga(Program _program, Word[] _memory) {
                                            log.info("{} Loading program . . .", Aux.mark);
        Word[] program = _program.read();

        for (int i = 0; i < program.length; i++) {
            _memory[i].opc = program[i].opc;     
            _memory[i].r1 = program[i].r1;     
            _memory[i].r2 = program[i].r2;     
            _memory[i].p = program[i].p;
        }

    }

    /* END */

    public static String mark = "[ Memory ] :";

    private static Logger log = LoggerFactory.getLogger(CPU.class);

}
