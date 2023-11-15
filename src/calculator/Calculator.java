package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


/**
 * ButtonClickEvent class implements ActionListener and controls the operations to be handled whenever any button is clicked.
 * It is the base class for all the buttons being pressed
 */
class ButtonClickEvent implements ActionListener{
    protected JTextField textField;

    /**
     *
     * @param textField is the displayed text field
     */
    public ButtonClickEvent(JTextField textField){
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

/**
 * Class for the object that's created when a number button is clicked
 */
class NumberBtnClicked extends ButtonClickEvent {
    private int number;

    /**
     *
     * @param textField is the displayed textField
     * @param number is the number being input by pressing that button
     */
    public NumberBtnClicked(JTextField textField, int number){
        super(textField);
        this.number = number;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (textField.getText().equals("0") || textField.getText().equals("-0")){
            textField.setText(String.valueOf(number));
        }
        else {
            textField.setText(textField.getText() + String.valueOf(number));
        }
    }

}


/**
 * Class that handles when an operator button is clicked
 * Has static variables so that the leftOperand and operator pressed can be used when "=" is pressed
 */
class OperatorButtonClickEvent extends ButtonClickEvent{
    private static String operator = "";
    private static double leftOperand;

    /**
     *
     * @param textField is the displayed textField
     * @param operator is the operator that was clicked
     */
    public OperatorButtonClickEvent(JTextField textField, String operator){
        super(textField);
        OperatorButtonClickEvent.operator = "";
    }

    /**
     *
     * getters and setters for the static variables
     */
    public String getOperator(){
        return operator;
    }
    public double getLeftOperand() {
        return leftOperand;
    }
    public void setLeftOperand(double leftOperand){
        OperatorButtonClickEvent.leftOperand = leftOperand;
    }
    public void setOperator(String operator){
        OperatorButtonClickEvent.operator = operator;
    }

}

/**
 * handles when the "+" is clicked
 */
class AddBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the current text field
     */
    public AddBtnClicked(JTextField textField){
        super(textField, "+");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        setLeftOperand(Double.parseDouble(textField.getText()));
        setOperator("+");
        textField.setText("");
    }
}

/**
 * handles when the "-" button is clicked
 */
class MinusBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the current text field
     */
    public MinusBtnClicked(JTextField textField){
        super(textField, "-");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        setLeftOperand(Double.parseDouble(textField.getText()));
        setOperator("-");
        textField.setText("");
    }
}

/**
 * handles when the multiply button is clicked
 */
class MulBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public MulBtnClicked(JTextField textField){
        super(textField, "*");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        setLeftOperand(Double.parseDouble(textField.getText()));
        setOperator("*");
        textField.setText("");
    }
}

/**
 * handles when the "/" button is clicked
 */
class DivBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public DivBtnClicked(JTextField textField){
        super(textField, "/");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        setLeftOperand(Double.parseDouble(textField.getText()));
        setOperator("/");
        textField.setText("");
    }
}

/**
 * handles when the clear button is pressed
 */
class ClrBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public ClrBtnClicked(JTextField textField){
        super(textField, "");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        setLeftOperand(0.0);
        setOperator("");
        textField.setText("0");
    }
}

/**
 * handles when the decimal button is pressed
 */
class DecBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public DecBtnClicked(JTextField textField){
        super(textField, ".");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        textField.setText(textField.getText() + ".");
    }
}

/**
 * handles when the sign button is clicked
 */
class SignBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public SignBtnClicked(JTextField textField){
        super(textField, "-/+");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (textField.getText().charAt(0)=='-'){
            textField.setText(textField.getText().substring(1));
        }
        //else if (textField.getText())
        else {
            textField.setText("-" + textField.getText());
        }
    }
}

/**
 * handles when the percent button is clicked
 */
class PercentBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public PercentBtnClicked(JTextField textField){
        super(textField, "%");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String text = textField.getText();
        double num = Double.parseDouble(text);
        double result = num / 100;
        DecimalFormat decimalFormat = new DecimalFormat("#.############");
        String formattedRes = decimalFormat.format(result);
        textField.setText(formattedRes);
    }
}

