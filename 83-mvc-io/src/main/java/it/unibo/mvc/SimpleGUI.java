package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Integer PROPORTION = 4;


    public SimpleGUI() {
        final SimpleController controller = new SimpleController();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel bottonCanvas = new JPanel();
        bottonCanvas.setLayout(new BorderLayout());
        final JTextField txtField = new JTextField();
        final JTextArea txtArea = new JTextArea();
        final JButton print = new JButton("Print");
        final JButton history = new JButton("Show history");


        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller.setNextStringToPrint(txtField.getText());
                controller.printCurrentList();
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                final List<String> history = controller.getStirngsHistory();
                for (final String print : history) {
                    txtArea.append(print);
                    txtArea.append("/n");
                }
            }

        });

        canvas.add(txtField, BorderLayout.NORTH);
        canvas.add(txtArea, BorderLayout.CENTER);
        canvas.add(bottonCanvas, BorderLayout.SOUTH);
        bottonCanvas.add(print, BorderLayout.LINE_START);
        bottonCanvas.add(history, BorderLayout.LINE_END);
        

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
    
    }

    private void display() {
        frame.setVisible(true);
    }
    
    public static void main(String... args) {
        new SimpleGUI().display();
    }

}

