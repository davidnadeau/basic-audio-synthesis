/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soote.cosc.cosc4p98.assignone.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.soote.cosc.cosc4p98.assignone.util.Wave;

/**
 *
 * @author soote
 */
public class DataToText {

    public static void writeTxt(Wave w) {
        ArrayList<String> l = w.getData();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("music/" + w.getName() + ".txt"));
            for (int i = 0; i < w.getData().size(); i++) {
                out.write(l.get(i));
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
        }
    }
}
