
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfaceGraphique extends JFrame implements ActionListener{

	/* TODO
	 * faire fonctionner l'affichage des bateaux
	 */
	
	JToggleButton[][] butt;
    JPanel panel;
    JLabel label;
    JLabel hp_bar1;
    JLabel hp_bar2;
    JLabel hp_bar3;
    ImageIcon fog;
    ImageIcon ship;
    int i,j, boat;
    int taille = 20;
    int grid_size = 40;
    int hp = 3;
    
    public InterfaceGraphique(){
    	butt = new JToggleButton[taille][taille];
        for(int i=0;i<taille;i++){
        	for (int j=0;j<taille;j++){
            butt[i][j] = new JToggleButton();
            butt[i][j].setPreferredSize(new Dimension(grid_size, grid_size));
            butt[i][j].addActionListener(this);
            butt[i][j].setActionCommand("0_"+String.valueOf(i)+"_"+String.valueOf(j));
            setWaterState(8, i, j);
        	}
        }
    }
    
    public void les_boutons(){
    
	setTitle("Bataille Navale with a twist");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    JPanel panelGrille = new JPanel(new GridLayout(taille, taille));
    
    // set image sizing
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
    add(top_side, BorderLayout.PAGE_START);
    
    //la carte
    for(int i=0;i<taille;i++){
    	for (int j=0;j<taille;j++){
        panelGrille.add(butt[i][j]);
    	}}
    
    add(panelGrille, BorderLayout.CENTER);
    
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
    JButton haut = new JButton();
    haut.addActionListener(this);
    haut.setActionCommand("1_Haut");
    ImageIcon up = new ImageIcon("images/up.png");
    haut.setIcon(up);
    JButton bas = new JButton();
    bas.addActionListener(this);
    bas.setActionCommand("1_Bas");
    ImageIcon bot = new ImageIcon("images/bot.png");
    bas.setIcon(bot);
    JButton gauche = new JButton();
    gauche.addActionListener(this);
    gauche.setActionCommand("1_Gauche");
    ImageIcon left = new ImageIcon("images/left.png");
    gauche.setIcon(left);
    JButton droite = new JButton();
    droite.addActionListener(this);
    droite.setActionCommand("1_Droite");
    ImageIcon right = new ImageIcon("images/right.png");
    droite.setIcon(right);
    JButton rot_G = new JButton();
    rot_G.addActionListener(this);
    rot_G.setActionCommand("1_rotG");
    ImageIcon rotG = new ImageIcon("images/rotG.png");
    rot_G.setIcon(rotG);
    JButton rot_D = new JButton();
    rot_D.addActionListener(this);
    rot_D.setActionCommand("1_rotD");
    ImageIcon rotD = new ImageIcon("images/rotD.png");
    rot_D.setIcon(rotD);
    JButton tir = new JButton();
    tir.addActionListener(this);
    tir.setActionCommand("3_FIRE");
    tir.setPreferredSize(new Dimension(70,60));
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
    
    add(bottom_side, BorderLayout.PAGE_END);
    
    // Le menu
    JMenuBar barreMenu = new JMenuBar();
    JMenu menu = new JMenu("Le_menu");
    JMenuItem itemSauvegarder = new JMenuItem(new SaveAction("Sauvegarder"));
    JMenuItem itemQuitter = new JMenuItem(new QuitterAction("Quitter"));
    menu.add(itemSauvegarder);
    menu.add(new JSeparator());
    menu.add(itemQuitter);
    barreMenu.add(menu);
    setJMenuBar(barreMenu); 
    
    //fin
    pack();
	setLocationRelativeTo(null);
	setVisible(true);
    
    }

    public void setWaterState(int I, int Bx, int By){
    	switch(I){
    	case 1:
    		ImageIcon water = new ImageIcon("images/water.jpg");
    	    Image water_img = water.getImage();
    	    water = new ImageIcon(water_img.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[Bx][By].setIcon(water);
    	    break;
    	case 2:
    		System.out.println("setWater check");
    		water = new ImageIcon("images/ship_y1.png");
    	    Image water_img2 = water.getImage();
    	    water = new ImageIcon(water_img2.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    System.out.println(butt[Bx][By]);
    	    butt[Bx][By].setIcon(water);
    	    break;
    	case 3:
    		water = new ImageIcon("images/ship_y2.png");
    	    Image water_img3 = water.getImage();
    	    water = new ImageIcon(water_img3.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[Bx][By].setIcon(water);
    	    break;
    	case 4:
    		water = new ImageIcon("images/ship_y3.png");
    	    Image water_img4 = water.getImage();
    	    water = new ImageIcon(water_img4.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[Bx][By].setIcon(water);
    	    break;
    	case 5:
    		water = new ImageIcon("images/ship_x1.png");
    	    Image water_img5 = water.getImage();
    	    water = new ImageIcon(water_img5.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[Bx][By].setIcon(water);
    	    break;
    	case 6:
    		water = new ImageIcon("images/ship_x2.png");
    	    Image water_img6 = water.getImage();
    	    water = new ImageIcon(water_img6.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[Bx][By].setIcon(water);
    	    break;
    	case 7:
    		water = new ImageIcon("images/ship_x3.png");
    	    Image water_img7 = water.getImage();
    	    water = new ImageIcon(water_img7.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[Bx][By].setIcon(water);
    	    break;
    	case 8:
    		fog = new ImageIcon("images/fog.png");
    	    Image fog_img = fog.getImage();
    	    water = new ImageIcon(fog_img.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    		butt[Bx][By].setIcon(fog);
    		break;
    	}

    	repaint();
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
    			setWaterState(1, i, j);
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
	        	setWaterState(2, i, j);
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
        		label.setText("Touché !");			
    		}
    		else
    			label.setText("T'es mauvais Jack");
        	break;
        }
	}
	
	public static void main(String[] args) {
		new InterfaceGraphique().les_boutons(); 
	}
}