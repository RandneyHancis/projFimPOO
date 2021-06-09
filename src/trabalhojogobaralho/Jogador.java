/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojogobaralho;

import javax.swing.JLabel;

/**
 *
 * @author Randney Hancis
 */
public class Jogador {
    private final int cartasNoJogo = 5;
    private final String nomeJogador;
    private CartaBaralho[] mao = CartaBaralho.geraBaralho();
    private int pontos;

    public Jogador(String nomeJogador, CartaBaralho[] mao, int pontos) {
        this.nomeJogador = nomeJogador;
        this.mao = CartaBaralho.geraBaralho();
        this.pontos = 0;
    }

    public int getCartasNoJogo() {
        return cartasNoJogo;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public CartaBaralho[] getMao() {
        return mao;
    }    

    public int getPontos() {
        return pontos;
    }
          
    public int soma(int i, int j, Jogador maoJogador, Jogador maoCasa, int soma, JLabel pontos) {
        soma += maoJogador.getMao()[j].getCarta().getPontos() + maoCasa.getMao()[i].getCarta().getPontos();            
        pontos.setText(Integer.toString(soma));        
        return soma;
    }

    
}
