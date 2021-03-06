package org.soote.cosc.cosc4p98.assignone.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author David Nadeau
 */
public abstract class Wave {

    private final int sampleCount,
            bitsPerSample,
            channels,
            sampleRate,
            frequency;
    private final List<String> data;
    private final String name;

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
        this.data.addAll(Arrays.asList(headers));
    }

    public void stripHeader() {
        for (int i = 0; i < 5; i++) {
            this.data.remove(0);
        }
    }

    //return a linear interpolation of two values
    public String interpolate(String a, String b) {
        return Integer.toString((int) ((Integer.parseInt(a) - (Integer.parseInt(
                b) - (Integer.parseInt(a)))) * 0.5));
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
        double minPossible = 0 - Math.pow(2, 15);
        double maxPossible = Math.pow(2, 15) - 1;
        double half = (max - min) / 2;

        double f1 = (double) (i - min) / (max - min);

        return (int) ((f1 < 0.5)
                ? minPossible - (2 * (minPossible * ((half * f1) / half)))
                : 2 * maxPossible * ((half * (f1 - 0.5)) / half));
    }

    //return immutable sample table
    public List<String> getData() {
        return Collections.unmodifiableList(this.data);
    }

    //waves will override this method
    public void synthesize() {
    }

    public String getType() {
        return "defualt";
    }

    public void addSample(String s) {
        this.data.add(s);
    }

    public int getSampleCount() {
        return this.sampleCount;
    }

    public int getBitsPerSample() {
        return this.bitsPerSample;
    }

    public int getNumChannels() {
        return this.channels;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public String getName() {
        return this.name;
    }

}
