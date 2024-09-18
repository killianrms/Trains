package fr.umontpellier.iut.trains;

import java.util.*;

import fr.umontpellier.iut.trains.cartes.*;
import fr.umontpellier.iut.trains.plateau.Tuile;
import fr.umontpellier.iut.trains.plateau.TuileEtoile;

public class Joueur {
    /**
     * Le jeu auquel le joueur est rattaché
     */
    private Jeu jeu;
    /**
     * Nom du joueur (pour les affichages console et UI)
     */
    private String nom;
    /**
     * Quantité d'argent que le joueur a (remis à zéro entre les tours)
     */
    private int argent;

    private int effetFerronerie;
    private boolean peutRecevoirFerraille;


    private int pointVictoireActuel;

    private boolean addDessus;


    private boolean surcoutRiviere;
    private boolean surcoutMontagne;
    private boolean surcoutVille;
    private boolean surcoutAutre;
    private boolean surcoutGeneral;

    private ArrayList<Integer> indiceTuile;


    /**
     * Nombre de points rails dont le joueur dispose. Ces points sont obtenus en
     * jouant les cartes RAIL (vertes) et remis à zéro entre les tous
     */
    private int pointsRails;
    /**
     * Nombre de jetons rails disponibles (non placés sur le plateau)
     */
    private int nbJetonsRails;
    /**
     * Liste des cartes en main
     */
    private ListeDeCartes main;
    /**
     * Liste des cartes dans la pioche (le début de la liste correspond au haut de
     * la pile)
     */
    private ListeDeCartes pioche;
    /**
     * Liste de cartes dans la défausse
     */
    private ListeDeCartes defausse;
    /**
     * Liste des cartes en jeu (cartes jouées par le joueur pendant le tour)
     */
    private ListeDeCartes cartesEnJeu;
    /**
     * Liste des cartes reçues pendant le tour
     */
    private ListeDeCartes cartesRecues;
    /**
     * Couleur du joueur (utilisé par l'interface graphique)
     */
    private CouleurJoueur couleur;

    public Joueur(Jeu jeu, String nom, CouleurJoueur couleur) {
        this.jeu = jeu;
        this.nom = nom;
        this.couleur = couleur;
        argent = 0;
        effetFerronerie = 0;
        peutRecevoirFerraille = true;
        pointsRails = 0;
        nbJetonsRails = 20;
        main = new ListeDeCartes();
        defausse = new ListeDeCartes();
        pioche = new ListeDeCartes();
        cartesEnJeu = new ListeDeCartes();
        cartesRecues = new ListeDeCartes();
        indiceTuile = new ArrayList<>();
        surcoutRiviere = true;
        surcoutAutre = true;
        surcoutMontagne = true;
        surcoutVille = true;
        surcoutGeneral = true;
        pointVictoireActuel = 0;
        addDessus = false;

        // créer 7 Train omnibus (non disponibles dans la réserve)
        pioche.addAll(FabriqueListeDeCartes.creerListeDeCartes("Train omnibus", 7));
        // prendre 2 Pose de rails de la réserve
        for (int i = 0; i < 2; i++) {
            pioche.add(jeu.prendreDansLaReserve("Pose de rails"));
        }
        // prendre 1 Gare de la réserve
        pioche.add(jeu.prendreDansLaReserve("Gare"));

        // mélanger la pioche
        pioche.melanger();
        // Piocher 5 cartes en main
        // Remarque : on peut aussi appeler piocherEnMain(5) si la méthode est écrite
        for (int i = 0; i < 5; i++) {
            main.add(pioche.remove(0));
        }

    }

    public Jeu getJeu() {
        return jeu;
    }

    public ListeDeCartes getPioche() {
        return pioche;
    }

    public ListeDeCartes getCartesEnJeu() {
        return cartesEnJeu;
    }

    public boolean isPeutRecevoirFerraille() {
        return peutRecevoirFerraille;
    }

    public void setPeutRecevoirFerraille(boolean peutRecevoirFerraille) {
        this.peutRecevoirFerraille = peutRecevoirFerraille;
    }

    public void setAddDessus(boolean addDessus) {
        this.addDessus = addDessus;
    }

    public void setSurcoutMontagne(boolean surcoutMontagne) {
        this.surcoutMontagne = surcoutMontagne;
    }

    public void setSurcoutVille(boolean surcoutVille) {
        this.surcoutVille = surcoutVille;
    }

    public void setSurcoutAutre(boolean surcoutAutre) {
        this.surcoutAutre = surcoutAutre;
    }

