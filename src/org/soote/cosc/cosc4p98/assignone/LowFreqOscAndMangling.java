/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import org.soote.cosc.cosc4p98.assignone.IO.DataToText;
import org.soote.cosc.cosc4p98.assignone.IO.TextToData;
import org.soote.cosc.cosc4p98.assignone.Samples.CustomWave;
import org.soote.cosc.cosc4p98.assignone.Samples.DynamicWave;

/**
 *
 * @author soote
 */
public class LowFreqOscAndMangling {
     private static final String FILEPATH = "/home/soote/documents/school/4p98/AssignOne/music/";

    public LowFreqOscAndMangling() throws IOException, Exception{
        ArrayList<String> customSample = TextToData.readTxt(FILEPATH+"gipsy.kings.hotel.california.txt");
        int[] headers = TextToData.parseHeader(customSample);
        
        for (int i = 0; i < 5; i++) customSample.remove(0);
        
        CustomWave cwave = new CustomWave(
                headers[0], //sampleCount
                headers[1], //bitsPerSample
                headers[2], //channels
                headers[3],  //sampleRate
                headers[4], //frequency
                customSample, //samples for custom clip
                "custom"); //fileName
        
        cwave.synthesize();
        cwave.stripHeader();
        
        LinkedList<DynamicWave> l = new LinkedList();
        double step, precentOfSample = 1;
        
        for (double i = 0 ; i <10;i++){
            step = sine(2, 1,0,0,i);
            System.out.println(step);
            l.add(new DynamicWave(
               "lfowave",
               cwave.getData(),
               step, 
               (int) (cwave.getData().size()*Math.abs(step)*precentOfSample)));
        }
        
        for (DynamicWave w : l) {
            w.synthesize();
        }
         
        DynamicWave  dwave = DynamicWave.concatenateWaves(l, "lfowave");
        dwave.loadSamples();
        DataToText.writeTxt(dwave);

    }
    
    public double sine(double amplitude, int frequency, double phase, double displacement, double i) {
        return  amplitude*Math.sin(frequency*(i-phase))+displacement;
    }
}
