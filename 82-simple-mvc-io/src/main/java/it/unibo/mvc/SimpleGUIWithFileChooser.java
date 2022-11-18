package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();
    private final Integer PROPORTION = 4;


    public SimpleGUIWithFileChooser() {
        final Controller controller = new Controller();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel canvas2 = new JPanel();
        canvas2.setLayout(new BorderLayout());
        final JTextArea txtArea = new JTextArea();
        final JTextField txtField = new JTextField();
        txtField.setText(controller.getCurrentFilePath());
        
        final JButton browse = new JButton("Browse...");
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    controller.save(txtArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "there was an error", JOptionPane.ERROR_MESSAGE);
                }                
            }            
        });

        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                final JFileChooser fileChooser = new JFileChooser();
                final int res =  fileChooser.showSaveDialog(frame);
                switch (res) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newDestination = fileChooser.getSelectedFile();
                        controller.setFile(newDestination);
                        txtField.setText(controller.getCurrentFilePath());                                              
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, res, "there was an error", JOptionPane.ERROR_MESSAGE);
                }
                
            }

        });

       


        canvas.add(canvas2, BorderLayout.NORTH);
        canvas.add(txtArea, BorderLayout.CENTER);
        canvas2.add(txtField, BorderLayout.CENTER);
        canvas2.add(browse, BorderLayout.LINE_END);
        canvas.add(save, BorderLayout.SOUTH);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        txtField.setEditable(false);

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
            new SimpleGUIWithFileChooser().display();
        }
}
