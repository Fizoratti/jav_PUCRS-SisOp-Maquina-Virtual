package vm.cpu.interruptions;

public class InvalidAddressException extends Exception{
    public InvalidAddressException(int address) {
        super("Endereço "+ address +" inválido.");
    }
}
