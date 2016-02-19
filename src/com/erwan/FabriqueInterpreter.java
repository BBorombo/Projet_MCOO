package com.erwan;

/**
 * Created by Erwan&Jonathan on 17/02/16.
 */
public class FabriqueInterpreter {

    private  static FabriqueInterpreter instance = null;

    private FabriqueInterpreter(){
        super();
    }

    public static FabriqueInterpreter getInstance() {
        if (instance == null)
            instance = new FabriqueInterpreter();
        return  instance;
    }

    public Interpreter getInterpreter(String type){
        if (type.equals("Validation"))
            return InterpreterValid.getInstance();
        else//if (type.equals("Execution"))
            return InterpreterExec.getInstance();

    }
}
