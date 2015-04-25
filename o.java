//Created by Victor Wolmeister - 25/04/2015
//1.00
import java.util.Scanner;
import java.util.Random;
public class o
{
  static int[] drawedX = new int[5];
  static int[] drawedO = new int[5];
  static int ixDrawed =0;
  static int ioDrawed =0;
  static int[] gone = new int[100];
  static int igone=0;
  static char[] tab = {'a','b','c','d','e','f','g','h','i'};
  static int vez=0;
  static int md;
  static int currentMd=0;
  static boolean foi=false;
  static int X=0;
  static int O=0;
  public static void main(String args[])
  {
    Random random = new Random();
    Scanner leia = new Scanner(System.in);
    String cord ="";
    char P1,P2;
    P1 = 'X'; //Par
    P2 = 'O'; //Impar
    md = melhorD();////////////////////
    while(X!=md && O!=md)
    {
      while(verifEnd()==false && vez!=9)
      {
        System.out.println("Digite a coordenada");
        while(foi==false)
        {
          cord=leia.nextLine();
          if(verifCMD(cord)==true)
          {
            foi=true;
          }
        }
        if(vez%2==0)
        {
          tab[DrawSlot(cord)-1]='X';
          drawedX[ixDrawed]=DrawSlot(cord);
          ixDrawed++;  
        }
        else
        {
          tab[DrawSlot(cord)-1]='O';
          drawedO[ioDrawed]=DrawSlot(cord);
          ioDrawed++;
        }
        vez++;
        gone[igone]=DrawSlot(cord);
        igone++;
        DrawTable();
        foi=false;
      }  
      //Verifica se houve empate,X ou Y ganhou
      if(verifEnd()==true)
      {
        System.out.println(verifWinner() + " ganhou!");
        if(verifWinner().equals("X"))
        {
          X++;
        }
        else
        {
          O++;
        }
      }
      else
      {
        System.out.println("Houve empate");
      }
      placar();
      reset();///////////////////////
    }
    
  }
  
  public static void DrawTable()
  {
    int contSlots=1;
    int totalSlots = 9;
    while(contSlots <= totalSlots)
    {
      if((contSlots==drawedX[0]) || (contSlots==drawedX[1]) || (contSlots==drawedX[2]) || (contSlots==drawedX[3]) || (contSlots==drawedX[4]))
      {
        System.out.print("[X]");
      }
      else if((contSlots==drawedO[0]) || (contSlots==drawedO[1]) || (contSlots==drawedO[2]) || (contSlots==drawedO[3]) || (contSlots==drawedO[4]))
      {
        System.out.print("[O]");
      }
      else
      {
        System.out.print("[ ]");
      }
      if(contSlots%3==0)
      {
        System.out.print("\n");
      }
      contSlots++;
      
    } 
  }
  public static int DrawSlot(String draw)
  {
    int x,y;
    int drawSlot;
    String xS,yS;
    xS = draw.substring(0,1);
    yS = draw.substring(2,3);
    x = Integer.parseInt(xS);
    y = Integer.parseInt(yS);
    if(y==1)
    {
      drawSlot=x;
    }
    else
    {
      drawSlot=(y-1)*3 + x;
    }
    return drawSlot;
  }
  
