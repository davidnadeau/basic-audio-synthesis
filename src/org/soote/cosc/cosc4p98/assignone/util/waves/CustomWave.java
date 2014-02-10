package org.soote.cosc.cosc4p98.assignone.util.waves;

import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author David Nadeau
 */
public class CustomWave extends Wave {

    private final ArrayList<String> samples;

    public CustomWave(int sc, int bps, int c, int sr, int f, ArrayList<String> l,
            String n) {
        super(sc, bps, c, sr, f, n);
        this.samples = l;
    }

    @Override
    public void synthesize() {
        String row;
        int s1, s2;
        for (int i = 0; i < super.getSampleCount(); i++) {
            row = this.samples.get(i);
            s1 = Integer.parseInt(row.substring(0, row.indexOf('\t')));
            s2 = Integer.parseInt(row.substring(row.indexOf('\t') + 1, row
                    .length()));
            super.addSample(s1 + "\t" + s2);
        }

    }

}
