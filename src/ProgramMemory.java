public class ProgramMemory {
    private Instruction[] programMemory = new Instruction[100];
    private int index;

    public void addInstruction(Instruction instruction) {
        // Adicionar instrução à memória de programa
        programMemory[index] = instruction;
        index++;
    }

    public Instruction getInstruction(int index) {
        // Obter instrução da memória de programa na posição especificada

        return programMemory[index];
    }
    public int getInstructionIndex() {
        // Obter instrução da memória de programa na posição especificada
        return index;
    }

    public void clear(){
        for (int i = 0; i < programMemory.length; i++) {
            programMemory[i] = null;
        }
        index = 0;
    }
}
