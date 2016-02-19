package com.erwan;

/**
 * Created by Erwan&Jonathan on 02/02/16.
 */
public interface ExprVisiteurValid {
    Object visitValid(Etat e);
    Object visitValid(Transition t);
    Object visitValid(Automate a);
    Object visitValid(Label l);
}
