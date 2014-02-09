/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone;

import org.soote.cosc.cosc4p98.assignone.util.waves.ReverbWave;
import java.io.IOException;
import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.io.DataToText;
import org.soote.cosc.cosc4p98.assignone.io.TextToData;

/**
 *
 * @author soote
 */
public class DSP {
    private static final String FILEPATH = "/home/soote/documents/school/4p98/AssignOne/music/";

    public DSP() throws IOException {
            ArrayList<String> customSample = TextToData.readTxt(FILEPATH+"gipsy.kings.hotel.california.txt");
            int[] headers = TextToData.parseHeader(customSample);

            for (int i = 0; i < 5; i++) customSample.remove(0);

            ReverbWave rwave = new ReverbWave(
                    headers[0], //sampleCount
                    headers[1], //bitsPerSample
                    headers[2], //channels
                    headers[3],  //sampleRate
                    headers[4], //frequency
                    customSample, //samples for custom clip
                    "reverb"); //fileName
          
            rwave.synthesize();
            DataToText.writeTxt(rwave);
            
        
    }
}
