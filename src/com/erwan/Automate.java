package com.erwan;

import java.util.ArrayList;

/**
 * Created by Erwan&Jonathan on 02/02/16.
 */
public class Automate implements Expr {

    private Etat etatCourant;
    private ArrayList<Etat> etats = new ArrayList<>();
    private ArrayList<Transition> transitions = new ArrayList<>();


    public void ajouterEtat(Etat e){
        etats.add(e);
        e.setAutomate(this);
    }

    public void ajouterTransition(Transition t){
        transitions.add(t);
        t.setAutomate(this);
    }

    public String toString(){
        String res = etatCourant.getNom();
        if (etatCourant.getSousAutomates().size() != 0){
            for (Automate a: etatCourant.getSousAutomates()) {
                res += "." + a.toString();
            }
        }
        return res;
    }

    @Override
    public Object acceptValid(ExprVisiteurValid e) {
        return e.visitValid(this);
    }

    @Override
    public Object acceptExec(ExprVisiteurExec e) {
        return e.visitExec(this);
    }

    /********************************
     *                              *
     *      GETTERS AND SETTERS     *
     *                              *
     ********************************/

    public ArrayList<Etat> getEtats() {
        return etats;
    }

    public void setEtats(ArrayList<Etat> etats) {
        this.etats = etats;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

}
