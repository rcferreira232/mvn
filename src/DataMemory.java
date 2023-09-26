public class DataMemory {
    private int[] dataMemory = new int[100];

    public void setValue(int index, int value) {
        // Colocar dado na memória de dados
        dataMemory[index] = value;
    }

    public int getValue(int index) {
        // Obter dado da memória de dados na posição especificada
        return dataMemory[index];
    }

    public void clear(){
        for (int i = 0; i < dataMemory.length; i++) {
            dataMemory[i] = 0;
        }
    }
}