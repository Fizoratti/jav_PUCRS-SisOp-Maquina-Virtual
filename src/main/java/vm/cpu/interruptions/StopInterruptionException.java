package vm.cpu.interruptions;

public class StopInterruptionException extends Exception{
    public StopInterruptionException(String message) {
        super(message);
    }
}
