package org.soote.cosc.cosc4p98.assignone.util.waves;

import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author David Nadeau
 */
public class ReverbWave extends Wave {

    private ArrayList<String> samples;
    private int[] maximums;

    public ReverbWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l, String n) {
        super(sc, bps, c, sr, f, n);
        this.samples = l;

        //get the smallest and the largest samples
        maximums = super.findGlobalMaximums(this.samples);
    }

    public void synthesize(int delay, double volume, int depth) throws IndexOutOfBoundsException {
        if (delay > 1543) {
            throw new IndexOutOfBoundsException("Echo delay must be less than 35mSec (1300)");
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
                    int c1reverb, c2reverb;

                    for (int j = 1; j <= depth - k; j++) {
                        //get the sample at sampleIndex - j*delay
                        reverb = this.samples.get(i - (j * delay));

                        //convert string row to ints
                        c1reverb = Integer.parseInt(reverb.substring(0, reverb.indexOf('\t')));
                        c2reverb = Integer.parseInt(reverb.substring(reverb.indexOf('\t') + 1, reverb.length()));

                        //adjust reverbs volume, further depth -> quieter volume
                        c1reverb *= volume * ((depth / ((double) j)) / depth);
                        c2reverb *= volume * ((depth / ((double) j)) / depth);

                        //update global maximums in case the sum of the original
                        //wave and the reverb wave results in a new min or max
                        maximums[0] = c1 + c1reverb < maximums[0] ? c1 + c1reverb : maximums[0];
                        maximums[0] = c2 + c2reverb < maximums[0] ? c2 + c2reverb : maximums[0];
                        maximums[1] = c1 + c1reverb > maximums[1] ? c1 + c1reverb : maximums[1];
                        maximums[1] = c2 + c2reverb > maximums[1] ? c2 + c2reverb : maximums[1];

                        //add reverb wave to original wave
                        c1 = super.normalize(maximums[0], maximums[1], c1 + c1reverb);
                        c2 = super.normalize(maximums[0], maximums[1], c2 + c2reverb);
                    }
                    break;
                }
            }
            super.addSample((int) c1 + "\t" + (int) c2);
        }

    }
}
