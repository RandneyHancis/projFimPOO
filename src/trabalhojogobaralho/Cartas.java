/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojogobaralho;

/**
 *
 * @author Randney Hancis
 */
public enum Cartas {
    DOIS ("2", 2, "C01"), TRES ("3", 3, "C02"), QUATRO ("4", 4, "C03"), CINCO ("5",5, "C04"), SEIS ("6",6, "C05"), 
    SETE ("7",7, "C06"), OITO ("8",8, "C07"), NOVE ("9",9, "C08"), DEZ ("10",10, "C09"), J ("Valete", 11, "C10"), 
    Q ("Rainha", 12, "C11"), K ("Rei", 13, "C12"), A ("Ás", 14, "C13");
    
    private final String nomeCarta;
    private final int pontos;
    private final String codigoCarta;

    private Cartas(String nomeCarta, int pontos, String codigo) {
        this.nomeCarta = nomeCarta;
        this.pontos = pontos;
        this.codigoCarta = codigo;
    }

    public String getCodigoCarta() {
        return codigoCarta;
    }

    public String getNomeCarta() {
        return nomeCarta;
    }

    public int getPontos() {
        return pontos;
    }        
}
