package com.erwan;

import java.util.ArrayList;

/**
 * Created by Erwan&Jonathan on 02/02/16.
 */
public class Label implements ExprValid, ExprExec {

    private String etiquette;
    private ArrayList<Transition> transitions = new ArrayList<>();

    public Label(String etiquette) {
        this.etiquette = etiquette;
    }

    public String getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }

    public ArrayList<Transition> getTransitions() { return transitions; }

    @Override
    public Object acceptValid(ExprVisiteurValid e) {
        return e.visitValid(this);
    }

    @Override
    public Object acceptExec(ExprVisiteurExec e) {
        return null;
    }
}
