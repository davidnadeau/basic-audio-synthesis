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
public class CustomWave extends Wave {
    private ArrayList<String> samples;
    
    public CustomWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n) {
        super(sc,bps,c,sr,f,n);
        samples = l;
    }
    
    public double[][] synthesize(int step) {
        double[][] l = new double[super.getSampleCount()][2];

        String row;
        for (int i = 5; i < super.getSampleRate(); i+=step) {
            row = samples.get(i);
            l[i][0] = Double.parseDouble(row.substring(0, row.indexOf('\t')));
            l[i][1] = Double.parseDouble(row.substring(row.indexOf('\t'),row.length()));
        }
        return l;
    }
}
