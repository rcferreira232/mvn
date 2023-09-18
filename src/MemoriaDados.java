public class MemoriaDados {
    private int[] posicoes;

    public void setPosicao(int posicao, int valor) {
        // Colocar dado na memória de dados
        posicoes[posicao] = valor;
    }

    public int getPosicao(int posicao) {
        // Obter dado da memória de dados na posição especificada
        return posicoes[posicao];
    }
}