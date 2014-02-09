/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.Samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Callable;

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

            int i1 = (int)(Math.floor(i) % this.samples.size()),
                  i2 = (int)(Math.ceil(i) % this.samples.size());
             
            String[] sFloor = this.samples.get(circularIndex(i1)).split("\t", -1),
                         sCeil = this.samples.get(circularIndex(i2)).split("\t", -1);
            
            String[] values = {
                interpolate(sFloor[0],sCeil[0]),
                interpolate(sFloor[1],sCeil[1])
            };
            row =  values[0]+"\t"+values[1];
            super.addSample(row);
            
            //if we're going in reverse, for will never terminate
            if (i<=0-this.duration) break;
        }
    }
    
    private String interpolate(String a, String b) {
        return Integer.toString((int)((Integer.parseInt(a)-(Integer.parseInt(b) - (Integer.parseInt(a))))*0.5));
    }
    
    private int circularIndex(int i) {
        return i < 0 ? this.samples.size() + i : i;
    }
    
    public static DynamicWave concatenateWaves(LinkedList<DynamicWave> ll, String fileName) {
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
        
        return new DynamicWave(fileName,l,0.0,l.size());
    }
}
