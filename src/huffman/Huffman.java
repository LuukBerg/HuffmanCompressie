/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author Luuk
 */
public class Huffman {

    
    /**
    * gets the priorityQueue from a HashMap
    * @param data data to get Hashmap from
    * @return Hashmap with the Character and the frequentie
    */
    public static HashMap<Character, Integer> getFrequentie(String data) {
       //split de input naar een char array
       char[] characters = data.toCharArray();
       //maakt nieuwe TreeMap<Character, Integer> aan om unique woorden in op te slaan met de hoeveelheid van dat woord
       HashMap<Character, Integer> map = new HashMap();
       for(Character c: characters){
           //kijkt of het woord nog niet bestaat zo niet wordt deze erin gezet met de hoeveelheid 1
           if(!map.containsKey(c)){
               map.put(c, 1);
           }
           //als het woord wel al bestaat wordt deze gereplaced met hetzelfde woord maar wordt er 1 bij de hoeveelheid opgeteld
           else{
               map.replace(c, map.get(c) + 1);
           }
       }
       return map;
       
    }

    /**
    * gets the priorityQueue from a HashMap
    * @param frequenties
    * @return PriorityQueue
    */
    public static PriorityQueue<HuffKnoop> getPriorityFrequentie(HashMap<Character, Integer> frequenties) {
        PriorityQueue<HuffKnoop> priorityQueue = new PriorityQueue<>(frequenties.size(),new PriorityComparator());
        frequenties.entrySet().stream().map((e) -> new HuffKnoop(e.getKey(), e.getValue())).forEachOrdered((k) -> {
            priorityQueue.offer(k);
        });
        return priorityQueue;
        
    }
    /**
    * gets the HuffMan tree
    * @param priorityFrequentie
    * @return Huffman Tree
    */
    public static HuffKnoop getHuffmanBoom(PriorityQueue<HuffKnoop> priorityFrequentie) {
        HuffKnoop hleft;
        HuffKnoop hright;
        while(priorityFrequentie.size() > 1){
            hleft = priorityFrequentie.remove();
            hright = priorityFrequentie.remove();
            priorityFrequentie.add(new HuffKnoop(hleft.karakter, hleft.frequentie + hright.frequentie, hleft, hright));
        }
        return priorityFrequentie.remove();
    }
    /**
    * Gets code from HuffMan Tree
    * @param h Tree to get codes from
    * @return HashMap with <character, CharacterCode>
    */
    public static HashMap<Character, String> Huffknoopcode(HuffKnoop h) {
        HashMap hashMap = new HashMap<>();
        findBitcode(h, hashMap, "");
        return hashMap;
    }
    
    /**
    * Recursive method that will find the code in the trees looping through them until it reaches a leaf. When leaf is reached it'll add it to the HashMap
    * @param current HuffManKnoop to find the codes of 
    * @param hashMap Map to add Codes to
    * @param currentString empty string on first call. Is used for calling itself.
    */
    private static void findBitcode(HuffKnoop current, HashMap<Character, String> hashMap, String currentString){
         // See if target immediately available
       if(!current.isleaf()){
           findBitcode(current.leftChild,hashMap, currentString + '0');
           findBitcode(current.rightChild,hashMap, currentString + '1');
       }
       else{
           hashMap.put(current.karakter, currentString);
       }
    }
    /**
    * Decodes encoded input using HuffMan Tree
    * @param data String to encode
    * @param characterCodes HashMap with Character codes
    * @return encoded String
    */
    public static BitSet getHuffmanEncode(String data, HashMap<Character, String> characterCodes) {
        BitSet bits = new BitSet();
        int counter = 0;
        for(char karakter : data.toCharArray()){
            String coded = (characterCodes.get(karakter));
            for(char bit : coded.toCharArray()){
                if(bit == '0')bits.set(counter, false);
                else bits.set(counter,true);
                counter++;
            }
        }
        return bits;
    }

    /**
    * Decodes encoded input using HuffMan Tree
    * @param input encoded input
    * @param h HuffMan Tree
    * @return decoded String
    */
    public static String getHuffmanDecode(BitSet input, HuffKnoop h) {
        StringBuilder bld = new StringBuilder();
        HuffKnoop currentKnoop = h;
        
        //loops through every bit in input going through the tree.
        for (int i = 0; i <= input.length(); i++) {
            //if the bit is 0 then it look through the left child to see if it's a leaf. if so it uses that character, otherwise it'll use that left child as new currentKnoop
            if(!input.get(i)){
                if(currentKnoop.leftChild != null){
                    currentKnoop = currentKnoop.leftChild;
                    if(currentKnoop.leftChild == null){
                        bld.append(currentKnoop.karakter);
                        currentKnoop = h;
                    }
                }
            }
            else if(input.get(i)){
                if(currentKnoop.rightChild != null){
                    currentKnoop = currentKnoop.rightChild;
                    if(currentKnoop.rightChild == null){
                        bld.append(currentKnoop.karakter);
                        currentKnoop = h;
                    }
                }
            }
        }
        return bld.toString();
    }
    
}
