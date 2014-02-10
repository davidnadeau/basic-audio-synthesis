package org.soote.cosc.cosc4p98.assignone.util.waves;

import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author David Nadeau
 */
public class RandomWave extends Wave {

    private static final int AMPLITUDE = 15000;

    public RandomWave(int sc, int bps, int c, int sr, int f, String n) {
        super(sc, bps, c, sr, f, n);
    }

    @Override
    public void synthesize() {
        int sample;
        for (int i = 1; i <= this.getSampleCount(); i++) {
            sample = (int) (AMPLITUDE * Math.random() * i);
            this.addSample(sample + "\t" + sample);
        }
    }

}
