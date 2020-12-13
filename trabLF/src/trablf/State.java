/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trablf;

import automato.State;

/**
 *
 * @author Allan
 */
public class State {
    
    private static int numstate = 0;
    private String id = "Q".concat(String.valueOf(numstate));
    private boolean isfinal;
    private boolean isInitial;

    public State() {
        numstate++;
        this.isInitial = false;
        this.isfinal = false;
        this.id = id;
    }

    public static int getNumstate() {
        return numstate;
    }

    public static void setNumstate(int numstate) {
        State.numstate = numstate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsfinal() {
        return isfinal;
    }

    public void setIsfinal(boolean isfinal) {
        this.isfinal = isfinal;
    }

    public boolean isIsInitial() {
        return isInitial;
    }

    public void setIsInitial(boolean isInitial) {
        this.isInitial = isInitial;
    }
    
    
    
    


    
    
    
    
    
    
    
}
