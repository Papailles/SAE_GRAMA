import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class GraphInterface extends JFrame implements ActionListener {

    private GRAPHMAP GMAP;
    private JButton boutonChargement;
    private JButton bouton0Distance;
    private JButton bouton1Distance;
    private JButton boutonVoisinage;
    private JButton boutonComparaison;
    private JLabel messageConfirmation;
    private JMenuItem villes;
    private JMenuItem centresLoisirs;
    private JMenuItem restaurants;
    private JMenuItem autoroutes;
    private JMenuItem nationales;
    private JMenuItem departementales;
    private JMenuItem nombreVilles;
    private JMenuItem nombreCentresLoisirs;
    private JMenuItem nombreRestaurants;
    private JMenuItem nombreAutoroutes;
    private JMenuItem nombreNationales;
    private JMenuItem nombreDepartementales;
    private JMenuItem listerParVilles;
    private JMenuItem listerParLoisirs;
    private JMenuItem listerParResto;
    private JMenuItem listerParAutoroutes;
    private JMenuItem listerParNationales;
    private JMenuItem listerParDepartementales;
    private JComboBox boxVilles;
    private JComboBox boxLoisirs;
    private JComboBox boxResto;
    private JComboBox boxAutoroutes;
    private JComboBox boxDepartementales;
    private JComboBox boxNationales;
    private JMenuItem villes1Voisin;
    private JMenuItem centresLoisirs1Voisin;
    private JMenuItem restaurants1Voisin;
    private static LinkedHashMap map;
    private static File s;
    private static String nodeString;
    private static String nodeString2;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boutonChargement){
            s = new File(this.getPath());
            s = new File("src/testgraph2.csv");
            try {
                map = GMAP.lectureFichier(s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(!map.isEmpty()) {
                setMessageConfirmation("Chargement réussi !");
                bouton0Distance.setEnabled(true);
                bouton1Distance.setEnabled(true);
                boutonComparaison.setEnabled(true);
                boutonVoisinage.setEnabled(true);
            }

            else
                setMessageConfirmation("Chargement échoué !");
        }

        if(e.getSource() == bouton0Distance) {
            setVisible(false);
            affichage0Voisin();

            listerParVilles.setEnabled(false);
            listerParLoisirs.setEnabled(false);
            listerParResto.setEnabled(false);
            listerParAutoroutes.setEnabled(false);
            listerParDepartementales.setEnabled(false);
            listerParNationales.setEnabled(false);

            nombreAutoroutes.setEnabled(false);
            nombreDepartementales.setEnabled(false);
            nombreNationales.setEnabled(false);

            GMAP.affichageNodeGraph(map);
            GMAP.affichageEdgeGraph(map);
        }

        if(e.getSource() == villes){
            GMAP.affichageSeulementVilles(map);
            listerParVilles.setEnabled(true);
        }
        if(e.getSource() == centresLoisirs){
            GMAP.affichageSeulementLoisirs(map);
            listerParLoisirs.setEnabled(true);
        }
        if(e.getSource() == restaurants){
            GMAP.affichageSeulementRestaurants(map);
            listerParResto.setEnabled(true);
        }
        if(e.getSource() == autoroutes){
            GMAP.affichageNodeGraphAutoroutes(map);
            GMAP.affichageSeulementAutoroutes(map);
            nombreAutoroutes.setEnabled(true);
            listerParAutoroutes.setEnabled(true);
        }
        if(e.getSource() == nationales){
            GMAP.affichageNodeGraphNationales(map);
            GMAP.affichageSeulementNationales(map);
            nombreNationales.setEnabled(true);
            listerParNationales.setEnabled(true);
        }
        if(e.getSource() == departementales){
            GMAP.affichageNodeGraphDepartementales(map);
            GMAP.affichageSeulementDepartementales(map);
            nombreDepartementales.setEnabled(true);
            listerParDepartementales.setEnabled(true);
        }

        if(e.getSource() == listerParVilles){
            ArrayList<String> list = GMAP.getVilles();
            for(String i : list){
                boxVilles.addItem(i);
            }
        }
        if(e.getSource() == listerParLoisirs){
            ArrayList<String> list = GMAP.getLoisirs();
            for(String i : list)
                boxLoisirs.addItem(i);
        }
        if(e.getSource() == listerParResto){
            ArrayList<String> list = GMAP.getResto();
            for(String i : list)
                boxResto.addItem(i);
        }
        if(e.getSource() == listerParAutoroutes){
            ArrayList<String> list = GMAP.getAutoroutes();
            for(String i : list)
                boxAutoroutes.addItem(i);
        }
        if(e.getSource() == listerParDepartementales){
            ArrayList<String> list = GMAP.getDepartementales();
            for(String i : list)
                boxDepartementales.addItem(i);
        }
        if(e.getSource() == listerParNationales){
            ArrayList<String> list = GMAP.getNationales();
            for(String i : list)
                boxNationales.addItem(i);
        }

        if(e.getSource() == nombreVilles){
            int nbVilles = GMAP.getNombreVilles(map);
            JOptionPane.showMessageDialog(null,"Il y a "+ nbVilles + " villes dans ce graph !");
        }
        if(e.getSource() == nombreCentresLoisirs){
            int nbLoisirs = GMAP.getNombreLoisirs(map);
            JOptionPane.showMessageDialog(null,"Il y a "+ nbLoisirs + " centres de loisirs dans ce graph !");
        }
        if(e.getSource() == nombreRestaurants){
            int nbResto = GMAP.getNombreResto(map);
            JOptionPane.showMessageDialog(null,"Il y a "+ nbResto + " restaurants dans ce graph !");
        }
        if(e.getSource() == nombreAutoroutes){
            int nbAutoroutes = GMAP.getNombreAutoroutes();
            JOptionPane.showMessageDialog(null,"Il y a "+ nbAutoroutes + " autoroutes dans ce graph !");
        }
        if(e.getSource() == nombreNationales){
            int nbNationales = GMAP.getNombreNationales();
            JOptionPane.showMessageDialog(null,"Il y a "+ nbNationales + " nationales dans ce graph !");
        }
        if(e.getSource() == nombreDepartementales){
            int nbDepartementales = GMAP.getNombreDepartementales();
            JOptionPane.showMessageDialog(null,"Il y a "+ nbDepartementales + " départementales dans ce graph !");
        }


        if(e.getSource() == bouton1Distance){
            GMAP.affichageNodeGraph(map);
            GMAP.affichageEdgeGraph(map);
            nodeString = JOptionPane.showInputDialog("Choisissez un point du graph (liste disponible dans l'analyse 0 distance)");
            if(GMAP.getNode(nodeString) == null)
                JOptionPane.showMessageDialog(null,"Le point " + nodeString + " n'existe pas sur le graph !");
            else {
                try {
                    map = GMAP.lectureFichier(s);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                affichage1Voisin();
                GMAP.affichage1Voisin(GMAP.getNode(nodeString), map);
            }
        }
        if(e.getSource() == villes1Voisin){
            GMAP.affichage1VoisinVilles(GMAP.getNode(nodeString),map);
        }
        if(e.getSource() == centresLoisirs1Voisin){
            GMAP.affichage1VoisinLoisirs(GMAP.getNode(nodeString),map);
        }
        if(e.getSource() == restaurants1Voisin){
            GMAP.affichage1VoisinResto(GMAP.getNode(nodeString),map);
        }

        if(e.getSource() == boutonVoisinage){
            GMAP.affichageNodeGraph(map);
            GMAP.affichageEdgeGraph(map);
            do {
                nodeString = JOptionPane.showInputDialog("Choisissez le premier point parmi les points du graph (liste disponible dans l'analyse 0 distance)");
                if (GMAP.getNode(nodeString) == null)
                    JOptionPane.showMessageDialog(null, "Le point " + nodeString + " n'existe pas sur le graph !");
            }while(GMAP.getNode(nodeString) == null);

            do {
                nodeString2 = JOptionPane.showInputDialog("Choisissez le deuxième point parmi les points du graph (liste disponible dans l'analyse 0 distance)");
                if (GMAP.getNode(nodeString2) == null)
                    JOptionPane.showMessageDialog(null, "Le point " + nodeString2 + " n'existe pas sur le graph !");
                if(nodeString.equals(nodeString2))
                    JOptionPane.showMessageDialog(null,"Les points sont identiques !");
            }while(GMAP.getNode(nodeString2) == null || nodeString.equals(nodeString2));
            System.out.println("C'est ok puto");
            GMAP.affichage2Distance(GMAP.getNode(nodeString), GMAP.getNode(nodeString2),map);
        }
    }

    public GraphInterface() {
        super();
        GMAP = new GRAPHMAP();
        constrFen();
        bouton0Distance.setEnabled(false);
        bouton1Distance.setEnabled(false);
        boutonComparaison.setEnabled(false);
        boutonVoisinage.setEnabled(false);
    }

    private void constrFen(){
        setTitle("String moteur interface"); //mise en oeuvze du titre
        setSize(1000, 500); //taille
        setLocationRelativeTo(null); //centrage
        setResizable(false); //non redimensionnable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Eermeture de 1'appli.
        setContentPane(constrPan()); //méthode de construction du content pane
        setVisible(true);
    }

    private JPanel constrPan(){
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));

        JLabel titre = new JLabel("Choisissez votre opération : ");
        p.add(titre);
        titre.setAlignmentX(CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());

        boutonChargement = new JButton("Chargement du GRAPHMAP");
        boutonChargement.addActionListener(this);
        p.add(boutonChargement);
        messageConfirmation = new JLabel("");
        p.add(messageConfirmation);
        messageConfirmation.setAlignmentX(CENTER_ALIGNMENT);
        boutonChargement.setAlignmentX(CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());

        bouton0Distance = new JButton("Affichage 0 distance");
        bouton0Distance.addActionListener(this);
        p.add(bouton0Distance);
        bouton0Distance.setAlignmentX(CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());

        bouton1Distance = new JButton("Affichage 1 distance");
        bouton1Distance.addActionListener(this);
        p.add(bouton1Distance);
        bouton1Distance.setAlignmentX(CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());

        boutonVoisinage = new JButton("Voisinage à 2 sauts");
        boutonVoisinage.addActionListener(this);
        p.add(boutonVoisinage);
        boutonVoisinage.setAlignmentX(CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());

        boutonComparaison = new JButton("Comparaison de sites à 2 distance");
        p.add(boutonComparaison);
        boutonComparaison.setAlignmentX(CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());

        return p;
    }

    private JFrame affichage0Voisin(){
        JFrame voisin0 = new JFrame();
        voisin0.setLayout(new BorderLayout());

        JMenuBar bar0voisin = new JMenuBar();

        JMenu afficherSeulementNoeuds = new JMenu("Afficher seulement (Noeuds)");
        villes = new JMenuItem("Villes");
        villes.addActionListener(this);

        centresLoisirs = new JMenuItem("Centres de loisirs");
        centresLoisirs.addActionListener(this);

        restaurants = new JMenuItem("Restaurants");
        restaurants.addActionListener(this);

        JMenu afficherSeulementLiens = new JMenu("Afficher seulement (Liens)");
        autoroutes = new JMenuItem("Autoroutes");
        autoroutes.addActionListener(this);

        nationales = new JMenuItem("Nationales");
        nationales.addActionListener(this);

        departementales = new JMenuItem("Départementales");
        departementales.addActionListener(this);

        JMenu lister = new JMenu("Lister par catégories");

        listerParVilles = new JMenuItem("Par ville");
        listerParVilles.addActionListener(this);

        listerParLoisirs = new JMenuItem("Par centres de loisirs");
        listerParLoisirs.addActionListener(this);

        listerParResto = new JMenuItem("Par restaurants");
        listerParResto.addActionListener(this);

        listerParAutoroutes = new JMenuItem("Par autoroutes");
        listerParAutoroutes.addActionListener(this);

        listerParDepartementales = new JMenuItem("Par départementales");
        listerParDepartementales.addActionListener(this);

        listerParNationales = new JMenuItem("Par nationales");
        listerParNationales.addActionListener(this);

        JMenu nombreDe = new JMenu("Afficher nombre de");

        nombreVilles = new JMenuItem("Villes");
        nombreVilles.addActionListener(this);

        nombreCentresLoisirs = new JMenuItem("Centres de loisirs");
        nombreCentresLoisirs.addActionListener(this);

        nombreRestaurants = new JMenuItem("Restaurants");
        nombreRestaurants.addActionListener(this);

        nombreAutoroutes = new JMenuItem("Autoroutes");
        nombreAutoroutes.addActionListener(this);

        nombreNationales = new JMenuItem("Nationales");
        nombreNationales.addActionListener(this);

        nombreDepartementales = new JMenuItem("Départementales");
        nombreDepartementales.addActionListener(this);

        afficherSeulementNoeuds.add(villes); afficherSeulementNoeuds.add(centresLoisirs); afficherSeulementNoeuds.add(restaurants);

        afficherSeulementLiens.add(autoroutes); afficherSeulementLiens.add(nationales); afficherSeulementLiens.add(departementales);

        lister.add(listerParVilles); lister.add(listerParLoisirs); lister.add(listerParResto); lister.add(listerParAutoroutes); lister.add(listerParDepartementales); lister.add(listerParNationales);

        nombreDe.add(nombreVilles); nombreDe.add(nombreCentresLoisirs); nombreDe.add(nombreRestaurants); nombreDe.add(nombreAutoroutes); nombreDe.add(nombreNationales); nombreDe.add(nombreDepartementales);

        bar0voisin.add(afficherSeulementNoeuds); bar0voisin.add(afficherSeulementLiens); bar0voisin.add(lister); bar0voisin.add(nombreDe);
        voisin0.setJMenuBar(bar0voisin);

        voisin0.setTitle("Affichage 0 distance");
        voisin0.setSize(1300, 700); //taille
        voisin0.setLocationRelativeTo(null); //centrage
        voisin0.setResizable(false); //non redimensionnable
        voisin0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fermeture de 1'appli.
        voisin0.setVisible(true);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        boxVilles = new JComboBox();
        boxVilles.addItem("VILLES");
        p.add(boxVilles);

        boxLoisirs = new JComboBox();
        boxLoisirs.addItem("CENTRES DE LOISIRS");
        p.add(boxLoisirs);

        boxResto = new JComboBox();
        boxResto.addItem("RESTAURANTS");
        p.add(boxResto);

        boxAutoroutes = new JComboBox();
        boxAutoroutes.addItem("AUTOROUTES");
        p.add(boxAutoroutes);

        boxDepartementales = new JComboBox();
        boxDepartementales.addItem("DEPARTEMENTALES");
        p.add(boxDepartementales);

        boxNationales = new JComboBox();
        boxNationales.addItem("NATIONALES");
        p.add(boxNationales);

        voisin0.add(p,BorderLayout.CENTER);

        return voisin0;
    }

    private JFrame affichage1Voisin(){
        JFrame voisin1 = new JFrame();
        voisin1.setLayout(new BorderLayout());

        JMenuBar bar1voisin = new JMenuBar();

        JMenu afficherSeulementNoeuds1Voisin = new JMenu("Afficher seulement ");
        villes1Voisin = new JMenuItem("Villes reliées au point");
        villes1Voisin.addActionListener(this);

        centresLoisirs1Voisin = new JMenuItem("Centres de loisirs reliés au point");
        centresLoisirs1Voisin.addActionListener(this);

        restaurants1Voisin = new JMenuItem("Restaurants reliés au point");
        restaurants1Voisin.addActionListener(this);

        afficherSeulementNoeuds1Voisin.add(villes1Voisin); afficherSeulementNoeuds1Voisin.add(centresLoisirs1Voisin); afficherSeulementNoeuds1Voisin.add(restaurants1Voisin);

        bar1voisin.add(afficherSeulementNoeuds1Voisin);
        voisin1.setJMenuBar(bar1voisin);

        voisin1.setTitle("Affichage 1 distance");
        voisin1.setSize(1300, 700); //taille
        voisin1.setLocationRelativeTo(null); //centrage
        voisin1.setResizable(false); //non redimensionnable
        voisin1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fermeture de 1'appli.
        voisin1.setVisible(true);

        return voisin1;
    }


    public void setMessageConfirmation(String newMessageConfirmation){
        messageConfirmation.setText(newMessageConfirmation);
    }

    public String getPath()
    {
        String chemin="";
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Choix du fichier ...");
        if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            chemin = chooser.getSelectedFile().getAbsolutePath();
        }
        return chemin;
    }


    public static void main(String[] args) {
        GraphInterface test = new GraphInterface();
    }
}
