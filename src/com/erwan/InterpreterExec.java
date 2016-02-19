package com.erwan;

import java.util.ArrayList;

/**
 * Created by Erwan&Jonathan on 17/02/16.
 */
public final class InterpreterExec extends Interpreter implements ExprVisiteurExec {

    private static InterpreterExec instance = null;

    private InterpreterExec(){
        super();
    }

    public final static InterpreterExec getInstance(){
        if (instance == null)
            instance = new InterpreterExec();

        return instance;
    }

    @Override
    public Object visitExec(Etat e) {
        return null;
    }

    @Override
    public Object visitExec(Transition t) {
        boolean valid = true;
        ArrayList listEtats = t.getAutomate().getEtats();
        if (!listEtats.contains(t.getSource()) || !listEtats.contains(t.getCible())) {
            valid = false;
            System.out.println("La transition n'appartient pas à l'automate.");
        }
        if (valid){
            t.getSource().acceptExec(this);
        }
        return valid;
    }

    @Override
    public Object visitExec(Automate a) {
        return null;
    }

    @Override
    public Object visitExec(Label l) {
        return null;
    }
}
