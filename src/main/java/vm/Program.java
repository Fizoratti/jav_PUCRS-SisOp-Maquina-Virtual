package vm;

public class Program {
    public Word[] operations;
    public int processID;

    public Program(Word[] _operations, int _processID) {
        this.operations = _operations;
        this.processID = _processID;
    }

    public Word[] read() {
        return operations.clone();
    }

}
