
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfaceGraphique extends JFrame implements ActionListener{

	/* TODO
	 * faire fonctionner l'affichage des bateaux
	 */
	
	JButton[][] butt;
    JPanel panel;
    JLabel label;
    JLabel hp_bar1;
    JLabel hp_bar2;
    JLabel hp_bar3;
    ImageIcon fog;
    ImageIcon ship;
    int i,j, boat;
    int tour;
    static int taille = 20;
    int grid_size = 40;
    int hp = 3;
    Joueur homoSapiens;
    Joueur IA;
    Carte carte;
    
    public InterfaceGraphique(Joueur homoSapiens, Joueur IA, Carte carte){
    	butt = new JButton[taille][taille];
    	this.carte = carte;
        for(int i=0;i<taille;i++){
        	for (int j=0;j<taille;j++){
            butt[i][j] = new JButton();
            butt[i][j].setPreferredSize(new Dimension(grid_size, grid_size));
            butt[i][j].addActionListener(this);
            butt[i][j].setActionCommand("0_"+String.valueOf(i)+"_"+String.valueOf(j));
        	}
        }
        this.homoSapiens = homoSapiens;
        this.IA = IA;
        //homoSapiens.nom = JOptionPane.showInputDialog(this, "Choose a name :", "Yarrhhh");
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
        if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Libre)
        	setWaterState(1, i, j);
        else
        	setWaterState(8, i, j);
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
    	    butt[By][Bx].setIcon(water);
    	    break;
    	case 2:
    		water = new ImageIcon("images/ship_y1.png");
    	    Image water_img2 = water.getImage();
    	    water = new ImageIcon(water_img2.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[By][Bx].setIcon(water);
    	    break;
    	case 3:
    		water = new ImageIcon("images/ship_y2.png");
    	    Image water_img3 = water.getImage();
    	    water = new ImageIcon(water_img3.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[By][Bx].setIcon(water);
    	    break;
    	case 4:
    		water = new ImageIcon("images/ship_y3.png");
    	    Image water_img4 = water.getImage();
    	    water = new ImageIcon(water_img4.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[By][Bx].setIcon(water);
    	    break;
    	case 5:
    		water = new ImageIcon("images/ship_x1.png");
    	    Image water_img5 = water.getImage();
    	    water = new ImageIcon(water_img5.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[By][Bx].setIcon(water);
    	    break;
    	case 6:
    		water = new ImageIcon("images/ship_x2.png");
    	    Image water_img6 = water.getImage();
    	    water = new ImageIcon(water_img6.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[By][Bx].setIcon(water);
    	    break;
    	case 7:
    		water = new ImageIcon("images/ship_x3.png");
    	    Image water_img7 = water.getImage();
    	    water = new ImageIcon(water_img7.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    	    butt[By][Bx].setIcon(water);
    	    break;
    	case 8:
    		fog = new ImageIcon("images/fog.png");
    	    Image fog_img = fog.getImage();
    	    water = new ImageIcon(fog_img.getScaledInstance(grid_size, grid_size, Image.SCALE_DEFAULT));
    		butt[By][Bx].setIcon(fog);
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
        
    
    public void setCaseBateau(Bateaux b){
		if (b.ori == Orientation.Verticale){
			setWaterState(2, b.p1.x, b.p1.y);
			setWaterState(3, b.centre.x, b.centre.y);
			setWaterState(4, b.p2.x, b.p2.y);

		}
		else{
			setWaterState(5, b.p1.x, b.p1.y);
			setWaterState(6, b.centre.x, b.centre.y);
			setWaterState(7, b.p2.x, b.p2.y);
		}
	}	
  
    public void updateCaseMachine(){
    	for(int x = 0; x<taille; x++) {
			for(int y = 0; y<taille; y++) {
				if (carte.cases[y][x].occupant == Proprio.Machine && carte.cases[y][x].vision == Vision.Claire){
					setCaseBateau(carte.cases[y][x].bat);
				}
			}}
    }
    
	public void actionPerformed(ActionEvent Act) {
    	String action = Act.getActionCommand();
    	String[] values = action.split("_");
    	switch (values[0]){
    	case "0":
    		// actions de tir
    		tour++;
    		i = Integer.valueOf(values[1]);
    		j = Integer.valueOf(values[2]);
    		if (action.equals("0_"+i+"_"+j)){
    			System.out.println(carte.cases[j][i].occupant+" "+carte.cases[j][i].vision);
    			if(carte.cases[j][i].occupant == Proprio.Libre){
    				setWaterState(1, j, i);
    				label.setText("Missed !");
    				carte.cases[j][i].vision = Vision.Claire;}
    			System.out.println(carte.cases[j][i].occupant+" "+carte.cases[j][i].vision);
    			//ca pique les yeux mais ca marche
    			if(carte.cases[j][i].occupant == Proprio.Machine){
               		setCaseBateau(carte.cases[j][i].bat);
               		carte.cases[j][i].bat.updateVie(); 
					homoSapiens.updateScore();
					label.setText("Touched !");
					if(carte.cases[j][i].bat.vie == 0){
	              		for(int x = carte.cases[j][i].bat.p1.x; x<=carte.cases[j][i].bat.p2.x; x++) {
							for(int y = carte.cases[j][i].bat.p1.y; y<=carte.cases[j][i].bat.p2.y; y++) {
								carte.cases[y][x].occupant = Proprio.Libre;
								setWaterState(1, x, y);
							}	
	              		}	
		              	carte.listeBatMachine.remove(carte.cases[j][i].bat);
		              	IA.listeBat.remove(carte.cases[j][i].bat);
		              	IA.listePoint.remove(carte.cases[j][i].bat.p1);
		              	IA.listePoint.remove(carte.cases[j][i].bat.centre);
		              	IA.listePoint.remove(carte.cases[j][i].bat.p2);
		              	label.setText("Un de moins");
		              	carte.cases[j][i] = new Case(Proprio.Libre, Vision.Claire);
					}	
    			}
    		}
    		break;
    	case "1":
    		tour++;
	        switch (values[1]){
	        case "Haut":
	        	if(carte.verifDepPossible(homoSapiens.listeBat.get(boat), Sens.Avant)) {
		        	homoSapiens.listeBat.get(boat).deplacementBateau(Sens.Avant);
		        	carte.updateCaseBateau(homoSapiens.listeBat.get(boat), Sens.Avant);
		        	carte.updateVisibilite(homoSapiens.listeBat.get(boat), Sens.Avant);
		        	setCaseBateau(homoSapiens.listeBat.get(boat));
		        	for(int i=0;i<taille;i++){
		            	for (int j=0;j<taille;j++){
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Libre)
		                	setWaterState(1, i, j);
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Machine) {
		                	setCaseBateau(carte.cases[i][j].bat);
			                for (int x= carte.cases[i][j].bat.p1.x; x <= carte.cases[i][j].bat.p2.x; x++) {
			                	for (int y= carte.cases[i][j].bat.p1.y; y <= carte.cases[i][j].bat.p2.y; y++) {
			                		if(carte.cases[x][y].vision == Vision.Brouillard)
			                			setWaterState(8, x, y);
			                	}
			                }
		            	}}}
	        	}
	        	break;
	        	
	        case "Bas":
	        	if(carte.verifDepPossible(homoSapiens.listeBat.get(boat), Sens.Arriere)) {
		        	homoSapiens.listeBat.get(boat).deplacementBateau(Sens.Arriere);
		        	carte.updateCaseBateau(homoSapiens.listeBat.get(boat), Sens.Arriere);
		        	carte.updateVisibilite(homoSapiens.listeBat.get(boat), Sens.Arriere);
		        	setCaseBateau(homoSapiens.listeBat.get(boat));
		        	for(int i=0;i<taille;i++){
		            	for (int j=0;j<taille;j++){
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Libre)
		                	setWaterState(1, i, j);
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Machine) {
		                	setCaseBateau(carte.cases[i][j].bat);
			                for (int x= carte.cases[i][j].bat.p1.x; x <= carte.cases[i][j].bat.p2.x; x++) {
			                	for (int y= carte.cases[i][j].bat.p1.y; y <= carte.cases[i][j].bat.p2.y; y++) {
			                		if(carte.cases[x][y].vision == Vision.Brouillard)
			                			setWaterState(8, x, y);
			                	}
			                }
		            	}
		            	}}
	        	}
	        	break;
	        	
	        case "Gauche":
	        	if(carte.verifDepPossible(homoSapiens.listeBat.get(boat), Sens.Arriere)) {
		        	homoSapiens.listeBat.get(boat).deplacementBateau(Sens.Arriere);
		        	carte.updateCaseBateau(homoSapiens.listeBat.get(boat), Sens.Arriere);
		        	carte.updateVisibilite(homoSapiens.listeBat.get(boat), Sens.Arriere);
		        	setCaseBateau(homoSapiens.listeBat.get(boat));
		        	for(int i=0;i<taille;i++){
		            	for (int j=0;j<taille;j++){
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Libre)
		                	setWaterState(1, i, j);
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Machine) {
		                	setCaseBateau(carte.cases[i][j].bat);
			                for (int x= carte.cases[i][j].bat.p1.x; x <= carte.cases[i][j].bat.p2.x; x++) {
			                	for (int y= carte.cases[i][j].bat.p1.y; y <= carte.cases[i][j].bat.p2.y; y++) {
			                		if(carte.cases[x][y].vision == Vision.Brouillard)
			                			setWaterState(8, x, y);;
			                	}
			                }
		            	}
		                }}
	        	}
	        	break;
	        	
	        case "Droite":
	        	if(carte.verifDepPossible(homoSapiens.listeBat.get(boat), Sens.Avant)) {
		        	homoSapiens.listeBat.get(boat).deplacementBateau(Sens.Avant);
		        	carte.updateCaseBateau(homoSapiens.listeBat.get(boat), Sens.Avant);
		        	carte.updateVisibilite(homoSapiens.listeBat.get(boat), Sens.Avant);
		        	setCaseBateau(homoSapiens.listeBat.get(boat));
		        	for(int i=0;i<taille;i++){
		            	for (int j=0;j<taille;j++){
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Libre)
		                	setWaterState(1, i, j);
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Machine) {
		                	setCaseBateau(carte.cases[i][j].bat);
		                	for (int x= carte.cases[i][j].bat.p1.x; x <= carte.cases[i][j].bat.p2.x; x++) {
			                	for (int y= carte.cases[i][j].bat.p1.y; y <= carte.cases[i][j].bat.p2.y; y++) {
			                		if(carte.cases[x][y].vision == Vision.Brouillard)
			                			setWaterState(8, x, y);
			                	}
			                }
		            	}
		                }
		            	}
	        	}
	        	break;
	        	
	        case "rotG":
	        	if(carte.verifDepPossible(homoSapiens.listeBat.get(boat), Sens.Rotation)) {
		        	homoSapiens.listeBat.get(boat).deplacementBateau(Sens.Rotation);
		        	carte.updateCaseBateau(homoSapiens.listeBat.get(boat), Sens.Rotation);
		        	carte.updateVisibilite(homoSapiens.listeBat.get(boat), Sens.Rotation);
		        	setCaseBateau(homoSapiens.listeBat.get(boat));
		        	for(int i=0;i<taille;i++){
		            	for (int j=0;j<taille;j++){
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Libre)
		                	setWaterState(1, i, j);
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Machine) {
		                	setCaseBateau(carte.cases[i][j].bat);
		                	for (int x= carte.cases[i][j].bat.p1.x; x <= carte.cases[i][j].bat.p2.x; x++) {
			                	for (int y= carte.cases[i][j].bat.p1.y; y <= carte.cases[i][j].bat.p2.y; y++) {
			                		if(carte.cases[x][y].vision == Vision.Brouillard)
			                			setWaterState(8, x, y);
			                	}
			                }
		            	}
		                }}
	        	}
	        	break;
	        	
	        case "rotD":
	        	if(carte.verifDepPossible(homoSapiens.listeBat.get(boat), Sens.Rotation)) {
		        	homoSapiens.listeBat.get(boat).deplacementBateau(Sens.Rotation);
		        	carte.updateCaseBateau(homoSapiens.listeBat.get(boat), Sens.Rotation);
		        	carte.updateVisibilite(homoSapiens.listeBat.get(boat), Sens.Rotation);
		        	setCaseBateau(homoSapiens.listeBat.get(boat));
		        	for(int i=0;i<taille;i++){
		            	for (int j=0;j<taille;j++){
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Libre)
		                	setWaterState(1, i, j);
		                if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Machine) {
		                	setCaseBateau(carte.cases[i][j].bat);
		                	for (int x= carte.cases[i][j].bat.p1.x; x <= carte.cases[i][j].bat.p2.x; x++) {
			                	for (int y= carte.cases[i][j].bat.p1.y; y <= carte.cases[i][j].bat.p2.y; y++) {
			                		if(carte.cases[x][y].vision == Vision.Brouillard)
			                			setWaterState(8, x, y);
			                	}
			                }
		            	}
		                }}
	        	}
	        	break;
	        	
	        }
	        break;
    	case "2":
    		boat = Integer.valueOf(values[2]);
    		System.out.println("boat value:"+ boat);
    		for (Bateaux bat: homoSapiens.listeBat) {
    			if (bat.numero == boat)
    				boat = homoSapiens.listeBat.indexOf(bat);
    			}
    		System.out.println("select boat :"+boat);
    		break;
//    	case "3":
//    		if (hp >= 1){
//    			homoSapiens.listeBat.get(boat).updateVie();
//        		hp -= 1;
//        		remaining_hp(boat, homoSapiens.listeBat.get(boat).vie);		
//    		}
//        	break;
        }
	}
}