package vm;

public class Program {
    public Word[] operations;

    public Program(Word[] _operations) {
        this.operations = _operations;
    }

    public Word[] read() {
        return operations.clone();
    }

}
