package org.soote.cosc.cosc4p98.assignone;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.soote.cosc.cosc4p98.assignone.IO.TextToWav;
import org.soote.cosc.cosc4p98.assignone.Samples.Wave;
import org.soote.cosc.cosc4p98.assignone.IO.WavFile;
import static org.soote.cosc.cosc4p98.assignone.IO.WavFile.newWavFile;
import org.soote.cosc.cosc4p98.assignone.IO.WavFileException;
import org.soote.cosc.cosc4p98.assignone.IO.WavToText;

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
    
    public SampAndSynth() throws IOException, WavFileException {
        
        WavFile readWavFile = WavToText.convert(FILEPATH+"a440highvolume.wav");
        readWavFile.display();
        
        WavFile writeWavFile = TextToWav.convert(FILEPATH + "out.wav", readWavFile);
        
        //give wavFile objects to gc
        readWavFile.close();
        writeWavFile.close();
        
    }
    
    public static void main(String[] args) throws IOException, WavFileException {
        new SampAndSynth();
    }
    
}
