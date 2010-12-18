package jia.begins.examples.basics.ventseslav.marinov;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Exercise8 extends JFrame implements ActionListener {

    final int MAX_INPUT_LENGTH = 20;
    final int INPUT_MODE = 0;
    final int RESULT_MODE = 1;
    final int ERROR_MODE = 2;
    int displayMode;

    boolean clearOnNextDigit;
    double lastNumber;
    String lastOperator;
    private JTextField result;
    private JButton jbnButtons[];
    private JComponent component;

    private static final Font BIGGER_FONT = new Font("monspaced", Font.PLAIN,20);



    public Exercise8() {
        this.init();
        for (int i = 0; i < jbnButtons.length; i++) {
            jbnButtons[i].addActionListener(this);
        }
    }



    public JComponent getComponent() {
        return component;

    }



    private void init() {

        result = new JTextField("0", 12);
        result.setEnabled(false);
        result.setFont(BIGGER_FONT);
        result.setHorizontalAlignment(JTextField.RIGHT);


        component = new JPanel(new BorderLayout());

        JPanel jplButtons = new JPanel();
        GridLayout grid = new GridLayout(4, 3, 2, 2);
        jplButtons.setLayout(grid);

        component.add(result, BorderLayout.NORTH);
        component.add(jplButtons, BorderLayout.CENTER);

        jbnButtons = new JButton[19];

        for (int i = 0; i <= 9; ++i) {
            jbnButtons[i] = new JButton(String.valueOf(i));
            jbnButtons[i].setFont(BIGGER_FONT);
        }

        jbnButtons[10] = new JButton("+");
        jbnButtons[11] = new JButton("-");
        jbnButtons[12] = new JButton("*");
        jbnButtons[13] = new JButton("/");
        jbnButtons[14] = new JButton(".");
        jbnButtons[15] = new JButton("=");
        jbnButtons[16] = new JButton("Backspace");
        jbnButtons[17] = new JButton("CE"); 
        jbnButtons[18] = new JButton("C");
        
        jplButtons.add(jbnButtons[7]);
        jplButtons.add(jbnButtons[8]);
        jplButtons.add(jbnButtons[9]);
        jplButtons.add(jbnButtons[13]);
        jplButtons.add(jbnButtons[16]);
        jplButtons.add(jbnButtons[4]);
        jplButtons.add(jbnButtons[5]);
        jplButtons.add(jbnButtons[6]);
        jplButtons.add(jbnButtons[12]);
        jplButtons.add(jbnButtons[17]);
        jplButtons.add(jbnButtons[1]);
        jplButtons.add(jbnButtons[2]);
        jplButtons.add(jbnButtons[3]);
        jplButtons.add(jbnButtons[11]);
        jplButtons.add(jbnButtons[18]);
        jplButtons.add(jbnButtons[0]);
        jplButtons.add(jbnButtons[14]);
        jplButtons.add(jbnButtons[15]);
        jplButtons.add(jbnButtons[10]);

        clearAll();

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(this.getComponent(), BorderLayout.CENTER);
        frame.pack();
        frame.setSize(515, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }



    private void addDigitToDisplay(int digit) {
        if (clearOnNextDigit)
            setDisplayString("");

        String inputString = getDisplayString();

        if (inputString.indexOf("0") == 0) {
            inputString = inputString.substring(1);
        }

        if ((!inputString.equals("0") || digit > 0)
                && inputString.length() < MAX_INPUT_LENGTH) {
            setDisplayString(inputString + digit);
        }

        displayMode = INPUT_MODE;
        clearOnNextDigit = false;
    }



    private void setDisplayString(String s) {
        result.setText(s);
    }



    String getDisplayString() {
        return result.getText();
    }



    private void clearAll() {
        setDisplayString("0");
        lastOperator = "0";
        lastNumber = 0;
        displayMode = INPUT_MODE;
        clearOnNextDigit = true;
    }

    private void clearExisting(){
        setDisplayString("0");
        clearOnNextDigit = true;
        displayMode = INPUT_MODE;
    }

    private void processOperator(String op) {
        if (displayMode != ERROR_MODE) {
            double numberInDisplay = getNumberInDisplay();

            if (!lastOperator.equals("0")) {
                try {
                    double result = processLastOperator();
                    displayResult(result);
                    lastNumber = result;
                }

                catch (DivideByZeroException e) {

                }
            }

            else {
                lastNumber = numberInDisplay;
            }

            clearOnNextDigit = true;
            lastOperator = op;
        }
    }



    private void processEquals() {
        double result = 0;

        if (displayMode != ERROR_MODE) {
            try {
                result = processLastOperator();
                displayResult(result);
            }

            catch (DivideByZeroException e) {
                displayError("Cannot divide by zero!");
            }

            lastOperator = "0";
        }
    }



    private void addDecimalPoint() {
        displayMode = INPUT_MODE;

        if (clearOnNextDigit)
            setDisplayString("");

        String inputString = getDisplayString();

        // If the input string already contains a decimal point, don't
        // do anything to it.
        if (inputString.indexOf(".") < 0) {
            setDisplayString(new String(inputString + "."));
        }
    }



    private double processLastOperator() throws DivideByZeroException {
        double result = 0;
        double numberInDisplay = getNumberInDisplay();

        if (lastOperator.equals("/")) {
            if (numberInDisplay == 0)
                throw (new DivideByZeroException());

            result = lastNumber / numberInDisplay;
        }

        if (lastOperator.equals("*")) {
            result = lastNumber * numberInDisplay;
        }
        if (lastOperator.equals("-")) {
            result = lastNumber - numberInDisplay;
        }
        if (lastOperator.equals("+")) {
            result = lastNumber + numberInDisplay;
        }
        return result;
    }



    private double getNumberInDisplay() {
        String input = result.getText();
        return Double.parseDouble(input);
    }



    private void displayResult(double result) {
        setDisplayString(Double.toString(result));
        lastNumber = result;
        displayMode = RESULT_MODE;
        clearOnNextDigit = true;
    }



    private void displayError(String errorMessage) {
        setDisplayString(errorMessage);
        lastNumber = 0;
        displayMode = ERROR_MODE;
        clearOnNextDigit = true;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < jbnButtons.length; i++) {

            if (e.getSource() == jbnButtons[i]) {
                switch (i) {
                    case 0:
                        addDigitToDisplay(i);
                        break;

                    case 1:
                        addDigitToDisplay(i);
                        break;

                    case 2:
                        addDigitToDisplay(i);
                        break;

                    case 3:
                        addDigitToDisplay(i);
                        break;

                    case 4:
                        addDigitToDisplay(i);
                        break;

                    case 5:
                        addDigitToDisplay(i);
                        break;

                    case 6:
                        addDigitToDisplay(i);
                        break;

                    case 7:
                        addDigitToDisplay(i);
                        break;

                    case 8:
                        addDigitToDisplay(i);
                        break;

                    case 9:
                        addDigitToDisplay(i);
                        break;

                    case 10:
                        processOperator("+");
                        break;

                    case 11:
                        processOperator("-");
                        break;

                    case 12:
                        processOperator("*");
                        break;

                    case 13:
                        processOperator("/");
                        break;

                    case 14:
                        addDecimalPoint();
                        break;

                    case 15:
                        processEquals();
                        break;
                        
                    case 16:
                        if (displayMode != ERROR_MODE){
                            setDisplayString(getDisplayString().substring(0,
                                      getDisplayString().length() - 1));
                            
                            if (getDisplayString().length() < 1)
                                setDisplayString("0");
                        }
                        break;

                    case 17:
                        clearExisting();
                        break;

                    case 18:
                        clearAll();
                        break;

                }
            }
        }

    }

    class DivideByZeroException extends Exception{
        public DivideByZeroException()
        {
            super();
        }
        
        public DivideByZeroException(String s)
        {
            super(s);
        }
    }

    public static void main(String[] args) {

        new Exercise8();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception unused) {

        }

    }

}