  public static boolean verifCMD(String cmd)
  {
    boolean ok=false;
    if(cmd.length()!=3) //Se a string nao tiver 3 caracteres..
    {
      System.out.println("Erro 00: Coordenada inválida, insira novamente");
      ok=false;
    }
    else // Se tiver exatamente 3 caracteres
    {
      if(!cmd.substring(1,2).equals(",")) // Se o do meio não for virgula...
      {
        System.out.println("Erro 01: Coordenada inválida, insira novamente");
        ok=false;
      }
      else //Se for virgula
      {
        //Se o primeiro caracter for diferente de 0~9
        if(!(cmd.substring(0,1).equals("0") || cmd.substring(0,1).equals("1") || cmd.substring(0,1).equals("2") || cmd.substring(0,1).equals("3") || cmd.substring(0,1).equals("4") || cmd.substring(0,1).equals("5") || cmd.substring(0,1).equals("6") || cmd.substring(0,1).equals("7") || cmd.substring(0,1).equals("8") || cmd.substring(0,1).equals("9")))
        {
          System.out.println("Erro 02: Coordenada inválida, insira novamente");
          ok=false;
        }
        else // se for 0~9
        {
          //Verifiac se o terceiro caracter é um numero de 0~9
          if((cmd.substring(2,3).equals("0") || cmd.substring(2,3).equals("1") || cmd.substring(2,3).equals("2") || cmd.substring(2,3).equals("3") || cmd.substring(2,3).equals("4") || cmd.substring(2,3).equals("5") || cmd.substring(2,3).equals("6") || cmd.substring(2,3).equals("7") || cmd.substring(2,3).equals("8") || cmd.substring(2,3).equals("9")))
          {
            for(int i=0;i<9;i++) //Verifica se a coordenada ja foi utilizada anteriormente
            {
              if(DrawSlot(cmd)==gone[i])
              {
                ok=false;
                System.out.println("Erro 04: Coordenada já foi utilizada, insira novamente");
                i=9;
              }
              else
              {
                ok=true;
              }
            }
          }
          else
          {
            System.out.println("Erro 03: Coordenada inválida, insira novamente");
            ok=false;
          }
        }
      }
    }
    return ok;
  }
  
  public static boolean verifEnd()
  {
    boolean ok;
    if((tab[0]==tab[1] && tab[1]==tab[2]) || (tab[3]==tab[4] && tab[4]==tab[5]) || (tab[6]==tab[7] && tab[7]==tab[8]) || (tab[0]==tab[3] && tab[3]==tab[6]) || (tab[1]==tab[4] && tab[4]==tab[7]) || (tab[2]==tab[5] && tab[5]==tab[8]) || (tab[0]==tab[4] && tab[4]==tab[8]) || (tab[2]==tab[4] && tab[4]==tab[6]))
    {
      ok=true;
    }
    else
    {
      ok=false;
    }
    return ok;
  }
  
  public static String verifWinner()
  {
    String winner;
    if(tab[0]=='X' && tab[1]=='X' && tab[2]=='X')
    {
      winner="X";
    }
    else if(tab[3]=='X' && tab[4]=='X' && tab[5]=='X')
    {
      winner="X";
    }
    else if(tab[6]=='X' && tab[7]=='X' && tab[8]=='X')
    {
      winner="X";
    }
    else if(tab[0]=='X' && tab[3]=='X' && tab[6]=='X')
    {
      winner="X";
    }
    else if(tab[1]=='X' && tab[4]=='X' && tab[7]=='X')
    {
      winner="X";
    }
    else if(tab[2]=='X' && tab[5]=='X' && tab[8]=='X')
    {
      winner="X";
    }
    else if(tab[0]=='X' && tab[4]=='X' && tab[8]=='X')
    {
      winner="X";
    }
    else if(tab[2]=='X' && tab[4]=='X' && tab[6]=='X')
    {
      winner="X";
    }
    else
    {
      winner="O";
    }
    return winner;
  }
  
  public static void reset()
  {
    vez=0;
    foi=false;
    igone=0;
    tab[0]='a';
    tab[1]='b';
    tab[2]='c';
    tab[3]='d';
    tab[4]='e';
    tab[5]='f';
    tab[6]='g';
    tab[7]='h';
    tab[8]='i';
    ixDrawed =0;
    ioDrawed=0;
    for(int i=0;i<5;i++)
    {
      drawedX[i]=0;
      drawedO[i]=0;
    }
    for(int i=0;i<100;i++)
    {
      gone[i]=0;
    }
  }
  
  public static int melhorD()
  {
    Scanner leia = new Scanner(System.in);
    int k;
    System.out.println("Voce deseja jogar uma melhor de 1,3 ou 5 partidas?");
    do
    {
      System.out.println("Digite 1 ou 3 ou 5");
      k=leia.nextInt();
    }while(k!=1 && k!=3 && k!=5);
    if(k==3)
    {
      k=2;
    }
    else if(k==5)
    {
      k=3;
    }
    return k;
  }
  
  public static void placar()
  {
    System.out.println("     PLACAR");
    System.out.println("  X          O");
    System.out.println("  "+X+"          "+O);
  }
}