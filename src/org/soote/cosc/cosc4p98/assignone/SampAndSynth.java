package org.soote.cosc.cosc4p98.assignone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import org.soote.cosc.cosc4p98.assignone.io.DataToText;
import org.soote.cosc.cosc4p98.assignone.io.TextToData;
import org.soote.cosc.cosc4p98.assignone.util.SimpleWaveManipulator;
import org.soote.cosc.cosc4p98.assignone.util.Wave;
import org.soote.cosc.cosc4p98.assignone.util.waves.CustomWave;
import org.soote.cosc.cosc4p98.assignone.util.waves.CustomWaveHalfPitch;
import org.soote.cosc.cosc4p98.assignone.util.waves.CustomWaveInterpolation;
import org.soote.cosc.cosc4p98.assignone.util.waves.RandomWave;
import org.soote.cosc.cosc4p98.assignone.util.waves.SawtoothWave;
import org.soote.cosc.cosc4p98.assignone.util.waves.SineWave;
import org.soote.cosc.cosc4p98.assignone.util.waves.SquareWave;

/**
 *
 * @author David Nadeau
 */
public class SampAndSynth {

    private static final String FILEPATH
            = "/home/soote/documents/school/4p98/AssignOne/music/";

    /**
     * Create all the samples for part 1a.
     *
     * @throws IOException
     */
    public SampAndSynth() throws IOException {
        LinkedList<Wave> waves = new LinkedList();

        ////////////////////////////////////
        //1a i
        ////////////////////////////////////
        waves.add(new SineWave(
                132300, //sampleCount
                16, //bitsPerSample
                2, //channels
                44100, //sampleRate
                440, //frequency
                "sine-440")); //fileName

        ////////////////////////////////////
        //1a ii
        ////////////////////////////////////
        waves.add(new SquareWave(
                132300, //sampleCount
                16, //bitsPerSample
                2, //channels
                44100, //sampleRate
                440, //frequency
                "square")); //fileName

        ////////////////////////////////////
        //1a iii
        ////////////////////////////////////
        waves.add(new SineWave(
                132300, //sampleCount
                16, //bitsPerSample
                2, //channels
                44100, //sampleRate
                880, //frequency
                "sine-880")); //fileName

        ////////////////////////////////////
        //1a iv
        ////////////////////////////////////
        waves.add(new SawtoothWave(
                132300, //sampleCount
                16, //bitsPerSample
                2, //channels
                44100, //sampleRate
                440, //frequency
                "sawtooth")); //fileName

        ////////////////////////////////////
        //1a v
        ////////////////////////////////////
        waves.add(new RandomWave(
                132300, //sampleCount
                16, //bitsPerSample
                2, //channels
                44100, //sampleRate
                440, //frequency
                "randomwave")); //fileName

        ////////////////////////////////////
        //1a vi
        ////////////////////////////////////
        ArrayList<String> customSample = TextToData.readTxt(
                FILEPATH + "gipsy.kings.hotel.california.txt");
        int[] headers = TextToData.parseHeader(customSample);
        for (int i = 0; i < 5; i++) {
            customSample.remove(0);
        }

        waves.add(new CustomWave(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3], //sampleRate
                headers[4], //frequency
                customSample, //samples for custome clip
                "custom")); //fileName

        ////////////////////////////////////
        //1a vii
        ////////////////////////////////////
        waves.add(new CustomWaveHalfPitch(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3], //sampleRate
                headers[4], //frequency
                customSample, //samples for custome clip
                "custom-halfpitch")); //fileName

        ////////////////////////////////////
        //1a viii
        ////////////////////////////////////
        waves.add(new CustomWaveInterpolation(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3], //sampleRate
                headers[4], //frequency
                customSample, //samples for custome clip
                "custom-interpolation")); //fileName

        //Generate every wave and write each to text
        for (Wave w : waves) {
            w.synthesize();
            DataToText.writeTxt(w);
        }

        ////////////////////////////////////
        //1b
        ////////////////////////////////////
        SineWave swave = new SineWave(
                132300, //sampleCount
                16, //bitsPerSample
                2, //channels
                44100, //sampleRate
                440,//frequency
                "dynamic-sine");//fileName

        swave.synthesize();
        swave.stripHeader();

        LinkedList<SimpleWaveManipulator> semitones = new LinkedList();
        for (double i = 0; i < 24; i += 1.0) {
            double step = i * (1.0 / 12.0);
            semitones.add(new SimpleWaveManipulator("dynamic" + i + Math
                    .random() * 10, swave.getData(), step, (int) (22050 * step)));
        }

        for (SimpleWaveManipulator w : semitones) {
            w.synthesize();
        }

        SimpleWaveManipulator dwave = SimpleWaveManipulator.concatenateWaves(
                semitones, "semitones");
        dwave.loadSamples();
        DataToText.writeTxt(dwave);
    }

}
