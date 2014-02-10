package org.soote.cosc.cosc4p98.assignone.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author David Nadeau
 */
public class SimpleWaveManipulator extends Wave {

    private final double phase;
    private final int duration;
    private final List<String> samples;

    public SimpleWaveManipulator(String n, List<String> s, double p, int d) {
        super(d, 16, 2, 44100, 440, n);
        this.samples = s;
        this.phase = p;
        this.duration = d;
    }

    public SimpleWaveManipulator(int sc, int bps, int c, int sr, int f,
            ArrayList<String> l, String n, double p, int d) {
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

    @Override
    public void synthesize() {
        String[] sFloor, sCeil, interpolated = new String[2];
        String row;
        int i1, i2;
        for (double i = 0; i < this.duration; i += this.phase) {

            i1 = (int) (Math.floor(i) % this.samples.size());
            i2 = (int) (Math.ceil(i) % this.samples.size());

            sFloor = this.samples.get(circularIndex(i1)).split("\t", -1);
            sCeil = this.samples.get(circularIndex(i2)).split("\t", -1);

            //interpolate fractional indices
            interpolated[0] = super.interpolate(sFloor[0], sCeil[0]);
            interpolated[1] = super.interpolate(sFloor[1], sCeil[1]);

            row = interpolated[0] + "\t" + interpolated[1];
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

    public static SimpleWaveManipulator concatenateWaves(
            LinkedList<SimpleWaveManipulator> ll, String fileName) {
        ArrayList<String> l = new ArrayList();
        List<String> al;
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
