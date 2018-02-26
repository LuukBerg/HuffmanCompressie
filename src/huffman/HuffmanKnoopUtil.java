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
             System.out.println(add);
        }
        return priorityFrequentie.remove();
    }

    static HashMap<Character, String> getHuffknoopcode(HuffKnoop h, String string, HashMap<Object, Object> hashMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    static private LinkedList<HashMap.Entry<Character, Integer>> sortByValue(Map<Character, Integer> unsorted) {
        //maakt een linkedList aan van de unsorted map
        LinkedList<HashMap.Entry<Character, Integer>> list = new LinkedList<>(unsorted.entrySet());
        //sorteerd de list door de values te comparen
        list.sort(Comparator.comparing(Map.Entry::getValue));
        return list;
    }

    static String getHuffmanCode(String data, HashMap<Character, String> characterCodes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static String getHuffmanDecode(String input, HuffKnoop h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
