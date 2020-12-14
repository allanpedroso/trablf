package automato;

import java.util.Map;
import java.util.HashMap;

class Transicao {

    private Map<Estado, Map<Character, Estado>> transicao = new HashMap<>();

   
    private Estado estado;

    Transicao() {

    }

    /**
     * Método que retorna todas as transições
     * @return 
     */
    Map<Estado, Map<Character, Estado>> getFuncaoDeTransicao() {
        return transicao;
    }

    /**
     * Método que adiciona a transição.
     * @param origem estado de origem
     * @param entrada caractere do alfabeto
     * @param destino estado de destino
     */
    void addTransicao(Estado origem, char entrada, Estado destino) {
        if (!transicao.containsKey(origem)) {    
            Map<Character, Estado> valor = new HashMap<>();//se a transição não contem um estado de origem, se cria um novo mapa com estado origem e valor de entrada
            transicao.put(origem, valor);

            valor.put(entrada, destino);    
        } else {
            Map<Character, Estado> value = transicao.get(origem); //se ja contem origem, só inclui a entrada e o destino. 

            value.put(entrada, destino);
        }
    }

    /**
     * Método que retorna o estado de destino da transição.
     * @param origem
     * @param entrada
     * @return 
     */
    Estado getDestino(Estado origem, char entrada) {
        Estado destino = transicao.get(origem).get(entrada);
        if (destino == null) {
            throw new NullPointerException("Transição não encontrada para o estado de origem: " + origem.getNome() + " Entrada: " + entrada);
        } else {
            return destino;
        }
    }

    /**
     * Método que imprime as transições, estado de origem, entrada e estado de destino.
     */
    void printTransicao() {
        System.out.print("Função de Transição: \n");
        for (Map.Entry<Estado, Map<Character, Estado>> entry : transicao.entrySet()) {  // Para cada entrada no mapa de transições
            Estado origem = entry.getKey(); //retorna o estado de origem.
            Map<Character, Estado> value = entry.getValue(); //retorna o mapa aninhado.       

            for (Map.Entry<Character, Estado> e : value.entrySet()) {
                char input = e.getKey(); //retorna a entrada da transição.
                Estado destino = e.getValue(); //retorna o estado de destino da transição.
                System.out.print("\tEstado origem: " + origem.getNome() + " entrada " + input + " --> Estado destino: " + destino.getNome() + "\n");
            }
        }
    }

    

}
