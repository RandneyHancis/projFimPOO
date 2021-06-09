/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojogobaralho;

import java.security.SecureRandom;
import javax.swing.ImageIcon;

/**
 *
 * @author Randney Hancis
 */
public class CartaBaralho {
    private Cartas carta;
    private Naipe naipe;
    static private ImageIcon image;    
    private static final SecureRandom randomNumbers = new SecureRandom();

    public CartaBaralho(Cartas carta, Naipe naipe, ImageIcon image) {
        this.carta = carta;
        this.naipe = naipe;
        this.image = image;
    }

    public Cartas getCarta() {
        return carta;
    }

    public Naipe getNaipe() {
        return naipe;
    }
    
    public ImageIcon getImage() {
        return image;
    }
    
    public static CartaBaralho[] geraBaralho () {
        int tamanhoBaralho = Naipe.values().length * Cartas.values().length;
        CartaBaralho baralho[] = new CartaBaralho[tamanhoBaralho];
        
        int i = 0;
        for(Naipe naipe : Naipe.values()) {            
            for(Cartas carta : Cartas.values()) {
                baralho[i++] = new CartaBaralho(carta, naipe, image);                
            }
        }        
                
        for(int first = 0; first < baralho.length; first++) {
            int second = randomNumbers.nextInt(tamanhoBaralho);
            CartaBaralho aux = baralho[first];
            baralho[first] = baralho[second];
            baralho[second] = aux;
        }
        
        return baralho;
    }
    
    
    //main para testar a aleatoriedade do baralho
    
    public static void main(String[] args) {
        CartaBaralho meuBaralho[] = CartaBaralho.geraBaralho();        
        for(CartaBaralho minhaCarta : meuBaralho) {
            System.out.println(minhaCarta.getCarta().getNomeCarta() + " de " +
                    minhaCarta.getNaipe().getNaipes() +
                    " codeImg: " + minhaCarta.getNaipe().getCodigoNaipe() + minhaCarta.getCarta().getCodigoCarta());
        }         
    }
}
