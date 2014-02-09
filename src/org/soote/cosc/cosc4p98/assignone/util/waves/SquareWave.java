/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.util.waves;

import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author soote
 */
public class SquareWave extends Wave{
    public SquareWave(int sc, int bps, int c, int sr, int f, String n) {
        super(sc,bps,c,sr,f,n);
    }
    
    public void synthesize() {
        int sample;
        for (int i = 1; i <= super.getSampleCount(); i++) {
            sample =  (Math.sin(i) > 0) ?1:0;
            super.addSample(sample +"\t" + sample);
        }
    }
}
