/** Alunos : Jéferson Bueno e xxxxxxxxxxx Trabalho GB Lab 1 Turma: 53 2016/2 */

public class Program
{
    public static void main(String args[])
    {
        Dependente d = new Dependente("Joaquim", 'v');
        PoupancaSaude ps = new PoupancaSaude(123, "Jéferson");
        Poupanca p = new Poupanca(123, "Jéferson");

        //System.out.println(d.toString());

        System.out.println("teste ordenacao");
        testeOrganiza();
    }

    public static void testeOrganiza()
    {
        /*Dependente[] dep = { null,
                             new Dependente("Joaquim", 'o'),
                             null,
                             new Dependente("Jeferson", 'o'),
                             new Dependente("Alice", 'o'),
                             null,
                             null

                            };


        PoupancaSaude.ordenaDependentes(dep);

        for(Dependente d : dep)
        {
            if(d == null)
                System.out.println("null");
            else
                System.out.println(d.getNome());
        }*/
    }
}