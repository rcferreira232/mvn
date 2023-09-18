public class UC {
    private int contadorDePrograma;
    private Instrucao registradorDeInstrucao;

    public void fetch(int posicao, MemoriaPrograma MP) {
        registradorDeInstrucao = MP.getInstrucao(posicao);
    }

    public void parse(ULA ula) {
        String mnemonico = registradorDeInstrucao.getMnemonico();
        int operador1 = registradorDeInstrucao.getOperador1();
        int operador2 = registradorDeInstrucao.getOperador2();

        // Decodificar e executar a instrução usando a ULA
        // Exemplo: ula.operar(mnemonico, operador1, operador2);
    }
}