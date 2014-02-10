package org.soote.cosc.cosc4p98.assignone.util.waves;

import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author David Nadeau
 */
public class EchoWave extends Wave {

    private final ArrayList<String> samples;
    private final int[] maximums;

    public EchoWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l,
            String n) {
        super(sc, bps, c, sr, f, n);
        this.samples = l;

        //get the smallest and largest samples
        maximums = super.findGlobalMaximums(this.samples);
    }

    @Override
    public void synthesize() {
        synthesize(2000, 0.8, 2);
    }

    public void synthesize(int delay, double volume, int depth) throws
            IndexOutOfBoundsException {
        if (delay < 1543) {
            throw new IndexOutOfBoundsException(
                    "Echo delay must be great than 35mSec (1300)");
        }
        String row, reverb;
        int c1, c2, c1echo, c2echo;
        for (int i = 0; i < super.getSampleCount(); i++) {

            //    g(n) = w1f(n)+w2f(n-100)+w2(f(n-200))+...
            row = this.samples.get(i);

            //channel 1, channel 2
            c1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            c2 = Integer.parseInt(row.substring(row.indexOf('\t') + 1, row
                    .length()));

            //Large depths would result in the start of the song having no
            //delay. This reduces the depth if the current sample can't read
            //back far enough.
            for (int k = 0; k < depth; k++) {
                if (i > (depth - k) * delay) {
                    for (int j = 1; j <= depth - k; j++) {
                        //get the sample at sampleIndex - j*delay
                        reverb = this.samples.get(i - (j * delay));

                        //convert string row to ints
                        c1echo = Integer.parseInt(reverb.substring(0, reverb
                                .indexOf('\t')));
                        c2echo = Integer.parseInt(reverb.substring(reverb
                                .indexOf('\t') + 1, reverb.length()));

                        //adjust reverbs volume, further depth -> quieter volume
                        c1echo *= volume * ((depth / ((double) j)) / depth);
                        c2echo *= volume * ((depth / ((double) j)) / depth);

                        //update global maximums in case the sum of the original
                        //wave and the reverb wave results in a new min or max
                        maximums[0] = c1 + c1echo < maximums[0] ? c1 + c1echo
                                : maximums[0];
                        maximums[0] = c2 + c2echo < maximums[0] ? c2 + c2echo
                                : maximums[0];
                        maximums[1] = c1 + c1echo > maximums[1] ? c1 + c1echo
                                : maximums[1];
                        maximums[1] = c2 + c2echo > maximums[1] ? c2 + c2echo
                                : maximums[1];

                        //add reverb wave to original wave
                        c1 = super.normalize(maximums[0], maximums[1],
                                c1 + c1echo);
                        c2 = super.normalize(maximums[0], maximums[1],
                                c2 + c2echo);
                    }
                    break;
                }
            }
            super.addSample(c1 + "\t" + c2);
        }
    }

}
