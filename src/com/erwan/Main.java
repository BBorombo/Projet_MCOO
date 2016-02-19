package com.erwan;

public class Main {

    public static void main(String[] args) {
        Automate a = initAutomate();
        InterpreterValid i = (InterpreterValid) FabriqueInterpreter.getInstance().getInterpreter("Validation");
        boolean autoValide = (boolean) a.acceptValid(i);
        System.out.print("Automate : ");
        if (autoValide)
            System.out.println("Valide");
        else
            System.out.println("Invalide");
    }

    public static Automate initAutomate(){
        // Création de l'automate
        Automate automate = new Automate();

        // Création des états
        Etat e1 = new Etat("Etat 1", automate, true, false);
        Etat e2 = new Etat("Etat 2",automate, false,true);
        Etat e3 = new Etat("Etat 3",automate, false,true);
        Etat e4 = new Etat("Etat 4",automate, false,false);

        // Création des labels
        Label a = new Label("a");
        Label b = new Label("b");

        // Création des transitions
        Transition t = new Transition(e1,e2,a);
        Transition t2 = new Transition(e1,e3,b);
        Transition t3 = new Transition(e2,e4,b);

        return  automate;
    }
}
