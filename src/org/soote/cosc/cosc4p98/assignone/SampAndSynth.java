package org.soote.cosc.cosc4p98.assignone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import org.soote.cosc.cosc4p98.assignone.IO.DataToText;
import org.soote.cosc.cosc4p98.assignone.IO.TextToData;
import org.soote.cosc.cosc4p98.assignone.Samples.CustomWave;
import org.soote.cosc.cosc4p98.assignone.Samples.CustomWaveHalfPitch;
import org.soote.cosc.cosc4p98.assignone.Samples.CustomWaveInterpolation;
import org.soote.cosc.cosc4p98.assignone.Samples.DynamicWave;
import org.soote.cosc.cosc4p98.assignone.Samples.RandomWave;
import org.soote.cosc.cosc4p98.assignone.Samples.SawtoothWave;
import org.soote.cosc.cosc4p98.assignone.Samples.SineWave;
import org.soote.cosc.cosc4p98.assignone.Samples.SquareWave;
import org.soote.cosc.cosc4p98.assignone.Samples.Wave;

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
        LinkedList<Wave> waves = new LinkedList();
        
        ////////////////////////////////////
        //1a i
        ////////////////////////////////////
        waves.add(new SineWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,     //frequency
                "sine-440")); //fileName

        ////////////////////////////////////
        //1a ii
        ////////////////////////////////////
        waves.add(new SquareWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,   //frequency
                "square")); //fileName
        
        ////////////////////////////////////
        //1a iii
        ////////////////////////////////////
        waves.add(new SineWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                880,   //frequency
                "sine-880")); //fileName

        ////////////////////////////////////
        //1a iv
        ////////////////////////////////////
        waves.add(new SawtoothWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,   //frequency
                "sawtooth")); //fileName

        ////////////////////////////////////
        //1a v
        ////////////////////////////////////
        waves.add(new RandomWave(
                132300, //sampleCount
                16,     //bitsPerSample
                2,      //channels
                44100,  //sampleRate
                440,   //frequency
                "randomwave")); //fileName
        
        ////////////////////////////////////
        //1a vi
        ////////////////////////////////////
        ArrayList<String> customSample = TextToData.readTxt(FILEPATH+"gipsy.kings.hotel.california.txt");
        int[] headers = TextToData.parseHeader(customSample);
        for (int i = 0; i < 5; i++) customSample.remove(0);
        
        waves.add(new CustomWave(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3],  //sampleRate
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
                headers[3],  //sampleRate
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
                headers[3],  //sampleRate
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
        
        //list to hold 24 notes
        LinkedList<Wave> notes = new LinkedList();
        //each note will be 39 hz larger
        int hertz = 0;
        for (int i = 0; i < 24; i++) {
            //create tone sample
            notes.add(new SineWave(
                         132300, //sampleCount
                         16,     //bitsPerSample
                         2,      //channels
                         44100,  //sampleRate
                         hertz+=39,     //frequency, each note will be 39 hz higher
                         "dynamic-sine")); //fileName
        }
        //generate tone data and the auto strip header
       for (Wave w : notes) {
           w.synthesize();
           w.stripHeader();
       }
       //grab the first n samples of every wave and combined into 1 array
       ArrayList<String> combinedWaves = DynamicWave.combineWaves(notes);

      DynamicWave cwave = new DynamicWave(
            combinedWaves.size(), //sampleCount
                16, //bitsPerSample
                2, //channels
                44100,  //sampleRate
                0, //frequency
                combinedWaves, //samples
                "dynamic", //fileName
                0,      //phase shift
                notes.size()*0.5);     //duration

        cwave.synthesize();
        DataToText.writeTxt(cwave);
        
    }
}