    public void setSurcoutGeneral(boolean surcoutGeneral) {
        this.surcoutGeneral = surcoutGeneral;
    }

    public ListeDeCartes getCartesRecues() {
        return cartesRecues;
    }

    public ListeDeCartes getDefausse() {
        return defausse;
    }

    public void setMain(ListeDeCartes main) {
        this.main = main;
    }

    public ListeDeCartes getMain() {
        return main;
    }

    public String getNom() {
        return nom;
    }

    public CouleurJoueur getCouleur() {
        return couleur;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public void setSurcoutRiviere(boolean surcoutRiviere) {
        this.surcoutRiviere = surcoutRiviere;
    }


    /**
     * Renvoie le score total du joueur
     * <p>
     * Le score total est la somme des points obtenus par les effets suivants :
     * <ul>
     * <li>points de rails (villes et lieux éloignés sur lesquels le joueur a posé
     * un rail)
     * <li>points des cartes possédées par le joueur (cartes VICTOIRE jaunes)
     * <li>score courant du joueur (points marqués en jouant des cartes pendant la
     * partie p.ex. Train de tourisme)
     * </ul>
     *
     * @return le score total du joueur
     */
    public int getScoreTotal() {
        int score = pointVictoireActuel;
        for (Carte c : main) {
            if (Objects.equals("jaune", c.getType())) {
                score += c.getPointVictoire();
            }
        }
        for (Carte c : cartesEnJeu) {
            if (Objects.equals("jaune", c.getType())) {
                score += c.getPointVictoire();
            }
        }
        for (Carte c : cartesRecues) {
            if (Objects.equals("jaune", c.getType())) {
                score += c.getPointVictoire();
            }
        }
        for (Carte c : defausse) {
            if (Objects.equals("jaune", c.getType())) {
                score += c.getPointVictoire();
            }
        }
        for (Carte c : pioche) {
            if (Objects.equals("jaune", c.getType())) {
                score += c.getPointVictoire();
            }
        }
        return score;
    }

    /**
     * Retire et renvoie la première carte de la pioche.
     * <p>
     * Si la pioche est vide, la méthode commence par mélanger toute la défausse
     * dans la pioche.
     *
     * @return la carte piochée ou {@code null} si aucune carte disponible
     */
    public Carte piocher() {
        if (pioche.isEmpty()) {
            if (!defausse.isEmpty()) {
                Collections.shuffle(defausse);
                pioche.addAll(defausse);
                defausse.clear();
            } else {
                return null;
            }
        }
        return pioche.remove(0);
    }

    /**
     * Retire et renvoie les {@code n} premières cartes de la pioche.
     * <p>
     * Si à un moment il faut encore piocher des cartes et que la pioche est vide,
     * la défausse est mélangée et toutes ses cartes sont déplacées dans la pioche.
     * S'il n'y a plus de cartes à piocher la méthode s'interromp et les cartes qui
     * ont pu être piochées sont renvoyées.
     *
     * @param n nombre de cartes à piocher
     * @return une liste des cartes piochées (la liste peut contenir moins de n
     * éléments si pas assez de cartes disponibles dans la pioche et la
     * défausse)
     */
    public List<Carte> piocher(int n) {
        List<Carte> cartesPiochees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Carte cartes = piocher();
            if (cartes == null) {
                break;
            }
            cartesPiochees.add(cartes);
        }
        return cartesPiochees;
    }

    /**
     * Joue un tour complet du joueur
     * <p>
     * Le tour du joueur se déroule en plusieurs étapes :
     * <ol>
     * <li>Initialisation
     * <p>
     * Dans ce jeu il n'y a rien de particulier à faire en début de tour à part un
     * éventuel affichage dans le log.
     *
     * <li>Boucle principale
     * <p>
     * C'est le cœur de la fonction. Tant que le tour du joueur n'est pas terminé,
     * il faut préparer la liste de tous les choix valides que le joueur peut faire
     * (jouer des cartes, poser des rails, acheter des cartes, etc.), puis demander
     * au joueur de choisir une action (en appelant la méthode {@code choisir}).
     * <p>
     * Ensuite, en fonction du choix du joueur il faut exécuter l'action demandée et
     * recommencer la boucle si le tour n'est pas terminé.
     * <p>
     * Le tour se termine lorsque le joueur décide de passer (il choisit {@code ""})
     * ou lorsqu'il exécute une action qui termine automatiquement le tour (par
     * exemple s'il choisit de recycler toutes ses cartes Ferraille en début de
     * tour)
     *
     * <li>Finalisation
     * <p>
     * Actions à exécuter à la fin du tour : réinitialiser les attributs
     * du joueur qui sont spécifiques au tour (argent, rails, etc.), défausser
     * toutes les
     * cartes, piocher 5 nouvelles cartes en main, etc.
     * </ol>
     */

