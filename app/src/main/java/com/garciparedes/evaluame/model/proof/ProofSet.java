package com.garciparedes.evaluame.model.proof;

import com.parse.ParseClassName;

import java.util.ArrayList;

/**
 * Created by garciparedes on 1/8/15.
 */
@ParseClassName(ProofSet.CLASS_NAME)
public class ProofSet extends Proof {

    public static final String CLASS_NAME = "ProofSet";

    private static final String PROOF_SET_TYPE = "proofSet";






    public void setProofSet(ArrayList<IndividualProof> individualProofs){

    }



    public ArrayList<IndividualProof> getProofSet(){
        return null;
    }



    public void addIndividualProof(IndividualProof individualProof){

    }



    @Override
    public double getMax() {
        return 0;
    }
}
