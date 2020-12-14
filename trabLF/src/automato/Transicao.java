package automato;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

class Transicao {
   private Map<Estado, Map<Character, Estado>> transicao = new HashMap<>();
   
   private List todosOsEstadosComTransicoes;
   
   Transicao() {
   
   }
   
   Map<Estado, Map<Character, Estado>> getTransitionFunction() {
      return transicao;
   }
   
   void addTransicao(Estado origem, char entrada, Estado destino) {
      if(!transicao.containsKey(origem)) {     // if key source is not in transition, add new Map
         Map<Character, Estado> valor = new HashMap<>();
         transicao.put(origem, valor);
         
         valor.put(entrada, destino);     // put into nested Map
      }
      else {
         Map<Character, Estado> value = transicao.get(origem);    // else retrieve nested map
         
         value.put(entrada, destino);
      }
   }
   
   Estado getDestino(Estado origem, char entrada) {
      Estado destino = transicao.get(origem).get(entrada);
      if(destino == null) {
         throw new NullPointerException("Transição não encontrada para o estado de origem: " + origem.getNome() + " Entrada: " + entrada);
      }
      
      else
         return destino;
   }
   
   void printTransicao() {
      System.out.print("Função de Transição: \n");
      for(Map.Entry<Estado, Map<Character, Estado>> entry: transicao.entrySet()) {  // Para cada entrada no mapa de transições
         Estado origem = entry.getKey();     
         Map<Character, Estado> value = entry.getValue();       // get value, single-entry Map(input Character, target Estado)
         
         for(Map.Entry<Character, Estado> e: value.entrySet()) {   
            char input = e.getKey();
            Estado target = e.getValue();
            System.out.print("\tEstado origem: " + origem.getNome() + " --> entrada " + input + " --> Estado destino: " + target.getNome() + "\n");
         }       
      }
   }
   
   void minimizacao(){
       todosOsEstadosComTransicoes= new ArrayList();
       for(Map.Entry<Estado, Map<Character, Estado>> entry: transicao.entrySet()) {  // Para cada entrada no mapa de transições
         Estado origem = entry.getKey();     
         Map<Character, Estado> value = entry.getValue();       // get value, single-entry Map(input Character, target Estado)
         //TODO ADICIONAR ESTADOS DE ORIGEM
        todosOsEstadosComTransicoes.add(origem);
         
         for(Map.Entry<Character, Estado> e: value.entrySet()) {   
            char input = e.getKey();
            Estado target = e.getValue();
            //TODO ADICIONAR ESTADOS DE DESTINO
        todosOsEstadosComTransicoes.add(target);    
            System.out.print("\tEstado origem: " + origem.getNome() + " --> entrada " + input + " --> Estado destino: " + target.getNome() + "\n");
         }       
      }
       
       
   }
   
   
}
