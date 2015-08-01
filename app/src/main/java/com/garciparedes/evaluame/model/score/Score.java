package com.garciparedes.evaluame.model.score;

import com.garciparedes.evaluame.model.proof.Proof;
import com.garciparedes.evaluame.model.roles.student.Student;
import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by garciparedes on 1/8/15.
 */
@ParseClassName(this.CLASS_NAME)
public class Score extends ParseObject {



    /**
     * ******************************** Attributes of the class ********************************
     */



    public static final String CLASS_NAME = "Score";

    private static final String RESULT_TYPE = "result";
    private static final String MAX_TYPE = "max";
    private static final String STUDENT_TYPE = "student";
    private static final String PROOF_TYPE = "proof";



    /**
     * ********************** Static Constructors ********************************
     */



    /**
     * Static constructor of <code>Score</code>.
     *
     * Initialized to parameter values the Score object.
     */
    public static Score newInstance(double result, double max
            , Student student, Proof proof){

        Score score = new Score();
        score.setResult(result);
        score.setMax(max);
        score.setStudent(student);
        score.setProof(proof);

        return score;
    }





    /**
     * ******************************** Constructors ********************************
     */



    /**
     * Constructor of <code>Score</code>.
     *
     */
    public Score() {
        // A default constructor is required.
    }



    /**
     * ******************************** Getters and Setters ********************************
     */



    /**
     * Setter of result attribute.
     *
     * @param value int with new numerator's value.
     */
    public void setResult(double value){
        put(RESULT_TYPE, value);
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
     * Setter of student attribute.
     *
     * @param value with student of this score.
     */
    public void setStudent(Student value){
        put(STUDENT_TYPE, value);
    }



    /**
     * Setter of proof attribute.
     *
     * @param value with proof of this score.
     */
    public void setProof(Proof value){
        put(PROOF_TYPE, value);
    }



    /**
     * Getter of result.
     *
     * @return result int with result's value.
     */
    public double getResult(){
        return getDouble(RESULT_TYPE);
    }



    /**
     * Getter of max.
     *
     * @return max int with max's value.
     */
    public double getMax(){
        return getDouble(MAX_TYPE);
    }


    /**
     * Getter of student.
     *
     * @return student of this score.
     */
    public Student getStudent(){
        return (Student) get(STUDENT_TYPE);
    }


    /**
     * Getter of proof.
     *
     * @return proof of this score.
     */
    public Proof getProof(){
        return (Proof) get(PROOF_TYPE);
    }



    /**
     * ******************************** Public Mehods ********************************
     */



}
