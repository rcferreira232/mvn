
public class CentralProcessingUnit {
    private int instructionCounter;
    private Instruction instructionRegister;
    private int registerA;
    private int registerB;
    private int registerX;
    private DataMemory DM;
    private ProgramMemory PM;
    private VonNeumann VN;


    public CentralProcessingUnit(VonNeumann VNi, DataMemory MDi, ProgramMemory MPi){
        this.VN = VNi;
        this.DM = MDi;
        this.PM = MPi;
    }


    public int step(){
        fetch(instructionCounter, PM);
        if (parse() == -1){
            return -1;
        }
        instructionCounter++;
        return 0;
    }


    public void fetch(int index, ProgramMemory MP) {
        instructionRegister = MP.getInstruction(index);
    }


    public int parse() {
        String mnemonico = instructionRegister.getMnemonic();
        int operador1 = instructionRegister.getOperator1();
        int operador2 = instructionRegister.getOperator2();

        switch(mnemonico){
            case "mova":
                registerA = operador1;
                break;
            
            case "movb":
                registerB = operador1;
                break;

            case "add":
                registerX = registerA + registerB;
                break;

            case "sub":
                registerX = registerA - operador1;
                break;

            case "mov":
                DM.setValue(operador1, registerX);
                break;

            case "lda":
                registerA = DM.getValue(operador1);
                break;

            case "ldb":
                registerB = DM.getValue(operador1);
                break;
            
            case "save":
                DM.setValue(operador1, operador2);
                break;
            case "jmp":
                instructionCounter = operador1 - 1;
                break;
            case "jnz":
                if(registerA != 0){
                    instructionCounter = operador1 - 1;
                }
                break;
            case "stop":
                return (-1);
            default:
                return (-1);
        }
        return 0;
        // Decodificar e executar a instrução usando a ULA
        // Exemplo: ula.operar(mnemonico, operador1, operador2);
    }


    public int getInstructionCounter(){
        return instructionCounter;
    }


    public void updateFrame(){
        VN.updateFrame(registerA, registerB, registerX);
    }


    public void Clear(){
        registerA = 0;
        registerB = 0;
        registerX = 0;
        instructionRegister = null;
        instructionCounter = 0;
        DM.clear();
        PM.clear();
    }
}