package com.erwan;

import java.util.ArrayList;

/**
 * Created by Erwan&Jonathan on 02/02/16.
 */
public final class InterpreterValid extends Interpreter implements ExprVisiteurValid {

    private static InterpreterValid instance = null;
    private int compteur;

    private InterpreterValid(){
        super();
    }

    public final static InterpreterValid getInstance() {
        if (instance == null)
            instance = new InterpreterValid();
        return instance;
    }

    @Override
    public Object visitValid(Etat e) {
        boolean valid = true;

        if (e.getInitial())
            this.compteur++;
        else{
            if(e.getTransCible().size() < 1){
                valid = false;
            }
        }

        if (e.getTransSource().size() == 0 && !e.getFinale())
            valid = false;

            // Vérifie que 2 noms ne sont pas les mêmes
            ArrayList<Etat> list = e.getAutomate().getEtats();
            int i = 0, j = i + 1;
            while (i < list.size() && valid) {
                while (j < list.size() && valid) {
                    if (list.get(i).getNom().equals(list.get(j).getNom())) {
                        valid = false;
                    }
                    j++;
                }
                i++;
            }


        return valid;
    }

    @Override
    public Object visitValid(Transition t) {
        return true;
    }

    @Override
    public Object visitValid(Automate a) {



        ArrayList<Boolean> verification = new ArrayList<>();
        boolean valid = true;
        this.compteur = 0;
        boolean temp;
        for (Etat e: a.getEtats()) {
            temp  = (boolean) e.acceptValid(this);
            System.out.println("Etat Valide : " + temp);
            verification.add(temp);
            if (!temp)
                valid = false;
        }
        if (this.compteur != 1)
            valid = false;

        return valid;
    }

    @Override
    public Object visitValid(Label l) {
        return true;
    }
}