/**
 * handles when the square root button is clicked
 */
class SqrRtBtnClicked extends OperatorButtonClickEvent{
    /**
     *
     * @param textField is the displayed text field
     */
    public SqrRtBtnClicked(JTextField textField){
        super(textField, "√");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        double num = Double.parseDouble(textField.getText());
        if (num<0){
            textField.setText("ERROR: Double check input. Press AC.");
            return;
        }
        double res = Math.sqrt(num);
        DecimalFormat decimalFormat = new DecimalFormat("#.############");
        String formattedRes = decimalFormat.format(res);
        textField.setText(formattedRes);
    }
}

/**
 * handles when the log button is clicked
 */
class LogBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public LogBtnClicked(JTextField textField){
        super(textField, "ln");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        double num = Double.parseDouble(textField.getText());
        if (num<=0){
            textField.setText("ERROR: Double check input. Press AC.");
            return;
        }
        double res = Math.log(num);
        DecimalFormat decimalFormat = new DecimalFormat("#.############");
        String formattedRes = decimalFormat.format(res);
        textField.setText(formattedRes);
    }
}

/**
 * handles when the square button is clicked
 */
class SquaredBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public SquaredBtnClicked(JTextField textField){
        super(textField, "x^2");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        double num = Double.parseDouble(textField.getText());
        double res = num * num;
        DecimalFormat decimalFormat = new DecimalFormat("#.############");
        String formattedRes = decimalFormat.format(res);
        textField.setText(formattedRes);
    }
}

/**
 * handles when the power button is clicked
 */
class PowerBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public PowerBtnClicked(JTextField textField){
        super(textField, "x^y");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        setLeftOperand(Double.parseDouble(textField.getText()));
        setOperator("x^y");
        textField.setText("");
    }
}

/**
 * handles when the equal button is clicked
 */
class EqBtnClicked extends OperatorButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */

    public EqBtnClicked(JTextField textField){
        super(textField, "");
    }

    @Override
    public void actionPerformed(ActionEvent e){

            double leftOperand = getLeftOperand();
            String operator = getOperator();
            double rightOperand = Double.parseDouble(textField.getText());
            double result = 0;
            boolean errorMsg = false;
            switch (operator) {
                case "+":
                    result = leftOperand + rightOperand;
                    break;
                case "-":
                    result = leftOperand - rightOperand;
                    break;
                case "*":
                    result = leftOperand * rightOperand;
                    break;
                case "/":
                    if (rightOperand==0.0){
                        errorMsg = true;
                        break;
                    }
                    result = leftOperand / rightOperand;
                    break;
                case "x^y":
                    result = Math.pow(leftOperand, rightOperand);
                    break;
                case "":
                    result = Double.parseDouble(textField.getText());
                    break;

            }

        DecimalFormat decimalFormat = new DecimalFormat("#.############");
        String formattedRes = decimalFormat.format(result);
        if (errorMsg){
            textField.setText("ERROR: Double check input. Press AC.");
        } else {
            textField.setText(formattedRes);
        }
        //textField.setText(String.valueOf(result));
        errorMsg = false;
        setOperator("");
    }
}

/**
 * handles when one of the memory buttons are clicked
 */
class MemoryButtonClickEvent extends ButtonClickEvent {
    private static String memoryOperation = "";
    private static double memValue = 0;

    /**
     *
     * @param textField is the displayed text field
     * @param memoryOperation is the pressed memory operation
     */
    public MemoryButtonClickEvent(JTextField textField, String memoryOperation){
        super(textField);
        MemoryButtonClickEvent.memoryOperation = "";
    }

   //getters and setters for the static variables. get and set for memory operation are never used but are left in case of future development.
    public String getMemoryOperation(){
        return memoryOperation;
    }
    public double getMemValue() {
        return memValue;
    }
    public void setMemValue(double memValue){
        MemoryButtonClickEvent.memValue = memValue;
    }
    public void setMemoryOperation(String memoryOperation){
        MemoryButtonClickEvent.memoryOperation = memoryOperation;
    }
}

