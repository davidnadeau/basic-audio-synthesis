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
public class DynamicWave extends Wave{
    private int phase, duration,sampleCountMod;
    private ArrayList<String> samples;

    public DynamicWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n, int p, int d) {
        super(d*sr,bps,c,sr,f,n);
        this.sampleCountMod = sc;
        this.samples = l;
        this.phase = p;
        this.duration = d;
    }
    
    public void synthesize() {
        String row;
        for (int i = 0; i < this.duration*super.getSampleRate(); i++) {
            row = this.samples.get(i%sampleCountMod);
            int s1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            int s2 = Integer.parseInt(row.substring(row.indexOf('\t')+1,row.length()));
            super.addSample(s1+"\t"+s2);
        }
    }
}
