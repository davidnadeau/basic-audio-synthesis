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
public class SawtoothWave extends Wave{
    private static final int AMPLITUDE = 15000;

    public SawtoothWave(int sc, int bps, int c, int sr, int f, String n) {
        super(sc,bps,c,sr,f,n);
    }
    
    public void synthesize() {
        double sample = -1,
               period = (this.getFrequency() * 2.) / this.getSampleRate();
        
        for (int i = 1; i <= this.getSampleCount(); i++) {
            System.out.println(sample);
            super.addSample((int)(AMPLITUDE*sample) +"\t" + (int)(AMPLITUDE*sample));
            sample+= period;
            if (sample >= 1) sample = -1;
        }
    }
}
