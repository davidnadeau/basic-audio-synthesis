/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soote.cosc.cosc4p98.assignone.util.waves;

import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author soote
 */
public class EchoWave extends Wave {

    private ArrayList<String> samples;
    private int[] maximums;

    public EchoWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n) {
        super(sc, bps, c, sr, f, n);
        this.samples = l;

        //get the smallest and largest samples
        maximums = findGlobalMaximums();
    }

    public void synthesize(int delay, double volume, int depth) throws IndexOutOfBoundsException {
        if (delay < 1300) {
            throw new IndexOutOfBoundsException("Echo delay must be great than 35mSec (1300)");
        }
        String row, reverb;
        for (int i = 0; i < super.getSampleCount(); i++) {

            //    g(n) = w1f(n)+w2f(n-100)+w2(f(n-200))+...
            row = this.samples.get(i);

            //channel 1, channel 2
            int c1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            int c2 = Integer.parseInt(row.substring(row.indexOf('\t') + 1, row.length()));

            //Large depths would result in the start of the song having no
            //delay. This reduces the depth if the current sample can't read
            //back far enough.
            for (int k = 0; k < depth; k++) {
                if (i > (depth - k) * delay) {
                    int c1echo, c2echo;

                    for (int j = 1; j <= depth - k; j++) {
                        //get the sample at currentDepth*delay
                        reverb = this.samples.get(i - (j * delay));

                        //convert string row to ints
                        c1echo = Integer.parseInt(reverb.substring(0, reverb.indexOf('\t')));
                        c2echo = Integer.parseInt(reverb.substring(reverb.indexOf('\t') + 1, reverb.length()));

                        //adjust reverbs volume, further depth -> quieter volume
                        c1echo *= volume * ((depth / ((double) j)) / depth);
                        c2echo *= volume * ((depth / ((double) j)) / depth);

                        //update global maximums in case the sum of the original
                        //wave and the reverb wave results in a new min or max
                        maximums[0] = c1 + c1echo < maximums[0] ? c1 + c1echo : maximums[0];
                        maximums[0] = c2 + c2echo < maximums[0] ? c2 + c2echo : maximums[0];
                        maximums[1] = c1 + c1echo > maximums[1] ? c1 + c1echo : maximums[1];
                        maximums[1] = c2 + c2echo > maximums[1] ? c2 + c2echo : maximums[1];

                        //add reverb wave to original wave
                        c1 = normalize(maximums[0], maximums[1], c1 + c1echo);
                        c2 = normalize(maximums[0], maximums[1], c2 + c2echo);
                    }
                    break;
                }
            }
            super.addSample((int) c1 + "\t" + (int) c2);
        }

    }

    private int normalize(int min, int max, int i) {
        double minPossible = -32768 + 1;
        double maxPossible = 32768 - 1;
        double half = (max - min) / 2;

        double f1 = (double) (i - min) / (max - min);

        return (int) ((f1 < 0.5)
                ? minPossible - (2 * (minPossible * ((half * f1) / half)))
                : 2 * maxPossible * ((half * (f1 - 0.5)) / half));
    }

    private int[] findGlobalMaximums() {
        int c1, c2;
        int[] m = new int[2];
        for (String w : this.samples) {
            c1 = Integer.parseInt(w.substring(0, w.indexOf('\t')));
            c2 = Integer.parseInt(w.substring(w.indexOf('\t') + 1, w.length()));
            m[0] = c1 < m[0] ? c1 : m[0];
            m[0] = c2 < m[0] ? c2 : m[0];
            m[1] = c1 > m[1] ? c1 : m[1];
            m[1] = c2 > m[1] ? c2 : m[1];
        }
        return m;
    }
}
