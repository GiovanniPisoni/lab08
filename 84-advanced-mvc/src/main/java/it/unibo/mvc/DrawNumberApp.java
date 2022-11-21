package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private int MIN;
    private int MAX;
    private int ATTEMPTS;
    private static final String PATH = "config.yml";

    private final DrawNumber model;
    private final List<DrawNumberView> views;
    

    /**
     * @param views
     *            the views to attach
     */
    public DrawNumberApp(final DrawNumberView... views) throws IOException, Exception{
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        try (var input = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(PATH)))) {
           for (var line = input.readLine(); line != null; line = input.readLine()) {
                final String [] splitted = line.split(":");
                if (splitted.length == 2) {
                    if (splitted[0].contains("minimum")) {
                        MIN = Integer.parseInt(splitted[1].trim()); // TRIM SERVE PER TOGLIERE GLI SPAZI BIANCHI PRIMA E DOPO DELLA PAROLA PRESDA IN QUESTIONE
                    } else if (splitted[0].contains("maximum")) {
                        MAX = Integer.parseInt(splitted[1].trim());
                    } else if (splitted[0].contains("attempts")) {
                        ATTEMPTS = Integer.parseInt(splitted[1].trim());
                    } else {
                        throw new Exception("No max, min or attempts");
                    }
                } else {
                    throw new IOException("Error whit reading config file");
                }
           }
        }
        this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException, IOException, Exception {
        new DrawNumberApp(new DrawNumberViewImpl(), new DrawNumberViewImpl(),
                          new PrintStreamView("outputs.log") , new PrintStreamView(System.out));
    }

}
