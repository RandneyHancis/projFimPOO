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
public enum Naipe {
    COPAS ("copas", "N1"), ESPADA ("espada", "N2"), OURO ("ouro", "N3"), PAUS ("paus", "N4");
    
    private final String naipes;
    private final String codigoNaipe;

    private Naipe(String naipes, String codigo) {
        this.naipes = naipes;
        this.codigoNaipe = codigo;
    }

    public String getNaipes() {
        return naipes;
    }    

    public String getCodigoNaipe() {
        return codigoNaipe;
    }
}
