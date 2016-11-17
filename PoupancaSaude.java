/** Alunos : Jéferson Bueno e xxxxxxxxxxx Trabalho GB Lab 1 Turma: 53 2016/2 */

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
        for(Dependente dep : this.dependentes)
        {
            if(dep == null)
            {
                dep = dependente;
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

    public void retiraSaude(double valor)
    {
        //TODO: Fazer essa porcaria
        if(valor > this.saldoVinculado)
        {

        }
    }

    public void amortizaFinanciamento()
    {
        //todo: fazer
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
        String ret = String.format("Saldo vinculado: %s Saldo financiado: %s", saldoVinculado, saldoFinanciado);

        ret += super.toString();

        ordenaDependentes();

        for(Dependente dep : dependentes)
        {
            if(dep == null)
                continue;

            ret += "\nDependentes:\n";
            ret += String.format("Nome: %s Parentesco: %s", dep.getNome(), dep.traduzParentesco());
        }

        return ret;
    }
}
