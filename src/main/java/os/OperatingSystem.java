package os;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vm.Aux;
import vm.CPU;
import vm.Memory;
import vm.Program;
import vm.Tag;
import vm.VirtualMachine;
import vm.VirtualMachineLibrary;

public class OperatingSystem {

    public VirtualMachineLibrary VM;
    
    public MemoryManager memoryManager;
    
    public ProcessManager processManager;



    private OperatingSystem() {
        memoryManager = new MemoryManager(1);
        processManager = new ProcessManager();
        VM = new VirtualMachineLibrary();
    }

    public void start() {
        VM.initOS(this);
    }

    public void load(Program _program) {
        VM.load(_program, Memory.get());				log.info("{} "+Tag.green("Program successfully loaded"), Tag.VM);
        Process process = processManager.newProcess(_program);
        processManager.startProcess(process);
        								                log.info("{} "+Tag.red("Program ended")+"\n", Tag.VM);

    }

    public void load(Program[] _programs) {
        for (Program program : _programs) {
            load(program);
        }
    }

    public void stop() {
        VM.stopOS(this);  // this == INSTANCE
    }

    public static void createOS() {
        if(INSTANCE == null)
            INSTANCE = new OperatingSystem();
    }


    public static OperatingSystem get() {
        return INSTANCE;
    }



	/* END */

    private static Logger log = LoggerFactory.getLogger("");

}
