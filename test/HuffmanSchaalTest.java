
import huffman.Huffman2;
import huffman.HuffmanKnoop;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import junit.framework.Assert;
import org.junit.Test;

/*
 * Het testen van de tijdscomplexiteit is niet gemakkelijk.
 * 
 * Een mogelijke aanpak is om zelf data te genereren, en de grootte van de data steeds
 * te laten toenemen, waarbij je de tijd bij houdt.
 */
public class HuffmanSchaalTest {
    
    
    // SPEEL MET ONDERSTAANDE PARAMETERS
    private static final int aantalUniekeCharacters = 15;
    private static final int grootteTotaleTekst = 10;
    private static final long seedVanRandomGenerator = 264;
    private static final int aantal_runs = 10;
    private static final int factorWaarmeeGrootteBijElkeStapVergrootMoetWorden = 2;
    
    
    @Test
    public void testTijd() {
       for(int i=1; i < aantal_runs; i++) {
            runHuffmanOnce(aantalUniekeCharacters, grootteTotaleTekst*factorWaarmeeGrootteBijElkeStapVergrootMoetWorden);
        }
    }

    private void runHuffmanOnce(int aantalUniekeCharacters, int grootteTotaleTekst) {
        String genereerTekst = genereerTekst(aantalUniekeCharacters, grootteTotaleTekst);
        
        System.out.println("Invoer: "+ genereerTekst);
        
        long tijd = System.currentTimeMillis();
        
        /**
         * TODO: vervang onderstaande code zodat er met jouw algoritme signatuur gewerkt wordt.
         */
        Huffman2 huffman = new Huffman2();
        
        Map frequentie = huffman.frequentie(genereerTekst);
        System.out.println("Frequentie: "+frequentie);
        
        HuffmanKnoop boom = huffman.bouwBoom(frequentie);
        
        // Performance test: hoe lang duurt het comprimeren?
        String gecomprimeerd = boom.decomprimeren(genereerTekst);
        
        long duur = (System.currentTimeMillis() - tijd) / 1000;
        
        System.out.println("Totale tijd om boom te maken en te comprimeren: "+duur);
        
        // Functionele test: is gegenereerde tekst weer correct?
        Assert.assertEquals(genereerTekst, boom.decomprimeren(gecomprimeerd));
    }
    
    
    public static String genereerTekst(int aantalUniekeCharacters, int grootteTotaleTekst) {
        
        StringBuilder d = new StringBuilder();
        
        char[] tokens = new char[aantalUniekeCharacters];
        tokens[0] = 'A';
        for(int i=1; i < aantalUniekeCharacters; i++) {
            tokens[i] = (char)(tokens[i-1]+1);
        }
        System.out.println("Dictionary: "+Arrays.toString(tokens));
        
        Random r = new Random(seedVanRandomGenerator); // verhaal kunnen reproduceren.
        
        for(int i=0; i < grootteTotaleTekst; i++) {
            d.append(tokens[r.nextInt(aantalUniekeCharacters)]);
        }
        
        return d.toString();
    }
}
