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
public abstract class Wave {
    
    private int sampleCount,
                bitsPerSample,
                channels,
                sampleRate,
                frequency;
    private ArrayList<String> data;
    protected String name;

    public Wave(int sc, int bps, int c, int sr, int f, String n) {
        data = new ArrayList();
        this.sampleCount = sc;
        this.bitsPerSample = bps;
        this.channels = c;
        this.sampleRate = sr;
        this.frequency = f;
        this.frequency = f;
        this.name = n;
        this.addHeaders();
    }
    private void addHeaders() {
        String[] headers = {
            "SAMPLES:\t"+this.sampleCount,
            "BITSPERSAMPLE:\t"+this.bitsPerSample,
            "CHANNELS:\t"+this.channels,
            "SAMPLERATE:\t"+this.sampleRate,
            "NORMALIZED:\tFALSE"
        };
        for (String h : headers) data.add(h);
        
    }
    public void synthesize() {}
    public String getType() { return "defualt"; }
    public void addSample(String s) { this.data.add(s); }
    
    public int getSampleCount() { return sampleCount; }
    public int getBitsPerSample() { return bitsPerSample; } 
    public int getNumChannels() { return channels; } 
    public int getSampleRate() { return sampleRate; } 
    public int getFrequency() { return frequency; } 
    public ArrayList<String> getSamples() { return data; } 
    public String getName() { return name; } 
    public ArrayList<String> getData() { return data; } 
}
