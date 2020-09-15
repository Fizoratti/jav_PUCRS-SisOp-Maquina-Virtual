package os;

import vm.CPU;
import vm.Program;

public class OperatingSystem {

    public CPU cpu;
    
    public MemoryManager memoryManager;

    // ProcessManager

    // FileManager

    public OperatingSystem() {
        memoryManager = new MemoryManager(1);
    }

    public void start() {}

    public void load(Program _program) {}

    public void stop() {}
}
