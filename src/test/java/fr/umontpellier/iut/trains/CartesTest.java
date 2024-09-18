package fr.umontpellier.iut.trains;

import fr.umontpellier.iut.trains.cartes.*;
import fr.umontpellier.iut.trains.plateau.TuileVille;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartesTest extends BaseTestClass {

    @Test
    void test_aiguillage_ajoute_1() {
        setupJeu("Aiguillage");
        initialisation();

        Carte c = new Aiguillage();
        Carte gare1 = new Gare();

        addAll(main, c);
        addAll(pioche, gare1);

        jouerTourPartiel("Aiguillage");

        assertTrue(containsReferences(main, gare1));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_aiguillage_pioche_vide() {
        setupJeu("Aiguillage");
        initialisation();

        Carte c = new Aiguillage();

        addAll(main, c);
        addAll(pioche);

        jouerTourPartiel("Aiguillage");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_Salle_de_contrôle_pioche_vide() {
        setupJeu("Salle de contrôle");
        initialisation();

        Carte c = new SalleDeControle();

        addAll(main, c);
        addAll(pioche);

        jouerTourPartiel("Salle de contrôle");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_Passage_en_gare_pioche_vide() {
        setupJeu("Passage en gare");
        initialisation();

        Carte c = new PassageEnGare();

        addAll(main, c);
        addAll(pioche);

        jouerTourPartiel("Passage en gare");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_salledecontrole() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte aig = new SalleDeControle();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();
        Carte gare3 = new Gare();

        addAll(main, c, aig);
        addAll(pioche, gare1, gare2, gare3, fondPioche);
        jouerTourPartiel("Bureau du chef de gare", "Salle de contrôle");

        assertTrue(containsReferences(main, aig, gare1, gare2, gare3));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    @Disabled
    void test_bureau_du_chef_de_gare_echangeur() {
        setupJeu("Bureau du chef de gare", "Échangeur");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte omni1 = new TrainOmnibus();
        Carte expr = new TrainExpress();

        addAll(main, c, expr);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Échangeur", "Train express");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, expr, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni1));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_ferronerie() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fero = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, fero, pr);
        jouerTourPartiel("Bureau du chef de gare", "Ferronnerie", "Pose de rails");

        assertTrue(containsReferences(main, fero));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, pr);
        jouerTourPartiel("Ferronnerie", "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(3, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_plusieurs_rails() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte pt = new PontEnAcier();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, pr, pt);
        jouerTourPartiel("Ferronnerie", "Pose de rails", "Pont en acier");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, pt));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(5, getArgent(joueur));
        assertEquals(2, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_plusieurs_carteverte() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte pt = new PontEnAcier();
        Carte vi = new Viaduc();
        Carte tu = new Tunnel();
        Carte vo = new VoieSouterraine();
        Carte co = new Cooperation();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, pr, pt, vi, tu, vo, co);
        jouerTourPartiel("Ferronnerie", "Pose de rails", "Pont en acier", "Tunnel", "Viaduc", "Voie souterraine", "Coopération");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, pt, vi, tu, vo, co));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(13, getArgent(joueur));
        assertEquals(6, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_effet_cumuler() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte fero2 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, fero2, pr);
        jouerTourPartiel("Ferronnerie", "Ferronnerie", "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, fero2));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(6, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_effet_cumuler2() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte fero2 = new Ferronnerie();
        Carte fero3 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, fero2, fero3, pr);
        jouerTourPartiel("Ferronnerie", "Ferronnerie", "Ferronnerie" , "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, fero2, fero3));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(9, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_effet_cumuler3() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte fero2 = new Ferronnerie();
        Carte fero3 = new Ferronnerie();
        Carte fero4 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, fero2, fero3, fero4 , pr);
        jouerTourPartiel("Ferronnerie", "Ferronnerie", "Ferronnerie" , "Ferronnerie" , "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, fero2, fero3, fero4));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(12, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }


    @Test
    void test_pont_en_acier() {
        setupJeu("Pont en acier");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new PontEnAcier();
        Carte omni = new TrainOmnibus();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, omni);
        jouerTourPartiel("Train omnibus", "Pont en acier", "TUILE:33");

        checkPlateau(null, List.of(23, 33), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_sans_pont_en_acier() {
        setupJeu("Pont en acier");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new PoseDeRails();
        Carte omni = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, omni);
        jouerTourPartiel("Train express", "Pose de rails", "TUILE:33");

        checkPlateau(null, List.of(23, 33), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_poser_sur_mer() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(41).ajouterRail(joueur);

        Carte c = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Pose de rails", "TUILE:40");

        checkPlateau(null, List.of(41), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_tunnel_surmontagne() {
        setupJeu("Tunnel");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new Tunnel();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Tunnel", "TUILE:14");

        checkPlateau(null, List.of(23, 14), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_tunnel_sansmontagne() {
        setupJeu("Tunnel");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new Tunnel();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Tunnel", "TUILE:22");

        checkPlateau(null, List.of(23, 22), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_montagne_sanstunnel() {
        setupJeu("Tunnel");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Pose de rails", "TUILE:14");

        checkPlateau(null, List.of(23, 14), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_viaduc_surville() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new Viaduc();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Viaduc", "TUILE:24");

        checkPlateau(null, List.of(23, 24), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_viaduc_sansville() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new Viaduc();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Viaduc", "TUILE:22");

        checkPlateau(null, List.of(23, 22), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ville_sansviaduc() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Pose de rails", "TUILE:24");

        checkPlateau(null, List.of(23, 24), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ville_sansviaduc_survilleavecgare() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);
        ((TuileVille) tuiles.get(24)).ajouterGare();

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Pose de rails", "TUILE:24");

        checkPlateau(null, List.of(23, 24), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ville_sansviaduc_survilleavec2gare() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(32).ajouterRail(joueur);
        ((TuileVille) tuiles.get(42)).ajouterGare();
        ((TuileVille) tuiles.get(42)).ajouterGare();

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx, epx1);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails", "TUILE:42");

        checkPlateau(null, List.of(32, 42), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx, epx1));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ville_sansviaduc_survilleavec3gare() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(20).ajouterRail(joueur);
        ((TuileVille) tuiles.get(29)).ajouterGare();
        ((TuileVille) tuiles.get(29)).ajouterGare();
        ((TuileVille) tuiles.get(29)).ajouterGare();

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx, epx1);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails", "TUILE:29");

        checkPlateau(null, List.of(20, 29), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx, epx1));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_poser_rails_barrière() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(42).ajouterRail(joueur);
        ((TuileVille) tuiles.get(42)).ajouterGare();

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Pose de rails", "TUILE:52");

        checkPlateau(null, List.of(42), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_surmontagne() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(21).ajouterRail(joueur);

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:20");

        checkPlateau(null, List.of(21, 20), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_surville() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(20).ajouterRail(joueur);
        ((TuileVille) tuiles.get(29)).ajouterGare();
        ((TuileVille) tuiles.get(29)).ajouterGare();
        ((TuileVille) tuiles.get(29)).ajouterGare();

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:29");

        checkPlateau(null, List.of(29, 20), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_surrivière() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:32");

        checkPlateau(null, List.of(23, 32), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_suretoile() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(29).ajouterRail(joueur);

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:38");

        checkPlateau(null, List.of(29, 38), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_surmer() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(41).ajouterRail(joueur);

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:50");

        checkPlateau(null, List.of(41), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_4etoiles() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(1).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, epx1, pr);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails",  "TUILE:0");

        checkPlateau(null, List.of(0,1), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, epx1, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_3etoiles() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(66).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, epx1, pr);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails",  "TUILE:75");

        checkPlateau(null, List.of(66, 75), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, epx1, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_2etoiles() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(60).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, epx1, pr);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails",  "TUILE:69");

        checkPlateau(null, List.of(60, 69), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, epx1, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_2etoiles_paspossible() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(60).ajouterRail(joueur);

        Carte c = new TrainOmnibus();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, pr);
        jouerTourPartiel( "Train omnibus", "Pose de rails",  "TUILE:69");

        checkPlateau(null, List.of(60), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c,  pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_3etoiles_paspossible() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(65).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, pr);
        jouerTourPartiel( "Train express", "Pose de rails",  "TUILE:75");

        checkPlateau(null, List.of(65), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_4etoiles_paspossible() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(1).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, pr);
        jouerTourPartiel( "Train express", "Pose de rails",  "TUILE:0");

        checkPlateau(null, List.of(1), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_coopération_avecadv() {
        setupJeu("Coopération");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);
        tuiles.get(23).ajouterRail(joueurs.get(0));

        Carte c = new Cooperation();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel("Train express", "Coopération", "TUILE:23");

        checkPlateau(List.of(23), List.of(22, 23), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_sanscoopération_avecadv() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);
        tuiles.get(23).ajouterRail(joueurs.get(0));

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);
        Carte f2 = reserve.get("Ferraille").get(1);

        addAll(main, c, epx);
        jouerTourPartiel("Train express", "Pose de rails" , "TUILE:23");

        checkPlateau(List.of(23), List.of(22,23), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertFalse(containsReference(reserve.get("Ferraille"), f2));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_coopération_sansadv() {
        setupJeu("Coopération");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);

        Carte c = new Cooperation();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel("Coopération",  "TUILE:23");

        checkPlateau(null, List.of(22, 23), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

}