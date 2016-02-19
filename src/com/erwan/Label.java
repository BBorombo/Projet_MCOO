package com.erwan;

/**
 * Created by Erwan&Jonathan on 02/02/16.
 */
public class Label implements ExprValid, ExprExec {

    private String etiquette;

    public Label(String etiquette) {
        this.etiquette = etiquette;
    }

    public String getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }

    @Override
    public Object acceptValid(ExprVisiteurValid e) {
        return e.visitValid(this);
    }

    @Override
    public Object acceptExec(ExprVisiteurExec e) {
        return null;
    }
}
