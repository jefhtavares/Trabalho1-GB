/** Alunos : Jéferson Bueno e xxxxxxxxxxx Trabalho GB Lab 1 Turma: 53 2016/2 */

public class PoupancaSaude extends Poupanca
{
    private double saldoVinculado;
    private double saldoFinanciado;
    private Dependente[] dependentes;

    public PoupancaSaude(int numConta, String nomeCliente)
    {
        super(numConta, nomeCliente);
        dependentes = new Dependente[5];
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

        super.creditaRendimento(taxa)
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
}