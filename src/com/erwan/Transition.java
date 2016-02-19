package com.erwan;

/**
 * Created by Erwan&Jonathan on 02/02/16.
 */
public class Transition implements ExprValid, ExprExec {

    private Etat source;
    private Etat cible;
    private Label etiquette;

    private Automate automate;

    public Transition(Etat source, Etat cible, Label etiquette) {
        this.source = source;
        this.source.getTransSource().add(this);
        this.cible = cible;
        this.cible.getTransCible().add(this);
        this.etiquette = etiquette;
        this.etiquette.getTransitions().add(this);

    }

    public Etat getSource() {
        return source;
    }

    public void setSource(Etat source) {
        this.source = source;
    }

    public Etat getCible() {
        return cible;
    }

    public void setCible(Etat cible) {
        this.cible = cible;
    }

    public Label getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(Label etiquette) {
        this.etiquette = etiquette;
    }

    @Override
    public Object acceptValid(ExprVisiteurValid e) {
        return e.visitValid(this);
    }

    @Override
    public Object acceptExec(ExprVisiteurExec e) {
        return e.visitExec(this);
    }
}
