/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.IO;

import java.io.File;
import java.io.IOException;
import static org.soote.cosc.cosc4p98.assignone.IO.WavFile.newWavFile;

/**
 *
 * @author soote
 */
public class TextToWav {
    public static WavFile convert(String filePath, WavFile w) throws IOException, WavFileException {
        WavFile writeWavFile = newWavFile(new File(filePath), w.getNumChannels(), w.getNumFrames(), w.getValidBits(), w.getSampleRate());

        final int BUF_SIZE = 5001;

//				int[] buffer = new int[BUF_SIZE * numChannels];
//				long[] buffer = new long[BUF_SIZE * numChannels];
        double[] buffer = new double[BUF_SIZE * w.getNumChannels()];

        int framesRead = 0;
        int framesWritten = 0;

        do
        {
                framesRead = w.readFrames(buffer, BUF_SIZE);
                framesWritten = writeWavFile.writeFrames(buffer, BUF_SIZE);
                System.out.printf("%d %d\n", framesRead, framesWritten);
        }
        while (framesRead != 0);
        
        return writeWavFile;
    }           
}
