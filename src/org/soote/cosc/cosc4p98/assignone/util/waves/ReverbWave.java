package org.soote.cosc.cosc4p98.assignone.util.waves;


import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.util.Wave;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author soote
 */
public class ReverbWave extends Wave{
    private ArrayList<String> samples;
    private int[] maximums;
    
    public ReverbWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n) {
        super(sc,bps,c,sr,f,n);
        this.samples = l;
        maximums = findGlobalMaximums();
        int c1 = normalize(50,100,50);
        int c2 = normalize(50,100,100);
        int c3 = normalize(50,100,70);
        int c4 = normalize(50,100,75);
        int c5 = normalize(50,100,76);
        System.out.println(c1+"    :   "+c2+"   :   "+c3+"    :   "+c4+"   :   "+c5);

    }
    
    @Override
    public void synthesize() {
        String row, reverb;
        for (int i = 0; i < super.getSampleCount(); i++) {
            
        //    g(n) = w1f(n)+w2f(n-100)+w2(f(n-200))+...
            row = this.samples.get(i);
            
            //channel 1, channel 2
            int c1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            int c2 = Integer.parseInt(row.substring(row.indexOf('\t')+1,row.length()));
            
            if (i > 1000) {
                int c1reverb, c2reverb;
                
                for (int j = 1; j <= 10; j++) {
                    reverb = this.samples.get(i-(100*j));
                    c1reverb = Integer.parseInt(reverb.substring(0, reverb.indexOf('\t')));
                    c2reverb = Integer.parseInt(reverb.substring(reverb.indexOf('\t')+1,reverb.length())); 
                    c1reverb*=0;//1.0/10.0;
                    c2reverb*=0;//1.0/10.0;
                    
                    if(c1+c1reverb>maximums[1]) maximums[1]=c1+c1reverb;
                    //add reverb wave to original wave
                    c1 = normalize(maximums[0],maximums[1],c1+c1reverb);
                    c2 = normalize(maximums[0],maximums[1],c2+c2reverb);
                }
            }
            super.addSample(c1+"\t"+c2);
        }
        
    }
    
    
    private int normalize(int min, int max, int i) {
        short minPossible = (short) (0-Math.pow(2,15));
        short maxPossible = (short) (Math.pow(2,15)-1);
        
        double f1 = (double)(i-min)/(max-min);
        
        double scale;
        if (f1<0.5) {
            scale = f1==0?minPossible:minPossible- (f1*minPossible);
        }else if (f1==0.5){
            scale = 0;
        } else {
            scale = f1*maxPossible;
        }
        
        return (int)scale;
    }
    
    private int[] findGlobalMaximums() {
        int c1,c2;
        int[] m = new int[2];
        for (String w : this.samples) {
            c1 = Integer.parseInt(w.substring(0, w.indexOf('\t')));
            c2 = Integer.parseInt(w.substring(w.indexOf('\t')+1,w.length()));
            m[0] = c1 < m[0] ? c1 : m[0];
            m[0] = c2 < m[0] ? c2 : m[0];
            m[1] = c1 > m[1] ? c1 : m[1];
            m[1] = c2 > m[1] ? c2 : m[1];
        }
        return m;
    }
}
