public class UnidadeControle {
    private int contadorDePrograma;
    private Instrucao registradorDeInstrucao;
    private int registradorA;
    private int registradorB;
    private int registradorX;
    private MemoriaDados MD;
    private MemoriaPrograma MP;
    private UnidadeLogicaAritmetica ULA;
    private VonNeumann VN;

    public UnidadeControle(VonNeumann VNi, MemoriaDados MDi, MemoriaPrograma MPi, UnidadeLogicaAritmetica ULAi){
        this.VN = VNi;
        this.MD = MDi;
        this.MP = MPi;
        this.ULA = ULAi;
    }
    
    public void step(){
        fetch(contadorDePrograma, MP);
        parse(ULA);
        contadorDePrograma++;
        VN.setregText(registradorA, registradorB, registradorX);
    }

    public void run(){
        contadorDePrograma = 0;
        while(true){
            fetch(contadorDePrograma, MP);
            if (parse(ULA) == -1){
                break;
            }
            contadorDePrograma++;
            VN.setregText(registradorA, registradorB, registradorX);
        }
    }

    public void fetch(int posicao, MemoriaPrograma MP) {
        registradorDeInstrucao = MP.getInstrucao(posicao);
    }

    public int parse(UnidadeLogicaAritmetica ula) {
        String mnemonico = registradorDeInstrucao.getMnemonico();
        int operador1 = registradorDeInstrucao.getOperador1();
        int operador2 = registradorDeInstrucao.getOperador2();

        switch(mnemonico){
            case "mova":
                registradorA = operador1;
                break;
            
            case "movb":
                registradorB = operador1;
                break;

            case "add":
                registradorX = registradorA + operador1;
                break;

            case "sub":
                registradorX = registradorA - operador1;
                break;

            case "mov":
                MD.setPosicao(operador1, registradorX);
                break;

            case "lda":
                registradorA = MD.getPosicao(operador1);
                break;

            case "ldb":
                registradorB = MD.getPosicao(operador1);
                break;
            
            case "save":
                MD.setPosicao(operador1, operador2);
                break;
            case "jmp":
                contadorDePrograma = operador1 - 1;
                break;
            case "jnz":
                if(registradorA != 0){
                    contadorDePrograma = operador1 - 1;
                }
                break;
            case "stop":
                return -1;
            default:
                return -1;
        }
        return 0;
        // Decodificar e executar a instrução usando a ULA
        // Exemplo: ula.operar(mnemonico, operador1, operador2);
    }
}