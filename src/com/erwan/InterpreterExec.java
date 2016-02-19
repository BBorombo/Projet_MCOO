package com.erwan;

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