    public void intitialisation(){
        List<String> choixPossibles = new ArrayList<>();
        for (int i = 0; i<jeu.getTuiles().size(); i++){
            if (!Objects.equals(jeu.getTuile(i).getNom(), "mer") && !Objects.equals(jeu.getTuile(i).getNom(), "etoile")){
                choixPossibles.add("TUILE:"+i);
            }
        }
        String entrer = choisir("Choississez votre point de spawn", choixPossibles, null, false);
        int indice = Integer.parseInt(entrer.split(":")[1]);
        jeu.getTuile(indice).ajouterRail(this);
        indiceTuile.add(indice);
        nbJetonsRails--;
    }


    public void jouerTour() {
        // Initialisation
        jeu.log("<div class=\"tour\">Tour de " + toLog() + "</div>");
        int coutsupp;
        boolean defausseFerraille = true;
        boolean finTour = false;
        // Boucle principale
        while (!finTour) {
            coutsupp = 0;
            List<String> choixPossibles = new ArrayList<>();
            for (Carte c : main) {
                if (!Objects.equals("jaune", c.getType())) {
                    choixPossibles.add(c.getNom());
                }
            }
            for (String nomCarte : jeu.getReserve().keySet()) {
                // ajoute les noms des cartes dans la réserve préfixés de "ACHAT:"
                choixPossibles.add("ACHAT:" + nomCarte);
            }
            indiceTuile = new ArrayList<>();
            for (int i = 0; i<jeu.getTuiles().size(); i++){
                if (jeu.getTuile(i).getRails().contains(this)){
                    indiceTuile.add(i);
                }
            }

            if (pointsRails>0){
                for (int i = 0; i< indiceTuile.size(); i++){
                    for (int j = 0; j <jeu.getTuiles().size(); j++) {
                        if (jeu.getTuile(j).estVoisine(jeu.getTuile(indiceTuile.get(i)))) {
                            if (!Objects.equals(jeu.getTuile(j).getNom(), "mer")){
                                if (surcoutGeneral){
                                    if (Objects.equals(jeu.getTuile(j).getNom(), "ville")){
                                        if (surcoutVille) {
                                            if (surcoutAutre){
                                                if (argent>= jeu.getTuile(j).getSurcout()+jeu.getTuile(j).nbJoueurSurTuile()){
                                                    choixPossibles.add("TUILE:"+j);
                                                }
                                            } else {
                                                if (argent>= jeu.getTuile(j).getSurcout()){
                                                    choixPossibles.add("TUILE:"+j);
                                                }
                                            }
                                        } else {
                                            if (!choixPossibles.contains("TUILE:"+j)){
                                                choixPossibles.add("TUILE:"+j);
                                            }
                                        }
                                    } else if (Objects.equals(jeu.getTuile(j).getNom(), "etoile")){
                                        if (surcoutAutre){
                                            if (argent>= jeu.getTuile(j).getSurcout()+jeu.getTuile(j).nbJoueurSurTuile()){
                                                choixPossibles.add("TUILE:"+j);
                                            }
                                        } else {
                                            if (argent>= jeu.getTuile(j).getSurcout()){
                                                choixPossibles.add("TUILE:"+j);
                                            }
                                        }
                                    } else if (Objects.equals(jeu.getTuile(j).getNom(), "fleuve")){
                                        if (surcoutRiviere) {
                                            if (surcoutAutre){
                                                if (argent>= jeu.getTuile(j).getSurcout()+jeu.getTuile(j).nbJoueurSurTuile()){
                                                    choixPossibles.add("TUILE:"+j);
                                                }
                                            } else {
                                                if (argent>= jeu.getTuile(j).getSurcout()){
                                                    choixPossibles.add("TUILE:"+j);
                                                }
                                            }
                                        } else {
                                            if (!choixPossibles.contains("TUILE:"+j)){
                                                choixPossibles.add("TUILE:"+j);
                                            }
                                        }
                                    } else if (Objects.equals(jeu.getTuile(j).getNom(), "montagne")) {
                                        if (surcoutMontagne) {
                                            if (argent>= jeu.getTuile(j).getSurcout()+jeu.getTuile(j).nbJoueurSurTuile()){
                                                choixPossibles.add("TUILE:"+j);
                                            } else {
                                                if (argent>= jeu.getTuile(j).getSurcout()){
                                                    choixPossibles.add("TUILE:"+j);
                                                }
                                            }
                                        } else {
                                            if (!choixPossibles.contains("TUILE:"+j)){
                                                choixPossibles.add("TUILE:"+j);
                                            }
                                        }
                                    } else  {
                                        if (!choixPossibles.contains("TUILE:"+j)){
                                            choixPossibles.add("TUILE:"+j);
                                        }
                                    }

                                } else {
                                    if (!choixPossibles.contains("TUILE:"+j)){
                                        choixPossibles.add("TUILE:"+j);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // Choix de l'action à réaliser
            String choix = choisir(String.format("Tour de %s", this.nom), choixPossibles, null, true);
            log(choix);
             if (choix.startsWith("ACHAT:")) {
                // prendre une carte dans la réserve
                String nomCarte = choix.split(":")[1];
                Carte carte = jeu.prendreDansLaReserve(nomCarte);

                if (carte != null) {
                    if (carte.getCout() <= argent) {

                        // ajoute d'une carte ferraille si on achete une carte jaune
                        if (Objects.equals("jaune", carte.getType())) {
                            if (isPeutRecevoirFerraille()) {
                                cartesRecues.add(jeu.prendreDansLaReserve("Ferraille"));
                            }
                        }
                        log("Achète " + carte); // affichage dans le log
                        argent -= carte.getCout();
                        if (addDessus) {
                            List<String> choixDessus = new ArrayList<>();
                            choixDessus.add("oui");
                            choixDessus.add("non");
                            List<Bouton> boutons = new ArrayList<>();
                            for (String tmp : choixPossibles) {
                                boutons.add(new Bouton(tmp, tmp));
                            }
                            String entrer = choisir("Voulez vous mettre cette au dessus de votre pioche", choixDessus, boutons, false);
                            if (Objects.equals(entrer, "oui")) {
                                pioche.add(0, carte);
                            } else {
                                cartesRecues.add(carte);
                            }
                        } else {
                            cartesRecues.add(carte);
                        }
                    } else {
                        log("vous n'avez pas assez d'argent :" + carte);
                        ListeDeCartes LCarte = new ListeDeCartes();
                        for (Carte tmp : jeu.getReserve().get(carte.getNom())) {
                            if (tmp == null) {
                                log("bug");
                            } else {
                                LCarte.add(tmp);
                            }
                        }

                        LCarte.add(carte);

                        jeu.getReserve().put(carte.getNom(), LCarte);
                    }
                }
            }
            else if (choix.startsWith("TUILE:")) {
                int indice = Integer.parseInt(choix.split(":")[1]);
                if (jeu.getTuile(indice).nbJoueurSurTuile()>0){
                    if (peutRecevoirFerraille){
                        ajouterFerraille();
                    }
                }
                if (surcoutGeneral){

                    if (surcoutAutre){
                        coutsupp += jeu.getTuile(indice).nbJoueurSurTuile();
                    }
                    if (Objects.equals(jeu.getTuile(indice).getNom(), "ville")){
                        if (surcoutVille) {
                            coutsupp += jeu.getTuile(indice).getSurcout();
                        }
                    } else if (Objects.equals(jeu.getTuile(indice).getNom(), "fleuve")){
                        if (surcoutRiviere) {
                            coutsupp += jeu.getTuile(indice).getSurcout();
                        }
                    } else if (Objects.equals(jeu.getTuile(indice).getNom(), "montagne")) {
                        if (surcoutMontagne) {
                            coutsupp += jeu.getTuile(indice).getSurcout();
                        }
                    } else if (Objects.equals(jeu.getTuile(indice).getNom(), "etoile")) {
                        coutsupp += jeu.getTuile(indice).getSurcout();
                        pointVictoireActuel += jeu.getTuile(indice).getValeur();
                    }

                }
                 argent -= coutsupp;


                jeu.getTuile(indice).ajouterRail(this);

                 indiceTuile.add(indice);
                 nbJetonsRails--;
                 pointsRails--;
            } else if (choix.equals("Ferraille") && defausseFerraille) {
                defausseFerrailleMain();
                finTour = true;
            } else if (choix.equals("")) {
                if (defausseFerraille) {
                    defausseFerrailleMain();
                }
                finTour = true;

            } else {
                // jouer une carte de la main
                Carte carte = main.retirer(choix);
                if (Objects.equals(carte.getType(), "verte") && effetFerronerie != 0) {
                    argent += effetFerronerie;
                }
                log("Joue " + carte); // affichage dans le log
                cartesEnJeu.add(carte); // mettre la carte en jeu
                carte.jouer(this); // exécuter l'action de la carte
            }
            defausseFerraille = false;
        }
        // Finalisation
        // défausser toutes les cartes
        defausse.addAll(main);
        main.clear();
        defausse.addAll(cartesRecues);
        cartesRecues.clear();
        defausse.addAll(cartesEnJeu);
        cartesEnJeu.clear();

        main.addAll(piocher(5)); // piocher 5 cartes en main
        argent = 0;
        pointsRails = 0;
        surcoutRiviere = true;
        surcoutAutre = true;
        surcoutMontagne = true;
        surcoutVille = true;
        surcoutGeneral = true;
        peutRecevoirFerraille = true;
        addDessus = false;
        effetFerronerie = 0;
    }

    private void defausseFerrailleMain() {
        ListeDeCartes LCarte = new ListeDeCartes();
        int count = 0;
        for (Carte tmp : getMain()) {
            if (Objects.equals(tmp.getNom(), "Ferraille")) {
                count++;
            }
        }

        for (Carte tmp : getJeu().getReserve().get("Ferraille")) {
            LCarte.add(tmp);
        }
        for (int i = 0; i < count; i++) {
            LCarte.add(getMain().retirer("Ferraille"));
        }
        getJeu().getReserve().put("Ferraille", LCarte);
    }


    /**
     * Attend une entrée de la part du joueur (au clavier ou sur la websocket) et
     * renvoie le choix du joueur.
     * <p>
     * Cette méthode lit les entrées du jeu ({@code Jeu.lireligne()}) jusqu'à ce
     * qu'un choix valide (un élément de {@code choix} ou la valeur d'un élément de
     * {@code boutons} ou éventuellement la chaîne vide si l'utilisateur est
     * autorisé à passer) soit reçu.
     * Lorsqu'un choix valide est obtenu, il est renvoyé par la fonction.
     * <p>
     * Exemple d'utilisation pour demander à un joueur de répondre à une question
     * par "oui" ou "non" :
     * <p>
     *
     * <pre>{@code
     * List<String> choix = Arrays.asList("oui", "non");
     * String input = choisir("Voulez-vous faire ceci ?", choix, null, false);
     * }</pre>
     * <p>
     * Si par contre on voulait proposer les réponses à l'aide de boutons, on
     * pourrait utiliser :
     *
     * <pre>{@code
     * List<String> boutons = Arrays.asList(new Bouton("Oui !", "oui"), new Bouton("Non !", "non"));
     * String input = choisir("Voulez-vous faire ceci ?", null, boutons, false);
     * }</pre>
     * <p>
     * (ici le premier bouton a le label "Oui !" et envoie la String "oui" s'il est
     * cliqué, le second a le label "Non !" et envoie la String "non" lorsqu'il est
     * cliqué)
     *
     * <p>
     * <b>Remarque :</b> Normalement, si le paramètre {@code peutPasser} est
     * {@code false} le choix
     * {@code ""} n'est pas valide. Cependant s'il n'y a aucun choix proposé (les
     * listes {@code choix} et {@code boutons} sont vides ou {@code null}), le choix
     * {@code ""} est accepté pour éviter un blocage.
     *
     * @param instruction message à afficher à l'écran pour indiquer au joueur la
     *                    nature du choix qui est attendu
     * @param choix       une collection de chaînes de caractères correspondant aux
     *                    choix valides attendus du joueur (ou {@code null})
     * @param boutons     une liste d'objets de type {@code Bouton} définis par deux
     *                    chaînes de caractères (label, valeur) correspondant aux
     *                    choix valides attendus du joueur qui doivent être
     *                    représentés par des boutons sur l'interface graphique (le
     *                    label est affiché sur le bouton, la valeur est ce qui est
     *                    envoyé au jeu quand le bouton est cliqué) ou {@code null}
     * @param peutPasser  booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la
     *                    chaîne de caractères vide ({@code ""}) qui signifie qu'il
     *                    désire passer.
     * @return le choix de l'utilisateur (un élement de {@code choix}, ou la valeur
     * d'un élément de {@code boutons} ou la chaîne vide)
     */
    public String choisir(
            String instruction,
            Collection<String> choix,
            List<Bouton> boutons,
            boolean peutPasser) {
        if (choix == null)
            choix = new ArrayList<>();
        if (boutons == null)
            boutons = new ArrayList<>();

        HashSet<String> choixDistincts = new HashSet<>(choix);
        choixDistincts.addAll(boutons.stream().map(Bouton::valeur).toList());
        if (peutPasser || choixDistincts.isEmpty()) {
            // si le joueur a le droit de passer ou s'il n'existe aucun choix valide, on
            // ajoute "" à la liste des choix possibles
            choixDistincts.add("");
        }

        String entree;
        // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
        while (true) {
            jeu.prompt(instruction, boutons, peutPasser);
            entree = jeu.lireLigne();
            // si une réponse valide est obtenue, elle est renvoyée
            if (choixDistincts.contains(entree)) {
                return entree;
            }
        }
    }

    /**
     * Ajoute un message dans le log du jeu
     *
     * @param message message à ajouter dans le log
     */
    public void log(String message) {
        jeu.log(message);
    }

    @Override
    public String toString() {
        // Vous pouvez modifier cette fonction comme bon vous semble pour afficher
        // d'autres informations si nécessaire
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("=== %s (%d pts) ===", nom, getScoreTotal()));
        joiner.add(String.format("  Argent: %d  Rails: %d", argent, pointsRails));
        joiner.add("  Cartes en jeu: " + cartesEnJeu);
        joiner.add("  Cartes reçues: " + cartesRecues);
        joiner.add("  Cartes en main: " + main);
        joiner.add("  Pioche: " + pioche);
        joiner.add("  Défausse: " + defausse);
        joiner.add("  Jetons rails: " + nbJetonsRails);
        joiner.add("  Actif: " + (jeu.getJoueurCourant() == this));
        joiner.add("  Peut recevoir ferraille: " + peutRecevoirFerraille);
        joiner.add("  Effet ferronerie: " + effetFerronerie);
        joiner.add("  Surcout riviere: " + surcoutRiviere);
        joiner.add("  Surcout montagne: " + surcoutMontagne);
        joiner.add("  Surcout ville: " + surcoutVille);
        joiner.add("  Surcout autre: " + surcoutAutre);
        joiner.add("  Surcout general: " + surcoutGeneral);
        joiner.add("  Add dessus: " + addDessus);
        joiner.add("  Point victoire actuel: " + pointVictoireActuel);
        return joiner.toString();
    }

    /**
     * @return une représentation du joueur pour l'affichage dans le log du jeu
     */
    public String toLog() {
        return String.format("<span class=\"joueur %s\">%s</span>", couleur.toString(), nom);
    }

    /**
     * @return une représentation du joueur sous la forme d'un dictionnaire de
     * valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     * l'interface graphique)
     */
    Map<String, Object> dataMap() {
        return Map.ofEntries(
                Map.entry("nom", nom),
                Map.entry("couleur", couleur),
                Map.entry("scoreTotal", getScoreTotal()),
                Map.entry("argent", argent),
                Map.entry("rails", pointsRails),
                Map.entry("nbJetonsRails", nbJetonsRails),
                Map.entry("main", main.dataMap()),
                Map.entry("defausse", defausse.dataMap()),
                Map.entry("cartesEnJeu", cartesEnJeu.dataMap()),
                Map.entry("cartesRecues", cartesRecues.dataMap()),
                Map.entry("pioche", pioche.dataMap()),
                Map.entry("actif", jeu.getJoueurCourant() == this));
    }

    public void addCarteMain(List<Carte> cartes) {
        for (Carte carte : cartes) {
            main.add(carte);
        }
    }

    public void addArgent(int nb) {
        argent += nb;
    }

    public void addEffetFerronerie() {
        effetFerronerie += 2;
    }

    public void addPointRail(int nb) {
        pointsRails += nb;
    }

    public void addPv(int nb) {
        pointVictoireActuel += nb;
    }

    public int getNbJetonsRail() {
        return nbJetonsRails;
    }

    public int getArgent() {
        return argent;
    }

    public void retirerJetonRail() {
        nbJetonsRails--;
    }

    public void ajouterArgent(int i) {
        argent += i;
    }

    public void ajouterFerraille() {
        Carte c = jeu.prendreDansLaReserve("Ferraille");
        if (c!= null){
            cartesRecues.add(c);
        }
    }
}
