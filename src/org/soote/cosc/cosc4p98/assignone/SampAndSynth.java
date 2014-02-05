package org.soote.cosc.cosc4p98.assignone;

import java.io.IOException;
import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.IO.DataToText;
import org.soote.cosc.cosc4p98.assignone.IO.TextToData;
import org.soote.cosc.cosc4p98.assignone.Samples.CustomWave;
import org.soote.cosc.cosc4p98.assignone.Samples.CustomWaveHalfPitch;
import org.soote.cosc.cosc4p98.assignone.Samples.CustomWaveInterpolation;
import org.soote.cosc.cosc4p98.assignone.Samples.RandomWave;
import org.soote.cosc.cosc4p98.assignone.Samples.SawtoothWave;
import org.soote.cosc.cosc4p98.assignone.Samples.SineWave;
import org.soote.cosc.cosc4p98.assignone.Samples.SquareWave;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author soote
 */
public class SampAndSynth {
    private static final String FILEPATH = "/home/soote/documents/school/4p98/AssignOne/music/";
    
    public SampAndSynth() throws IOException {
        ////////////////////////////////////
        //a i
        ////////////////////////////////////
        SineWave sine440 = new SineWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,     //frequency
                "sine-440");
        sine440.synthesize();
        DataToText.writeTxt(sine440);

        ////////////////////////////////////
        //a ii
        ////////////////////////////////////
        SquareWave square = new SquareWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,   //frequency
                "square");

        square.synthesize();
        DataToText.writeTxt(square);
        
        ////////////////////////////////////
        //a iii
        ////////////////////////////////////
        SineWave sine880 = new SineWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                880,   //frequency
                "sine-880");
        sine880.synthesize();
        DataToText.writeTxt(sine880);

        ////////////////////////////////////
        //a iv
        ////////////////////////////////////
        SawtoothWave sawtooth = new SawtoothWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,   //frequency
                "sawtooth");
        sawtooth.synthesize();
        DataToText.writeTxt(sawtooth);

        ////////////////////////////////////
        //a v
        ////////////////////////////////////
        RandomWave randomWave = new RandomWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,   //frequency
                "randomwave");
        randomWave.synthesize();
        DataToText.writeTxt(randomWave);
        
        ////////////////////////////////////
        //a vi
        ////////////////////////////////////
        ArrayList<String> customSample = TextToData.readTxt(FILEPATH+"gipsy.kings.hotel.california.txt");
        //System.out.println(Arrays.toString(customSample.toArray()));
        int[] headers = TextToData.parseHeader(customSample);
        CustomWave customWave = new CustomWave(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3],  //sampleRate
                headers[4], //frequency
                customSample,
                "custom");
        customWave.synthesize();
        DataToText.writeTxt(customWave);
        
        ////////////////////////////////////
        //a vii
        ////////////////////////////////////
        CustomWaveHalfPitch customWaveHalfPitch = new CustomWaveHalfPitch(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3],  //sampleRate
                headers[4], //frequency
                customSample, 
                "custom-halfpitch");
        customWaveHalfPitch.synthesize();
        DataToText.writeTxt(customWaveHalfPitch);
        
        ////////////////////////////////////
        //a viii
        ////////////////////////////////////
        CustomWaveInterpolation customWaveInterpolation = new CustomWaveInterpolation(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3],  //sampleRate
                headers[4], //frequency
                customSample, 
                "custom-interpolation");
        customWaveInterpolation.synthesize();
        DataToText.writeTxt(customWaveInterpolation);        
    }
    
    public static void main(String[] args) throws IOException {
        new SampAndSynth();
    }
    
}
