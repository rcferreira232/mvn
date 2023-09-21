public class MemoriaDados {
    private int[] posicoes = new int[99];

    public void setPosicao(int posicao, int valor) {
        // Colocar dado na memória de dados
        posicoes[posicao] = valor;
    }

    public int getPosicao(int posicao) {
        // Obter dado da memória de dados na posição especificada
        return posicoes[posicao];
    }

    public void clear(){
        for (int i = 0; i < posicoes.length; i++) {
            posicoes[i] = 0;
        }
    }
}