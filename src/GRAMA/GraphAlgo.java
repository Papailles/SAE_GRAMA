package GRAMA;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.swing_viewer.DefaultView;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Classe qui gère les algorithmes relatifs à l'application
 * @author Giani VERRELLI - G2S2
 * @version 2.0
 */
public class GraphAlgo {
    private static Graph graph = new MultiGraph("GraphMap"); //Initialisation d'un graphe qui peut avoir plusieurs voisins
    private static Graph graph1Voisin = new MultiGraph("GraphMap1Voisin");
    private static Graph graph2Voisin = new MultiGraph("GraphMap2Voisin");
    private static Graph graphVilles = new MultiGraph("GraphMapVilles");
    private static Graph graphLoisirs = new MultiGraph("GraphMapLoisirs");
    private static Graph graphResto = new MultiGraph("GraphMapResto");
    private static Graph graphAutoroutes = new MultiGraph("GraphMapAutoroutes");
    private static Graph graphNationales = new MultiGraph("GraphMapNationales");
    private static Graph graphDepartementales = new MultiGraph("GraphMapDepartementales");
    private static Graph graph1VoisinVille = new MultiGraph("GraphMap1VoisinVille");
    private static Graph graph1VoisinLoisirs = new MultiGraph("GraphMap1VoisinLoisirs");
    private static Graph graph1VoisinResto = new MultiGraph("GraphMap1VoisinResto");
    private static SpriteManager sman = new SpriteManager(graph); //Objet qui permet d'ajouter un sprite à un élément (node, edge...)
    private static SpriteManager sman1voisin = new SpriteManager(graph1Voisin);
    private static SpriteManager sman2voisin = new SpriteManager(graph2Voisin);
    private static SpriteManager smanVilles = new SpriteManager(graphVilles);
    private static SpriteManager smanLoisirs = new SpriteManager(graphLoisirs);
    private static SpriteManager smanResto = new SpriteManager(graphResto);
    private static SpriteManager smanAutoroutes = new SpriteManager(graphAutoroutes);
    private static SpriteManager smanNationales = new SpriteManager(graphNationales);
    private static SpriteManager smanDepartementales = new SpriteManager(graphDepartementales);
    private static SpriteManager sman1VoisinVilles = new SpriteManager(graph1VoisinVille);
    private static SpriteManager sman1VoisinLoisirs = new SpriteManager(graph1VoisinLoisirs);
    private static SpriteManager sman1VoisinResto = new SpriteManager(graph1VoisinResto);

    /**
     * Permet de lire le fichier passé en paramètre et de remplir la LinkedHashMap (dictionnaire ordonné)
     *
     * @param s : le fichier csv contenant le graphe correspondant à au testgraph2 présent dans le projet (remplacement des "::" par ">")
     * @return LinkedHashMap remplie avec les points du graphe
     * @throws IOException
     * @throws FileNotFoundException
     */
    public LinkedHashMap lectureFichier(File s) throws IOException, FileNotFoundException {
        System.setProperty("org.graphstream.ui", "swing");
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph.setAttribute("ui.stylesheet", "url('stylesheet')"); //On précise la feuille de style du graphe
        String filename = s.toString();

        if (filename.endsWith(".csv")) {
            LinkedHashMap map = new LinkedHashMap();
            BufferedReader line = new BufferedReader(new FileReader(s));
            String strng = line.readLine(); //Mise en place du lecteur de fichier
            String[] split;
            do {
                split = strng.split(":");
                map.put(split[0], split[1]);
            } while (((strng = line.readLine()) != null));  //tant que le fichier n'est pas vide, on place le point d'origine (le premier de la ligne) en clé et ses voisins en valeur de la map
            return map;
        } else
            return null;
    }