/**
 * handles when the memory add button is pressed
 */
class MemAdd extends MemoryButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public MemAdd(JTextField textField){
        super(textField, "M+");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        double num = Double.parseDouble(textField.getText());
        double res = num + getMemValue();
        setMemValue(res);
        DecimalFormat decimalFormat = new DecimalFormat("#.############");
        String formattedRes = decimalFormat.format(res);
        setMemValue(Double.parseDouble(formattedRes));
    }
}

/**
 * handles the memory subtract button being pressed
 */
class MemSub extends MemoryButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public MemSub(JTextField textField){
        super(textField, "M-");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        double num = Double.parseDouble(textField.getText());
        double res = getMemValue() - num;
        DecimalFormat decimalFormat = new DecimalFormat("#.############");
        String formattedRes = decimalFormat.format(res);
        setMemValue(Double.parseDouble(formattedRes));
    }
}

/**
 * handles when the memory clear button is pressed
 */
class MemClear extends MemoryButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public MemClear(JTextField textField){
        super(textField, "MC");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        setMemValue(0);
    }
}

/**
 * handles when the memory recall button is pressed
 */
class MemRecall extends MemoryButtonClickEvent {
    /**
     *
     * @param textField is the displayed text field
     */
    public MemRecall(JTextField textField){
        super(textField, "MR");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        DecimalFormat decimalFormat = new DecimalFormat("#.############");
        String formattedRes = decimalFormat.format(getMemValue());
        textField.setText(formattedRes);
    }
}

/**
 * main calculator object class that sets up the UI and adds action listeners to all the buttons
 */
public class Calculator {
    private JTextField textField;
    private JButton mClearBtn;
    private JButton mRecallBtn;
    private JButton mAddBtn;
    private JButton mSubBtn;
    private JButton squareBtn;
    private JButton powerBtn;
    private JButton logBtn;
    private JButton sqrBtn;
    private JButton clearBtn;
    private JButton signBtn;
    private JButton percentageBtn;
    private JButton divideBtn;
    private JButton sevenBtn;
    private JButton eightBtn;
    private JButton nineBtn;
    private JButton mulBtn;
    private JButton fourBtn;
    private JButton fiveBtn;
    private JButton sixBtn;
    private JButton minusBtn;
    private JButton oneBtn;
    private JButton twoBtn;
    private JButton threeBtn;
    private JButton addBtn;
    private JButton zeroBtn;
    private JButton digitBtn;
    private JButton equalBtn;
    private JPanel Calculator;

