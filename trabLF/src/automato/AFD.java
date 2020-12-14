package automato;

import com.google.common.base.Predicates;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AFD {

    private Set<Character> alfabeto = new HashSet<>();
    private Set<Estado> estados = new HashSet<>();
    private Set<Estado> estadosAceitos = new HashSet<>();
    private Transicao funcaoTransicao = new Transicao();
    private List<Estado> todosOsEstadosComTransicoes;
    private List<Estado> estadosInalcancaveis;
    private Estado estadoInicial;
    private String titulo;

    AFD() {

    }

    AFD(String titulo) {
        this.titulo = titulo;
        todosOsEstadosComTransicoes = new ArrayList();
        estadosInalcancaveis = new ArrayList();
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

    void setEstadoInicial() {
        Scanner scn = new Scanner(System.in);
        String idEstado;
        Estado estado = null;
        do {
            System.out.println("Informe o estado inicial do autômato: ");
            idEstado = scn.next();

            if (encontraEstado(idEstado) != null) {
                estado = encontraEstado(idEstado);
                this.estadoInicial = estado;
            } else {
                System.out.println("Estado não encontrado!");
            }

        } while (estado == null);

    }

    Estado encontraEstado(String idEstado) {

        for (Estado estado : estados) {
            if (estado.getNome().equalsIgnoreCase(idEstado)) {
                return estado;
            }
        }
        return null;
    }

    void addAlfabeto() {
        Scanner scn = new Scanner(System.in);
        char caractere;
        System.out.println("Informe o caractere que deseja adicionar ao alfabeto: ");
        caractere = scn.next().charAt(0);
        alfabeto.add(caractere);

        printAlfabeto();
    }

    void printAlfabeto() {
        System.out.println("Alfabeto: ");
        for (char caractere : alfabeto) {
            System.out.print(caractere + " ");
        }
        System.out.println();
    }

    Set<Estado> getEstados() {
        return estados;
    }

    void addEstado() {
        Scanner scn = new Scanner(System.in);
        int numEstados;
        System.out.println("Informe o número de estados que o autômato deverá conter: ");
        numEstados = scn.nextInt();

        for (int i = 0; i < numEstados; i++) {
            Estado estado = new Estado();
            estados.add(estado);
        }
        printEstados();
    }

    void printEstados() {
        System.out.println("Conjunto de estados: ");
        for (Estado estado : estados) {
            System.out.print(estado.getNome() + " ");
        }
        System.out.println();
    }

    Set<Estado> getEstadosAceitos() {
        return estadosAceitos;
    }

    void addEstadoAceito() {
        Scanner scn = new Scanner(System.in);
        String idEstado;
        Estado estado = null;
        do {
            System.out.println("Informe o estado de aceitação do autômato: ");
            idEstado = scn.next();

            if (encontraEstado(idEstado) != null) {
                estado = encontraEstado(idEstado);
                estadosAceitos.add(estado);
            } else {
                System.out.println("Estado não encontrado!");
            }

        } while (estado == null);
    }

    /**
     * Imprime os estados Aceitos
     */
    void printEstadosAceitos() {
        System.out.println("Conjunto de estados Finais: ");
        for (Estado estado : estadosAceitos) {
            System.out.println(estado.getNome() + "\n");
        }

    }

    /**
     * Adiciona transição
     *
     * @param origem estado de origem
     * @param entrada valor de entrada
     * @param destino estado de destino
     */
    void addTransicao() {
        Scanner scn = new Scanner(System.in);
        String estado1, estado2;
        char entrada;
        Estado origem = null;
        Estado destino = null;

        do {
            System.out.println("Informe o estado de origem da transição: ");
            estado1 = scn.next();

            if (encontraEstado(estado1) != null) {
                origem = encontraEstado(estado1);
            } else {
                System.out.println("Estado não encontrado!");
            }

        } while (origem == null);

        do {
            System.out.println("Informe o estado de destino da transição: ");
            estado2 = scn.next();

            if (encontraEstado(estado2) != null) {
                destino = encontraEstado(estado2);
            } else {
                System.out.println("Estado não encontrado!");
            }

        } while (destino == null);

        do {
            System.out.println("Informe o caractere da transição: ");
            entrada = scn.next().charAt(0);
        } while (verificaEntrada(Character.toString(entrada)) == false);

        if (verificaEntrada(Character.toString(entrada)) == true) {
            funcaoTransicao.addTransicao(origem, entrada, destino);
        }
        funcaoTransicao.printTransicao();
    }

    /**
     * Imprime as informações do autômato: alfabeto, conjuntos de estados,
     * estado inicial, estados de aceitação.
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
     * Imprime a sequência de estados que é acessado pelo autômato na leitura da
     * palavra.
     *
     * @param sequencia
     */
    void printSequencia(ArrayList<Estado> sequencia) {
        System.out.println("Sequência de Estados: \n");
        for (int i = 0; i < sequencia.size(); i++) {
            System.out.print(sequencia.get(i).getNome() + " ");
        }
        System.out.println();
    }

    /**
     * Verifica se os caracteres inseridos na entrada são compativeis com o
     * alfabeto.
     *
     * @param stringEntrada palavra informada pelo usuário.
     * @return
     */
    boolean verificaEntrada(String stringEntrada) {
        boolean isValido = true;
        for (int i = 0; i < stringEntrada.length(); i++) {
            if (!alfabeto.contains(stringEntrada.charAt(i))) {     // Se a string de entrada não estiver contida no alfabeto retorna falso
                isValido = false;
                break;
            }

        }
        return isValido;
    }

    /**
     * Execução do automato baseado na entrada, retorna true para aceita e false
     * para palavra rejeitada.
     *
     * @param palavra palavra de entrada.
     * @return true ou false. true para aceita e falso para rejeitada.
     */
    boolean executaAutomato(String palavra) {
        if (!verificaEntrada(palavra)) {
            throw new IllegalArgumentException("Os caracteres inseridos não estão contidos no alfabeto!");
        } else {
            ArrayList<Estado> sequencia = new ArrayList<>();     // Sequência de estados acessados
            Estado origem = estadoInicial; //seta o primeiro estado de origem do autômato com base no estadoInicial setado anteriormente.
            sequencia.add(estadoInicial);     // Adiciona o estado inicial a sequencia de estados alcançados

            for (int i = 0; i < palavra.length(); i++) {
                char caractereAtualLeitura = palavra.charAt(i);
                try {
                    Estado destino = funcaoTransicao.getDestino(origem, caractereAtualLeitura);    // Encontra o estado destino através do estado origem e do caracter atual do processamento da palavra.

                    sequencia.add(destino);      // adiciona o estado a sequência
                    origem = destino;     // estado destino se torna estado origem para a próxima iteração.
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());

                }
            }

            Estado estadoFinal = sequencia.get(sequencia.size() - 1);
            if (estadosAceitos.contains(estadoFinal)) {    // Se o estado final esta no conjunto de estados de aceitação retorna true
                printSequencia(sequencia);
                return true;
            } else {
                printSequencia(sequencia);
                return false;
            }
        }
    }

    /**
     * Método que adiciona os estados que estão contidos nas transições em um
     * array, para comparação e eliminação de estados mortos
     */
    void addEstadosTransicoes() {

        for (Map.Entry<Estado, Map<Character, Estado>> entry : funcaoTransicao.getFuncaoDeTransicao().entrySet()) {  // Para cada entrada no mapa de transições
            Estado origem = entry.getKey();
            Map<Character, Estado> entradaEdestino = entry.getValue();
            //Adiciona estados de origem ao array
            todosOsEstadosComTransicoes.add(origem);

            for (Map.Entry<Character, Estado> e : entradaEdestino.entrySet()) {
                Estado destino = e.getValue();
                //Adiciona estados de destino ao array
                todosOsEstadosComTransicoes.add(destino);
                estadosInalcancaveis.add(destino);

            }
        }
        printEstados();

    }

    void minimizacao() {
        for (Map.Entry<Estado, Map<Character, Estado>> entry : funcaoTransicao.getFuncaoDeTransicao().entrySet()) {  // Para cada entrada no mapa de transições
            Estado origem = entry.getKey();
            Map<Character, Estado> entradaEdestino = entry.getValue();
            //Adiciona estados de origem ao array
            todosOsEstadosComTransicoes.add(origem);

            for (Estado e : entradaEdestino.values()) {
                if(estadosInalcancaveis.contains(e) && entradaEdestino.containsValue(e)){
                  
                   funcaoTransicao.getFuncaoDeTransicao().values().removeIf(value -> value.containsValue(e));
                    
                  
                } 
            }
        }
    }

    /**
     * O método remove os estados mortos do autômato.
     */
    void minimiza() {
        minimizacao();
        estados.retainAll(todosOsEstadosComTransicoes);
        System.out.println("Autômato minimizado: ");
        printEstados();
    }

    /**
     * Menu do programa.
     */
    void menu() {
        int op;
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.println("Informe a opção que deseja: \n 1. Adicionar alfabeto \n 2. Adicionar Estados \n 3. Adicionar Transição \n 4. Definir Estado Inicial \n 5. Definir Estado de Aceitação "
                    + "\n 6. Executar autômato \n 7. Minizar automato \n 0. Sair");
            op = entrada.nextInt();
            switch (op) {
                case 1:
                    addAlfabeto();
                    break;
                case 2:
                    addEstado();
                    break;
                case 3:
                    addTransicao();
                    break;
                case 4:
                    setEstadoInicial();
                    break;
                case 5:
                    addEstadoAceito();
                    break;
                case 6:
                    Scanner scn = new Scanner(System.in);
                    String palavra;
                    System.out.println("Informe uma palavra: ");
                    palavra = scn.next();

                    try {
                        executaAutomato(palavra);
                        boolean estadoAceito = executaAutomato(palavra);

                        if (estadoAceito) {
                            System.out.println("A palavra " + palavra + " foi aceita pelo autômato\n");
                        } else {
                            System.out.println("A palavra " + palavra + " foi rejeitada pelo autômato\n");
                        }
                    } catch (NullPointerException e) {
                        menu();
                    } catch (IllegalArgumentException e) {
                        menu();
                    }

                    break;
                case 7:
                    addEstadosTransicoes();
                    minimiza();

                    break;

                default:
                    System.exit(op);
            }
        } while (op != 0);

    }
}
