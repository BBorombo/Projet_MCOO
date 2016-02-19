package com.erwan;

public class Main {

    public static void main(String[] args) {
        // Validation de l'automate sur le premier visiteur
        Automate a = initAutomate();
        visiteurValid(a);
        // Validation de l'automate sur le second visiteur
        Transition t = a.getTransitions().get(a.getTransitions().size()-1);
        visiteurExecution(t);
    }

    /**
     * Fonction qui permet de visiter l'automate et de le
     * parcourir jusqu'a la transition voulu, si celle ci existe
     * @param t La transition à "retrouver"
     */
    public static void visiteurExecution(Transition t){
        InterpreterExec i = (InterpreterExec) FabriqueInterpreter.getInstance().getInterpreter("Execution");
        boolean autoValide = (boolean) t.acceptExec(i);

    }

    /**
     * Fonction qui permet de visiter et valider un automate
     * @param a L'automate à vérifier
     */
    public static void visiteurValid(Automate a){
        InterpreterValid i = (InterpreterValid) FabriqueInterpreter.getInstance().getInterpreter("Validation");
        boolean autoValide = (boolean) a.acceptValid(i);
        System.out.print("Automate : ");
        if (autoValide)
            System.out.println("Valide");
        else
            System.out.println("Invalide");
    }

    /**
     * Foncion qui permet d'initialiser un automate en y ajoutant
     * des état/label/transitions
     * @return l'automate complet
     */
    public static Automate initAutomate(){
        // Création de l'automate
        Automate automate = new Automate();

        // Création des états
        Etat e1 = new Etat("Etat 1", true, false);
        Etat e2 = new Etat("Etat 2", false,true);
        Etat e3 = new Etat("Etat 3", false,true);
        Etat e4 = new Etat("Etat 4", false,false);

        // Création des labels
        Label a = new Label("a");
        Label b = new Label("b");

        // Création des transitions
        Transition t1 = new Transition(e1,e2,a);
        Transition t2 = new Transition(e1,e3,b);
        Transition t3 = new Transition(e2,e4,b);

        // Ajout des états à l'automate
        automate.ajouterEtat(e1);
        automate.ajouterEtat(e2);
        automate.ajouterEtat(e3);
        automate.ajouterEtat(e4);

        // Ajout des transitions à l'automate
        automate.ajouterTransition(t1);
        automate.ajouterTransition(t2);
        automate.ajouterTransition(t3);

        return automate;
    }
}
