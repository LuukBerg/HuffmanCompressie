/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import huffman.Resources.LoremIpsum;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timeutil.TimeStamp;

/**
 *
 * @author Brian
 */
public class HuffmanTestAdvanced {
    public String data;
    public String data2;
    public HashMap<Character,String> cheatSheet;
    public TimeStamp timer;
    public HuffmanTestAdvanced() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        String[] array;
        array = new String[6];
        array[0] = "hallo ";
        array[1] = "mijn ";
        array[2] = "naam ";
        array[3] = "is ";
        array[4] = "unittester ";
        array[5] = "master ";
        StringBuilder bld = new StringBuilder();
        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j < 6; j++) {
                bld.append(array[j]);
            }
        }
        data = bld.toString();
        data2 = "1001101101010";
        cheatSheet = new HashMap();
        cheatSheet.put(' ', "111");
        cheatSheet.put('a', "011");
        cheatSheet.put('e', "000");
        cheatSheet.put('h', "00100");
        cheatSheet.put('i', "1101");
        cheatSheet.put('j', "00110");
        cheatSheet.put('l', "1000");
        cheatSheet.put('m', "1011");
        cheatSheet.put('n', "1100");
        cheatSheet.put('o', "00101");
        cheatSheet.put('r', "1001");
        cheatSheet.put('s', "1010");
        cheatSheet.put('t', "010");
        cheatSheet.put('u', "00111");
        timer = new TimeStamp();
    }
    
    @After
    public void tearDown() {
        System.out.println(timer.toString());
    }

    @Test
    public void testGetFrequentie() {
        HashMap<Character, Integer> expResult = new HashMap<Character, Integer>();
        expResult.put(' ', 12000);
        expResult.put('a', 8000);
        expResult.put('e', 6000);
        expResult.put('h', 2000);
        expResult.put('i', 6000);
        expResult.put('j', 2000);
        expResult.put('l', 4000);
        expResult.put('m', 6000);
        expResult.put('n', 6000);
        expResult.put('o', 2000);
        expResult.put('r', 4000);
        expResult.put('s', 6000);
        expResult.put('t', 8000);
        expResult.put('u', 2000);
        
        HashMap<Character, Integer> result = Huffman.getFrequentie(data);
        assertEquals(expResult,result);

    }
    
    @Test
    public void testGetPriorityFrequentie()
    {
        HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
        timer.setBegin("10.000 PriorityFrequentie");
        PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
        timer.setEnd("10.000 PriorityFrequentie");
        HuffKnoop h;
        String frequentieOrder = "hojulrnimesat ";
        for (int i = 0; i < 14; i++) {
           h = priorityFrequentie.remove();
           assertEquals(frequentieOrder.charAt(i),h.karakter);
        }
       
    }
    
    @Test
    public void testGetHuffmanBoom()
    {
        HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
        timer.setBegin("10.000 HuffmanBoom");
        HuffKnoop h = Huffman.getHuffmanBoom(priorityFrequentie);
        timer.setEnd("10.000 HuffmanBoom");
        assertNotNull(h);
        assertEquals('e',h.leftChild.leftChild.leftChild.karakter);
        assertEquals('h',h.leftChild.leftChild.rightChild.leftChild.leftChild.karakter);
        assertEquals('o',h.leftChild.leftChild.rightChild.leftChild.rightChild.karakter);
        assertEquals('u',h.leftChild.leftChild.rightChild.rightChild.rightChild.karakter);
        assertEquals('j',h.leftChild.leftChild.rightChild.rightChild.leftChild.karakter);
        assertEquals('a',h.leftChild.rightChild.rightChild.karakter);
        assertEquals('t',h.leftChild.rightChild.leftChild.karakter);
        assertEquals(' ',h.rightChild.rightChild.rightChild.karakter);
        assertEquals('i',h.rightChild.rightChild.leftChild.rightChild.karakter);
        assertEquals('n',h.rightChild.rightChild.leftChild.leftChild.karakter);
        assertEquals('m',h.rightChild.leftChild.rightChild.rightChild.karakter);
        assertEquals('l',h.rightChild.leftChild.leftChild.leftChild.karakter);
        assertEquals('r',h.rightChild.leftChild.leftChild.rightChild.karakter);
        assertEquals('s',h.rightChild.leftChild.rightChild.leftChild.karakter);
    }
    
    @Test
    public void testGetCharacterCode()
    {
        HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
        HuffKnoop h = Huffman.getHuffmanBoom(priorityFrequentie);
        HashMap<Character, String> expResult =  new HashMap<>();
        expResult = cheatSheet;
        timer.setBegin("10.000 Huffknoopcode");
        HashMap<Character,String> result = Huffman.Huffknoopcode(h);
        timer.setEnd("10.000 Huffknoopcode");
        assertEquals(expResult,result);
    }
    
    @Test
    public void testGetCodedKnoop()
    {
        HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
        HuffKnoop h = Huffman.getHuffmanBoom(priorityFrequentie);
        HashMap<Character,String> characterCodes = Huffman.Huffknoopcode(h);
        BitSet expResult = new BitSet();
        int counter = 0;
        for(char karakter : data.toCharArray()){
            String coded = (characterCodes.get(karakter));
            for(char bit : coded.toCharArray()){
                if(bit == '0')expResult.set(counter, false);
                else expResult.set(counter,true);
                counter++;
            }
        }
        timer.setBegin("10.000 HuffmanCode");
        BitSet result = Huffman.getHuffmanEncode(data, characterCodes);
        timer.setEnd("10.000 HuffmanCode");
        assertEquals(expResult,result);
        byte[] bytes = result.toByteArray();
        System.out.println("Coded bytes length: " + bytes.length);
    }
    
    @Test
    public void testGetDecodedKnoop()
    {
       HashMap<Character, Integer> frequenties = Huffman.getFrequentie(data);
       PriorityQueue<HuffKnoop> priorityFrequentie = Huffman.getPriorityFrequentie(frequenties);
       HuffKnoop h = Huffman.getHuffmanBoom(priorityFrequentie);
       StringBuilder bld = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            bld.append(cheatSheet.get(data.charAt(i)));
        }
       String expResult = data;
       timer.setBegin("10.000 HuffmanDecode");
       String result = Huffman.getHuffmanDecode(bld.toString(), h);
       timer.setEnd("10.000 HuffmanDecode");
       assertEquals(expResult,result);
       byte[] bytes = result.getBytes();
       System.out.println("unCoded bytes length: " + bytes.length);
    }
}
