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
public class CustomWave extends Wave {
    private ArrayList<String> samples;
    
    public CustomWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n) {
        super(sc,bps,c,sr,f,n);
        this.samples = l;
    }
    
    public void synthesize() {
        String row;
        for (int i = 0; i < super.getSampleCount(); i++) {
            row = this.samples.get(i);
            int s1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            int s2 = Integer.parseInt(row.substring(row.indexOf('\t')+1,row.length()));
            super.addSample(s1+"\t"+s2);
        }
        
    }
}
