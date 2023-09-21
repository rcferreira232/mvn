public class MemoriaPrograma {
    private Instrucao[] posicoes = new Instrucao[99];
    private int NumeroDeInstruçoes;

    public void addInstrucao(Instrucao instrucao) {
        // Adicionar instrução à memória de programa
        posicoes[NumeroDeInstruçoes] = instrucao;
        NumeroDeInstruçoes++;
    }

    public Instrucao getInstrucao(int posicao) {
        // Obter instrução da memória de programa na posição especificada
        
        return posicoes[posicao];
    }
    public int getNumeroDeInstrucao() {
        // Obter instrução da memória de programa na posição especificada
        return NumeroDeInstruçoes;
    }

    public void clear(){
        for (int i = 0; i < posicoes.length; i++) {
            posicoes[i] = null;
        }
        NumeroDeInstruçoes = 0;
    }
}
