package com.erwan;


import java.util.ArrayList;

/**
 * Created by Erwan&Jonathan on 02/02/16.
 */
public class Etat implements Expr {

    private String nom;
    private Boolean initial = false;
    private Boolean finale = false;
    private Automate automate;

    private ArrayList<Transition> transSource = new ArrayList<>();
    private ArrayList<Transition> transCible = new ArrayList<>();

    private ArrayList<Automate> sousAutomates = new ArrayList<>();


    public Etat(String nom, boolean initial, boolean finale){
        this.nom = nom;
        this.initial = initial;
        this.finale = finale;
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

    public void ajouterSousAutomate(Automate a){
        sousAutomates.add(a);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getInitial() {
        return initial;
    }

    public void setInitial(Boolean initial) {
        this.initial = initial;
    }

    public Boolean getFinale() {
        return finale;
    }

    public void setFinale(Boolean finale) {
        this.finale = finale;
    }

    public ArrayList<Automate> getSousAutomates() {
        return sousAutomates;
    }

    public void setSousAutomates(ArrayList<Automate> sousAutomates) {
        this.sousAutomates = sousAutomates;
    }

    public Automate getAutomate() { return automate; }

    public void setAutomate(Automate automate) { this.automate = automate; }

    public ArrayList<Transition> getTransSource() { return transSource; }

    public void setTransSource(ArrayList<Transition> transSource) { this.transSource = transSource;}

    public ArrayList<Transition> getTransCible() { return transCible;}

    public void setTransCible(ArrayList<Transition> transCible) { this.transCible = transCible; }

}
