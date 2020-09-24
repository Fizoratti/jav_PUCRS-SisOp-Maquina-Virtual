public class Process {
    public int ID;
    public State state;

    public Time arrival
    public Seconds w;               // Tempo no sistema esperando
    public Seconds e;               // Tempo total gasto em execução (service time?)
    public int s;                   // 

    public Program program;


    public Process(Program _program) {
        changeState(State.New);

    }

    public Process createProcess(Program _program) {
        return new Process(_program);
    }

    public changeState(State _state) {
        state = _state;
    }

    public setProgram(Program _program) {
        program = _program;
    }

    //
    public 
}