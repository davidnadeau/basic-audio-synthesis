/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.Samples;

import java.util.ArrayList;

/**
 *
 * @author soote
 */
public class SineWave extends Wave{
    private static final String TYPE = "Sine";

    public SineWave(int sc, int bps, int c, int sr, boolean n) {
        super(sc,bps,c,sr,n);
    }
    
    public int[] synthesize() {
        int[] l = new int[this.getSampleCount()];
        int hertz;
        float time = 0;
        for (int i = 0; i < this.getSampleCount(); i++) {
            hertz = this.getSampleCount() / this.getSampleRate();
            l[i] =(int) ( 10000*Math.sin((hertz*2)*Math.PI*time));
            time+=0.002;
        }
        return l;
    }
    
    public String getType() {
        return TYPE;
    }
}
