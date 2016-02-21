package com.erwan;

import java.util.ArrayList;


/**
 * Created by Erwan&Jonathan on 17/02/16.
 */
public final class InterpreterExec extends Interpreter implements ExprVisiteurExec {

    private static InterpreterExec instance = null;
    private ArrayList<String> parcours = new ArrayList<>();


    private InterpreterExec(){
        super();
    }

    /**
     * Fonction qui permet de récupérer l'instance de la classe
     * @return instance
     */
    public final static InterpreterExec getInstance(){
        if (instance == null)
            instance = new InterpreterExec();

        return instance;
    }

    @Override
    public Object visitExec(Etat e) {
        boolean valid = true;
        // On ajoute le nom de l'état à la liste
        parcours.add(e.getNom() + " : ");
        // Si l'état n'est pas initial
        if (!e.getInitial()){
            // On regarde si il est la cible d'une transition, et on va vérifier la première
            if (e.getTransCible().size() >= 1){
                e.getTransCible().get(0).acceptExec(this);
            }else{
                // Sinon, on supprime tous ce qu'on a parcouru jusqu'ici, et on ajoute
                // le message d'erreur
                parcours.clear();
                valid = false;
                parcours.add("L'accès à la transition se faisant depuis l'" + e.getNom() + " n'est pas un état initial" +
                        "de l'automate.");
            }
        }
        return valid;
    }

    @Override
    public Object visitExec(Transition t) {
        // Si la transition appartien bien a un automate
        if (t.getAutomate() != null) {
            boolean valid = true;
            // On récupère le liste d'état de cet automate
            ArrayList listEtats = t.getAutomate().getEtats();

            // Si l'état cible ou source de la transition n'est pas dans l'automate
            // On ajoute un message d'erreur à la liste
            if (!listEtats.contains(t.getSource()) || !listEtats.contains(t.getCible())) {
                valid = false;
                parcours.add("La transition n'appartient pas à l'automate.");
            }
            // Si la transition appartient bien a un automate
            if (valid) {
                // On vérifie l'étiquette, on affiche la transition, et on vérifie l'état source
                // de la transition
                t.getEtiquette().acceptExec(this);
                parcours.add("\tTransition de " + t.getSource().getNom() + " à " + t.getCible().getNom());
                t.getSource().acceptExec(this);
            }
        // Si la transition n'appartien pas à l'automate on ajoute un message d'erreur
        }else{
            parcours.add("La transition n'appartient pas à un automate.");
        }
        return parcours;
    }

    @Override
    public Object visitExec(Automate a) {
        return null;
    }

    @Override
    public Object visitExec(Label l) {
        // Ajoute l'étiquette de la transition à la liste
        return parcours.add("\tEtiquette de la transition : " + l.getEtiquette());
    }
}
