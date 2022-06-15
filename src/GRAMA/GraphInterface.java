package GRAMA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Classe qui gère les interfaces relatives à l'application
 * @author Giani VERRELLI - G2S2
 * @version 2.0
 */
public class GraphInterface extends JFrame implements ActionListener {

    private GRAPHMAP GMAP;
    private JButton boutonChargement;
    private JButton boutonChargementGraphFourni;
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
    private static String nodeString3;
    private static String nodeString4;
    private static JLabel plusMoinsOuverte;
    private static JLabel plusMoinsGastronomique;
    private static JLabel plusMoinsCulturelle;
    private static int nbVillesPoint1;
    private static int nbVillesPoint2;
    private static int nbLoisirsPoint1;
    private static int nbLoisirsPoint2;
    private static int nbRestoPoint1;
    private static int nbRestoPoint2;
    private static JComboBox villes2VoisinPoint1;
    private static JComboBox villes2VoisinPoint2;
    private static JComboBox loisirs2VoisinPoint1;
    private static JComboBox loisirs2VoisinPoint2;
    private static JComboBox restos2VoisinPoint1;
    private static JComboBox restos2VoisinPoint2;
    private static JButton retour0Voisin = new JButton("Retour");
    private static JButton retour1Voisin = new JButton("Retour");
    private static JButton retourVoisinage = new JButton("Retour");
    private static JButton retourComparaison = new JButton("Retour");
    private static JButton boutonAffichageGraph = new JButton("Afficher le graph entier");
    private static JButton quitter = new JButton("Quitter");
    private static GraphInterface test;
    private static JPanel panelGraph0Voisin;
    private static JPanel panel0Voisin;
    private static JPanel lastPanelUsed;
    private static JPanel panelSeulementVilles;
    private static JPanel panelSeulementLoisirs;
    private static JPanel panelSeulementRestos;
    private static JPanel panelSeulementAutoroutes;
    private static JPanel panelSeulementNationales;
    private static JPanel panelSeulementDepartementales;
    private static boolean isPanelGraph0VoisinComputed = false;
    private static boolean isPanelSeulementVillesComputed = false;
    private static boolean isPanelSeulementLoisirsComputed = false;
    private static boolean isPanelSeulementRestosComputed = false;
    private static boolean isPanelSeulementAutoroutesComputed = false;
    private static boolean isPanelSeulementNationalesComputed = false;
    private static boolean isPanelSeulementDepartementalesComputed = false;
    private static boolean isPanel1VoisinComputed = false;
    private static JFrame voisin1;
    private static JPanel panel1Voisin;
    private static JPanel panel1VoisinVilles;
    private static JPanel panel1VoisinLoisirs;
    private static JPanel panel1VoisinResto;
    private static boolean isPanel1VoisinVillesComputed = false;
    private static boolean isPanel1VoisinLoisirsComputed = false;
    private static boolean isPanel1VoisinRestoComputed = false;
    private static JFrame voisin0;
    private static JPanel voisinage;
    private static JPanel panelVoisinage;
    private static boolean isPanelVoisinageComputed = false;
    private static JFrame affichageVoisinage;
    private static JFrame affichageChoixPoint;
    private static JPanel panelChoixPoint;
    private static JFrame affichageComparaison;

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boutonChargement){
            s = new File(this.getPath());
            try {
                map = GMAP.lectureFichier(s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(map != null) {
                setMessageConfirmation("Chargement réussi !");
                bouton0Distance.setEnabled(true);
                if(isPanel1VoisinComputed)
                    bouton1Distance.setText("Affichage 1 distance (redémarrer le graph pour changer de point)");
                else
                    bouton1Distance.setEnabled(true);
                if(isPanelVoisinageComputed)
                    boutonVoisinage.setText("Voisinage à 2 distance (redémarrer le graph pour changer de point)");
                else
                    boutonVoisinage.setEnabled(true);
                boutonComparaison.setEnabled(true);
            }
            else {
                setMessageConfirmation("Chargement échoué !");
                bouton0Distance.setEnabled(false);
                bouton1Distance.setEnabled(false);
                boutonComparaison.setEnabled(false);
                boutonVoisinage.setEnabled(false);
            }
        }
        if(e.getSource() == boutonChargementGraphFourni){
            s = new File("src/testgraph2.csv");
            try {
                map = GMAP.lectureFichier(s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(!map.isEmpty()) {
                setMessageConfirmation("Chargement réussi !");
                bouton0Distance.setEnabled(true);
                if(isPanel1VoisinComputed)
                    bouton1Distance.setText("Affichage 1 distance (redémarrer le graph pour changer de point");
                else
                    bouton1Distance.setEnabled(true);
                if(isPanelVoisinageComputed)
                    boutonVoisinage.setText("Voisinage à 2 distance (redémarrer le graph pour changer de point)");
                else
                    boutonVoisinage.setEnabled(true);
                boutonComparaison.setEnabled(true);

            }
            else
                setMessageConfirmation("Chargement échoué !");
        }

        if(e.getSource() == bouton0Distance) {
            setVisible(false);
            affichage0Voisin();

            if(!isPanelGraph0VoisinComputed) {
                panelGraph0Voisin = GMAP.affichageNodeGraph(map);
                GMAP.affichageEdgeGraph(map);
                lastPanelUsed = panelGraph0Voisin;
                panel0Voisin.add(panelGraph0Voisin);
                panel0Voisin.updateUI();
                isPanelGraph0VoisinComputed = true;
            }
            else{
                lastPanelUsed = panelGraph0Voisin;
                panel0Voisin.add(panelGraph0Voisin);
                panel0Voisin.updateUI();
            }

            listerParVilles.setEnabled(false);
            listerParLoisirs.setEnabled(false);
            listerParResto.setEnabled(false);
            listerParAutoroutes.setEnabled(false);
            listerParDepartementales.setEnabled(false);
            listerParNationales.setEnabled(false);

            nombreAutoroutes.setEnabled(false);
            nombreDepartementales.setEnabled(false);
            nombreNationales.setEnabled(false);
        }

        if(e.getSource() == boutonAffichageGraph){
            panel0Voisin.remove(lastPanelUsed);
            lastPanelUsed = panelGraph0Voisin;
            panel0Voisin.add(panelGraph0Voisin);
            panel0Voisin.updateUI();
        }

        if(e.getSource() == villes){
            if(!isPanelSeulementVillesComputed){
                panelSeulementVilles = GMAP.affichageSeulementVilles(map);
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementVilles;
                panel0Voisin.add(panelSeulementVilles);
                panel0Voisin.updateUI();
                isPanelSeulementVillesComputed = true;
            }
            else{
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementVilles;
                panel0Voisin.add(panelSeulementVilles);
                panel0Voisin.updateUI();
            }
            listerParVilles.setEnabled(true);

        }
        if(e.getSource() == centresLoisirs){
            if(!isPanelSeulementLoisirsComputed) {
                panelSeulementLoisirs = GMAP.affichageSeulementLoisirs(map);
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementLoisirs;
                panel0Voisin.add(panelSeulementLoisirs);
                panel0Voisin.updateUI();
                isPanelSeulementLoisirsComputed = true;
            }
            else{
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementLoisirs;
                panel0Voisin.add(panelSeulementLoisirs);
                panel0Voisin.updateUI();
            }
            listerParLoisirs.setEnabled(true);
        }
        if(e.getSource() == restaurants){
            if(!isPanelSeulementRestosComputed) {
                panelSeulementRestos = GMAP.affichageSeulementRestaurants(map);
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementRestos;
                panel0Voisin.add(panelSeulementRestos);
                panel0Voisin.updateUI();
                isPanelSeulementRestosComputed = true;
            }
            else{
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementRestos;
                panel0Voisin.add(panelSeulementRestos);
                panel0Voisin.updateUI();
            }
            listerParResto.setEnabled(true);
        }
        if(e.getSource() == autoroutes){
            if(!isPanelSeulementAutoroutesComputed) {
                panelSeulementAutoroutes = GMAP.affichageNodeGraphAutoroutes(map);
                GMAP.affichageSeulementAutoroutes(map);
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementAutoroutes;
                panel0Voisin.add(panelSeulementAutoroutes);
                panel0Voisin.updateUI();
                isPanelSeulementAutoroutesComputed = true;
            }
            else{
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementAutoroutes;
                panel0Voisin.add(panelSeulementAutoroutes);
                panel0Voisin.updateUI();
            }
            nombreAutoroutes.setEnabled(true);
            listerParAutoroutes.setEnabled(true);
        }
        if(e.getSource() == nationales){
            if(!isPanelSeulementNationalesComputed) {
                panelSeulementNationales = GMAP.affichageNodeGraphNationales(map);
                GMAP.affichageSeulementNationales(map);
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementNationales;
                panel0Voisin.add(panelSeulementNationales);
                panel0Voisin.updateUI();
                isPanelSeulementNationalesComputed = true;
            }
            else{
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementNationales;
                panel0Voisin.add(panelSeulementNationales);
                panel0Voisin.updateUI();
            }
            nombreNationales.setEnabled(true);
            listerParNationales.setEnabled(true);
        }
        if(e.getSource() == departementales){
            if(!isPanelSeulementDepartementalesComputed){
                panelSeulementDepartementales = GMAP.affichageNodeGraphDepartementales(map);
                GMAP.affichageSeulementDepartementales(map);
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementDepartementales;
                panel0Voisin.add(panelSeulementDepartementales);
                panel0Voisin.updateUI();
                isPanelSeulementDepartementalesComputed = true;
            }
            else{
                panel0Voisin.remove(lastPanelUsed);
                lastPanelUsed = panelSeulementDepartementales;
                panel0Voisin.add(panelSeulementDepartementales);
                panel0Voisin.updateUI();
            }
            nombreDepartementales.setEnabled(true);
            listerParDepartementales.setEnabled(true);
        }

        if(e.getSource() == listerParVilles){
            ArrayList<String> list = GMAP.getVilles();
            for(String i : list)
                boxVilles.addItem(i);
            boxVilles.setVisible(true);
        }
        if(e.getSource() == listerParLoisirs){
            ArrayList<String> list = GMAP.getLoisirs();
            for(String i : list)
                boxLoisirs.addItem(i);
            boxLoisirs.setVisible(true);
        }
        if(e.getSource() == listerParResto){
            ArrayList<String> list = GMAP.getResto();
            for(String i : list)
                boxResto.addItem(i);
            boxResto.setVisible(true);
        }
        if(e.getSource() == listerParAutoroutes){
            ArrayList<String> list = GMAP.getAutoroutes();
            for(String i : list)
                boxAutoroutes.addItem(i);
            boxAutoroutes.setVisible(true);
        }
        if(e.getSource() == listerParDepartementales){
            ArrayList<String> list = GMAP.getDepartementales();
            for(String i : list)
                boxDepartementales.addItem(i);
            boxDepartementales.setVisible(true);
        }
        if(e.getSource() == listerParNationales){
            ArrayList<String> list = GMAP.getNationales();
            for(String i : list)
                boxNationales.addItem(i);
            boxNationales.setVisible(true);
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

        if(e.getSource() == retour0Voisin){
            voisin0.setVisible(false);
            test = new GraphInterface();
        }


        if(e.getSource() == bouton1Distance){
            setVisible(false);
            affichage1Voisin();

            if(!isPanelGraph0VoisinComputed) {
                panelGraph0Voisin = GMAP.affichageNodeGraph(map);
                GMAP.affichageEdgeGraph(map);
                lastPanelUsed = panelGraph0Voisin;
                voisin1.add(panelGraph0Voisin);
                isPanelGraph0VoisinComputed = true;
            }
            else{
                lastPanelUsed = panelGraph0Voisin;
                voisin1.add(panelGraph0Voisin);
            }
            do {
                nodeString = JOptionPane.showInputDialog("Choisissez un point du graph (liste disponible dans l'analyse 0 distance)");
                if(nodeString == null){
                    voisin1.dispose();
                    test = new GraphInterface();
                }
                else {
                    if (GMAP.getNode(nodeString) == null)
                        JOptionPane.showMessageDialog(null, "Le point " + nodeString + " n'existe pas sur le graph !");
                }
            }while(GMAP.getNode(nodeString) == null && nodeString != null);
            if(nodeString != null) {
                try {
                    map = GMAP.lectureFichier(s);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (!isPanel1VoisinComputed) {
                    panel1Voisin = GMAP.affichage1Voisin(GMAP.getNode(nodeString), map);
                    voisin1.remove(lastPanelUsed);
                    lastPanelUsed = panel1Voisin;
                    voisin1.add(panel1Voisin);
                    isPanel1VoisinComputed = true;
                    panel1Voisin.updateUI();
                } else {
                    lastPanelUsed = panel1Voisin;
                    voisin1.add(panel1Voisin);
                    panel1Voisin.updateUI();
                }
            }
        }

        if(e.getSource() == villes1Voisin){
            if(!isPanel1VoisinVillesComputed) {
                panel1VoisinVilles = GMAP.affichage1VoisinVilles(GMAP.getNode(nodeString), map);
                voisin1.remove(lastPanelUsed);
                lastPanelUsed = panel1VoisinVilles;
                voisin1.add(panel1VoisinVilles);
                panel1VoisinVilles.updateUI();
                isPanel1VoisinVillesComputed = true;
            }
            else{
                voisin1.remove(lastPanelUsed);
                lastPanelUsed = panel1VoisinVilles;
                voisin1.add(panel1VoisinVilles);
                panel1VoisinVilles.updateUI();
            }
        }
        if(e.getSource() == centresLoisirs1Voisin){
            if(!isPanel1VoisinLoisirsComputed) {
               panel1VoisinLoisirs = GMAP.affichage1VoisinLoisirs(GMAP.getNode(nodeString), map);
               voisin1.remove(lastPanelUsed);
               lastPanelUsed = panel1VoisinLoisirs;
               voisin1.add(panel1VoisinLoisirs);
               panel1VoisinLoisirs.updateUI();
               isPanel1VoisinLoisirsComputed = true;
            }
            else{
                voisin1.remove(lastPanelUsed);
                lastPanelUsed = panel1VoisinLoisirs;
                voisin1.add(panel1VoisinLoisirs);
                panel1VoisinLoisirs.updateUI();
            }
        }
        if(e.getSource() == restaurants1Voisin){
            if(!isPanel1VoisinRestoComputed) {
                panel1VoisinResto = GMAP.affichage1VoisinResto(GMAP.getNode(nodeString), map);
                voisin1.remove(lastPanelUsed);
                lastPanelUsed = panel1VoisinResto;
                voisin1.add(panel1VoisinResto);
                panel1VoisinResto.updateUI();
                isPanel1VoisinRestoComputed = true;
            }
            else{
                voisin1.remove(lastPanelUsed);
                lastPanelUsed = panel1VoisinResto;
                voisin1.add(panel1VoisinResto);
                panel1VoisinResto.updateUI();
            }
        }

        if(e.getSource() == retour1Voisin){
            voisin1.setVisible(false);
            test = new GraphInterface();
        }

        if(e.getSource() == boutonVoisinage) {
            setVisible(false);
            affichageVoisinage();

            if (!isPanelGraph0VoisinComputed) {
                panelGraph0Voisin = GMAP.affichageNodeGraph(map);
                GMAP.affichageEdgeGraph(map);
                lastPanelUsed = panelGraph0Voisin;
                voisinage.add(panelGraph0Voisin);
                isPanelGraph0VoisinComputed = true;
                voisinage.updateUI();
            }
            else{
                lastPanelUsed = panelGraph0Voisin;
                voisinage.add(panelGraph0Voisin);
                voisinage.updateUI();
                panelGraph0Voisin.updateUI();
            }
            do {
                nodeString = JOptionPane.showInputDialog("Choisissez le premier point parmi les points du graph (liste disponible dans l'analyse 0 distance)");
                if(nodeString == null){
                    affichageVoisinage.dispose();
                    test = new GraphInterface();
                }
                else {
                    if (GMAP.getNode(nodeString) == null)
                        JOptionPane.showMessageDialog(null, "Le point " + nodeString + " n'existe pas sur le graph !");
                }
            } while (GMAP.getNode(nodeString) == null && nodeString != null);

            if(nodeString != null) {
                do {
                    nodeString2 = JOptionPane.showInputDialog("Choisissez le deuxième point parmi les points du graph (liste disponible dans l'analyse 0 distance)");
                    if(nodeString2 == null){
                        affichageVoisinage.dispose();
                        test = new GraphInterface();
                    }
                    else {
                        if (GMAP.getNode(nodeString2) == null)
                            JOptionPane.showMessageDialog(null, "Le point " + nodeString2 + " n'existe pas sur le graph !");
                        if (nodeString.equals(nodeString2))
                            JOptionPane.showMessageDialog(null, "Les points sont identiques !");
                    }
                } while (nodeString2 != null && GMAP.getNode(nodeString2) == null || nodeString.equals(nodeString2));

                if(nodeString2 != null) {
                    if (!isPanelVoisinageComputed) {
                        panelVoisinage = GMAP.affichage2Distance(GMAP.getNode(nodeString), GMAP.getNode(nodeString2), map);
                        voisinage.remove(lastPanelUsed);
                        lastPanelUsed = panelVoisinage;
                        voisinage.add(panelVoisinage);
                        panelVoisinage.updateUI();
                        isPanelVoisinageComputed = true;
                    } else {
                        voisinage.remove(lastPanelUsed);
                        lastPanelUsed = panelVoisinage;
                        voisinage.add(panelVoisinage);
                        panelVoisinage.updateUI();
                    }
                }
            }
        }
        if(e.getSource() == retourVoisinage){
            affichageVoisinage.setVisible(false);
            test = new GraphInterface();
        }




        if(e.getSource() == boutonComparaison) {
            setVisible(false);
            comparaisonChoixPoint();

            if (!isPanelGraph0VoisinComputed) {
                panelGraph0Voisin = GMAP.affichageNodeGraph(map);
                GMAP.affichageEdgeGraph(map);
                lastPanelUsed = panelGraph0Voisin;
                affichageChoixPoint.add(panelGraph0Voisin);
                isPanelGraph0VoisinComputed = true;
                panelGraph0Voisin.updateUI();
            } else {
                lastPanelUsed = panelGraph0Voisin;
                affichageChoixPoint.add(panelGraph0Voisin);
                panelGraph0Voisin.updateUI();
            }
            do {
                nodeString3 = JOptionPane.showInputDialog("Choisissez la première ville parmi les points du graph (liste disponible dans l'analyse 0 distance)");
                if (nodeString3 == null) {
                    affichageChoixPoint.dispose();
                    test = new GraphInterface();
                    break;
                } else {
                    if (GMAP.getNode(nodeString3) == null)
                        JOptionPane.showMessageDialog(null, "Le point " + nodeString3 + " n'existe pas sur le graph !");
                    if (!nodeString3.startsWith("V"))
                        JOptionPane.showMessageDialog(null, "Le point " + nodeString3 + " n'est pas une ville !");
                }
            } while (nodeString3 != null && GMAP.getNode(nodeString3) == null || !nodeString3.startsWith("V"));
            if(nodeString3 != null) {
                do {
                    nodeString4 = JOptionPane.showInputDialog("Choisissez la deuxième ville parmi les points du graph (liste disponible dans l'analyse 0 distance)");
                    if (nodeString4 == null) {
                        affichageChoixPoint.dispose();
                        test = new GraphInterface();
                        break;
                    } else {
                        if (GMAP.getNode(nodeString4) == null)
                            JOptionPane.showMessageDialog(null, "Le point " + nodeString4 + " n'existe pas sur le graph !");
                        if (nodeString3.equals(nodeString4))
                            JOptionPane.showMessageDialog(null, "Les points sont identiques !");
                        if (!nodeString4.startsWith("V"))
                            JOptionPane.showMessageDialog(null, "Le point " + nodeString4 + " n'est pas une ville !");
                    }
                } while (nodeString4 != null && GMAP.getNode(nodeString4) == null || nodeString3.equals(nodeString4) || !nodeString4.startsWith("V"));

                if (nodeString4 != null) {
                    affichageChoixPoint.setVisible(false);
                    ArrayList<String> voisin2DistancePoint1 = new ArrayList<>();
                    ArrayList<String> voisin2DistancePoint2 = new ArrayList<>();
                    voisin2DistancePoint1 = GMAP.getVoisin2Distance(GMAP.getNode(nodeString3), map);
                    voisin2DistancePoint2 = GMAP.getVoisin2Distance(GMAP.getNode(nodeString4), map);

                    affichageComparaison();
                    for (Object i : voisin2DistancePoint1) {
                        if (i.toString().startsWith("V")) {
                            nbVillesPoint1++;
                            villes2VoisinPoint1.addItem(i.toString());
                        } else if (i.toString().startsWith("L")) {
                            nbLoisirsPoint1++;
                            loisirs2VoisinPoint1.addItem(i.toString());
                        } else if (i.toString().startsWith("R")) {
                            nbRestoPoint1++;
                            restos2VoisinPoint1.addItem(i.toString());
                        }
                    }
                    for (Object j : voisin2DistancePoint2) {
                        if (j.toString().startsWith("V")) {
                            nbVillesPoint2++;
                            villes2VoisinPoint2.addItem(j.toString());
                        } else if (j.toString().startsWith("L")) {
                            nbLoisirsPoint2++;
                            loisirs2VoisinPoint2.addItem(j.toString());
                        } else if (j.toString().startsWith("R")) {
                            nbRestoPoint2++;
                            restos2VoisinPoint2.addItem(j.toString());
                        }
                    }
                    if (nbVillesPoint1 > nbVillesPoint2)
                        plusMoinsOuverte.setText("La ville " + nodeString3 + " est plus ouverte que la ville " + nodeString4);
                    else if (nbVillesPoint1 < nbVillesPoint2)
                        plusMoinsOuverte.setText("La ville " + nodeString3 + " est moins ouverte que la ville " + nodeString4);
                    else
                        plusMoinsOuverte.setText("La ville " + nodeString3 + " est aussi ouverte que la ville " + nodeString4);

                    if (nbLoisirsPoint1 > nbLoisirsPoint2)
                        plusMoinsCulturelle.setText("La ville " + nodeString3 + " est plus culturelle que la ville " + nodeString4);
                    else if (nbLoisirsPoint1 < nbLoisirsPoint2)
                        plusMoinsCulturelle.setText("La ville " + nodeString3 + " est moins culturelle que la ville " + nodeString4);
                    else
                        plusMoinsCulturelle.setText("La ville " + nodeString3 + " est aussi culturelle que la ville " + nodeString4);

                    if (nbRestoPoint1 > nbRestoPoint2)
                        plusMoinsGastronomique.setText("La ville " + nodeString3 + " est plus gastronomique que la ville " + nodeString4);
                    else if (nbRestoPoint1 < nbRestoPoint2)
                        plusMoinsGastronomique.setText("La ville " + nodeString3 + " est moins gastronomique que la ville " + nodeString4);
                    else
                        plusMoinsGastronomique.setText("La ville " + nodeString3 + " est aussi gastronomique que la ville " + nodeString4);
                }
            }
        }
        if(e.getSource() == retourComparaison){
            affichageComparaison.setVisible(false);

            nbVillesPoint1 = 0;
            nbVillesPoint2 = 0;
            nbLoisirsPoint1 = 0;
            nbLoisirsPoint2 = 0;
            nbRestoPoint1 = 0;
            nbRestoPoint2 = 0;


            test = new GraphInterface();
        }

        if(e.getSource() == quitter) {
            System.exit(0);
        }
    }

    /**
     * Permet de générer l'application
     */
    public GraphInterface() {
        super();
        GMAP = new GRAPHMAP();
        constrFen();
        bouton0Distance.setEnabled(false);
        bouton1Distance.setEnabled(false);
        boutonComparaison.setEnabled(false);
        boutonVoisinage.setEnabled(false);
    }

    /**
     * Gère l'affichage de la fenêtre principale
     */
    private void constrFen(){
        setTitle("Graph Map Analysis"); //mise en oeuvze du titre
        setSize(1000, 500); //taille
        setLocationRelativeTo(null); //centrage
        setResizable(false); //non redimensionnable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Eermeture de 1'appli.
        setContentPane(constrPan()); //méthode de construction du content pane
        setVisible(true);
        quitter.addActionListener(this);
    }

    /**
     * @return JPanel du menu principal
     */
    private JPanel constrPan(){
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));

        JLabel titre = new JLabel("Choisissez votre opération : ");
        p.add(titre);
        titre.setAlignmentX(CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());

        JPanel chargementFichier = new JPanel();
        chargementFichier.setLayout(new FlowLayout());

        boutonChargement = new JButton("Chargement du GRAMA.GRAPHMAP");
        boutonChargement.addActionListener(this);
        chargementFichier.add(boutonChargement);

        boutonChargementGraphFourni = new JButton("Chargement avec GRAMA.GRAPHMAP fourni");
        boutonChargementGraphFourni.addActionListener(this);
        chargementFichier.add(boutonChargementGraphFourni);

        p.add(chargementFichier);

        messageConfirmation = new JLabel("");
        p.add(messageConfirmation);
        messageConfirmation.setAlignmentX(CENTER_ALIGNMENT);

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
        boutonComparaison.addActionListener(this);
        p.add(boutonComparaison);
        boutonComparaison.setAlignmentX(CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());

        p.add(quitter);
        quitter.setAlignmentX(CENTER_ALIGNMENT);

        p.add(Box.createVerticalGlue());

        return p;
    }

    /**
     * @return JFrame du menu affichage0Voisin
     */
    private JFrame affichage0Voisin(){
        voisin0 = new JFrame();
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
        voisin0.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Fermeture de 1'appli.
        voisin0.setVisible(true);

        panel0Voisin = new JPanel();
        panel0Voisin.setLayout(new FlowLayout());

        boxVilles = new JComboBox();
        boxVilles.addItem("LISTE DES VILLES");
        boxVilles.setVisible(false);
        panel0Voisin.add(boxVilles);

        boxLoisirs = new JComboBox();
        boxLoisirs.addItem("LISTE DES CENTRES DE LOISIRS");
        boxLoisirs.setVisible(false);
        panel0Voisin.add(boxLoisirs);

        boxResto = new JComboBox();
        boxResto.addItem("LISTE DES RESTAURANTS");
        boxResto.setVisible(false);
        panel0Voisin.add(boxResto);

        boxAutoroutes = new JComboBox();
        boxAutoroutes.addItem("LISTE DES AUTOROUTES");
        boxAutoroutes.setVisible(false);
        panel0Voisin.add(boxAutoroutes);

        boxDepartementales = new JComboBox();
        boxDepartementales.addItem("LISTE DES DEPARTEMENTALES");
        boxDepartementales.setVisible(false);
        panel0Voisin.add(boxDepartementales);

        boxNationales = new JComboBox();
        boxNationales.addItem("LISTE DES NATIONALES");
        boxNationales.setVisible(false);
        panel0Voisin.add(boxNationales);

        voisin0.add(panel0Voisin,BorderLayout.CENTER);

        voisin0.add(boutonAffichageGraph,BorderLayout.NORTH);
        boutonAffichageGraph.addActionListener(this);

        voisin0.add(retour0Voisin,BorderLayout.SOUTH);
        retour0Voisin.addActionListener(this);


        return voisin0;
    }

    /**
     * @return JFrame du menu affichage1Voisin
     */
    private JFrame affichage1Voisin(){
        voisin1 = new JFrame();
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
        voisin1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Fermeture de 1'appli.
        voisin1.setVisible(true);

        voisin1.add(retour1Voisin,BorderLayout.SOUTH);
        retour1Voisin.addActionListener(this);

        return voisin1;
    }

    /**
     * @return JFrame du menu affichageVoisinage
     */
    private JFrame affichageVoisinage(){
        affichageVoisinage = new JFrame();
        voisinage = new JPanel(new BorderLayout());

        voisinage.add(retourVoisinage,BorderLayout.SOUTH);
        retourVoisinage.addActionListener(this);

        affichageVoisinage.add(voisinage);

        affichageVoisinage.setTitle("Affichage voisinage 2 distance");
        affichageVoisinage.setSize(1300, 700); //taille
        affichageVoisinage.setLocationRelativeTo(null); //centrage
        affichageVoisinage.setResizable(false); //non redimensionnable
        affichageVoisinage.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Fermeture de 1'appli.
        affichageVoisinage.setVisible(true);

        return affichageVoisinage;
    }

    /**
     * @return JFrame du menu affichageComparaison
     */
    private JFrame affichageComparaison(){
        affichageComparaison = new JFrame();

        JPanel comparaison = new JPanel();
        comparaison.setLayout(new BoxLayout(comparaison,BoxLayout.Y_AXIS));

        comparaison.add(Box.createVerticalGlue());

        plusMoinsOuverte = new JLabel("Gneugneu");
        comparaison.add(plusMoinsOuverte);
        plusMoinsOuverte.setAlignmentX(CENTER_ALIGNMENT);

        JPanel showVilles = new JPanel();
        showVilles.setLayout(new FlowLayout());

        villes2VoisinPoint1 = new JComboBox<>();
        villes2VoisinPoint1.addItem("Villes à 2 distance du point " + nodeString3);

        villes2VoisinPoint2 = new JComboBox<>();
        villes2VoisinPoint2.addItem("Villes à 2 distance du point " + nodeString4);

        showVilles.add(villes2VoisinPoint1); showVilles.add(villes2VoisinPoint2);
        showVilles.setAlignmentX(CENTER_ALIGNMENT);
        comparaison.add(showVilles);

        comparaison.add(Box.createVerticalGlue());

        plusMoinsCulturelle = new JLabel("Gneugneu2");
        comparaison.add(plusMoinsCulturelle);
        plusMoinsCulturelle.setAlignmentX(CENTER_ALIGNMENT);

        JPanel showLoisirs = new JPanel();
        showLoisirs.setLayout(new FlowLayout());

        loisirs2VoisinPoint1 = new JComboBox<>();
        loisirs2VoisinPoint1.addItem("Centres de loisirs à 2 distance du point " + nodeString3);

        loisirs2VoisinPoint2 = new JComboBox<>();
        loisirs2VoisinPoint2.addItem("Centres de loisirs à 2 distance du point " + nodeString4);

        showLoisirs.add(loisirs2VoisinPoint1); showLoisirs.add(loisirs2VoisinPoint2);
        showLoisirs.setAlignmentX(CENTER_ALIGNMENT);
        comparaison.add(showLoisirs);

        comparaison.add(Box.createVerticalGlue());

        plusMoinsGastronomique = new JLabel("Gneugneu3");
        comparaison.add(plusMoinsGastronomique);
        plusMoinsGastronomique.setAlignmentX(CENTER_ALIGNMENT);

        JPanel showRestos = new JPanel();
        showRestos.setLayout(new FlowLayout());

        restos2VoisinPoint1 = new JComboBox<>();
        restos2VoisinPoint1.addItem("Restaurants à 2 distance du point " + nodeString3);

        restos2VoisinPoint2 = new JComboBox<>();
        restos2VoisinPoint2.addItem("Restaurants à 2 distance du point " + nodeString4);

        showRestos.add(restos2VoisinPoint1); showRestos.add(restos2VoisinPoint2);
        showRestos.setAlignmentX(CENTER_ALIGNMENT);
        comparaison.add(showRestos);

        comparaison.add(Box.createVerticalGlue());

        comparaison.add(retourComparaison);
        retourComparaison.setAlignmentX(CENTER_ALIGNMENT);
        retourComparaison.addActionListener(this);

        affichageComparaison.setTitle("Affichage comparaison à 2 distance");
        affichageComparaison.setSize(1300, 700); //taille
        affichageComparaison.setLocationRelativeTo(null); //centrage
        affichageComparaison.setResizable(false); //non redimensionnable
        affichageComparaison.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Fermeture de 1'appli.
        affichageComparaison.setVisible(true);

        affichageComparaison.add(comparaison);

        return affichageComparaison;
    }

    /**
     * @return JFrame du menu choix des points
     */
    private JFrame comparaisonChoixPoint(){
        affichageChoixPoint = new JFrame();
        panelChoixPoint = new JPanel();

        affichageChoixPoint.setTitle("Affichage comparaison 2 distance - choix des points");
        affichageChoixPoint.setSize(1000, 600); //taille
        affichageChoixPoint.setLocationRelativeTo(null); //centrage
        affichageChoixPoint.setResizable(false); //non redimensionnable
        affichageChoixPoint.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Fermeture de 1'appli.
        affichageChoixPoint.setVisible(true);

        return affichageChoixPoint;
    }


    /**
     * @param newMessageConfirmation
     * Change le texte de confirmation du chargement du graph
     */
    public void setMessageConfirmation(String newMessageConfirmation){
        messageConfirmation.setText(newMessageConfirmation);
    }

    /**
     * @return Le chemin absolu du fichier choisi dans l'interface graphique
     */
    public String getPath() {
        FileDialog fd = new FileDialog(new Frame(), "Sélectionnez votre fichier...", FileDialog.LOAD);
        String nomFic = new String();
        fd.setFile("*.csv");
        fd.setVisible(true);
        if(fd.getFile() == null){
            nomFic = "Annuler";
        }
        else {
            nomFic = ((fd.getDirectory()).concat(fd.getFile()));
        }
        return nomFic;
    }
}
