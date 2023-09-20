public class UnidadeLogicaAritmetica {

    public int operar(int operador,int registradorA, int registradorB) {
        switch (operador) {
            case 0:
                return (registradorA + registradorB);
            case 1:
                return (registradorA - registradorB);
            case 2:
                if(registradorA < registradorB){
                    return 0;
                }
                else{
                    return 1;
                }
            default:
                return -99;
        }
        // Implementar operações de acordo com o operador
        // Exemplo: somar, subtrair, comparar
    }
}