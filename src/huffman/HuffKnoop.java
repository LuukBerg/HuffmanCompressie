/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.Serializable;

/**
 *
 * @author Luuk
 */
public class HuffKnoop implements Serializable {


    public char karakter;
    public int frequentie;
    public HuffKnoop leftChild;
    public HuffKnoop rightChild;

    public HuffKnoop(Character karakter, int frequentie) {
        this.karakter = karakter;
        this.frequentie = frequentie;
    }

    public HuffKnoop(Character karakter, int frequentie, HuffKnoop left, HuffKnoop right) {
        this.karakter = karakter;
        this.frequentie = frequentie;
        leftChild = left;
        rightChild = right;
    }
    /**
    * looks if the current HuffKnoop is a leaf
    * @return true if leaf. false if not.
    */
    public boolean isleaf() {
        return leftChild == null && rightChild == null;
    }

}
