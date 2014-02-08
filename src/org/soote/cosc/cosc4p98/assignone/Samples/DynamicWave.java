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
    private double phase;
    private int duration;
    private ArrayList<String> samples;

    public DynamicWave(String n, ArrayList<String> s, double p, int d) {
        super(d,16,2,44100,440,n);
        this.samples = s;
        this.phase = p;
        this.duration = d;
    }
    public DynamicWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n, double p, int d) {
        super(d,bps,c,sr,f,n);
        this.samples = l;
        this.phase = p;
        this.duration = d;

    }
    public void loadSamples() {
         for (String s : this.samples) {
            super.addSample(s);
        }
    }
    
    public void synthesize() {
        String row;
        for (double i = 0; i < this.duration; i+=this.phase) {
            String[] sFloor = this.samples.get((int)(Math.floor(i) % this.samples.size())).split("\t", -1),
                         sCeil = this.samples.get((int)(Math.ceil(i) % this.samples.size())).split("\t", -1);

            String[] averages = {
                avg(sFloor[0],sCeil[0]),
                avg(sFloor[1],sCeil[1])
            };
            
            row =  averages[0]+"\t"+averages[1];
            super.addSample(row);
        }
    }
    
    private String avg(String a, String b) {
        return Integer.toString((Integer.parseInt(a)+Integer.parseInt(b))/2);
    }
    
    public static DynamicWave concatenateWaves(LinkedList<DynamicWave> ll) {
        ArrayList<String> l = new ArrayList();
        ArrayList<String> al;
        int index;
        for (Wave w  : ll) {
            w.stripHeader();
            index = 0;
            al = w.getData();
            for (String s : al) {
                l.add(s);
                index++;
            }
        }
        
        return new DynamicWave("dynamic",l,0.0,l.size());
    }
}
