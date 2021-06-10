/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojogobaralho;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Randney Hancis
 */
public class Jogo extends javax.swing.JFrame {

    /**
     * Creates new form Jogo
     */
    
    //Atributos do jogo
    CartaBaralho maoCasa[];  
    CartaBaralho maoJogador[];
    Jogador casa = new Jogador("Casa", maoCasa, 0);
    Jogador jogador = new Jogador("Jogador", maoJogador, 0);    
    int i = 0;
    int j = 0;
    int somaCasa = 0;
    int somaJogador = 0;
    int vitoriasCasa = 0;
    int vitoriasJogador = 0;
   
    
    public Jogo() {
        initComponents();
        
        nomeCasa.setText("Cartas da " + casa.getNomeJogador());
        nomeJogador.setText("Cartas do " + jogador.getNomeJogador());
        
        JButton botaoJogador[];
        JButton botaoCasa[];
        
        botaoJogador = new JButton[]{cartaJogador1, cartaJogador2, cartaJogador3, cartaJogador4, cartaJogador5};
        botaoCasa = new JButton[] {cartaCasa2, cartaCasa3, cartaCasa4, cartaCasa5};
        
        for(JButton b : botaoJogador)
            b.setVisible(false);
        for(JButton b : botaoCasa)
            b.setVisible(false);       
        imagemInicialCartas();
        
    }
    
    //método que mostra a parte de trás das cartas do baralho
    public void imagemInicialCartas() {
        ImageIcon imgInicial = new ImageIcon(getClass().getResource("../imgCartas/backtile.png"));
        JLabel cartasCasa[];
        JLabel cartasJogador[];
        
        cartasCasa = new JLabel[]{cartaCasa1_img, cartaCasa2_img, cartaCasa3_img, cartaCasa4_img, cartaCasa5_img};
        cartasJogador = new JLabel[] {cartaJogador1_img, cartaJogador2_img, cartaJogador3_img, cartaJogador4_img, cartaJogador5_img};
        
        for(JLabel lbl : cartasCasa)
            lbl.setIcon(imgInicial);
        for(JLabel lbl : cartasJogador)
           lbl.setIcon(imgInicial);
    }
    
        
    //método para mostrar a foto da carta no jogo da casa de apostas
    public void imagemCartaCasa(int i) {
        ImageIcon imgCasa = new ImageIcon(getClass().getResource("..\\imgCartas\\"+casa.getMao()[i].getNaipe().getCodigoNaipe()+casa.getMao()[i].getCarta().getCodigoCarta()+".png"));
        switch(i) {
            case 0: cartaCasa1_img.setIcon(imgCasa);
            break;
            case 1: cartaCasa2_img.setIcon(imgCasa);
            break;
            case 2: cartaCasa3_img.setIcon(imgCasa);
            break;
            case 3: cartaCasa4_img.setIcon(imgCasa);  
            break;
            case 4: cartaCasa5_img.setIcon(imgCasa);
            break;
        }           
    } 
    
    //método para mostrar a foto da carta no jogo no campo do jogador
    public void imagemCartaJogador(int j) {
        ImageIcon imgJogador = new ImageIcon(getClass().getResource("..\\imgCartas\\"+jogador.getMao()[j].getNaipe().getCodigoNaipe()+jogador.getMao()[j].getCarta().getCodigoCarta()+".png"));
        switch(j) {
            case 0: cartaJogador1_img.setIcon(imgJogador);
            break;
            case 1: cartaJogador2_img.setIcon(imgJogador);
            break;
            case 2: cartaJogador3_img.setIcon(imgJogador);
            break;
            case 3: cartaJogador4_img.setIcon(imgJogador);  
            break;
            case 4: cartaJogador5_img.setIcon(imgJogador);
            break;
        }        
    }
    
