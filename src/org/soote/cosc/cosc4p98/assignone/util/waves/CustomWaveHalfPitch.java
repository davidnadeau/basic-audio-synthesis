/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.util;

import java.util.ArrayList;

/**
 *
 * @author soote
 */
public class CustomWaveHalfPitch extends Wave {
    private ArrayList<String> samples;
    
    public CustomWaveHalfPitch(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n) {
        //doubling every sample, so doubling the sample count
        super(sc*2,bps,c,sr,f,n);
        this.samples = l;
    }
    
    public void synthesize() {
        String row;
        for (int i = 0; i < super.getSampleCount()/2; i++) {
            row = this.samples.get(i);
            int s1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            int s2 = Integer.parseInt(row.substring(row.indexOf('\t')+1,row.length()));
            super.addSample(s1+"\t"+s2);
            super.addSample(s1+"\t"+s2);
        }

    }
}
