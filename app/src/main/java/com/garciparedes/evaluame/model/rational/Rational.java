package com.garciparedes.evaluame.model.rational;


import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Modeling class Rational Numbers.
 * 
 * @author garciparedes
 *
 */
@ParseClassName(Rational.CLASS_NAME)
public class Rational extends ParseObject {


	
	/**
	 * ******************************** Attributes of the class ********************************
	 */


	public static final String CLASS_NAME = "Rational";


	private static final int UNIT = 1;

    private static final String DENOMINATOR_TYPE = "denominator";
    private static final String NUMERATOR_TYPE = "numerator";

	
	/**
	 * ********************** Static Constructors ********************************
	 */



    /**
     * Static constructor of <code>Racional</code>.
     *
     * Initialized to zero the Rational object.
     */
    public static Rational newInstance(){
        Rational rational = new Rational();
        rational.setDenominator(UNIT);
        rational.setNumerator(0);

        return rational;
    }



    /**
     * Static constructor of <code>Racional</code>.
     *
     * Initialized to value parametter the Rational object.
     *
     * @param value int whit initialization value.
     */
    public static Rational newInstance(int value){
        Rational rational = newInstance();
        rational.setNumerator(value);

        return rational;
    }



    /**
     * Static constructor of <code>Racional</code>.
     *
     * Initialized to value parametter the Rational object.
     *
     *
     * The code to convert double to Rational was obtained
     * from StackOverFlow
     * <a href="http://stackoverflow.com/a/14014200">http://stackoverflow.com/a/14014200</a>
     *
     * @param value double whit initialization value.
     */
    public static Rational newInstance(double value){
        Rational rational = newInstance();

        String s = String.valueOf(value);
        int digitsDec = s.length() - 1 - s.indexOf('.');

        int denom = 1;
        for(int i = 0; i < digitsDec; i++){
            value *= 10;
            denom *= 10;
        }
        int num = (int) Math.round(value);

        rational.setNumerator(num);
        rational.setDenominator(denom);

        return rational;
    }



    /**
     * Static constructor of <code>Racional</code>.
     *
     * Initialized the instance with the values of
     * parametters <code>numerator</code> y <code>denominator</code>.
     *
     * It uses <code>reduce</code>
     * to keep minimum value size consistency.
     *
     * @param numerator int with initialization value of numerator.
     * @param denominator int with initialization value of denominator.
     */
    public static Rational newInstance(int numerator, int denominator){
        Rational rational = newInstance();
        rational.setNumerator(numerator);
        rational.setDenominator(denominator);

        return rational;
    }



    /**
     * ******************************** Constructors ********************************
     */



	/**
	 * Constructor of <code>Racional</code>.
	 * 
	 */
	public Rational(){
        // A default constructor is required.
	}



	/**
	 * ******************************** Getters and Setters ********************************
	 */



	/**
	 * Setter of numerator attribute.
	 * 
	 * It uses <code>reduce</code> method
	 * to keep minimum value size consistency.
	 * 
	 * @param value int with new numerator's value.
	 */
	public void setNumerator(int value){
        put(NUMERATOR_TYPE, value);

        reduce();
	}
	


	/**
	 * Setter of denominator.
	 * 
	 * It uses <code>reduce</code> method
     * to keep minimum value size consistency.
	 * 
	 * @param value int with new denominator's value.
	 */
	public void setDenominator(int value){
        put(DENOMINATOR_TYPE, value);
		
		reduce();
	}
	


	/**
	 * Getter of numerator.
	 * 
	 * @return numerator int with numerator's value.
	 */
	public int getNumerator(){
        return getInt(NUMERATOR_TYPE);

    }
	


	/**
	 * Getter of denominator.
	 * 
	 * @return denominator int with denominator's value.
	 */
	public int getDenominator(){
        return getInt(DENOMINATOR_TYPE);

    }


	
	/**
	 * Funtion which returns if <code>this</code> is entire.
	 *  
	 * @return isEntere boolean Boolean value about <code>this</code> is entire.
	 */
	public boolean isEntire(){
		return getDenominator() == 1;
	}
	


	/**
	 * ******************************** Public Mehods ********************************
	 */
	


	/**
     * Procedure which is responsible to keep
     * minimum value size consistency to anothers that
     * represents same value.
	 */
	public void reduce(){
		// abs values:
		int absNum = Math.abs(getNumerator());
		int absDen = Math.abs(getDenominator());
		
		// denominator sign:
		int signo = getDenominator()/absDen;
		int s = mcd(absNum,absDen);

        put(NUMERATOR_TYPE, signo*(getNumerator()/s));
        put(DENOMINATOR_TYPE, signo*(getDenominator()/s));


    }
	


	/**
     * Funtion which is responsible to add
     * invocant object with the <code>value</code>
     * parametter.
	 * 
	 * @param value Rational Second value of the operation.
	 * @return result Rational Result of the operation.
	 */
	public Rational add(Rational value){
		int numerador = getNumerator() * value.getDenominator()
				+ getDenominator() * value.getNumerator();
		
		int denominador = getDenominator() * value.getDenominator();
		
		return newInstance(numerador, denominador);
	}


    /**
     * Funtion which is responsible to subtract
     * invocant object with the <code>value</code>
     * parametter.
     *
     * @param value Rational Second value of the operation.
     * @return result Rational Result of the operation.
     */
	public Rational subtract(Rational value) {
		
		int numerador = getNumerator() * value.getDenominator()
				- getDenominator() * value.getNumerator();
		
		int denominador = getDenominator() * value.getDenominator();
		
		return newInstance(numerador, denominador);
	}


    /**
     * Funtion which is responsible to multiply
     * invocant object with the <code>value</code>
     * parametter.
     *
     * @param value Rational Second value of the operation.
     * @return result Rational Result of the operation.
     */

	public Rational multiply(Rational value){
		return newInstance(getNumerator() * value.getNumerator(),
                getDenominator() * value.getDenominator());
	}



    /**
     * Funtion which is responsible to multiply
     * invocant object with the <code>value</code>
     * parametter.
     *
     * @param value double Second value of the operation.
     * @return result Rational Result of the operation.
     */
	public Rational multiply(double value) {
		return multiply(newInstance(value));
	}



    /**
     * Funtion which is responsible to divide
     * invocant object with the <code>value</code>
     * parametter.
     *
     * @param value Rational Second value of the operation.
     * @return result Rational Result of the operation.
     */
	public Rational divide(Rational value){
		return newInstance(getNumerator() * value.getDenominator(),
                getDenominator() * value.getNumerator());
	}



    /**
     * Funtion which is responsible to divide
     * invocant object with the <code>value</code>
     * parametter.
     *
     * @param value double Second value of the operation.
     * @return result Rational Result of the operation.
     */
	public Rational divide(double value) {
		return divide(newInstance(value));
	}
	


	/**
     * Method that Override toString() of the <code>Object</code> class.
     *
     * If denominator is equals to 1 it returns only numerator.
     *
     * @return String whit "numerator/denominator" format.
     */
	@Override
	public String toString(){
		if (isEntire()){
			return Integer.toString(getNumerator());
		} else {
			return (getNumerator() + "/" + getDenominator());
		}
	}



	/**
	 * ******************************** Private Methods ********************************
	 */



	/**
	 * Funtion that returns mcd of two ints.
	 *  
	 * @param a int First integer.
	 * @param b int Second integer.
	 * @return mcd int MCD of a and b.
	 */
	private int mcd(int a, int b) {
	     int r;
	     while (a % b != 0) {
	       r = a % b;
	       a = b;
	       b = r;
	     }
	     return b;
	}
}
