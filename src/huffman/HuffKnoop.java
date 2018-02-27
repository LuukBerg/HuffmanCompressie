/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author Luuk
 */
public class HuffKnoop {

    static HuffKnoop create(char c, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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
    public boolean isleaf(){
        if(leftChild == null && rightChild == null){
            return true;
        }
        else{
            return false;
        }
    }
    
}
