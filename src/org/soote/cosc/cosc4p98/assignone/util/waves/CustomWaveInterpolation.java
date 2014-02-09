package org.soote.cosc.cosc4p98.assignone.util.waves;

import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author David Nadeau
 */
public class CustomWaveInterpolation extends Wave {

    private ArrayList<String> samples;

    public CustomWaveInterpolation(int sc, int bps, int c, int sr, int f,
            ArrayList<String> l, String n) {
        super(sc * 2, bps, c, sr, f, n);
        this.samples = l;
    }

    public void synthesize() {
        String[] sFloor, sCeil, interpolated = new String[2];
        String row;
        int i1, i2;
        double step = 0.5;
        super.addSample(0 + "\t" + 0);
        super.addSample(0 + "\t" + 0);

        for (double i = 0.0; i < super.getSampleCount() / 2 - 1; i += step) {
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
            if (i <= 0 - (super.getSampleCount() / 2 - 1)) {
                break;
            }
        }
    }

    private int circularIndex(int i) {
        return i < 0 ? this.samples.size() + i : i;
    }
}
