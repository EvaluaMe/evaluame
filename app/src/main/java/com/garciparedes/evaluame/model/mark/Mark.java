package com.garciparedes.evaluame.model.mark;

import com.garciparedes.evaluame.model.rational.Rational;
import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by garciparedes on 31/7/15.
 */
@ParseClassName(Mark.CLASS_NAME)
public class Mark extends ParseObject {

    public static final String CLASS_NAME = "Mark";

    private static final String SCORE_TYPE = "score";
    private static final String PROOF_TYPE = "proof";


    public Mark(){

    }


    public void setScore(Rational score){
        put(SCORE_TYPE, score);
    }

    public void setProof(){

    }
}
