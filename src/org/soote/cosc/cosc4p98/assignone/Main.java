/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soote.cosc.cosc4p98.assignone;

import java.io.IOException;

/**
 *
 * @author soote
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