    //método para somar os pontos do vencedor da rodada
    public void somaDePontos(int i, int j) {
        if(jogador.getMao()[j].getCarta().getPontos() > casa.getMao()[i].getCarta().getPontos()) {
            somaJogador = jogador.soma(i, j, jogador, casa, somaJogador, pontosJogador);            
            
        } else if(jogador.getMao()[j].getCarta().getPontos() < casa.getMao()[i].getCarta().getPontos())
        {
           somaCasa = casa.soma(i, j, jogador, casa, somaCasa, pontosCasa);
        }        
    }    
    
    //método para computar o vencedor da partida e a quantidade de vitórias por partida
    public void vencedor() {
        if(somaJogador < somaCasa) {
            vitoriasCasa++;
            vencedor.setText("Vitoria da Casa!");
            vitCasa.setText(Integer.toString(vitoriasCasa));
        } else if (somaJogador > somaCasa) {
            vitoriasJogador++;
            vencedor.setText("Vitoria do Jogador!");
            vitJogador.setText(Integer.toString(vitoriasJogador));
        } else {
            vencedor.setText("Empate!");
        }
    }

    private JLabel setText(String nomeJogador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //classe para mostrar um popup com o resultado da partida
    public class PainelVencedor {
        JFrame f;  
        PainelVencedor(){  
            f=new JFrame(); 
            if(somaJogador < somaCasa)
                JOptionPane.showMessageDialog(f,"Vitoria da Casa!", "Partida Finalizada!", JOptionPane.WARNING_MESSAGE);  
            else if (somaJogador > somaCasa)
                JOptionPane.showMessageDialog(f,"Vitoria do Jogador!", "Partida Finalizada!", JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(f,"Partida Empatada!", "Partida Finalizada!", JOptionPane.WARNING_MESSAGE);
        }  
    }   
    
    //método para mudar a cor e texto do botão vencedor da rodada
    public void botaoVencedorRodada(int j, int i, java.awt.event.ActionEvent evt, JButton botao1) {
        JButton botao2 = (JButton) evt.getSource();
        if(jogador.getMao()[j].getCarta().getPontos() < casa.getMao()[i].getCarta().getPontos()) {
            botao1.setBackground(Color.red);
            botao1.setForeground(Color.black);
            botao1.setText("Vence!");
            botao2.setText("Perde!");
        } else if(jogador.getMao()[j].getCarta().getPontos() > casa.getMao()[i].getCarta().getPontos()){
            botao2.setBackground(Color.red);
            botao2.setForeground(Color.black);
            botao2.setText("Vence!");
            botao1.setText("Perde!");
        }
        else {
            botao1.setBackground(Color.blue);
            botao1.setForeground(Color.red);
            botao1.setText("Empate!");
            botao2.setBackground(Color.blue);
            botao2.setForeground(Color.red);
            botao2.setText("Empate!");
        }
    }
    
    //método reseta botões para padrão inicial (Não consegui fazer funcionar)
//    public void reinciarPadraoBotao(java.awt.event.ActionEvent evt, JButton botao1, JButton botao2) {        
//        //botao1 = new JButton();
//        botao1.setBackground(Color.LIGHT_GRAY);
//        botao1.setForeground(Color.YELLOW);
//        //botao1.setText("Carta 1");
//        //botao2 = new JButton();
//        botao1.setBackground(Color.LIGHT_GRAY);
//        botao1.setForeground(Color.YELLOW);
//        //botao2.setText("Carta 1");              
//    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cartaCasa2 = new javax.swing.JButton();
        cartaCasa3 = new javax.swing.JButton();
        cartaCasa4 = new javax.swing.JButton();
        cartaCasa5 = new javax.swing.JButton();
        cartaJogador1 = new javax.swing.JButton();
        cartaJogador2 = new javax.swing.JButton();
        cartaJogador3 = new javax.swing.JButton();
        cartaJogador4 = new javax.swing.JButton();
        cartaJogador5 = new javax.swing.JButton();
        nomeCasa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nomeJogador = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pontosCasa = new javax.swing.JLabel();
        pontosJogador = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        vencedor = new javax.swing.JLabel();
        ResetJogo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        vitCasa = new javax.swing.JLabel();
        vitJogador = new javax.swing.JLabel();
        cartaCasa1 = new javax.swing.JButton();
        cartaCasa1_img = new javax.swing.JLabel();
        cartaCasa2_img = new javax.swing.JLabel();
        cartaCasa3_img = new javax.swing.JLabel();
        cartaCasa4_img = new javax.swing.JLabel();
        cartaCasa5_img = new javax.swing.JLabel();
        cartaJogador1_img = new javax.swing.JLabel();
        cartaJogador2_img = new javax.swing.JLabel();
        cartaJogador3_img = new javax.swing.JLabel();
        cartaJogador4_img = new javax.swing.JLabel();
        cartaJogador5_img = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 0));

