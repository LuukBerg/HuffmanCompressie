/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Luuk
 */
public class HuffmanKnoopUtil {

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
             HuffKnoop add;
             add = new HuffKnoop(hleft.karakter, hleft.frequentie + hright.frequentie, hleft, hright);
             priorityFrequentie.add(add);
        }
        return priorityFrequentie.remove();
    }

    public static HashMap<Character, String> getHuffknoopcode(HuffKnoop h, String encodeString, HashMap<Character, String> hashMap) {
        HuffKnoop currentKnoop = h;
        while(!h.rightChild.done){
            if(currentKnoop.leftChild != null && !currentKnoop.leftChild.done){
                encodeString += "0";
                currentKnoop = currentKnoop.leftChild;
            }
            else if(currentKnoop.rightChild != null && !currentKnoop.rightChild.done){
                encodeString += "1";
                currentKnoop = currentKnoop.rightChild;
            }
            else if(currentKnoop.rightChild != null && currentKnoop.leftChild != null&& currentKnoop.rightChild.done && currentKnoop.leftChild.done){
                currentKnoop.done = true;
                encodeString = "";
                currentKnoop = h; 
            }
            else{
                hashMap.put(currentKnoop.karakter, encodeString);
                encodeString = "";
                currentKnoop.done = true;
                currentKnoop = h;
            }
        }
        
        return hashMap;
    }
    static String getHuffmanCode(String data, HashMap<Character, String> characterCodes) {
        StringBuilder bld = new StringBuilder();
        char[] charArray = data.toCharArray();
        for(char karakter : charArray){
            bld.append(characterCodes.get(karakter));
        }
        return bld.toString();
    }

    static String getHuffmanDecode(String input, HuffKnoop h) {
        char[] charArray = input.toCharArray();
        StringBuilder bld = new StringBuilder();
        HuffKnoop currentKnoop = h;
        boolean done = false;
        int counter = 0;
        while(!done){
            if(charArray[counter]  == '0'){
                if(currentKnoop.leftChild != null){
                    currentKnoop = currentKnoop.leftChild;
                    counter++;
                }
                else{
                    bld.append(currentKnoop.karakter);
                    currentKnoop = h;
                }
            }
            else if(charArray[counter] == '1'){
                if(currentKnoop.rightChild != null){
                    currentKnoop = currentKnoop.rightChild;
                    counter++;
                }
                else{
                    bld.append(currentKnoop.karakter);
                    currentKnoop = h;
                }
            }
            if(counter == charArray.length){
                done = true;
                bld.append(currentKnoop.karakter);
            }
        }
        return bld.toString();
    }
}
