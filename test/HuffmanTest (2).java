package com.company;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.*;

/**
 * Created by marij on 29/03/2016.
 */
public class HuffmanTest {
    Map<Character, String> charCodeMap;
    String testString;

    Map<Character, String> charCodeMapGroot;
    String testStringGroot;


    @Before
    public void setUp(){
        testString = "BANANEN";
        charCodeMap = new HashMap<>();
        charCodeMap.put('N',"0");
        charCodeMap.put('A',"11");
        charCodeMap.put('B',"100");
        charCodeMap.put('E',"101");

        testStringGroot = "Dit is een grote tekst hierin staan meerdere tekens en deze moeten worden uitgelezen";
        charCodeMapGroot = new HashMap<>();
    }

    @Test
    public void test_huffmanCoderen(){
        String expected = "1001101101010";
        String result = Huffman.codeerBericht(charCodeMap,testString);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void test_huffmanTotaal(){
        String testString = "In deze tekst staan veel verschillende tekens!";

        Set<HuffKnoop> charLijst = Huffman.frequentieTekens(testString);
        PriorityQueue<HuffKnoop> charQueue = Huffman.sorteerLijst(charLijst);
        HuffKnoop boom = Huffman.maakBoom(charQueue);
        Huffman.leesBoom(boom, "", charCodeMap);
        String code = Huffman.codeerBericht(charCodeMap, testString);

        StringBuilder sb = new StringBuilder();
        String result = boom.getDecodeerMessage(code, sb, boom);

        Assert.assertEquals(testString, result);
    }

    @Test
    public void test_MeestVoorkomendeTekenKleinsteCode(){
        String testString = "In deze tekst staan veel verschillende tekens maar het meeste een e!";

        charCodeMap = new HashMap<>();
        Set<HuffKnoop> charLijst = Huffman.frequentieTekens(testString);
        PriorityQueue<HuffKnoop> charQueue = Huffman.sorteerLijst(charLijst);
        HuffKnoop boom = Huffman.maakBoom(charQueue);
        Huffman.leesBoom(boom, "", charCodeMap);


        String kleinste = "10000000000000000000000000000000";
        for(String s : charCodeMap.values()){
            if (s.length() < kleinste.length()){
                kleinste = s;
            }
        }
        Assert.assertEquals(charCodeMap.get('e'), kleinste);
    }
}
