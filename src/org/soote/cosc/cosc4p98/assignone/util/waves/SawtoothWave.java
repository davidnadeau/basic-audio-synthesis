package org.soote.cosc.cosc4p98.assignone.util.waves;

import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author David Nadeau
 */
public class SawtoothWave extends Wave {

    private static final int AMPLITUDE = 10000;

    public SawtoothWave(int sc, int bps, int c, int sr, int f, String n) {
        super(sc, bps, c, sr, f, n);
    }

    public void synthesize() {
        double sample = -1,
                period = (super.getFrequency() * 2.) / super.getSampleRate();

        for (int i = 1; i <= super.getSampleCount(); i++) {
            super.addSample(Math.round(AMPLITUDE * sample) + "\t" + Math.round(AMPLITUDE * sample));
            sample += period;
            if (sample >= 1) {
                sample = -1;
            }
        }
    }
}
