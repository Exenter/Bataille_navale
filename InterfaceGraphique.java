
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfaceGraphique implements ActionListener{

	/* TODO
	 * Selection de bateau
	 * selection sauvegarde
	 */
	JToggleButton[][] butt;
	JFrame frame;
    JPanel panel;
    JLabel label;
    JLabel hp_bar1;
    JLabel hp_bar2;
    JLabel hp_bar3;
    ImageIcon water;
    ImageIcon fog;
    ImageIcon ship;
    int i,j, boat;
    int taille = 20;
    int grid_size = 40;
    int hp = 3;
    
    public void les_boutons(){
    
	frame=new JFrame("Bataille Navaleuuhhh with a twist");
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
    ship = new ImageIcon("images/ship_hp.png");
    Image ship_img = ship.getImage();
    ship = new ImageIcon(ship_img.getScaledInstance(60, 35, Image.SCALE_DEFAULT));
    
    //enlever pour le vrai test !
    ImageIcon ship_hp3 = new ImageIcon("images/3_hp.png");    
    
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
    JPanel hp_bar = new JPanel(new GridLayout(1,6));
    hp_bar1 = new JLabel("", JLabel.CENTER);
    hp_bar1.setIcon(ship_hp3);
    hp_bar2 = new JLabel("", JLabel.CENTER);
    hp_bar2.setIcon(ship_hp3);
    hp_bar3 = new JLabel("", JLabel.CENTER);
    hp_bar3.setIcon(ship_hp3);
    JButton ship1 = new JButton();
    ship1.addActionListener(this);
    ship1.setActionCommand("2_ship_1");
    ship1.setIcon(ship);
    hp_bar.add(ship1);
    hp_bar.add(hp_bar1);
    JButton ship2 = new JButton();
    ship2.addActionListener(this);
    ship2.setActionCommand("2_ship_2");
    ship2.setIcon(ship);
    hp_bar.add(ship2);
    hp_bar.add(hp_bar2);
    JButton ship3 = new JButton();
    ship3.addActionListener(this);
    ship3.setActionCommand("2_ship_3");
    ship3.setIcon(ship);
    hp_bar.add(ship3);
    hp_bar.add(hp_bar3);
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
    tir.setActionCommand("3_FIRE");
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

    public int setWater(int I, JToggleButton B){
    	switch(I){
    	case 1:
    		water = new ImageIcon("images/water.jpg");
    	    Image water_img = water.getImage();
    	    water = new ImageIcon(water_img.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    B.setIcon(water);
    	    break;
    	case 2:
    		water = new ImageIcon("images/ship_y1.png");
    	    Image water_img2 = water.getImage();
    	    water = new ImageIcon(water_img2.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    B.setIcon(water);
    	    break;
    	case 3:
    		B.setIcon(fog);
    	}
    	return I;
    }
    
    public int remaining_hp(int boat, int hp){
    	ImageIcon ship_hp0 = new ImageIcon("images/0_hp.png");
    	ImageIcon ship_hp1 = new ImageIcon("images/1_hp.png");
    	ImageIcon ship_hp2 = new ImageIcon("images/2_hp.png");
    	ImageIcon ship_hp3 = new ImageIcon("images/3_hp.png");
    	switch(boat){
    	case 1:
    		switch(hp){
    		case 0:
    			hp_bar1.setIcon(ship_hp0);
    			break;
    		case 1:
    			hp_bar1.setIcon(ship_hp1);
    			break;
    		case 2:
    			hp_bar1.setIcon(ship_hp2);
    			break;
    		case 3:
    			hp_bar1.setIcon(ship_hp3);
    			break;
    		default:
    			hp_bar1.setIcon(ship_hp3);
    			break;    		
    		}
    		break;
    	case 2:
    		switch(hp){
    		case 0:
    			hp_bar2.setIcon(ship_hp0);
    			break;
    		case 1:
    			hp_bar2.setIcon(ship_hp1);
    			break;
    		case 2:
    			hp_bar2.setIcon(ship_hp2);
    			break;
    		case 3:
    			hp_bar2.setIcon(ship_hp3);
    			break;
    		default:
    			hp_bar2.setIcon(ship_hp3);
    			break;    		
    		}
    		break;
    	case 3:
    		switch(hp){
    		case 0:
    			hp_bar3.setIcon(ship_hp0);
    			break;
    		case 1:
    			hp_bar3.setIcon(ship_hp1);
    			break;
    		case 2:
    			hp_bar3.setIcon(ship_hp2);
    			break;
    		case 3:
    			hp_bar3.setIcon(ship_hp3);
    			break;
    		default:
    			hp_bar3.setIcon(ship_hp3);
    			break;    		
    		}
    		break;
    	}
    	return hp;
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
    			setWater(1, butt[i][j]);
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
	        	setWater(2, butt[i][j]);
	        	System.out.println("check");
	        	break;
	        case "rotG":
	        	System.out.println("check");
	        	break;
	        case "rotD":
	        	System.out.println("check");
	        	break;
	        }
	        break;
    	case "2":
    		//hp =3;
    		boat = Integer.valueOf(values[2]);
    		break;
    	case "3":
    		System.out.println(boat);
    		if (hp >= 1){
        		hp -= 1;
        		remaining_hp(boat, hp);
        		System.out.println("check");
    		}
        	break;
        }
	}
    
    public static void main(String[] args){
        new InterfaceGraphique().les_boutons();         
    }

}