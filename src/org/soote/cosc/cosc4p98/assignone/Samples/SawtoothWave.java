/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.Samples;

/**
 *
 * @author soote
 */
public class SawtoothWave extends Wave{
    private static final String TYPE = "Sawtooth";

    public SawtoothWave(int sc, int bps, int c, int sr, boolean n) {
        super(sc,bps,c,sr,n);
    }
    
    public int[] synthesize() {
        return new int[10];
    }
    
    public String getType() {
        return TYPE;
    }
    
}
