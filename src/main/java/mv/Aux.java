package mv;

public class Aux {
    public void dump(Word w) {
        System.out.print("[ "); 
        System.out.print(w.opc); System.out.print(", ");
        System.out.print(w.r1);  System.out.print(", ");
        System.out.print(w.r2);  System.out.print(", ");
        System.out.print(w.p);   System.out.println("  ] ");
    }

    public void dump(Word[] m, int ini, int fim) {
        for (int i = ini; i < fim; i++) {
            System.out.print(i); System.out.print(":  ");  dump(m[i]);
        }
    }

    public void carga(Programa _p, Word[] _m) {
        Word[] p = _p.read();
        for (int i = 0; i < p.length; i++) {
            _m[i].opc = p[i].opc;     
            _m[i].r1 = p[i].r1;     
            _m[i].r2 = p[i].r2;     
            _m[i].p = p[i].p;
        }
    }
}
