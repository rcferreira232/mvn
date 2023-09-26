public class Instruction {
    private String mnemonic;
    private int operator1;
    private int operator2;

    public Instruction(String mnemonic, int operator, int operador2) {
        this.mnemonic = mnemonic;
        this.operator1 = operator;
        this.operator2 = operador2;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public int getOperator1() {
        return operator1;
    }

    public int getOperator2() {
        return operator2;
    }

    public String toString() {
        return mnemonic + " " + operator1 + " " + operator2;
    }
}