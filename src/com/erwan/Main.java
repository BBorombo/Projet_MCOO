package com.erwan;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Automate a = new Automate();
        Etat e1 = new Etat();
        e1.setNom("Etat1");
        e1.setAutomate(a);
        e1.setInitial(true);
        Etat e2 = new Etat();
        e2.setNom("Etat2");
        e2.setAutomate(a);
        e2.setInitial(false);
        ArrayList<Etat> etats = new ArrayList();
        etats.add(e1);
        etats.add(e2);
        a.setEtats(etats);
        InterpreterValid i = (InterpreterValid) FabriqueInterpreter.getInstance().getInterpreter("Validation");
        System.out.println("Automate Valide : " + a.acceptValid(i));
    }
}
