/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brian
 */
public class HuffmanTest {
    public String data;
    public String data2;
    
    public HuffmanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        data = "bananen";
        data2 = "1001101101010";
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testGetFrequentie() {
        System.out.println("getFrequentie");
        HashMap<Character, Integer> expResult = new HashMap<Character, Integer>();
        expResult.put('n',3);
        expResult.put('a',2);
        expResult.put('b',1);
        expResult.put('e',1);        
        
        HashMap<Character, Integer> result = Huffman.getFrequentie(data);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testGetPriorityFrequentie()
    {
        HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);

        HuffKnoop h;
        h = priorityFrequentie.remove();
        assertEquals('b',h.karakter);
        
        h = priorityFrequentie.remove();
        assertEquals('e',h.karakter);
        
        h = priorityFrequentie.remove();
        assertEquals('a',h.karakter);
        
        h = priorityFrequentie.remove();
        assertEquals('n',h.karakter);
        
         
    }
    
    @Test
    public void testGetHuffmanBoom()
    {
        HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
        HuffKnoop h = Huffman.getHuffmanBoom(priorityFrequentie);
        assertNotNull(h);
        assertEquals('n', h.karakter);
        assertEquals('n',h.leftChild.karakter);
        assertEquals('a',h.rightChild.leftChild.karakter);
        assertEquals('e',h.rightChild.rightChild.rightChild.karakter);
        assertEquals('b',h.rightChild.rightChild.leftChild.karakter);
    }
    
    @Test
    public void testGetCharacterCode()
    {
        HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
        HuffKnoop h = Huffman.getHuffmanBoom(priorityFrequentie);
        
        HashMap<Character, String> expResult =  new HashMap<>();
        expResult.put('n', "0");
        expResult.put('b', "110");
        expResult.put('e', "111");
        expResult.put('a', "10");
        
        HashMap<Character,String> result = Huffman.Huffknoopcode(h);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testGetCodedKnoop()
    {
        HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
        HuffKnoop h = Huffman.getHuffmanBoom(priorityFrequentie);
        HashMap<Character,String> characterCodes = Huffman.Huffknoopcode(h);
        String ResultString = "1101001001110";
        BitSet expResult = new BitSet();
        int counter = 0;
        for (char bit : ResultString.toCharArray()) {
            if (bit == '0') {
                expResult.set(counter, false);
            } else {
                expResult.set(counter, true);
            }
            counter++;
        }
        BitSet result = Huffman.getHuffmanEncode(data, characterCodes);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testGetDecodedKnoop()
    {
       HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
       PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
       HuffKnoop h = Huffman.getHuffmanBoom(priorityFrequentie);
       String input = "1101001001110";
       String expResult = "bananen";
       String result = Huffman.getHuffmanDecode(input, h);
       assertEquals(expResult,result);
    }
}
