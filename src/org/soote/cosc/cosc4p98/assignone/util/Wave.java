package org.soote.cosc.cosc4p98.assignone.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author David Nadeau
 */
public abstract class Wave {

    private int sampleCount,
            bitsPerSample,
            channels,
            sampleRate,
            frequency;
    private ArrayList<String> data;
    private String name;

    public Wave(int sc, int bps, int c, int sr, int f, String n) {
        data = new ArrayList();
        this.sampleCount = sc;
        this.bitsPerSample = bps;
        this.channels = c;
        this.sampleRate = sr;
        this.frequency = f;
        this.name = n;
        this.addHeaders();
    }

    private void addHeaders() {
        String[] headers = {
            "SAMPLES:\t" + this.sampleCount,
            "BITSPERSAMPLE:\t" + this.bitsPerSample,
            "CHANNELS:\t" + this.channels,
            "SAMPLERATE:\t" + this.sampleRate,
            "NORMALIZED:\tFALSE"
        };
        data.addAll(Arrays.asList(headers));
    }

    public void stripHeader() {
        for (int i = 0; i < 5; i++) {
            this.data.remove(0);
        }
    }

    //return a linear interpolation of two values
    public String interpolate(String a, String b) {
        return Integer.toString((int) ((Integer.parseInt(a) - (Integer.parseInt(b) - (Integer.parseInt(a)))) * 0.5));
    }

    //find global min and global max values in list
    public int[] findGlobalMaximums(ArrayList<String> s) {
        int c1, c2;
        int[] m = new int[2];
        for (String w : s) {
            c1 = Integer.parseInt(w.substring(0, w.indexOf('\t')));
            c2 = Integer.parseInt(w.substring(w.indexOf('\t') + 1, w.length()));
            m[0] = c1 < m[0] ? c1 : m[0];
            m[0] = c2 < m[0] ? c2 : m[0];
            m[1] = c1 > m[1] ? c1 : m[1];
            m[1] = c2 > m[1] ? c2 : m[1];
        }
        return m;
    }

    //change amplitude of sample to reside in acceptable range
    public int normalize(int min, int max, int i) {
        double minPossible = -32768 + 1;
        double maxPossible = 32768 - 1;
        double half = (max - min) / 2;

        double f1 = (double) (i - min) / (max - min);

        return (int) ((f1 < 0.5)
                ? minPossible - (2 * (minPossible * ((half * f1) / half)))
                : 2 * maxPossible * ((half * (f1 - 0.5)) / half));
    }

    //waves will override this method
    public void synthesize() {
    }

    public String getType() {
        return "defualt";
    }

    public void setFrequency(int f) {
        this.frequency = f;
    }

    public void addSample(String s) {
        this.data.add(s);
    }

    public int getSampleCount() {
        return sampleCount;
    }

    public int getBitsPerSample() {
        return bitsPerSample;
    }

    public int getNumChannels() {
        return channels;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public int getFrequency() {
        return frequency;
    }

    public ArrayList<String> getSamples() {
        return data;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
