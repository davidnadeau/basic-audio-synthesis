package org.soote.cosc.cosc4p98.assignone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.soote.cosc.cosc4p98.assignone.IO.DataToText;
import org.soote.cosc.cosc4p98.assignone.IO.TextToData;
import org.soote.cosc.cosc4p98.assignone.Samples.CustomWave;
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
        //a i
        SineWave sine440 = new SineWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,     //frequency
                "sine440");
        sine440.synthesize();
        DataToText.writeTxt(sine440);

        //a ii
        SquareWave square440 = new SquareWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,   //frequency
                "square440");

        square440.synthesize();
        DataToText.writeTxt(square440);

        //a iii
        SineWave sine880 = new SineWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                880,   //frequency
                "sine880");
        sine880.synthesize();
        DataToText.writeTxt(sine880);

        //a iv
        SawtoothWave sawtooth440 = new SawtoothWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,   //frequency
                "sawtooth440");
        sawtooth440.synthesize();
        DataToText.writeTxt(sawtooth440);

        //a v
        RandomWave randomWave = new RandomWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,   //frequency
                "randomwave");
        randomWave.synthesize();
        DataToText.writeTxt(randomWave);

        //a vi
        ArrayList<String> customSample = TextToData.readTxt(FILEPATH+"gispy.kings-hotel.california.txt");
        //System.out.println(Arrays.toString(customSample.toArray()));
        int[] headers = TextToData.parseHeader(customSample);
        CustomWave customWave = new CustomWave(headers[0],headers[1],headers[2],headers[3],0,customSample, "CustomeNormal");
        customWave.synthesize(1);
        DataToText.writeTxt(customWave);
        
        //a vii
        customWave = new CustomWave(headers[0],headers[1],headers[2],headers[3]/2,0, customSample, "CustomeHalfPitch");
        customWave.synthesize(1);
        DataToText.writeTxt(customWave);
        
    }
    
    public static void main(String[] args) throws IOException {
        new SampAndSynth();
    }
    
}
