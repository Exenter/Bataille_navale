
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfaceGraphique implements ActionListener{

	/* Selection de bateau
	 * selection sauvgarde
	 * fermeture
	 */
	JToggleButton[][] butt;
	JFrame frame;
    JPanel panel;
    JLabel label;
    int i,j;
    static int k;
    int taille = 20;
    ImageIcon water;
    ImageIcon fog;
    ImageIcon ship_HP;
    int grid_size = 40;
    
    public void les_boutons(){
    
	frame=new JFrame("Bataille Navale with a twist");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    JPanel panelGrille = new JPanel(new GridLayout(taille, taille));
    
    // set image sizing
    fog = new ImageIcon("images/fog.png");
    Image fog_img = fog.getImage();
    fog = new ImageIcon(fog_img.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    water = new ImageIcon("images/water.jpg");
    Image water_img = water.getImage();
    water = new ImageIcon(water_img.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    ImageIcon fire = new ImageIcon("images/canon.png");
    Image fire_img = fire.getImage();
    fire = new ImageIcon(fire_img.getScaledInstance(70, 50, Image.SCALE_DEFAULT));
    
    
    // info du dessus
    JPanel top_side = new JPanel();
    label = new JLabel("THE CAKE IS A LIE", JLabel.CENTER);
    top_side.add(label);
    frame.add(top_side, BorderLayout.PAGE_START);
    
    //la carte
    butt = new JToggleButton[taille][taille];
    for(int i=0;i<taille;i++){
    	for (int j=0;j<taille;j++){
        butt[i][j] = new JToggleButton();
        butt[i][j].setPreferredSize(new Dimension(grid_size, grid_size));
        butt[i][j].setIcon(fog);
        butt[i][j].addActionListener(this);
        butt[i][j].setActionCommand("0_"+String.valueOf(i)+"_"+String.valueOf(j));
        panelGrille.add(butt[i][j]);
    	}}
    frame.add(panelGrille, BorderLayout.CENTER);
    
    BorderLayout bottom_master = new BorderLayout();
    JPanel bottom_side = new JPanel(bottom_master);
    // barre de vie
    JPanel hp_bar = new JPanel(new GridLayout(1,3));
    ship_HP = new ImageIcon("images/ship_hp.png");
    Image ship_hp_img = ship_HP.getImage();
    ship_HP = new ImageIcon(ship_hp_img.getScaledInstance(60, 35, Image.SCALE_DEFAULT));
    JLabel ship1 = new JLabel("", JLabel.CENTER);
    ship1.setIcon(ship_HP);
    hp_bar.add(ship1);
    JLabel ship2 = new JLabel("", JLabel.CENTER);
    ship2.setIcon(ship_HP);
    hp_bar.add(ship2);
    JLabel ship3 = new JLabel("", JLabel.CENTER);
    ship3.setIcon(ship_HP);
    hp_bar.add(ship3);
    bottom_side.add(hp_bar, BorderLayout.PAGE_START);
    
    // actions
    JPanel actions = new JPanel(new GridLayout(2,1));
    JPanel actions1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel actions2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton haut = new JButton("Haut");
    haut.addActionListener(this);
    haut.setActionCommand("1_Haut");
    JButton bas = new JButton("Bas");
    bas.addActionListener(this);
    bas.setActionCommand("1_Bas");
    JButton gauche = new JButton("Gauche");
    gauche.addActionListener(this);
    gauche.setActionCommand("1_Gauche");
    JButton droite = new JButton("Droite");
    droite.addActionListener(this);
    droite.setActionCommand("1_Droite");
    JButton rot_G = new JButton("rot_G");
    rot_G.addActionListener(this);
    rot_G.setActionCommand("1_rotG");
    JButton rot_D = new JButton("rot_D");
    rot_D.addActionListener(this);
    rot_D.setActionCommand("1_rotD");
    JButton tir = new JButton();
    tir.addActionListener(this);
    tir.setActionCommand("1_FIRE");
    tir.setIcon(fire);
    bottom_side.add(tir, BorderLayout.LINE_START);
    actions1.add(gauche);
    actions1.add(haut);
    actions1.add(droite);
    actions2.add(rot_G);
    actions2.add(bas);    
    actions2.add(rot_D);  
    actions.add(actions1, JPanel.TOP_ALIGNMENT);
    actions.add(actions2, JPanel.BOTTOM_ALIGNMENT);
    bottom_side.add(actions, BorderLayout.CENTER);
    
    frame.add(bottom_side, BorderLayout.PAGE_END);
    
    //fin
    frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    
    }

    
	public void actionPerformed(ActionEvent Act) {
    	String action = Act.getActionCommand();
    	System.out.println(action);
    	String[] values = action.split("_");
    	switch (values[0]){
    	case "0":
    		i = Integer.valueOf(values[1]);
    		j = Integer.valueOf(values[2]);
    		if (action.equals("0_"+i+"_"+j)){
    			butt[i][j].setIcon(water);
    		}
    		break;
    	case "1":
	        switch (values[1]){
	        case "Haut":
	        	System.out.println("check");
	        	break;
	        case "Bas":
	        	System.out.println("check");
	        	break;
	        case "Gauche":
	        	System.out.println("check");
	        	break;
	        case "Droite":
	        	System.out.println("check");
	        	break;
	        case "rotG":
	        	System.out.println("check");
	        	break;
	        case "rotD":
	        	System.out.println("check");
	        	break;
	        case "FIRE":
	        	System.out.println("check");
	        	break;
	        }
	        break;
        }
	}
    
    public static void main(String[] args){
        new InterfaceGraphique().les_boutons(); 
        
    }

}