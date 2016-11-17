/** Alunos : Jéferson Bueno e Sol Orion Trabalho GB Lab 1 Turma: 53 2016/2 */

public class PoupancaSaude extends Poupanca
{
    private double saldoVinculado;
    private double saldoFinanciado;
    private Dependente[] dependentes;
    public static final int maxDependentes = 5;

    public PoupancaSaude(int numConta, String nomeCliente)
    {
        super(numConta, nomeCliente);
        dependentes = new Dependente[maxDependentes];
    }

    public int contaDependentes()
    {
        int contagem = 0;
        for(Dependente dep : dependentes)
        {
            if(dep != null)
                contagem += 1;
        }

        return contagem;
    }

    @Override
    public void deposita(double valor)
    {
        double percRetencao = percentualRetencaoPorDependente();

        double vlrVincular = valor * percRetencao;

        this.saldoVinculado += vlrVincular;
        super.deposita(valor - vlrVincular);
    }

    /**retorna o percentual de retenção para movimentações de acordo com a quantidade de dependentes */
    private double percentualRetencaoPorDependente()
    {
        double percRetencao;
        int qtdDependentes = contaDependentes();

        if(qtdDependentes == 0)
            percRetencao = 0.15;
        else if(qtdDependentes <= 2)
            percRetencao = 0.2;
        else if(qtdDependentes <= 4)
            percRetencao = 0.3;
        else
            percRetencao = 0.5;

        return percRetencao;
    }

    @Override
    public double creditaRendimento(double taxa)
    {
        double rendimento = this.saldoVinculado * taxa;
        this.saldoVinculado += rendimento;

        return super.creditaRendimento(taxa);
    }

    public boolean insereDependente(Dependente dependente)
    {
        for(int i = 0; i < this.dependentes.length; i++)
        {
            if(this.dependentes[i] == null)
            {
                this.dependentes[i] = dependente;
                return true;
            }
        }

        return false;
    }

    public int buscaDependente(String nome)
    {
        for(int i = 0; i < dependentes.length; i++)
        {
            if(dependentes[i] != null && dependentes[i].getNome().equalsIgnoreCase(nome))
                return i;
        }

        return 99;
    }

    public Dependente retiraDependente(String nome)
    {
        int indice = buscaDependente(nome);

        if(indice != 99)
        {
            Dependente retorno = dependentes[indice];
            dependentes[indice] = null;
            return retorno;
        }

        return null;
    }

    public double retiraSaude(double valor)
    {
        if(valor > this.saldoVinculado)
        {
            double valorRestante = valor - saldoVinculado;
            System.out.println(String.format("Valor restante da despesa R$ %s | Saldo livre R$ %s", valorRestante, getSaldoLivre()));
            
            double valorSaldoLivre;

            while(true)
            {    
                valorSaldoLivre = Teclado.leDouble("Quanto você quer usar do saldo livre?");         
                if(valorSaldoLivre > getSaldoLivre() || valorSaldoLivre > valor)
                    System.out.println("Valor muito grande, redigite!");
                else
                    break;
            }

            super.retira(valorSaldoLivre);

            //Financiamento
            if(valorSaldoLivre < valorRestante)
            {
                double restante = valorRestante - valorSaldoLivre;
                double percJuros;

                if(saldoFinanciado == 0)
                    percJuros = 0.05;
                else if (saldoFinanciado <= 500)
                    percJuros = 0.1;
                else
                    percJuros = 0.15;
                
                double valorFinanciamentoComJuros = restante + (restante * percJuros);

                this.saldoFinanciado += valorFinanciamentoComJuros;
                return valorFinanciamentoComJuros;
            }
            
            return 0;
        }

        saldoVinculado -= valor;
        return 0;
    }

    public double amortizaFinanciamento(double valor)
    {
        if(valor > this.saldoFinanciado)
            return 0;

        this.saldoFinanciado -= valor;

        if(this.saldoFinanciado == 0){
            double valorDeposito = valor + (valor * 0.05); 
            deposita(valorDeposito);

            return valorDeposito;
        }

        return 0;
    }

    public void ordenaDependentes()
    {
        boolean troca = true;

        while(troca)
        {
            troca = false;

            for(int i = 0; i < dependentes.length - 1; i++)
            {
                if(dependentes[i + 1] == null)
                    continue;

                if(dependentes[i] == null)
                {
                    dependentes[i] = dependentes[i + 1];
                    dependentes[i + 1] = null;
                    troca = true;
                    continue;
                }

                if(dependentes[i].getNome().compareTo(dependentes[i + 1].getNome()) > 0)
                {
                    Dependente aux = dependentes[i];
                    dependentes[i] = dependentes[i + 1];
                    dependentes[i + 1] = aux;
                    troca = true;
                }
            }
        }
    }

    @Override
    public String toString()
    {
        String ret = super.toString();
        ret += String.format(" | Saldo vinculado: %s | Saldo financiado: %s ", saldoVinculado, saldoFinanciado);

        ordenaDependentes();

        if(contaDependentes() <= 0)
            return ret;

        ret += "\n * Dependentes: * \n";

        for(Dependente dep : dependentes)
        {
            if(dep == null)
                continue;

            ret += dep.toString();
        }

        return ret;
    }
}
