package mv;

public class Programa {
    public Word[] operations;

    public Programa(Word[] _operations) {
        this.operations = _operations;
    }

    public Word[] read() {
        return operations.clone();
    }

}
