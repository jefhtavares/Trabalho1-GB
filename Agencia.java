/** Alunos : Jéferson Bueno e Sol Orion Trabalho GB Lab 1 Turma: 53 2016/2 */

public class Agencia
{
    private Poupanca[] poupancas;
    private int qtdPoupancas; //Qtd até o momento

    public Agencia(int qtdContas)
    {
        poupancas = new Poupanca[qtdContas];
    }

    private int abreConta()
    {
        if(poupancas.length == qtdPoupancas)
            return -1;

        int tipo = Teclado.leInt("Que tipo de conta deseja abrir: (1) Poupanca simples ou (2) Poupanca Saude? ");

        int numeroConta = Teclado.leInt("Digite o numero da conta: ");
        String nomeCliente = Teclado.leString("Digite o nome do cliente: ");

        Poupanca poupanca;
        if(tipo == 1)
        {
            poupanca = new Poupanca(numeroConta, nomeCliente);
            poupancas[qtdPoupancas] = poupanca;
        }
        else //Considerar 2 para todos os casos
        {
            poupanca = new PoupancaSaude(numeroConta, nomeCliente);
            PoupancaSaude poupancaSaude = (PoupancaSaude)poupanca;

            char resposta;

            while(true)
            {
                if(poupancaSaude.contaDependentes() == poupancaSaude.maxDependentes)
                    break;

                resposta = Teclado.leChar("Deseja inserir um dependente (S/N)? ");

                if(resposta == 'N' || resposta == 'n')
                    break;

                String nomeDep = Teclado.leString("Digite o nome do dependente: ");
                char parentesco = Teclado.leChar("Digite o parentesco do dependente (c - conjugue, f - filho, p - progenitor, o - outro): ");

                poupancaSaude.insereDependente(new Dependente(nomeDep, parentesco));
                poupancaSaude.contaDependentes();
            }

            poupancas[qtdPoupancas] = poupancaSaude;
        }

        qtdPoupancas += 1;
        return poupanca.getNumero();
    }

    private int buscaConta(int numeroConta)
    {
        for(int i = 0; i < poupancas.length; i++)
        {
            if(poupancas[i] != null && poupancas[i].getNumero() == numeroConta)
                return i;
        }

        return -1;
    }

    public void menuDeTransacoes()
    {
        final String conteudoMenu = "1 - Abre conta\n" +
                                    "2 - Deposita\n" +
                                    "3 - Retira\n" +
                                    "4 - Retira para saude\n" +
                                    "5 - Amortiza financiamento\n" +
                                    "6 - Emite extrato da conta\n" +
                                    "7 - Credita rendimentos\n" +
                                    "8 - Insere um dependente\n" +
                                    "9 - Retira um dependente\n" +
                                    "10 - Encerra";

        loop: while(true)
        {
            System.out.println("\n\t** Menu de transacoes **");
            System.out.println("Entre com a opcao desejada");
            int opcao = Teclado.leInt(conteudoMenu);

            switch(opcao)
            {
                case 1:
                {
                    int retorno = abreConta();

                    String msg;
                    if(retorno == -1)
                        msg = "Nao pode abrir novas contas nesta agencia";
                    else
                        msg = String.format("Conta aberta de numero %s", retorno);

                    System.out.println(msg + "\n");
                    break;
                }
                case 2:
                {
                    int index = buscaConta(Teclado.leInt("Digite o numero da conta: "));

                    if(index == -1)
                    {
                        System.out.println("Conta inexistente");
                        break;
                    }

                    double valor = Teclado.leDouble("Entre com o valor do deposito: ");
                    poupancas[index].deposita(valor);
                    break;

                }
                case 3:
                {
                    int index = buscaConta(Teclado.leInt("Digite o numero da conta: "));

                    if(index == -1)
                    {
                        System.out.println("Conta inexistente");
                        break;
                    }

                    double valor = Teclado.leDouble("Entre com o valor do retirada: ");
                    if(!poupancas[index].retira(valor))
                        System.out.println("Saldo insuficiente!");

                    break;
                }
                case 4:
                {
                    int index = buscaConta(Teclado.leInt("Digite o numero da conta: "));

                    if(index == -1)
                    {
                        System.out.println("Conta inexistente");
                        break;
                    }

                    if(!(poupancas[index] instanceof PoupancaSaude)){
                        System.out.println("Nao e poupanca saude");
                    }

                    double valor = Teclado.leDouble("Entre com o valor da retirada: ");
                    double retorno = ((PoupancaSaude)poupancas[index]).retiraSaude(valor);

                    String msg = retorno == 0 ? "Nao houve financiamento" : String.format("Valor financiado: R$ %s", retorno);

                    System.out.println(msg);
                    break;
                }
                case 5:
                {
                    int index = buscaConta(Teclado.leInt("Digite o numero da conta: "));

                    if(index == -1)
                    {
                        System.out.println("Conta inexistente");
                        break;
                    }

                    if(!(poupancas[index] instanceof PoupancaSaude)){
                        System.out.println("Tipo de conta não aceita esta operacao");
                    }

                    double valor = Teclado.leDouble("Digite o valor a ser amortizado");
                    double retorno = ((PoupancaSaude)poupancas[index]).amortizaFinanciamento(valor);

                    //String msg = 

                    break;
                }
                case 6:
                {
                    int index = buscaConta(Teclado.leInt("Digite o numero da conta: "));

                    if(index == -1)
                    {
                        System.out.println("Conta inexistente");
                        break;
                    }

                    String msg = (poupancas[index] instanceof PoupancaSaude) ? ((PoupancaSaude)poupancas[index]).toString() : poupancas[index].toString();
                    System.out.println(msg);

                    break;
                }
                case 8:
                {
                    int index = buscaConta(Teclado.leInt("Digite o numero da conta: "));

                    if(index == -1)
                    {
                        System.out.println("Conta inexistente");
                        break;
                    }

                    if(!(poupancas[index] instanceof PoupancaSaude)){
                        System.out.println("Nao e poupanca saude");
                        break;
                    }

                    PoupancaSaude poupancaSaude = (PoupancaSaude)poupancas[index];

                    if(poupancaSaude.contaDependentes() == poupancaSaude.maxDependentes){
                        System.out.println("Essa conta nao admite mais dependentes");
                        break;
                    }

                    String nomeDep = Teclado.leString("Digite o nome do dependente: ");
                    char parentesco = Teclado.leChar("Digite o parentesco do dependente (c - conjugue, f - filho, p - progenitor, o - outro): ");

                    poupancaSaude.insereDependente(new Dependente(nomeDep, parentesco));

                    break;
                }
                case 9:
                {
                    int index = buscaConta(Teclado.leInt("Digite o numero da conta: "));

                    if(index == -1)
                    {
                        System.out.println("Conta inexistente");
                        break;
                    }

                    if(!(poupancas[index] instanceof PoupancaSaude)){
                        System.out.println("Nao e poupanca saude");
                        break;
                    }

                    String nomeDependente = Teclado.leString("Digite o nome do dependente a ser removido: ");

                    Dependente retorno = ((PoupancaSaude)poupancas[index]).retiraDependente(nomeDependente);
                    String msg = retorno == null ? "Nao existe dependente com este nome" : String.format("%s removido da lista de dependentes", nomeDependente);
                    break;
                }
                case 10:
                    System.out.println("\n\t**Tchau**");
                    break loop;
                default:
                    break;
            }
        }
    }
}
