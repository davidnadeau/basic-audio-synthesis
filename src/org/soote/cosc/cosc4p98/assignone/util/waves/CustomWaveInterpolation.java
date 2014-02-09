/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.util.waves;

import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author soote
 */
public class CustomWaveInterpolation extends Wave{
    private ArrayList<String> samples;
    
    public CustomWaveInterpolation(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n) {
        super(sc*2,bps,c,sr,f,n);
        this.samples = l;
    }
    
    public void synthesize() {
        String row;
        
        double step = 0.5;
        super.addSample(0+"\t"+0);
        super.addSample(0+"\t"+0);
        
        for (double i = 0.0; i < super.getSampleCount()/2 - 1; i += step) {
            row = samples.get((int)Math.floor(i));
            int s1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            int s2 = Integer.parseInt(row.substring(row.indexOf('\t')+1,row.length()));
            row = samples.get((int)Math.ceil(i));
            int sa1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            int sa2 = Integer.parseInt(row.substring(row.indexOf('\t')+1,row.length()));
            super.addSample((s1+sa1)/2+"\t"+(s2+sa2)/2);
        }
    }
}
