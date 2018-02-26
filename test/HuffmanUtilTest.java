/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sword
 */
public class HuffmanUtilTest {
    
    String data;
    
    @Before
    public void setUp() {
        data = "bananen";
    }
    
    @Test
    public void testgetFrequentieHuffKnoop()
    {
        System.out.println("testgetFrequentieHuffKnoop");
        List<HuffKnoop> expResult = new ArrayList<>();   
        expResult.add(HuffKnoop.create('b',1)); 
        expResult.add(HuffKnoop.create('e',1));
        expResult.add(HuffKnoop.create('a',2)); 
        expResult.add(HuffKnoop.create('n',3));
        List<HuffKnoop> result = HuffmanUtil.getFrequentieHuffKnoop(data);        
        assertEquals(expResult,result);
    }

    /**
     * Test of getFrequentie method, of class HuffmanUtil.
     */
    @Test
    public void testGetFrequentie() {
        System.out.println("getFrequentie");
        HashMap<Character, Integer> expResult = new HashMap<Character, Integer>();
        expResult.put('n',3);
        expResult.put('a',2);
        expResult.put('b',1);
        expResult.put('e',1);        
        HashMap<Character, Integer> result = HuffmanUtil.getFrequentie(data);
        assertEquals(expResult,result);
    }
    
    /**
     * Test of getFrequentie method, of class HuffmanUtil.
     */
    @Test
    public void testGetFrequentieBigWords() {
        System.out.println("getFrequentie");
        String bigData = "this is a very big word with several spaces in it, to test the scalibility of this software project.";
        HashMap<Character, Integer> expResult = new HashMap<Character, Integer>();
        expResult.put('a',5);
        expResult.put('b',2);
        expResult.put('c',3);
        expResult.put('d',1);
        expResult.put('e',8);
        expResult.put('f',2);
        expResult.put('g',1);
        expResult.put('h',4);
        expResult.put('i',10);
        expResult.put('j',1);
        expResult.put('l',3);
        expResult.put('n',1);
        expResult.put('o',5);
        expResult.put('p',2);
        expResult.put('r',5);
        expResult.put('s',9);
        expResult.put('t',11);
        expResult.put('v',2);
        expResult.put('w',3);
        expResult.put('y',2);
        expResult.put(' ',18);
        expResult.put('.',1);
        expResult.put(',',1);
        HashMap<Character, Integer> result = HuffmanUtil.getFrequentie(bigData);
        assertEquals(expResult,result);
    }
   
    
    @Test   
    public void getKoppel()
        {
            System.out.println("getKoppel");
            List<HuffKnoop> list = new ArrayList<>();
            list.add(HuffKnoop.create('b',1)); 
            list.add(HuffKnoop.create('e',1));
            list.add(HuffKnoop.create('a',2)); 
            list.add(HuffKnoop.create('n',3));  
        
            List<HuffKnoop> expected = new ArrayList<>();
            int index = 0;
            expected.add(HuffKnoop.create('b',1)); 
            expected.add(HuffKnoop.create('e',1));
            
            List<HuffKnoop> result = HuffmanUtil.getKoppel(list, index);
           assertEquals(expected,result);
        }
    
      @Test   
    public void getKoppelOfIndexOne()
        {
            System.out.println("getKoppelOfIndexOne");
            List<HuffKnoop> list = new ArrayList<>();
            list.add(HuffKnoop.create('b',1)); 
            list.add(HuffKnoop.create('e',1));
            list.add(HuffKnoop.create('a',2)); 
            list.add(HuffKnoop.create('n',3));  
        
            List<HuffKnoop> expected = new ArrayList<>();
            int index = 1;
            expected.add(HuffKnoop.create('a',2)); 
            expected.add(HuffKnoop.create('n',3));
            
            List<HuffKnoop> result = HuffmanUtil.getKoppel(list, index);
           assertEquals(expected,result);
        }
    
      @Test   
    public void testGetKoppelOfIndexTwo()
        {
            System.out.println("testGetKoppelOfIndexTwo");
            List<HuffKnoop> list = new ArrayList<>();
            list.add(HuffKnoop.create('b',1)); 
            list.add(HuffKnoop.create('e',1));
            list.add(HuffKnoop.create('a',2)); 
            list.add(HuffKnoop.create('n',3));  
        
            List<HuffKnoop> expected = new ArrayList<>();
            int index = 2;
            
            List<HuffKnoop> result = HuffmanUtil.getKoppel(list, index);
           assertEquals(expected,result);
        }
    
    @Test
    public void testGetHuffmanBoomRij()
    {
        System.out.println("testGetHuffmanBoomRij");
      
        PriorityQueue<HuffKnoop> expected = new PriorityQueue<>();
        expected.add(HuffKnoop.create('b',1)); 
        expected.add(HuffKnoop.create('e',1));
        expected.add(HuffKnoop.create('\0',2));
        expected.add(HuffKnoop.create('a',2)); 
        expected.add(HuffKnoop.create('n',3)); 
        expected.add(HuffKnoop.create('\0', 5));
        PriorityQueue<HuffKnoop> result = HuffmanUtil.getHuffmanBoomRij(HuffmanUtil.getFrequentieHuffKnoop(data));
        assertEquals(expected,result);
    }
    
    
}
