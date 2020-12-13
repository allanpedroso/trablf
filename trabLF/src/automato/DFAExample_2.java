package automato;

import java.util.Scanner;

public class DFAExample_2 {
   public static void main(String[] args) {
   
      Scanner input = new Scanner(System.in);
      
      AFD dfa = new AFD("0(00)*(11)*");
      
      dfa.addAlfabeto('0');     
      dfa.addAlfabeto('1');
      
      Estado q0 = new Estado("Q0");
      Estado q1 = new Estado("Q1");
      Estado q2 = new Estado("Q2");
      Estado q3 = new Estado("Q3");
      Estado q4 = new Estado("Q4");
      
      dfa.addEstado(q0);
      dfa.addEstado(q1);
      dfa.addEstado(q2);
      dfa.addEstado(q3);
      dfa.addEstado(q4);
      
      dfa.setEstadoInicial(q0);
      
      dfa.addEstadoAceito(q1);
      dfa.addEstadoAceito(q3);
      
      dfa.addTransicao(q0, '0', q1);
      dfa.addTransicao(q0, '1', q4);
      dfa.addTransicao(q1, '0', q0);
      dfa.addTransicao(q1, '1', q2);
      dfa.addTransicao(q2, '0', q4);
      dfa.addTransicao(q2, '1', q3);
      dfa.addTransicao(q3, '0', q4);
      dfa.addTransicao(q3, '1', q2);
      dfa.addTransicao(q4, '0', q4);
      dfa.addTransicao(q4, '1', q4);
      
      String userString;
      boolean restart = true;
      while(restart){      // program restart loop
         System.out.println("Enter a string of 0's and 1's: ");
         userString = input.next();
         
         //dfa.getInfoAFD();
         
         try {
            boolean acceptance = dfa.solucao(userString);
         
            if(acceptance)
               System.out.println(userString + " is accepted by machine: " + dfa.getTitulo() + "\n");
            else
               System.out.println(userString + " is rejected by machine: " + dfa.getTitulo() + "\n");
         }
         catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
         }
         
         System.out.println("To restart, press any key.  To exit, enter 0.");
         String restartString = input.next();
         if(restartString.charAt(0) == '0'){
            restart = false;
            System.out.println("Program has terminated. ");
         }
         else
            input.nextLine();
      }
   }
}
