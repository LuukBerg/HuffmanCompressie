/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.Comparator;

/**
 *
 * @author Luuk
 */
public class PriorityComparator implements Comparator<HuffKnoop> {

    /**
     * Compares 2 HuffKnoops by their value.
     */
    @Override
    public int compare(HuffKnoop o1, HuffKnoop o2) {
        return o1.frequentie - o2.frequentie;
    }

}
