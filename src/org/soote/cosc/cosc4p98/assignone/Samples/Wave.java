/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.Samples;

/**
 *
 * @author soote
 */
public abstract class Wave {
    
    private int sampleCount,
                bitsPerSample,
                channels,
                sampleRate;
    
    private boolean normalized;

    public Wave(int sc, int bps, int c, int sr, boolean n) {
        this.sampleCount = sc;
        this.bitsPerSample = bps;
        this.channels = c;
        this.sampleRate = sr;
        this.normalized = n;
    }
    
    public int[] synthesize() {return new int[0];};
    public String getType() { return "defualt"; };
    
    public int getSampleCount() { return sampleCount; }
    public int getBitsPerSample() { return bitsPerSample; } 
    public int getChannels() { return channels; } 
    public int getSampleRate() { return sampleRate; } 
    public boolean isNormalized() { return normalized; } 
}
