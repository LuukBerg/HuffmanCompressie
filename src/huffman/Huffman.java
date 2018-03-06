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

    //Frequentie maar dan gesorteerd
    public static PriorityQueue<HuffKnoop> getPriorityFrequentie(HashMap<Character, Integer> frequenties) {
        PriorityQueue<HuffKnoop> priorityQueue = new PriorityQueue<>(frequenties.size(),new PriorityComparator());
        for(HashMap.Entry<Character, Integer> e : frequenties.entrySet()) {
            HuffKnoop k = new HuffKnoop(e.getKey(), e.getValue());
            priorityQueue.offer(k);
        }
        return priorityQueue;
        
    }

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

    public static HashMap<Character, String> Huffknoopcode(HuffKnoop h) {
        HashMap hashMap = new HashMap<>();
        findBitcode(h, hashMap, "");
        return hashMap;
    }
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
   /* static String getHuffmanCode(String data, HashMap<Character, String> characterCodes) {
        StringBuilder bld = new StringBuilder();
        char[] charArray = data.toCharArray();
        for(char karakter : charArray){
            bld.append(characterCodes.get(karakter));
        }
        return bld.toString();
    }
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

    public static String getHuffmanDecode(BitSet input, HuffKnoop h) {
        StringBuilder bld = new StringBuilder();
        HuffKnoop currentKnoop = h;
        for (int i = 0; i <= input.length(); i++) {
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
