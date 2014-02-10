package org.soote.cosc.cosc4p98.assignone;

import java.io.IOException;
import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.io.DataToText;
import org.soote.cosc.cosc4p98.assignone.io.TextToData;
import org.soote.cosc.cosc4p98.assignone.util.waves.EchoWave;
import org.soote.cosc.cosc4p98.assignone.util.waves.ReverbWave;

/**
 *
 * @author David Nadeau
 */
public class DSP {

    private static final String FILEPATH
            = "/home/soote/documents/school/4p98/AssignOne/music/";

    public DSP() throws IOException {
        //load samples into a list
        ArrayList<String> customSample = TextToData.readTxt(
                FILEPATH + "gipsy.kings.hotel.california.txt");
        int[] headers = TextToData.parseHeader(customSample);

        //remove headers
        for (int i = 0; i < 5; i++) {
            customSample.remove(0);
        }

        //Reverb Effect
        ReverbWave rwave = new ReverbWave(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3], //sampleRate
                headers[4], //frequency
                customSample, //samples for custom clip
                "reverb"); //fileName

        //delay must be < 1543.5
        rwave.synthesize(1000, 0.7); //(delay,volume)
        DataToText.writeTxt(rwave);

        //Echo Effect
        EchoWave ewave = new EchoWave(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3], //sampleRate
                headers[4], //frequency
                customSample, //samples for custom clip
                "echo"); //fileName

        //delay myst be > 1543.5, can mix a depth # of times
        ewave.synthesize(5000, 0.7, 2); //(delay,volume,depth)
        DataToText.writeTxt(ewave);

    }

}
