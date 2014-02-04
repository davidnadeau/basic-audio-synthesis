/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.soote.cosc.cosc4p98.assignone.IO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.soote.cosc.cosc4p98.assignone.IO.WavFile.openWavFile;
import org.soote.cosc.cosc4p98.assignone.Samples.Wave;

/**
 *
 * @author soote
 */

// Wav file IO class
// A.Greensted
// http://www.labbookpages.co.uk

// File format is based on the information from
// http://www.sonicspot.com/guide/wavefiles.html
// http://www.blitter.com/~russtopia/MIDI/~jglatt/tech/wave.htm
public class WavToText {
    public static WavFile convert(String fileName) {
        WavFile readWavFile = null;
        try {
            readWavFile = openWavFile(new File(fileName));
        } catch (IOException | WavFileException ex) {}
        
        return readWavFile;
    }
}
