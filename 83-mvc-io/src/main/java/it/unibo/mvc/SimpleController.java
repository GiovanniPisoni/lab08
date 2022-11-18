package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> stringHistory = new LinkedList<>();
    private String nextString;

    @Override
    public void setNextStringToPrint(String nextString) {
        this.nextString = Objects.requireNonNull(nextString, "The string could not be null");
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public List<String> getStirngsHistory() {
        return stringHistory;
    }

    @Override
    public void printCurrentList() {
        if (nextString == null) {
            throw new IllegalStateException("NO STRING SET");
        }
        stringHistory.add(nextString);
        System.out.println(nextString);
        nextString = null;
    }

}