        cartaCasa2.setText("Carta 2");
        cartaCasa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaCasa2ActionPerformed(evt);
            }
        });

        cartaCasa3.setText("Carta 3");
        cartaCasa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaCasa3ActionPerformed(evt);
            }
        });

        cartaCasa4.setText("Carta 4");
        cartaCasa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaCasa4ActionPerformed(evt);
            }
        });

        cartaCasa5.setText("Carta 5");
        cartaCasa5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaCasa5ActionPerformed(evt);
            }
        });

        cartaJogador1.setText("Carta 1");
        cartaJogador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaJogador1ActionPerformed(evt);
            }
        });

        cartaJogador2.setText("Carta 2");
        cartaJogador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaJogador2ActionPerformed(evt);
            }
        });

        cartaJogador3.setText("Carta 3");
        cartaJogador3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaJogador3ActionPerformed(evt);
            }
        });

        cartaJogador4.setText("Carta 4");
        cartaJogador4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaJogador4ActionPerformed(evt);
            }
        });

        cartaJogador5.setText("Carta 5");
        cartaJogador5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaJogador5ActionPerformed(evt);
            }
        });

        nomeCasa.setBackground(new java.awt.Color(204, 255, 51));
        nomeCasa.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        nomeCasa.setForeground(new java.awt.Color(0, 0, 0));
        nomeCasa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setBackground(new java.awt.Color(204, 255, 51));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pontos da Casa");

        nomeJogador.setBackground(new java.awt.Color(204, 255, 51));
        nomeJogador.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        nomeJogador.setForeground(new java.awt.Color(0, 0, 0));
        nomeJogador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setBackground(new java.awt.Color(204, 255, 51));
        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pontos do Jogador");

        pontosCasa.setBackground(new java.awt.Color(0, 0, 0));
        pontosCasa.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        pontosCasa.setForeground(new java.awt.Color(0, 0, 0));
        pontosCasa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pontosCasa.setText("0");

        pontosJogador.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        pontosJogador.setForeground(new java.awt.Color(0, 0, 0));
        pontosJogador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pontosJogador.setText("0");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Vencedor");

        vencedor.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        vencedor.setForeground(new java.awt.Color(0, 0, 0));
        vencedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vencedor.setText("...");

        ResetJogo.setText("Reiniciar Jogo");
        ResetJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetJogoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Vitorias");

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Vitorias");

        vitCasa.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        vitCasa.setForeground(new java.awt.Color(0, 0, 0));
        vitCasa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vitCasa.setText("0");

        vitJogador.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        vitJogador.setForeground(new java.awt.Color(0, 0, 0));
        vitJogador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vitJogador.setText("0");

        cartaCasa1.setText("Carta 1");
        cartaCasa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaCasa1ActionPerformed(evt);
            }
        });

        cartaCasa1_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartaCasa2_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartaCasa3_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartaCasa4_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartaCasa5_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartaJogador1_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartaJogador2_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartaJogador3_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartaJogador4_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartaJogador5_img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cartaJogador1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cartaCasa1_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartaCasa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cartaJogador1_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cartaJogador2_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartaCasa2_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartaCasa2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cartaJogador2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomeCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomeJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cartaJogador3_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartaJogador3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cartaJogador4_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartaJogador4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cartaJogador5_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartaJogador5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cartaCasa3_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartaCasa3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cartaCasa4_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartaCasa4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cartaCasa5_img, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartaCasa5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pontosJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pontosCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(vitJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(vitCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ResetJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(vencedor, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cartaCasa1_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cartaCasa2_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cartaCasa3_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cartaCasa5_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cartaCasa4_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cartaCasa1)
                                .addComponent(cartaCasa2)
                                .addComponent(cartaCasa5))
                            .addComponent(cartaCasa3)
                            .addComponent(cartaCasa4))
                        .addGap(36, 36, 36)
                        .addComponent(nomeJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pontosCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vitCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(vencedor, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cartaJogador1_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartaJogador2_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartaJogador3_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(vitJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pontosJogador, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cartaJogador5_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cartaJogador4_img, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cartaJogador1)
                            .addComponent(cartaJogador2)
                            .addComponent(cartaJogador4)
                            .addComponent(cartaJogador5)
                            .addComponent(ResetJogo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cartaJogador3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    /**OBS: Botões ficaram fora de ordem pq depois que deu o problema de envio tive que
     * copiar tudo para um novo projeto, na hora de colar ficou tudo desorganizado, mas tá funcionando normal;   
     * 
     */
    
    //método para ação das cartas da casa
    private int acaoBotaoCasa(int iValor, JButton casaEnabler, JButton casaVisible) {
        i = iValor;
        imagemCartaCasa(i);
        casaEnabler.setEnabled(false);
        casaVisible.setVisible(true);
        
        return i;
    } 
    
    private void cartaCasa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaCasa2ActionPerformed
        acaoBotaoCasa(1, cartaCasa2, cartaJogador2);        
    }//GEN-LAST:event_cartaCasa2ActionPerformed

    private void cartaCasa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaCasa3ActionPerformed
        acaoBotaoCasa(2, cartaCasa3, cartaJogador3);   
    }//GEN-LAST:event_cartaCasa3ActionPerformed

    private void cartaCasa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaCasa4ActionPerformed
       acaoBotaoCasa(3, cartaCasa4, cartaJogador4);   
    }//GEN-LAST:event_cartaCasa4ActionPerformed

    private void cartaCasa5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaCasa5ActionPerformed
        acaoBotaoCasa(4, cartaCasa5, cartaJogador5);   
    }//GEN-LAST:event_cartaCasa5ActionPerformed

    
    //método para a ação do botão das cartas do jogador
    //Botão jogador 5 não recebe esse método
    private int acaoBotaoJogador(int iValor, JButton jogadorEnabler, JButton jogadorVisible) {
        j = iValor;
        imagemCartaJogador(j);        
        jogadorEnabler.setEnabled(false);
        jogadorVisible.setVisible(true);
        
        return j;
    }
    
    private void cartaJogador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaJogador1ActionPerformed
        acaoBotaoJogador(0, cartaJogador1, cartaCasa2);  
        somaDePontos(i, j);
        botaoVencedorRodada(i, j, evt, cartaCasa1);        
    }//GEN-LAST:event_cartaJogador1ActionPerformed

    private void cartaJogador2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaJogador2ActionPerformed
        acaoBotaoJogador(1, cartaJogador2, cartaCasa3);            
        somaDePontos(i, j);
        botaoVencedorRodada(i, j, evt, cartaCasa2);        
    }//GEN-LAST:event_cartaJogador2ActionPerformed

    private void cartaJogador3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaJogador3ActionPerformed
        acaoBotaoJogador(2, cartaJogador3, cartaCasa4);  
        somaDePontos(i, j);
        botaoVencedorRodada(i, j, evt, cartaCasa3);       
    }//GEN-LAST:event_cartaJogador3ActionPerformed

    private void cartaJogador4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaJogador4ActionPerformed
        acaoBotaoJogador(3, cartaJogador4, cartaCasa5);  
        somaDePontos(i, j);
        botaoVencedorRodada(i, j, evt, cartaCasa4);
    }//GEN-LAST:event_cartaJogador4ActionPerformed

    private void cartaJogador5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaJogador5ActionPerformed
        j = 4;
        imagemCartaJogador(j);        
        somaDePontos(i, j);
        botaoVencedorRodada(i, j, evt, cartaCasa5);
        new Jogo.PainelVencedor(); 
        vencedor();        
        cartaJogador5.setEnabled(false);
    }//GEN-LAST:event_cartaJogador5ActionPerformed

    private void cartaCasa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaCasa1ActionPerformed
        acaoBotaoCasa(0, cartaCasa1, cartaJogador1);   
    }//GEN-LAST:event_cartaCasa1ActionPerformed
     
    private void ResetJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetJogoActionPerformed
        casa = new Jogador("Casa", maoCasa, 0);
        jogador = new Jogador("Jogador", maoJogador, 0);    
        i = 0;
        j = 0;
        somaCasa = casa.getPontos();
        somaJogador = jogador.getPontos();  
        
        new Jogo().initComponents();
        
        imagemInicialCartas();
                     
        pontosCasa.setText("0");
        pontosJogador.setText("0");
        vencedor.setText("...");
                
        
        //tentei criar um método para fazer as mudanças, mas não deu certo
        //o dito cujo está comentado mais acima        
        JButton novaCartaJogador[];
        JButton novaCartaCasa[];
        
        novaCartaJogador = new JButton[] {cartaJogador1, cartaJogador2, cartaJogador3, cartaJogador4, cartaJogador5};
        novaCartaCasa = new JButton[] {cartaCasa2, cartaCasa3, cartaCasa4, cartaCasa5};
        
        int indxJ = 1;
        int indxC = 2;
        
        for (JButton c : novaCartaJogador){            
            c.setVisible(false);
            c.setEnabled(true);
            c.setBackground(Color.LIGHT_GRAY);
            c.setForeground(Color.BLACK);
            c.setText("Carta "+indxJ);
            indxJ++;
        }
                
        for (JButton c : novaCartaCasa){            
            c.setVisible(false);
            c.setEnabled(true);
            c.setBackground(Color.LIGHT_GRAY);
            c.setForeground(Color.BLACK);
            c.setText("Carta "+indxC);
            indxC++;
        }

        cartaCasa1.setEnabled(true);        
        cartaCasa1.setBackground(Color.LIGHT_GRAY);
        cartaCasa1.setForeground(Color.YELLOW);
        cartaCasa1.setText("Carta 1");        
    }//GEN-LAST:event_ResetJogoActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jogo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ResetJogo;
    private javax.swing.JButton cartaCasa1;
    private javax.swing.JLabel cartaCasa1_img;
    private javax.swing.JButton cartaCasa2;
    private javax.swing.JLabel cartaCasa2_img;
    private javax.swing.JButton cartaCasa3;
    private javax.swing.JLabel cartaCasa3_img;
    private javax.swing.JButton cartaCasa4;
    private javax.swing.JLabel cartaCasa4_img;
    private javax.swing.JButton cartaCasa5;
    private javax.swing.JLabel cartaCasa5_img;
    private javax.swing.JButton cartaJogador1;
    private javax.swing.JLabel cartaJogador1_img;
    private javax.swing.JButton cartaJogador2;
    private javax.swing.JLabel cartaJogador2_img;
    private javax.swing.JButton cartaJogador3;
    private javax.swing.JLabel cartaJogador3_img;
    private javax.swing.JButton cartaJogador4;
    private javax.swing.JLabel cartaJogador4_img;
    private javax.swing.JButton cartaJogador5;
    private javax.swing.JLabel cartaJogador5_img;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nomeCasa;
    private javax.swing.JLabel nomeJogador;
    private javax.swing.JLabel pontosCasa;
    private javax.swing.JLabel pontosJogador;
    private javax.swing.JLabel vencedor;
    private javax.swing.JLabel vitCasa;
    private javax.swing.JLabel vitJogador;
    // End of variables declaration//GEN-END:variables
}
