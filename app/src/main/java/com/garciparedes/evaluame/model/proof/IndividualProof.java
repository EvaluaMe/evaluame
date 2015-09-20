package com.garciparedes.evaluame.model.proof;

import com.garciparedes.evaluame.model.score.Score;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by garciparedes on 1/8/15.
 */
@ParseClassName(IndividualProof.CLASS_NAME)
public class IndividualProof extends Proof {



    /**
     * ******************************** Attributes of the class ********************************
     */



    public static final String CLASS_NAME = "IndividualProof";

    private static final String MAX_TYPE = "max";
    private static final String SCORE_TYPE = "score";
    private static final String DATE_TYPE = "date";



    /**
     * ********************** Static Constructors ********************************
     */



    /**
     * Static constructor of <code>Score</code>.
     *
     * Initialized to parameter values the Score object.
     */
    public static IndividualProof newInstance(String name, double max, Date date){
        IndividualProof individualProof = new IndividualProof();

        individualProof.setName(name);
        individualProof.setMax(max);
        individualProof.setDate(date);

        return individualProof;
    }



    /**
     * ******************************** Constructors ********************************
     */



    /**
     * Constructor of <code>Score</code>.
     *
     */
    public IndividualProof(){
        // A default constructor is required.
    }



    /**
     * ******************************** Getters and Setters ********************************
     */



    /**
     * Setter of max attribute.
     *
     * @param value int with new numerator's value.
     */
    private void setDate(Date value){
        put(DATE_TYPE, value);
    }



    /**
     * Setter of max attribute.
     *
     * @param value int with new numerator's value.
     */
    public void setMax(double value){
        put(MAX_TYPE, value);
    }



    /**
     * Setter of max attribute.
     *
     * @param result int with new numerator's value.
     */
    public void setScore(double result ){
        Score.newInstance(result, null, this);
    }



    /**
     * Getter of max.
     *
     * @return max int with max's value.
     */
    public Date getDate(){
        return getDate(DATE_TYPE);
    }



    /**
     * Getter of max.
     *
     * @return max int with max's value.
     */
    @Override
    public double getMax(){
        return getDouble(MAX_TYPE);
    }



    /**
     * Getter of max.
     *
     * @return max int with max's value.
     */
    public Score getScore() {
        ParseQuery<Score> query = ParseQuery.getQuery(Score.CLASS_NAME);
        query.whereEqualTo(Score.PROOF_TYPE, this);
        try {
            return query.find().get(0);
        } catch (ParseException e) {
            return null;
        }
    }



    /**
     * ******************************** Public Mehods ********************************
     */



}
