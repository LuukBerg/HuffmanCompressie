/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

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
public class HuffmanKnoopUtilTest {
    public String data;
    public String data2;
    
    public HuffmanKnoopUtilTest() {
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
        
        HashMap<Character, Integer> result = HuffmanKnoopUtil.getFrequentie(data);
        assertEquals(expResult,result);
    }
    
    @Test
    public void testGetPriorityFrequentie()
    {
        HashMap<Character, Integer> frequenties = HuffmanKnoopUtil.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanKnoopUtil.getPriorityFrequentie(frequenties);

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
        HashMap<Character, Integer> frequenties = HuffmanKnoopUtil.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanKnoopUtil.getPriorityFrequentie(frequenties);
        HuffKnoop h = HuffmanKnoopUtil.getHuffmanBoom(priorityFrequentie);
        assertNotNull(h);
        assertEquals('n', h.karakter);
        assertEquals('n',h.leftChild.karakter);
        assertEquals('a',h.rightChild.rightChild.karakter);
        assertEquals('e',h.rightChild.leftChild.rightChild.karakter);
        assertEquals('b',h.rightChild.leftChild.leftChild.karakter);
    }
    
    @Test
    public void testGetCharacterCode()
    {
        HashMap<Character, Integer> frequenties = HuffmanKnoopUtil.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanKnoopUtil.getPriorityFrequentie(frequenties);
        HuffKnoop h = HuffmanKnoopUtil.getHuffmanBoom(priorityFrequentie);
        
        HashMap<Character, String> expResult =  new HashMap<>();
        expResult.put('n', "0");
        expResult.put('b', "100");
        expResult.put('e', "101");
        expResult.put('a', "11");
        
        HashMap<Character,String> result = HuffmanKnoopUtil.getHuffknoopcode(h,"",new HashMap<>());
        assertEquals(expResult,result);
    }
    
    @Test
    public void testGetCodedKnoop()
    {
        HashMap<Character, Integer> frequenties = HuffmanKnoopUtil.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanKnoopUtil.getPriorityFrequentie(frequenties);
        HuffKnoop h = HuffmanKnoopUtil.getHuffmanBoom(priorityFrequentie);
        HashMap<Character,String> characterCodes = HuffmanKnoopUtil.getHuffknoopcode(h,"",new HashMap<>());
        
        
        String expResult = "1001101101010";
        String result = HuffmanKnoopUtil.getHuffmanCode(data, characterCodes);
        assertEquals(expResult,result);
        
    }
    
    @Test
    public void testGetDecodedKnoop()
    {
        HashMap<Character, Integer> frequenties = HuffmanKnoopUtil.getFrequentie(data);
        PriorityQueue<HuffKnoop> priorityFrequentie = HuffmanKnoopUtil.getPriorityFrequentie(frequenties);
        HuffKnoop h = HuffmanKnoopUtil.getHuffmanBoom(priorityFrequentie);

       String input = "1001101101010";
       String expResult = "bananen";
       String result = HuffmanKnoopUtil.getHuffmanDecode(input, h);
       assertEquals(expResult,result);
    }
}
