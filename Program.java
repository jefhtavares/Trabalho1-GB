/** Alunos : Jéferson Bueno e xxxxxxxxxxx Trabalho GB Lab 1 Turma: 53 2016/2 */

public class Program
{
    public static void main(String args[])
    {
        Dependente d = new Dependente("Joaquim", 'v');
        PoupancaSaude ps = new PoupancaSaude(123, "Jéferson");
        Poupanca p = new Poupanca(123, "Jéferson");

        System.out.println(d.toString());
    }
}