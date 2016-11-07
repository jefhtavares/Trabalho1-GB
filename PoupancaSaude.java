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
    }

    @Override
    public void deposita(double valor)
    {
        //todo:
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