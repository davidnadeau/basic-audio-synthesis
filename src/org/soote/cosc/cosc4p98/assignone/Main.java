package org.soote.cosc.cosc4p98.assignone;

import java.io.IOException;

/**
 *
 * @author David Nadeau
 */
public class Main {

    public static void main(String[] args) throws IOException, Exception {
        ////////////////////////////////////
        //Part 1
        //@purpose: create some waves
        ////////////////////////////////////
        new SampAndSynth();
        ////////////////////////////////////
        //Part 2
        //@purpose: run oscilator over a sample
        ////////////////////////////////////
        new LowFreqOscAndMangling();
        ////////////////////////////////////
        //Part 3
        //@purpose: run reverb and echo over a sample
        ////////////////////////////////////
        new DSP();
    }
}
