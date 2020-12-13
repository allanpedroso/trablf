package automato;

import java.util.Map;
import java.util.HashMap;

class Transicao {
   private Map<Estado, Map<Character, Estado>> transition = new HashMap<>();
   
   Transicao() {
   
   }
   
   Map<Estado, Map<Character, Estado>> getTransitionFunction() {
      return transition;
   }
   
   void addTransicao(Estado source, char input, Estado target) {
      if(!transition.containsKey(source)) {     // if key source is not in transition, add new Map
         Map<Character, Estado> value = new HashMap<>();
         transition.put(source, value);
         
         value.put(input, target);     // put into nested Map
      }
      else {
         Map<Character, Estado> value = transition.get(source);    // else retrieve nested map
         
         value.put(input, target);
      }
   }
   
   Estado getDestino(Estado origem, char entrada) {
      Estado destino = transition.get(origem).get(entrada);
      if(destino == null) {
         throw new NullPointerException("NPException: Transição não encontrada para o estado de origem: " + origem.getNome() + " Entrada: " + entrada);
      }
      
      else
         return destino;
   }
   
   void printTransicao() {
      System.out.print("Função de Transição: \n");
      for(Map.Entry<Estado, Map<Character, Estado>> entry: transition.entrySet()) {  // Para cada entrada no mapa de transições
         Estado source = entry.getKey();     
         Map<Character, Estado> value = entry.getValue();       // get value, single-entry Map(input Character, target Estado)
         
         for(Map.Entry<Character, Estado> e: value.entrySet()) {     // for each entry(1) in nested Map...
            char input = e.getKey();
            Estado target = e.getValue();
            System.out.print("\tSource State " + source.getNome() + " --> input " + input + " --> Target State " + target.getNome() + "\n");
         }       
      }
   }
}
