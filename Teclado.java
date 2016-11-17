import java.io.*;

public class Teclado
{
     private static InputStreamReader i = new InputStreamReader (System.in);
     private static BufferedReader d = new BufferedReader(i);

     public static int leInt ()
     {
          System.out.print('\b');  //para abrir saida
         int a = 0;
         try
         {
             String s = d.readLine();
             a = Integer.parseInt(s);
         }
         catch (IOException e)
         {
             System.out.println ("Erro de I/O: " + e);
         }
         catch (NumberFormatException e)
         {
             System.out.println ("o valor digitado deve ser inteiro: "+e );
         }
         return (a);
     }

     public static int leInt (String msg)
     {
         int a = 0;
         System.out.println(msg);
         try
         {
             String s = d.readLine();
             a = Integer.parseInt(s);
         }
         catch (IOException e)
         {
             System.out.println ("Erro de I/O: "+e );
         }
         catch (NumberFormatException e)
         {
             System.out.println ("o valor digitado deve ser inteiro: "+e );
         }
         return (a);
     }

     public static double leDouble()
     {
         System.out.print('\b');  //para abrir saida
         double a = 0;
         try
         {
             String s = d.readLine();
             a = Double.parseDouble(s);
         }
         catch (IOException e)
         {
             System.out.println ("Erro de I/O: " + e);
         }
         catch (NumberFormatException e)
         {
             System.out.println ("o valor digitado deve ser numero: "+e );
         }
         return (a);
     }

     public static double leDouble(String msg)
     {
          double a = 0;
          System.out.println(msg);
          try
          {
             String s = d.readLine();
             a = Double.parseDouble(s);
          }
          catch (IOException e)
          {
             System.out.println ("Erro de I/O: " + e);
          }
         catch (NumberFormatException e)
         {
             System.out.println ("o valor digitado deve ser numero: "+e );
         }
         return (a);
     }

     public static String leString()
     {
         System.out.print('\b');  //para abrir saida
         String s = "";
         try
         {
            s = d.readLine();
         }
         catch (IOException e)
         {
            System.out.println ("Erro de I/O: " + e);
         }
         return (s);
     }

     public static String leString(String msg)
     {
          String s = "";
          System.out.println(msg);
          try
          {
               s = d.readLine();
          }
          catch (IOException e)
          {
               System.out.println ("Erro de I/O: " + e);
          }
          return (s);
     }

     public static char leChar()
     {
         System.out.print('\b');  //para abrir saida

         String s = "";
         char c=' ';
         try
         {
            s = d.readLine();
            c = s.charAt(0);
         }
         catch (IOException e)
         {
            System.out.println ("Erro de I/O: " + e);
         }
         return (c);
     }

     public static char leChar(String msg)
     {
         String s = "";
         char c=' ';
         System.out.println(msg);
         try
         {
            s = d.readLine();
            c = s.charAt(0);
         }
         catch (IOException e)
         {
            System.out.println ("Erro de I/O: " + e);
         }
         return (c);
     }
}