    /**
     * Permet d'afficher les points du graphe (Ville, Restaurant, Loisir)
     *
     * @param map -> LinkedHashMap remplie par lecturefichier()
     */
    public JPanel affichageNodeGraph(LinkedHashMap map) {

        Node node;

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Sprite sprite;
        String mot = "";
        for (Object i : map.keySet()) { //On récupère toutes les clés
            mot = i.toString();
            graph.addNode(mot); //On ajoute le point qui correspond à la clé
            node = graph.getNode(mot);
            if(mot.startsWith("V"))
                node.setAttribute("ui.color",Color.BLUE);
            else if(mot.startsWith("L"))
                node.setAttribute("ui.color",Color.RED);
            else if(mot.startsWith("R"))
                node.setAttribute("ui.color",Color.GREEN);
            sprite = sman.addSprite(mot); //On ajoute un "sprite" (ici une zone de texte)...
            sprite.setAttribute("ui.label", mot); //Le label du point est donc son nom
            sprite.attachToNode(mot); //... qu'on vient "attacher" au point créé
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * Permet d'afficher les liens entre les points du graphe
     *
     * @param map -> LinkedHashMap remplie par lecturefichier()
     */
    public void affichageEdgeGraph(LinkedHashMap map) {
        String string = "";
        String[] splitEdges;
        String[] splitter;
        Sprite sprite2;
        for (Object i : map.keySet()) { //On récupère les clés de la map
            string = (String) map.get(i); //On récupère les valeurs correspondant à la clé
            splitEdges = string.split(";"); //Puis on séquence les différents voisins du point
            for (String voisin : splitEdges) {   //Pour chaque voisin du point clé...
                splitter = voisin.split(">"); //On sépare le type de route et le voisin
                Node n1 = graph.getNode((String) i); //On récupère le point clé dans une variable node
                if (!n1.hasEdgeToward(splitter[1])) {    //On vérifie si le point clé à déjà un lien avec son voisin
                    graph.addEdge(splitter[0] + "--" + (String) i + "--" + splitter[1], (String) i, splitter[1]); //Si non, on crée un lien entre le point clé et le voisin
                    sprite2 = sman.addSprite((String) i + "--" + splitter[1]); //On ajoute une zone de texte au lien
                    sprite2.setAttribute("ui.label", (splitter[0])); //Qu'on appelle par le type de route et la distance
                    sprite2.attachToEdge(splitter[0] + "--" + (String) i + "--" + splitter[1]); //Qu'on vient ensuite attacher au lien
                    sprite2.setPosition(0.5); //On place la zone de texte au milieu du lien
                }
            }
        }
    }


    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     * @return Un jpanel contenant le graphe avec seulement les villes
     */
    public JPanel affichageSeulementVilles(LinkedHashMap map) {
        graphVilles.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graphVilles, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Node node;
        Sprite sprite;
        for (Object i : map.keySet()) {
            node = graph.getNode(i.toString());
            if (node.getId().startsWith("V")) {
                graphVilles.addNode(node.toString());
                sprite = smanVilles.addSprite(node.toString()); //On ajoute un "sprite" (ici une zone de texte)...
                sprite.setAttribute("ui.label", node.toString()); //Le label du point est donc son nom
                sprite.attachToNode(node.toString()); //... qu'on vient "attacher" au point créé
            }
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     * @return jpanel contenant le graph avec seulement les centres de loisirs
     */
    public JPanel affichageSeulementLoisirs(LinkedHashMap map) {
        graphLoisirs.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graphLoisirs, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Node node;
        Sprite sprite;
        for (Object i : map.keySet()) {
            node = graph.getNode(i.toString());
            if (node.getId().startsWith("L")) {
                graphLoisirs.addNode(node.toString());
                sprite = smanLoisirs.addSprite(node.toString()); //On ajoute un "sprite" (ici une zone de texte)...
                sprite.setAttribute("ui.label", node.toString()); //Le label du point est donc son nom
                sprite.attachToNode(node.toString()); //... qu'on vient "attacher" au point créé
            }
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     * @return JPanel contenant le graph avec seulement des restaurants
     */
    public JPanel affichageSeulementRestaurants(LinkedHashMap map) {
        graphResto.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graphResto, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Node node;
        Sprite sprite;
        for (Object i : map.keySet()) {
            node = graph.getNode(i.toString());
            if (node.getId().startsWith("R")) {
                graphResto.addNode(node.toString());
                sprite = smanResto.addSprite(node.toString()); //On ajoute un "sprite" (ici une zone de texte)...
                sprite.setAttribute("ui.label", node.toString()); //Le label du point est donc son nom
                sprite.attachToNode(node.toString()); //... qu'on vient "attacher" au point créé
            }
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     * @return un jpanel contenant tout les points, doit fonctionner avec affichageSeulementAutoroutes()
     */
    public JPanel affichageNodeGraphAutoroutes(LinkedHashMap map) {
        graphAutoroutes.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graphAutoroutes, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Sprite sprite;
        String mot = "";
        for (Object i : map.keySet()) { //On récupère toutes les clés
            mot = i.toString();
            graphAutoroutes.addNode(mot); //On ajoute le point qui correspond à la clé
            sprite = smanAutoroutes.addSprite(mot); //On ajoute un "sprite" (ici une zone de texte)...
            sprite.setAttribute("ui.label", mot); //Le label du point est donc son nom
            sprite.attachToNode(mot); //... qu'on vient "attacher" au point créé
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     *            Permet de générer les liens qui sont uniquement des autoroutes sur le graph autoroutes
     */
    public void affichageSeulementAutoroutes(LinkedHashMap map) {
        String string = "";
        String[] splitEdges;
        String[] splitter;
        Sprite sprite2;
        for (Object i : map.keySet()) { //On récupère les clés de la map
            string = (String) map.get(i); //On récupère les valeurs correspondant à la clé
            splitEdges = string.split(";"); //Puis on séquence les différents voisins du point
            for (String voisin : splitEdges) {   //Pour chaque voisin du point clé...
                splitter = voisin.split(">"); //On sépare le type de route et le voisin
                Node n1 = graphAutoroutes.getNode((String) i); //On récupère le point clé dans une variable node
                if (!n1.hasEdgeToward(splitter[1])) {    //On vérifie si le point clé à déjà un lien avec son voisin
                    if (splitter[0].startsWith("A")) {
                        graphAutoroutes.addEdge(splitter[0] + "--" + (String) i + "--" + splitter[1], (String) i, splitter[1]); //Si non, on crée un lien entre le point clé et le voisin
                        sprite2 = smanAutoroutes.addSprite((String) i + "--" + splitter[1]); //On ajoute une zone de texte au lien
                        sprite2.setAttribute("ui.label", (splitter[0])); //Qu'on appelle par le type de route et la distance
                        sprite2.attachToEdge(splitter[0] + "--" + (String) i + "--" + splitter[1]); //Qu'on vient ensuite attacher au lien
                        sprite2.setPosition(0.5); //On place la zone de texte au milieu du lien
                    }
                }
            }
        }
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     * @return un jpanel contenant tout les points, doit fonctionner avec affichageSeulementNationales()
     */
    public JPanel affichageNodeGraphNationales(LinkedHashMap map) {
        graphNationales.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graphNationales, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Sprite sprite;
        String mot = "";
        for (Object i : map.keySet()) { //On récupère toutes les clés
            mot = i.toString();
            graphNationales.addNode(mot); //On ajoute le point qui correspond à la clé
            sprite = smanNationales.addSprite(mot); //On ajoute un "sprite" (ici une zone de texte)...
            sprite.setAttribute("ui.label", mot); //Le label du point est donc son nom
            sprite.attachToNode(mot); //... qu'on vient "attacher" au point créé
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     *            Permet de générer les liens qui sont uniquement des nationales sur le graph nationales
     */
    public void affichageSeulementNationales(LinkedHashMap map) {
        String string = "";
        String[] splitEdges;
        String[] splitter;
        Sprite sprite2;
        for (Object i : map.keySet()) { //On récupère les clés de la map
            string = (String) map.get(i); //On récupère les valeurs correspondant à la clé
            splitEdges = string.split(";"); //Puis on séquence les différents voisins du point
            for (String voisin : splitEdges) {   //Pour chaque voisin du point clé...
                splitter = voisin.split(">"); //On sépare le type de route et le voisin
                Node n1 = graphNationales.getNode((String) i); //On récupère le point clé dans une variable node
                if (!n1.hasEdgeToward(splitter[1])) {    //On vérifie si le point clé à déjà un lien avec son voisin
                    if (splitter[0].startsWith("N")) {
                        graphNationales.addEdge(splitter[0] + "--" + (String) i + "--" + splitter[1], (String) i, splitter[1]); //Si non, on crée un lien entre le point clé et le voisin
                        sprite2 = smanNationales.addSprite((String) i + "--" + splitter[1]); //On ajoute une zone de texte au lien
                        sprite2.setAttribute("ui.label", (splitter[0])); //Qu'on appelle par le type de route et la distance
                        sprite2.attachToEdge(splitter[0] + "--" + (String) i + "--" + splitter[1]); //Qu'on vient ensuite attacher au lien
                        sprite2.setPosition(0.5); //On place la zone de texte au milieu du lien
                    }
                }
            }
        }
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     * @return un jpanel contenant tout les points, doit fonctionner avec affichageSeulementDepartementales()
     */
    public JPanel affichageNodeGraphDepartementales(LinkedHashMap map) {
        graphDepartementales.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graphDepartementales, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Sprite sprite;
        String mot = "";
        for (Object i : map.keySet()) { //On récupère toutes les clés
            mot = i.toString();
            graphDepartementales.addNode(mot); //On ajoute le point qui correspond à la clé
            sprite = smanDepartementales.addSprite(mot); //On ajoute un "sprite" (ici une zone de texte)...
            sprite.setAttribute("ui.label", mot); //Le label du point est donc son nom
            sprite.attachToNode(mot); //... qu'on vient "attacher" au point créé
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     *            Permet de générer les liens qui sont uniquement des departementales sur le graph departementales
     */
    public void affichageSeulementDepartementales(LinkedHashMap map) {
        String string = "";
        String[] splitEdges;
        String[] splitter;
        Sprite sprite2;
        for (Object i : map.keySet()) { //On récupère les clés de la map
            string = (String) map.get(i); //On récupère les valeurs correspondant à la clé
            splitEdges = string.split(";"); //Puis on séquence les différents voisins du point
            for (String voisin : splitEdges) {   //Pour chaque voisin du point clé...
                splitter = voisin.split(">"); //On sépare le type de route et le voisin
                Node n1 = graphDepartementales.getNode((String) i); //On récupère le point clé dans une variable node
                if (!n1.hasEdgeToward(splitter[1])) {    //On vérifie si le point clé à déjà un lien avec son voisin
                    if (splitter[0].startsWith("D")) {
                        graphDepartementales.addEdge(splitter[0] + "--" + (String) i + "--" + splitter[1], (String) i, splitter[1]); //Si non, on crée un lien entre le point clé et le voisin
                        sprite2 = smanDepartementales.addSprite((String) i + "--" + splitter[1]); //On ajoute une zone de texte au lien
                        sprite2.setAttribute("ui.label", (splitter[0])); //Qu'on appelle par le type de route et la distance
                        sprite2.attachToEdge(splitter[0] + "--" + (String) i + "--" + splitter[1]); //Qu'on vient ensuite attacher au lien
                        sprite2.setPosition(0.5); //On place la zone de texte au milieu du lien
                    }
                }
            }
        }
    }

    /**
     * @param node Le point pour lequel on cherche les 1 voisins
     * @param map  -> LinkedHashMap remplie par lecturefichier()
     * @return JPanel contenant le graph contenant le point en paramètre et ses voisins à 1 distance
     */
    public JPanel affichage1Voisin(Node node, LinkedHashMap map) {
        graph1Voisin.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graph1Voisin, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Sprite sprite;
        String[] splitId;
        graph1Voisin.addNode(node.toString());
        sprite = sman1voisin.addSprite(node.toString()); //On ajoute un "sprite" (ici une zone de texte)...
        sprite.setAttribute("ui.label", node.toString()); //Le label du point est donc son nom
        sprite.attachToNode(node.toString()); //... qu'on vient "attacher" au point créé
        Node voisin;
        Edge edgeVoisin;
        for (Object i : map.keySet()) {
            voisin = graph.getNode((String) i);
            if (voisin.hasEdgeToward(node) && !voisin.toString().equals(node.toString())) {
                graph1Voisin.addNode(voisin.toString());
                //Nom sur le node
                sprite = sman1voisin.addSprite(voisin.toString());
                sprite.setAttribute("ui.label", voisin.toString());
                sprite.attachToNode(voisin.toString());

                //Récupération du lien entre node principal et voisin
                edgeVoisin = node.getEdgeBetween(voisin);
                splitId = edgeVoisin.getId().split("--");
                //Ajout lien node / voisin
                graph1Voisin.addEdge(edgeVoisin.getId(), node.toString(), voisin.toString());
                //Nom sur le lien
                sprite = sman1voisin.addSprite(edgeVoisin.getId());
                sprite.setAttribute("ui.label", (splitId[0]));
                sprite.attachToEdge(edgeVoisin.getId());
                sprite.setPosition(0.5);
            }
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param node Le point passé en paramètre dans la méthode affichage1Voisin
     * @param map  -> LinkedHashMap remplie par lecturefichier()
     * @return JPanel contenant le graph contenant le point en paramètre et ses voisins à 1 distance qui sont des villes
     */
    public JPanel affichage1VoisinVilles(Node node, LinkedHashMap map) {
        graph1VoisinVille.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graph1VoisinVille, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Sprite sprite;
        String[] splitId;
        graph1VoisinVille.addNode(node.toString());
        sprite = sman1VoisinVilles.addSprite(node.toString()); //On ajoute un "sprite" (ici une zone de texte)...
        sprite.setAttribute("ui.label", node.toString()); //Le label du point est donc son nom
        sprite.attachToNode(node.toString()); //... qu'on vient "attacher" au point créé
        Node voisin;
        Edge edgeVoisin;
        for (Object i : map.keySet()) {
            voisin = graph.getNode((String) i);
            if (voisin.hasEdgeToward(node) && !voisin.toString().equals(node.toString()) && voisin.toString().startsWith("V")) {
                graph1VoisinVille.addNode(voisin.toString());
                //Nom sur le node
                sprite = sman1VoisinVilles.addSprite(voisin.toString());
                sprite.setAttribute("ui.label", voisin.toString());
                sprite.attachToNode(voisin.toString());

                //Récupération du lien entre node principal et voisin
                edgeVoisin = node.getEdgeBetween(voisin);
                splitId = edgeVoisin.getId().split("--");
                //Ajout lien node / voisin
                graph1VoisinVille.addEdge(edgeVoisin.getId(), node.toString(), voisin.toString());
                //Nom sur le lien
                sprite = sman1VoisinVilles.addSprite(edgeVoisin.getId());
                sprite.setAttribute("ui.label", (splitId[0]));
                sprite.attachToEdge(edgeVoisin.getId());
                sprite.setPosition(0.5);
            }
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param node Le point passé en paramètre dans la méthode affichage1Voisin
     * @param map  -> LinkedHashMap remplie par lecturefichier()
     * @return JPanel contenant le graph contenant le point en paramètre et ses voisins à 1 distance qui sont des centres de loisirs
     */
    public JPanel affichage1VoisinLoisirs(Node node, LinkedHashMap map) {
        graph1VoisinLoisirs.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graph1VoisinLoisirs, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Sprite sprite;
        String[] splitId;
        graph1VoisinLoisirs.addNode(node.toString());
        sprite = sman1VoisinLoisirs.addSprite(node.toString()); //On ajoute un "sprite" (ici une zone de texte)...
        sprite.setAttribute("ui.label", node.toString()); //Le label du point est donc son nom
        sprite.attachToNode(node.toString()); //... qu'on vient "attacher" au point créé
        Node voisin;
        Edge edgeVoisin;
        for (Object i : map.keySet()) {
            voisin = graph.getNode((String) i);
            if (voisin.hasEdgeToward(node) && !voisin.toString().equals(node.toString()) && voisin.toString().startsWith("L")) {
                graph1VoisinLoisirs.addNode(voisin.toString());
                //Nom sur le node
                sprite = sman1VoisinLoisirs.addSprite(voisin.toString());
                sprite.setAttribute("ui.label", voisin.toString());
                sprite.attachToNode(voisin.toString());

                //Récupération du lien entre node principal et voisin
                edgeVoisin = node.getEdgeBetween(voisin);
                splitId = edgeVoisin.getId().split("--");
                //Ajout lien node / voisin
                graph1VoisinLoisirs.addEdge(edgeVoisin.getId(), node.toString(), voisin.toString());
                //Nom sur le lien
                sprite = sman1VoisinLoisirs.addSprite(edgeVoisin.getId());
                sprite.setAttribute("ui.label", (splitId[0]));
                sprite.attachToEdge(edgeVoisin.getId());
                sprite.setPosition(0.5);
            }
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param node Le point passé en paramètre dans la méthode affichage1Voisin
     * @param map  -> LinkedHashMap remplie par lecturefichier()
     * @return JPanel contenant le graph contenant le point en paramètre et ses voisins à 1 distance qui sont des restaurants
     */
    public JPanel affichage1VoisinResto(Node node, LinkedHashMap map) {
        graph1VoisinResto.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graph1VoisinResto, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Sprite sprite;
        String[] splitId;
        graph1VoisinResto.addNode(node.toString());
        sprite = sman1VoisinResto.addSprite(node.toString()); //On ajoute un "sprite" (ici une zone de texte)...
        sprite.setAttribute("ui.label", node.toString()); //Le label du point est donc son nom
        sprite.attachToNode(node.toString()); //... qu'on vient "attacher" au point créé
        Node voisin;
        Edge edgeVoisin;
        for (Object i : map.keySet()) {
            voisin = graph.getNode((String) i);
            if (voisin.hasEdgeToward(node) && !voisin.toString().equals(node.toString()) && voisin.toString().startsWith("R")) {
                graph1VoisinResto.addNode(voisin.toString());
                //Nom sur le node
                sprite = sman1VoisinResto.addSprite(voisin.toString());
                sprite.setAttribute("ui.label", voisin.toString());
                sprite.attachToNode(voisin.toString());

                //Récupération du lien entre node principal et voisin
                edgeVoisin = node.getEdgeBetween(voisin);
                splitId = edgeVoisin.getId().split("--");
                //Ajout lien node / voisin
                graph1VoisinResto.addEdge(edgeVoisin.getId(), node.toString(), voisin.toString());
                //Nom sur le lien
                sprite = sman1VoisinResto.addSprite(edgeVoisin.getId());
                sprite.setAttribute("ui.label", (splitId[0]));
                sprite.attachToEdge(edgeVoisin.getId());
                sprite.setPosition(0.5);
            }
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param node1 Premier point
     * @param node2 Deuxième point
     * @param map   -> LinkedHashMap remplie par lecturefichier()
     * @return JPanel contenant le graph avec les deux points et leur(s) éventuel(s) voisin(s) commun
     */
    public JPanel affichage2Distance(Node node1, Node node2, LinkedHashMap map) {
        graph2Voisin.setAttribute("ui.stylesheet", "url('stylesheet')");

        Layout graphLayout = new SpringBox(false);
        SwingViewer viewer = new SwingViewer(graph2Voisin, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        JPanel graphPanel = new JPanel();

        DefaultView view = (DefaultView) viewer.addDefaultView(false);
        view.setPreferredSize(new Dimension(800, 500));

        Node voisinCommun;
        Sprite sprite;
        Edge lien;
        String[] splitter;

        graph2Voisin.addNode(node1.toString());
        sprite = sman2voisin.addSprite(node1.toString()); //On ajoute un "sprite" (ici une zone de texte)...
        sprite.setAttribute("ui.label", node1.toString()); //Le label du point est donc son nom
        sprite.attachToNode(node1.toString()); //... qu'on vient "attacher" au point créé

        graph2Voisin.addNode(node2.toString());
        sprite = sman2voisin.addSprite(node2.toString()); //On ajoute un "sprite" (ici une zone de texte)...
        sprite.setAttribute("ui.label", node2.toString()); //Le label du point est donc son nom
        sprite.attachToNode(node2.toString()); //... qu'on vient "attacher" au point créé

        for (Object i : map.keySet()) {
            voisinCommun = graph.getNode((String) i);
            if (node1.hasEdgeToward(voisinCommun) && node2.hasEdgeToward(voisinCommun) && voisinCommun != node1 && voisinCommun != node2) {
                graph2Voisin.addNode(voisinCommun.toString());
                sprite = sman2voisin.addSprite(voisinCommun.toString()); //On ajoute un "sprite" (ici une zone de texte)...
                sprite.setAttribute("ui.label", voisinCommun.toString()); //Le label du point est donc son nom
                sprite.attachToNode(voisinCommun.toString()); //... qu'on vient "attacher" au point créé

                lien = node1.getEdgeToward(voisinCommun.toString());
                splitter = lien.getId().split("--");
                graph2Voisin.addEdge(splitter[0], splitter[1], splitter[2]);
                sprite = sman2voisin.addSprite(lien.getId());
                sprite.setAttribute("ui.label", (splitter[0]));
                sprite.attachToEdge(splitter[0]);
                sprite.setPosition(0.5);

                lien = node2.getEdgeToward(voisinCommun.toString());
                splitter = lien.getId().split("--");
                graph2Voisin.addEdge(splitter[0], splitter[1], splitter[2]);
                sprite = sman2voisin.addSprite(lien.getId());
                sprite.setAttribute("ui.label", (splitter[0]));
                sprite.attachToEdge(splitter[0]);
                sprite.setPosition(0.5);

            }
        }
        graphLayout.compute();

        viewer.enableAutoLayout();

        graphPanel.add(view);
        return graphPanel;
    }

    /**
     * @param nodeString Le nom du point qu'on cherche sur le graph
     * @return null si le point n'existe pas sur le graph, le point Node sinon
     */
    public Node getNode(String nodeString) {
        return graph.getNode(nodeString);
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     * @return le nombre de villes contenues dans le fichier csv
     */
    public int getNombreVilles(LinkedHashMap map) {
        int nbVilles = 0;
        for (Object i : map.keySet()) {
            if (i.toString().startsWith("V"))
                nbVilles++;
        }
        return nbVilles;
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     * @return le nombre de centres de loisirs contenus dans le fichier csv
     */
    public int getNombreLoisirs(LinkedHashMap map) {
        int nbLoisirs = 0;
        for (Object i : map.keySet()) {
            if (i.toString().startsWith("L"))
                nbLoisirs++;
        }
        return nbLoisirs;
    }

    /**
     * @param map -> LinkedHashMap remplie par lecturefichier()
     * @return le nombre de centres de restaurants contenus dans le fichier csv
     */
    public int getNombreResto(LinkedHashMap map) {
        int nbResto = 0;
        for (Object i : map.keySet()) {
            if (i.toString().startsWith("R"))
                nbResto++;
        }
        return nbResto;
    }

    /**
     * @return le nombre d'autoroutes contenues dans le graph autoroute (besoin que le graph autoroute soit généré)
     */
    public int getNombreAutoroutes() {
        return graphAutoroutes.getEdgeCount();
    }

    /**
     * @return le nombre de départementales contenues dans le graph départementales (besoin que le graph départementale soit généré)
     */
    public int getNombreDepartementales() {
        return graphDepartementales.getEdgeCount();
    }

    /**
     * @return le nombre de nationales contenues dans le graph nationales (besoin que le graph nationales soit généré)
     */
    public int getNombreNationales() {
        return graphNationales.getEdgeCount();
    }

    /**
     * @return le nombre de villes
     */
    public ArrayList<String> getVilles() {
        ArrayList<String> Villes = new ArrayList<>();
        for (Object i : graphVilles.nodes().toArray())
            Villes.add(String.valueOf(i));
        return Villes;
    }

    /**
     * @return le nombre de centres de loisirs
     */
    public ArrayList<String> getLoisirs() {
        ArrayList<String> Loisirs = new ArrayList<>();
        for (Object i : graphLoisirs.nodes().toArray())
            Loisirs.add(String.valueOf(i));
        return Loisirs;
    }

    /**
     * @return le nombre d'autoroutes
     */
    public ArrayList<String> getResto() {
        ArrayList<String> Restos = new ArrayList<>();
        for (Object i : graphResto.nodes().toArray())
            Restos.add(String.valueOf(i));
        return Restos;
    }

    /**
     * @return le nombre d'autoroutes
     */
    public ArrayList<String> getAutoroutes() {
        ArrayList<String> Autoroutes = new ArrayList<>();
        for (Object i : graphAutoroutes.edges().toArray())
            Autoroutes.add(String.valueOf(i));
        return Autoroutes;
    }

    /**
     * @return le nombre de départementales
     */
    public ArrayList<String> getDepartementales() {
        ArrayList<String> Departementales = new ArrayList<>();
        for (Object i : graphDepartementales.edges().toArray())
            Departementales.add(String.valueOf(i));
        return Departementales;
    }

    /**
     * @return le nombre de nationales
     */
    public ArrayList<String> getNationales() {
        ArrayList<String> Nationales = new ArrayList<>();
        for (Object i : graphNationales.edges().toArray())
            Nationales.add(String.valueOf(i));
        return Nationales;
    }

    /**
     * @param node Le point pour lequel on cherche les voisins à 2 distance
     * @param map  -> LinkedHashMap remplie par lecturefichier()
     * @return la liste des points à 2 distance du point passé en paramètre
     */
    public ArrayList<String> getVoisin2Distance(Node node, LinkedHashMap map) {
        ArrayList<String> list1Voisin = new ArrayList<>();
        ArrayList<String> list2Voisin = new ArrayList<>();
        for (Object i : map.keySet()) {
            Node voisin1Distance = graph.getNode(i.toString());
            if (node.hasEdgeToward(voisin1Distance))
                list1Voisin.add(voisin1Distance.toString());
        }
        for (Object j : list1Voisin) {
            for (Object i : map.keySet()) {
                Node voisin1Distance = graph.getNode(j.toString());
                Node voisin2Distance = graph.getNode(i.toString());
                if (voisin1Distance.hasEdgeToward(voisin2Distance) && node != voisin2Distance && !list2Voisin.contains(voisin2Distance.toString()) && !list1Voisin.contains(voisin2Distance.toString())) {
                    list2Voisin.add(voisin2Distance.toString());
                }
            }
        }
        return list2Voisin;
    }
}
