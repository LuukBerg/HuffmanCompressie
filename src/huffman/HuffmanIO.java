package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Luuk
 */
public class HuffmanIO {

    /**
     * Writes an encoded string and the fitting HuffMan tree to a file so it can
     * be decoded later.
     *
     * @param input string to encode
     * @param filename where to save file to
     * @throws java.io.FileNotFoundException
     */
    static public void writeFile(String input, String filename) throws FileNotFoundException, IOException {
        HashMap<Character, Integer> frequenties = Huffman.getFrequentie(input);
        PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
        HuffKnoop tree = Huffman.getHuffmanBoom(priorityFrequentie);
        HashMap<Character, String> characterCodes = Huffman.Huffknoopcode(tree);
        BitSet encoded = Huffman.getHuffmanEncode(input, characterCodes);
        File outFile = new File(filename);
        FileOutputStream compressedFile = new FileOutputStream(outFile);
        try (ObjectOutputStream oos = new ObjectOutputStream(compressedFile)) {
            CompleteObject o = new CompleteObject(tree, encoded);
            oos.writeObject(o);
            oos.flush();
        }
    }

    /**
     * Reads encoded file and returns readable string
     *
     * @param filename where open file from
     * @return decoded string
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    static public String readFile(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        File inputFile = new File(filename);
        FileInputStream compressedFile = new FileInputStream(inputFile);
        ObjectInputStream ois = new ObjectInputStream(compressedFile);
        CompleteObject o = (CompleteObject) ois.readObject();
        return Huffman.getHuffmanDecode(o.encoded, o.tree);
    }
}
