/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.Samples;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author soote
 */
public class DynamicWave extends Wave{
    private double phase, duration;
    private int sampleCountMod;
    private ArrayList<String> samples;

    public DynamicWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n, double p, double d) {
        super((int)(d*sr),bps,c,sr,f,n);
        this.sampleCountMod = sc;
        this.samples = l;
        this.phase = p;
        this.duration = d;
    }
    
    public void synthesize() {
        String row;
        for (int i = 0; i <  super.getSampleCount(); i++) {
            row = this.samples.get((int)(this.phase+i)%this.samples.size());
            super.addSample(row);
        }
    }
    
    public static ArrayList<String> combineWaves(LinkedList<Wave> ll) {
        ArrayList<String> l = new ArrayList();
        ArrayList<String> al;
        int index;
        for (Wave w  : ll) {
            w.stripHeader();
            index = 0;
            al = w.getData();
            for (String s : al) {
                if (index>22050) break;
                l.add(s);
                index++;
            }
        }
        return l;
    }
}
