/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.util;

/**
 *
 * @author soote
 */
public class SineWave extends Wave{
    private static final int AMPLITUDE = 15000;

    public SineWave(int sc, int bps, int c, int sr, int f, String n) {
        super(sc,bps,c,sr,f,n);
    }
    
    public void synthesize() {
        int sample;
        for (int i = 0; i < super.getSampleCount(); i++) {
            sample =  (int) (AMPLITUDE*Math.sin(2 * Math.PI * super.getFrequency()/super.getSampleRate() * i));
            super.addSample(sample +"\t" + sample);
        }
    }
}
