package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
     * 
     * @param nextString next string to print
     */
    void setNextStringToPrint(String nextString);

    /**
     * 
     * @return next string to print
     */
    String getNextStringToPrint();

    /**
     * 
     * @return history of the printed strings in form of a List
     */
    List<String> getStirngsHistory();

    /**
     * 
     */
    void printCurrentList();


}
