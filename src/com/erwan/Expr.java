package com.erwan;

/**
 * Created by Erwan&Jonathan on 17/02/16.
 */
public interface Expr {

    Object acceptExec(ExprVisiteurExec e);
    Object acceptValid(ExprVisiteurValid e);

}
