public class Instrucao {
    private String mnemonico;
    private int operador1;
    private int operador2;

    public Instrucao(String mnemonico, int operador1, int operador2) {
        this.mnemonico = mnemonico;
        this.operador1 = operador1;
        this.operador2 = operador2;
    }

    public String getMnemonico() {
        return mnemonico;
    }

    public int getOperador1() {
        return operador1;
    }

    public int getOperador2() {
        return operador2;
    }

    public String toString() {
        return mnemonico + " " + operador1 + " " + operador2;
    }
}