package os;

public class ProcessManager {
    public Process[] queue;

    public Process newProcess(Program _program) {
        return new Process(_program);
    }

    public void runProcess(Process _process){
        VM.run(_process);
        _process.changeState(State.Running);
    }
}
