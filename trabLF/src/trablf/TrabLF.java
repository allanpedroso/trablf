/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablf;

import automato.State;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Allan
 */
public class TrabLF {

    private int numStates;

    private ArrayList<State> states;
    private ArrayList<Transiction> transictions;
    int[] alphabet = {0, 1};
    Scanner scn;

    public TrabLF() {
        this.states = new ArrayList<State>();
        this.transictions = new ArrayList<Transiction>();
        this.scn = new Scanner(System.in);
    }

    public void addState(State state) {

        System.out.println("Informe o número de estados que o autômato deverá conter: ");
        numStates = scn.nextInt();

        for (int i = 0; i < this.numStates; i++) {
            state = new State();
            states.add(state);
        }

    }

    public void addTransictions() {
        String idstate1, idstate2;
        int symbol;
        Transiction transiction = new Transiction();
        State state1, state2;

        do {
            System.out.println("Informe o estado origem da transição: ");
            idstate1 = scn.nextLine();
            state1 = stateIsValid(idstate1);
            transiction.setState1(state1);
        } while (state1 == null);

        do {
            System.out.println("Informe o estado de destino da transição: ");
            idstate2 = scn.nextLine();
            state2 = stateIsValid(idstate2);
            transiction.setState2(state2);
        } while (state2 == null);

        do {
            System.out.println("Informe o valor de entrada da transição: ");
            symbol = scn.nextInt();
            if (isValidSymbol(symbol) == true) {
                transiction.setValue(String.valueOf(symbol));
            }
        } while (isValidSymbol(symbol) == false);
        transictions.add(transiction);
    }

    
    
    
    public State stateIsValid(String idstate) {
        State state = null;
        for (int i = 0; i < states.size(); i++) {
            if (idstate.equalsIgnoreCase(states.get(i).getId())) {
                state = states.get(i);
            }

            return null;

        }

        return state;
    }

    public boolean isValidSymbol(int symbol) {

        for (int i = 0; i < alphabet.length; i++) {
            if (symbol == alphabet[i]) {
                return true;
            }

            return false;
        }

    

    

    

    public static void main(String[] args) {
        // TODO code application logic here
    }

}
