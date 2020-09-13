package mv;

public class Aux {
    public void dump(Word _word) {
        System.out.println(_word.toString());
    }

    public void dump(Word[] _memory) {
        for (int i = 0; 0 < _memory.length; i++) {
            if(!Word.isEmpty(_memory[i])) {                                           // caso a Word tenha conteúdo
                System.out.print(i); System.out.print(":  ");  dump(_memory[i]);  // exibe o conteúdo da Word
            }
        }
    }

    public void dump(Word[] m, int ini, int fim) {
        for (int i = ini; i < fim; i++) {
            System.out.print(i); System.out.print(":  ");  dump(m[i]);
        }
    }

    public void carga(Programa _programa, Word[] _memory) {
        Word[] programa = _programa.read();
        System.out.println("Memory lenght: " + _memory.length);
        System.out.println("Memory[0]: " + _memory[0]);
        System.out.println("Memory[1]: " + _memory[1]);
        System.out.println("Memory[2]: " + _memory[2]);
        System.out.println("Memory[3]: " + _memory[3]);
        System.out.println("Programa lenght: " + programa.length);
        for (int i = 0; i < programa.length; i++) {
            _memory[i].opc = programa[i].opc;     
            _memory[i].r1 = programa[i].r1;     
            _memory[i].r2 = programa[i].r2;     
            _memory[i].p = programa[i].p;
        }

    }
}
