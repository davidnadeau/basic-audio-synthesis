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
public class RandomWave extends Wave{
    private static final int AMPLITUDE = 15000;

    public RandomWave(int sc, int bps, int c, int sr, int f, String n) {
        super(sc,bps,c,sr,f,n);
    }
        
    public void synthesize() {
        int sample;
        for (int i = 1; i <= this.getSampleCount(); i++) {
            sample =  (int) (AMPLITUDE * Math.random() * i);
            this.addSample(sample +"\t" + sample);
        }
    }    
}
