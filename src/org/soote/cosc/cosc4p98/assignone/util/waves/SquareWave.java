package org.soote.cosc.cosc4p98.assignone.util.waves;

import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author David Nadeau
 */
public class SquareWave extends Wave {

    public SquareWave(int sc, int bps, int c, int sr, int f, String n) {
        super(sc, bps, c, sr, f, n);
    }

    @Override
    public void synthesize() {
        int sample;
        for (int i = 1; i <= super.getSampleCount(); i++) {
            sample = (Math.sin(i) > 0) ? 1 : 0;
            super.addSample(sample + "\t" + sample);
        }
    }

}
