package com.erwan;

/**
 * Created by Erwan&Jonathan on 17/02/16.
 */
public interface ExprVisiteurExec {
    Object visitExec(Etat e);
    Object visitExec(Transition t);
    Object visitExec(Automate a);
    Object visitExec(Label l);
}
