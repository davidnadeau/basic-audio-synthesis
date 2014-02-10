package org.soote.cosc.cosc4p98.assignone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import org.soote.cosc.cosc4p98.assignone.io.DataToText;
import org.soote.cosc.cosc4p98.assignone.io.TextToData;
import org.soote.cosc.cosc4p98.assignone.util.SimpleWaveManipulator;
import org.soote.cosc.cosc4p98.assignone.util.waves.CustomWave;

/**
 *
 * @author David Nadeau
 */
public class LowFreqOscAndMangling {

    private static final String FILEPATH
            = "/home/soote/documents/school/4p98/AssignOne/music/";

    public LowFreqOscAndMangling() throws IOException, Exception {
        ArrayList<String> customSample = TextToData.readTxt(
                FILEPATH + "gipsy.kings.hotel.california.txt");
        int[] headers = TextToData.parseHeader(customSample);

        for (int i = 0; i < 5; i++) {
            customSample.remove(0);
        }

        CustomWave cwave = new CustomWave(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3], //sampleRate
                headers[4], //frequency
                customSample, //samples for custom clip
                "custom"); //fileName

        cwave.synthesize();
        cwave.stripHeader();

        LinkedList<SimpleWaveManipulator> l = new LinkedList();
        double step, precentOfSample = 1;

        for (double i = 0; i < 10; i++) {
            //could use any function for step
            step = sine(2, 1, 0, 0, i);
            l.add(new SimpleWaveManipulator(
                    "lfowave",
                    cwave.getData(),
                    step,
                    (int) (cwave.getData().size() * Math.abs(step) * precentOfSample)));
        }

        for (SimpleWaveManipulator w : l) {
            w.synthesize();
        }

        SimpleWaveManipulator dwave = SimpleWaveManipulator.concatenateWaves(l,
                "lfowave");
        dwave.loadSamples();
        DataToText.writeTxt(dwave);

    }

    private double sine(double amplitude, int frequency, double phase,
            double displacement, double i) {
        return amplitude * Math.sin(frequency * (i - phase)) + displacement;
    }

}