    /**
     * creates the objects for every button clicked and adds a unique action listener to every button
     */
    public Calculator() {

        textField.setText("0");

        NumberBtnClicked zeroClicked = new NumberBtnClicked(textField, 0);
        zeroBtn.addActionListener(zeroClicked);
        NumberBtnClicked oneClicked = new NumberBtnClicked(textField, 1);
        oneBtn.addActionListener(oneClicked);
        NumberBtnClicked twoClicked = new NumberBtnClicked(textField, 2);
        twoBtn.addActionListener(twoClicked);
        NumberBtnClicked threeClicked = new NumberBtnClicked(textField, 3);
        threeBtn.addActionListener(threeClicked);
        NumberBtnClicked fourClicked = new NumberBtnClicked(textField, 4);
        fourBtn.addActionListener(fourClicked);
        NumberBtnClicked fiveClicked = new NumberBtnClicked(textField, 5);
        fiveBtn.addActionListener(fiveClicked);
        NumberBtnClicked sixClicked = new NumberBtnClicked(textField, 6);
        sixBtn.addActionListener(sixClicked);
        NumberBtnClicked sevenClicked = new NumberBtnClicked(textField, 7);
        sevenBtn.addActionListener(sevenClicked);
        NumberBtnClicked eightClicked = new NumberBtnClicked(textField, 8);
        eightBtn.addActionListener(eightClicked);
        NumberBtnClicked nineClicked = new NumberBtnClicked(textField, 9);
        nineBtn.addActionListener(nineClicked);

        AddBtnClicked addClicked = new AddBtnClicked(textField);
        addBtn.addActionListener(addClicked);
        MinusBtnClicked minClicked = new MinusBtnClicked(textField);
        minusBtn.addActionListener(minClicked);
        MulBtnClicked mulClicked = new MulBtnClicked(textField);
        mulBtn.addActionListener(mulClicked);
        DivBtnClicked divClicked = new DivBtnClicked(textField);
        divideBtn.addActionListener(divClicked);
        ClrBtnClicked clrClicked = new ClrBtnClicked(textField);
        clearBtn.addActionListener(clrClicked);
        EqBtnClicked eqClicked = new EqBtnClicked(textField);
        equalBtn.addActionListener(eqClicked);
        DecBtnClicked decClicked = new DecBtnClicked(textField);
        digitBtn.addActionListener(decClicked);
        SignBtnClicked signClicked = new SignBtnClicked(textField);
        signBtn.addActionListener(signClicked);
        PercentBtnClicked percentClicked = new PercentBtnClicked(textField);
        percentageBtn.addActionListener(percentClicked);
        SqrRtBtnClicked sqrRtClicked = new SqrRtBtnClicked(textField);
        sqrBtn.addActionListener(sqrRtClicked);
        LogBtnClicked logClicked = new LogBtnClicked(textField);
        logBtn.addActionListener(logClicked);
        SquaredBtnClicked sqrClicked = new SquaredBtnClicked(textField);
        squareBtn.addActionListener(sqrClicked);
        PowerBtnClicked powClicked = new PowerBtnClicked(textField);
        powerBtn.addActionListener(powClicked);
        MemAdd memAddClicked = new MemAdd(textField);
        mAddBtn.addActionListener(memAddClicked);
        MemClear memClrClicked = new MemClear(textField);
        mClearBtn.addActionListener(memClrClicked);
        MemSub memSubClicked = new MemSub(textField);
        mSubBtn.addActionListener(memSubClicked);
        MemRecall memRecallClicked = new MemRecall(textField);
        mRecallBtn.addActionListener(memRecallClicked);
    }

    /**
     *
     * @param button is the button clicked for running test scripts
     */
    public void test( String button){
        switch (button){
            case "0": zeroBtn.doClick();break;
            case "1": oneBtn.doClick();break;
            case "2": twoBtn.doClick();break;
            case "3": threeBtn.doClick();break;
            case "4": fourBtn.doClick();break;
            case "5": fiveBtn.doClick();break;
            case "6": sixBtn.doClick();break;
            case "7": sevenBtn.doClick();break;
            case "8": eightBtn.doClick();break;
            case "9": nineBtn.doClick();break;
            case "%": percentageBtn.doClick();break;
            case "-/+": signBtn.doClick();break;
            case "AC": clearBtn.doClick();break;
            case "*2": squareBtn.doClick();break;
            case "sqr": sqrBtn.doClick();break;
            case "log": logBtn.doClick();break;
            case ".": digitBtn.doClick();break;
            case "+": addBtn.doClick();break;
            case "-": minusBtn.doClick();break;
            case "*": mulBtn.doClick();break;
            case "/": divideBtn.doClick();break;
            case "**": powerBtn.doClick();break;
            case "M+": mAddBtn.doClick();break;
            case "M-": mSubBtn.doClick();break;
            case "MR": mRecallBtn.doClick();break;
            case "MC": mClearBtn.doClick();break;
            case "=": equalBtn.doClick();break;
            case "txt": System.out.println("The result is: " +
                    textField.getText());break;
            default:System.out.println("invalid input");break;
        }
    }

    /**
     * main method generated by the program
     * @param args main method argument
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().Calculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}




