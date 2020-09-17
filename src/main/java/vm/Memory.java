package vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Memory {

    private static Memory INSTANCE;
    
    public int size;
    public Word[] data;

    private Memory(int _size) {
        this.size = _size;
        data = new Word[size];
    }


    public void write(Word _word, int _position) {
        Word.copy(data[_position] = _word);
    }


    public Word read(int _position) {
        return Word.copy(data[_position]);
    }


    public void delete(int _position) {
        data[_position] = Word.BLANK;
    }

    


    public void dump(Word _word) {          log.info("{} Dumping memory . . .", Tag.MEMORY);
        System.out.println(_word.toString());
    }

    public void dump(Word[] _memory) {      log.info("{} Dumping memory . . .", Tag.MEMORY);
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
                                            log.info("{} Dumping memory . . .", Tag.MEMORY);
        for (int i = ini; i < fim; i++) {
            System.out.print(i); System.out.print(":  ");  dump(m[i]);
        }
    }


    public static void createMemory(int _size) {
        if(INSTANCE == null)
            INSTANCE = new Memory(_size);
    }


    public static Memory get() {
        return INSTANCE;
    }



    /* END */

    private static Logger log = LoggerFactory.getLogger(Memory.class);

}
