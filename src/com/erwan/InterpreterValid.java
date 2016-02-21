package com.erwan;

import java.util.ArrayList;

/**
 * Created by Erwan&Jonathan on 02/02/16.
 */
public final class InterpreterValid extends Interpreter implements ExprVisiteurValid {

    private static InterpreterValid instance = null;
    private int compteur;
    private boolean deterministe;

    private InterpreterValid(){
        super();
    }

    /**
     * Fonction qui permet de retourner l'instance de la classe
     * @return instance
     */
    public final static InterpreterValid getInstance() {
        if (instance == null)
            instance = new InterpreterValid();

        return instance;
    }

    @Override
    public Object visitValid(Etat e) {
        boolean valid = true;


        /********    VERIFICATION DETERMINISTE    *************/
        //On va regarder pour chaque transition si elle est valide
        for (Transition t : e.getTransSource()) {
            boolean tValid = (boolean) t.acceptValid(this);
            if (tValid)
                System.out.println("\t Transition "+ t.getEtiquette().getEtiquette() +" valide");
            else {
                valid = false;
                System.out.println("\t Transition "+ t.getEtiquette().getEtiquette() +" Invalide");
                break;
            }
        }


        /********    VERIFICATION UN SEUL ETAT INITIAL    *************/
        //Compte le nombre d'états initiaux
        if (e.getInitial())
            this.compteur++;
        else{
            /********    VERIFICATION ETATS NON-INITIAUX CIBLE    *************/
            // Si l'état n'est pas initial, on vérifie qu'il a bien au moins
            // une transition dont il est la cible
            if(e.getTransCible().size() < 1){
                if (valid)
                    System.out.println(e.getNom() + " : ");
                valid = false;
                System.out.println("\t Invalide - Non-initial & non-cible");
            }
        }
        /********    VERIFICATION ETATS PUITS FINAUX    *************/
        // Si l'état n'as pas de transition dont il est la source, et qu'il
        // n'est pas fianl, alors il n'est pas valide
        if (e.getTransSource().size() == 0 && !e.getFinale()) {
            if (valid)
                System.out.println(e.getNom() + " : ");
            valid = false;
            System.out.println("\t Invalide - Etat Puit non-final");
        }

        /********    VERIFICATION NOMS DIFFERENTS    *************/
        // Vérifie que 2 noms ne sont pas les mêmes
        // On récupère la liste d'états, l'index de l'état courant
        // et le nombre d'état.
        ArrayList<Etat> list = e.getAutomate().getEtats();
        int i = list.indexOf(e);
        int nbEtats = list.size();
        // On commence la comparaison au suivant
        int j = i + 1;
        // On parcours tous les états suivant tant que celui
        // est valide
        while (j < nbEtats && valid) {
            // Si le nom d'un état est égal à celui d'un autre, on
            // passe valid à false
            if (list.get(i).getNom().equals(list.get(j).getNom())) {
                valid = false;
                System.out.println(list.get(i).getNom() +" et "+list.get(j).getNom()+" :\n\t Invalide - Même nom");
            }
            j++;
        }
        return valid;
    }

    @Override
    public Object visitValid(Transition t) {
        // On vérifie que l'etiquette de la transition est valide
        // Pour savoir si la transition est valide
        boolean valid = (boolean) t.getEtiquette().acceptValid(this);
        Etat e = t.getSource();
        if (valid)
            System.out.println(e.getNom() + " : \n\t Label de Transition "+ t.getEtiquette().getEtiquette() +" Valide");
        else
            System.out.println("\t Label de Transition "+ t.getEtiquette().getEtiquette() +" Invalide");
        return valid;
    }

    @Override
    public Object visitValid(Automate a) {
        // On initialise l'automate comme étant valide
        // et détarministe
        boolean valid = true;
        deterministe = true;
        // On initialise le ocmpteur d"état initiaux à 0
        this.compteur = 0;

        boolean temp;
        // Pour chaque état, on va vérifier si il est valide ou non
        for (Etat e: a.getEtats()) {
            temp  = (boolean) e.acceptValid(this);
            if (temp)
                System.out.println(e.getNom() + " : Valide");
            else {
                valid = false;
                System.out.println(e.getNom() + " : Invalide");
            }
        }
        // On vérifie grace au compteur si l'automate à plus d'un état
        // initial ou non
        if (this.compteur != 1) {
            valid = false;
            System.out.println("Automate : \n\t Invalide - Plus d'un état initial");
        }
        // On vérifie grace a la variable booléenne si l'automate est
        // déterministe
        if (!deterministe)
            System.out.println("Automate : \n\t Invalide - Non déterministe");

        return valid;
    }

    @Override
    public Object visitValid(Label l) {
        boolean valid = true;
        // On créer une liste pour stocker tous les états qui
        // on ce label
        ArrayList<Etat> etatsSource = new ArrayList<>();
        // On récupère toutes les transitions qui ont ce label
        ArrayList<Transition> transitions = l.getTransitions();
        // On stocke tous les états source dans la liste que l'on
        // vient de créer
        for (Transition t:transitions) {
            etatsSource.add(t.getSource());
        }
        // On créer une variable qui va servire de compteur
        // et une autre pour avoir le nombre d'états dans la
        // liste
        int i = 0;
        int nbEtats = etatsSource.size();
        // Tant qu'on a pas parcouru tous les états de la liste
        // ou que le label n'est pas valid
        while(i < nbEtats && valid){
            // On stocke l'état courant, et on commence à comparer
            // à partir de l'état suivant dans la liste
            int j = i+1;
            Etat e = etatsSource.get(i);
            // On parcours tous les autres états
            while(j < nbEtats && valid){
                // Si 2 état sont égaux, cela veux dire qu'un même
                // état est la source de 2 transition avec le même
                // label, on passe donc valid à false
                if (etatsSource.get(j).equals(e)){
                    valid = false;
                    deterministe = false;
                    System.out.println(e.getNom() + " : \n\t Invalide - Deux transitions " +
                            "étiquetées par le même label : " + l.getEtiquette());
                }
                j++;
            }
            i++;
        }
        return valid;
    }
}
