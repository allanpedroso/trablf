package automato;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import automato.Transicao;

class AFD {
   
   private Set<Character> alfabeto = new HashSet<>();
   private Set<Estado> estados = new HashSet<>();
   private Set<Estado> estadosAceitos = new HashSet<>();
   private Transicao funcaoTransicao = new Transicao();
   private Estado estadoInicial;
   private String titulo;
   
   AFD() {
 
   }
   
   AFD(String title) {
      this.titulo = title;
   }
   
   String getTitulo() {
      return titulo;
   }
   
   void setTitulo(String titulo) {
      this.titulo = titulo;
   }
   
   Estado getEstadoInicial() {
      return estadoInicial;
   }
   
   void setEstadoInicial(Estado estado) {
      estadoInicial = estado;
   }
   
   Set<Character> getAlfabeto() {
      return alfabeto;
   }
   
   void addAlfabeto(char character) {
      alfabeto.add(character);
   }
   
   void printAlfabeto() {
      System.out.print("Alphabet: ");
      for(char character: alfabeto) {
         System.out.print(character + " ");
      }
      System.out.println();
   }
   
   Set<Estado> getEstados() {
      return estados;
   }
   
   void addEstado(Estado estado) {
      estados.add(estado);
   }
   
   void printEstados() {
      System.out.print("Estados: ");
      for(Estado estado: estados) {
         System.out.print(estado.getNome() + " ");
      }
      System.out.println();
   }
   
   Set<Estado> getEstadosAceitos() {
      return estadosAceitos;
   }
   
   void addEstadoAceito(Estado estado) {
      estadosAceitos.add(estado);
   }
   
   /**
    * Imprime os estados Aceitos
    */
   void printEstadosAceitos() {
      System.out.print("Estados aceitos: ");
      for(Estado estado: estadosAceitos) {
         System.out.print(estado.getNome() + " ");
      }
      System.out.println();
   }
   
   /**
    * Adiciona transição
    * @param origem estado de origem   
    * @param entrada valor de entrada
    * @param destino estado de destino
    */
   void addTransicao(Estado origem, char entrada, Estado destino) {
      funcaoTransicao.addTransicao(origem, entrada, destino);
   }
   
   /**
    * Imprime as informações do autômato: alfabeto, conjuntos de estados, estado inicial, estados de aceitação.
    */
   void getInfoAFD() {
      System.out.println("AFD: " + getTitulo());
      printAlfabeto();
      printEstados();
      printEstadosAceitos();
      funcaoTransicao.printTransicao();
      System.out.println("Estado Inicial: " + estadoInicial.getNome() + "\n");
   }
   
   
   /**
    * Imprime a sequência de estados
    * @param sequence 
    */
   void printSequencia(ArrayList<Estado> sequence) {
      System.out.print("Sequência de Estados:\n");
      for(int i = 0; i < sequence.size(); i++) {
         System.out.print(sequence.get(i).getNome() + " "); 
      }
      System.out.println();
   }
   
  
   /**
    * Verifica se os caracteres inseridos na entrada são compativeis com o alfabeto.
    * @param stringEntrada
    * @return 
    */
   boolean verificaEntrada(String stringEntrada) {    
      boolean isValido = true;
      for(int i = 0; i < stringEntrada.length(); i++) {
         if(!alfabeto.contains(stringEntrada.charAt(i))) {     // Se a string de entrada não estiver contida no alfabeto retorna falso
            isValido = false;
            break;
         }
      }
      return isValido;   
   }
   
   /**
    * Execução do automato baseado na entrada, retorna true para aceita e false para palavra rejeitada.
    * @param userString
    * @return 
    */
   boolean solucao(String userString) {     
      if(!verificaEntrada(userString)) {
         throw new IllegalArgumentException("IAException: String characters not in alfabeto. ");
      }
      else {
         ArrayList<Estado> sequence = new ArrayList<>();     // Sequência de estados acessados
         Estado source = estadoInicial;
         sequence.add(estadoInicial);     // add initial start estado to sequence
         
         for(int i = 0; i < userString.length(); i++) {
            char currentChar = userString.charAt(i);
            try {
               Estado target = funcaoTransicao.getDestino(source, currentChar);    // find target Estado: given source Estado, current char
               
               sequence.add(target);      // add target Estado to sequence
               source = target;     // target Estado becomes source Estado, for next iteration
            }
            catch(NullPointerException ex) {
               System.out.println(ex.getMessage());
               System.exit(1);
            }
         }
         
         Estado lastState = sequence.get(sequence.size() - 1);
         if(estadosAceitos.contains(lastState)) {    // if last estado is in accept estados, return true
            printSequencia(sequence);
            return true;
         }
         else {
            printSequencia(sequence);
            return false;
         }
      }
   }
}
