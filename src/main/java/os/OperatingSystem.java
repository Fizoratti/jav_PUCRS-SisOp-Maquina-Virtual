package os;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vm.Aux;
import vm.CPU;
import vm.Memory;
import vm.Program;
import vm.Tag;
import vm.VirtualMachine;

public class OperatingSystem {

    public static OperatingSystem INSTANCE;

    public CPU cpu;
    
    public MemoryManager memoryManager;

    public Status status;

    // ProcessManager

    // FileManager

    private OperatingSystem() {
        memoryManager = new MemoryManager(1);
    }

    public void start() {
        this.status = Status.ACTIVE;
    }

    public void load(Program _program) {
        this.status = Status.RUNNING;
        /* Carrega na memoria... */
        Aux.carga(_program, Memory.get());				log.info("{} "+Tag.green("Program successfully loaded"), Tag.VM);
        
        /* Executa o programa */				
        // run(program.processID);									log.info("{} Program ended\n", Tag.VM);
        
        // VirtualMachine.cpu.run();									    log.info("{} "+Tag.red("Program ended")+"\n", Tag.VM);

        /* Sempre que der carga na memoria, aumentar o processID */
        // program.processID++;
    }

    public void load(Program[] _programs) {
        for (Program program : _programs) {
            load(program);
        }
    }

    public void stop() {
        this.status = Status.INACTIVE;
    }


    enum Status {
        ACTIVE,
        INACTIVE,
        RUNNING;
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
