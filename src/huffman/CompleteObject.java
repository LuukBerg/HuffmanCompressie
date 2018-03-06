/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.Serializable;
import java.util.BitSet;

/**
 *
 * @author Luuk
 */
public class CompleteObject implements Serializable{
    
    public  HuffKnoop tree;
    public  BitSet encoded;

    public CompleteObject(HuffKnoop tree, BitSet encoded) {
        this.tree = tree;
        this.encoded = encoded;
    }
    
}
