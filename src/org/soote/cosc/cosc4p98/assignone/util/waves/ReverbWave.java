package org.soote.cosc.cosc4p98.assignone.util.waves;

import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author David Nadeau
 */
public class ReverbWave extends Wave {

    private final ArrayList<String> samples;
    private final int[] maximums;

    public ReverbWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l,
            String n) {
        super(sc, bps, c, sr, f, n);
        this.samples = l;

        //get the smallest and the largest samples
        maximums = super.findGlobalMaximums(this.samples);
    }

    @Override
    public void synthesize() {
        synthesize(500, 0.8);
    }

    public void synthesize(int delay, double volume) throws
            IndexOutOfBoundsException {
        if (delay > 1543) {
            throw new IndexOutOfBoundsException(
                    "Echo delay must be less than 35mSec (1300)");
        }
        String row, reverb;
        int c1, c2, c1reverb, c2reverb;
        for (int i = 0; i < super.getSampleCount(); i++) {

            //    g(n) = w1f(n)+w2f(n-100)+w2(f(n-200))+...
            row = this.samples.get(i);

            //channel 1, channel 2
            c1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            c2 = Integer.parseInt(row.substring(row.indexOf('\t') + 1, row
                    .length()));

            if (i > delay) {
                reverb = this.samples.get(i - delay);

                //convert string row to ints
                c1reverb = Integer.parseInt(reverb.substring(0, reverb
                        .indexOf('\t')));
                c2reverb = Integer.parseInt(reverb.substring(reverb
                        .indexOf('\t') + 1, reverb.length()));

                //adjust reverbs volume, further depth -> quieter volume
                c1reverb *= volume;
                c2reverb *= volume;

                //update global maximums in case the sum of the original
                //wave and the reverb wave results in a new min or max
                maximums[0] = c1 + c1reverb < maximums[0]
                        ? c1 + c1reverb : maximums[0];
                maximums[0] = c2 + c2reverb < maximums[0]
                        ? c2 + c2reverb : maximums[0];
                maximums[1] = c1 + c1reverb > maximums[1]
                        ? c1 + c1reverb : maximums[1];
                maximums[1] = c2 + c2reverb > maximums[1]
                        ? c2 + c2reverb : maximums[1];

                //add reverb wave to original wave
                c1 = super.normalize(maximums[0], maximums[1],
                        c1 + c1reverb);
                c2 = super.normalize(maximums[0], maximums[1],
                        c2 + c2reverb);
            }
            super.addSample(c1 + "\t" + c2);
        }

    }

}
