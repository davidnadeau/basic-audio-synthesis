package org.soote.cosc.cosc4p98.assignone.util;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author David Nadeau
 */
public class SimpleWaveManipulator extends Wave {

    private double phase;
    private int duration;
    private ArrayList<String> samples;

    public SimpleWaveManipulator(String n, ArrayList<String> s, double p, int d) {
        super(d, 16, 2, 44100, 440, n);
        this.samples = s;
        this.phase = p;
        this.duration = d;
    }

    public SimpleWaveManipulator(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n, double p, int d) {
        super(d, bps, c, sr, f, n);
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

        for (double i = 0; i < this.duration; i += this.phase) {

            int i1 = (int) (Math.floor(i) % this.samples.size()),
                    i2 = (int) (Math.ceil(i) % this.samples.size());

            String[] sFloor = this.samples.get(circularIndex(i1)).split("\t", -1),
                    sCeil = this.samples.get(circularIndex(i2)).split("\t", -1);

            String[] values = {
                super.interpolate(sFloor[0], sCeil[0]),
                super.interpolate(sFloor[1], sCeil[1])
            };
            row = values[0] + "\t" + values[1];
            super.addSample(row);

            //if we're going in reverse
            if (i <= 0 - this.duration) {
                break;
            }
        }
    }

    private int circularIndex(int i) {
        return i < 0 ? this.samples.size() + i : i;
    }

    public static SimpleWaveManipulator concatenateWaves(LinkedList<SimpleWaveManipulator> ll, String fileName) {
        ArrayList<String> l = new ArrayList();
        ArrayList<String> al;
        int index;
        for (Wave w : ll) {
            w.stripHeader();
            index = 0;
            al = w.getData();
            for (String s : al) {
                l.add(s);
                index++;
            }
        }

        return new SimpleWaveManipulator(fileName, l, 0.0, l.size());
    }
}
