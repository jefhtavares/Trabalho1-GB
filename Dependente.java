/** Alunos : Jéferson Bueno e xxxxxxxxxxx Trabalho GB Lab 1 Turma: 53 2016/2 */

public class Dependente
{
    private String nome;
    private char parentesco;

    public Dependente(String nome, char parentesco)
    {
        this.nome = nome;
        setParentesco(parentesco);
    }

    public void setParentesco(char parentesco)
    {
        parentesco = Character.toLowerCase(parentesco);

        if(parentesco != 'c' || parentesco != 'f' || parentesco != 'p')
            parentesco = 'o';

        this.parentesco = parentesco;
    }

    public String traduzParentesco()
    {
        if(parentesco == 'c')
            return "Cônjuge";

        if(parentesco == 'f')
            return "Filho(a)";

        if(parentesco == 'p')
            return "progenitor (pais, avós)";

        return "outros";
    }

    @Override
    public String toString()
    {
        return String.format("Nome: %s - Parentesco: %s", this.nome, traduzParentesco());
    }

    //Todo: gets
}