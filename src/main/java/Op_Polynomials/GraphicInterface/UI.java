package main.java.Op_Polynomials.GraphicInterface;
import main.java.Op_Polynomials.Structures.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class UI extends Frame {
    public UI() {
        //Frame design
        setSize(2000, 2000);
        setTitle("Op_Polynomial");
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Left panel
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        Panel left = new Panel();

        //Spacing empty Panel
        c.gridwidth = 1;
        c.gridx = 1;
        Panel space = new Panel();

        //Right panel
        c.gridwidth = 1;
        c.gridheight = 2;
        c.gridx = 2;
        Panel operations = new Panel();

        //Right panel design
        operations.setFont(new Font("N", Font.BOLD, 20));
        operations.setLayout(new GridLayout(10, 1));

        CheckboxGroup ops = new CheckboxGroup();
        operations.add(new Label("Choose Operation:"));
        operations.add(new Label(""));
        operations.add(new Checkbox("Add", ops, true));
        operations.add(new Checkbox("Subtract", ops, false));
        operations.add(new Checkbox("Multiply", ops, false));
        operations.add(new Checkbox("Divide", ops, false));
        operations.add(new Checkbox("Derivate", ops, false));
        operations.add(new Checkbox("Integrate", ops, false));
        operations.add(new Label(""));
        Button compute = new Button("Compute");


        left.setFont(new Font("N", Font.PLAIN, 25));
        left.setLayout(new GridLayout(3, 1));
        Panel p1Panel = new Panel();
        Panel p2Panel = new Panel();
        Panel resultsPanel = new Panel();
        p1Panel.setLayout(new GridLayout(4, 1));
        p2Panel.setLayout(new GridLayout(4, 1));
        resultsPanel.setLayout(new GridLayout(4, 1));

        //Left panel design
        CheckboxGroup selectPol = new CheckboxGroup();
        p1Panel.add(new Checkbox("Use P1:", selectPol, true));
        p2Panel.add(new Checkbox("Use P2:", selectPol, false));


        TextField pol1 = new TextField(100);
        TextField pol2 = new TextField(100);
        Label computedFirst = new Label();
        Label computedSecond = new Label();
        Pol first = new Pol(new ArrayList<>());
        Pol second= new Pol (new ArrayList<>());
        Pol result= new Pol (new ArrayList<>());
        Pol rem = new Pol(new ArrayList<>());
        Label lRem = new Label("");
        Label lResult = new Label("Result:");

        //Listeners
        compute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                first.interpret(pol1.getText());
                second.interpret(pol2.getText());
                computedSecond.setText(second.formString());
                computedFirst.setText(first.formString());;
                    if (ops.getSelectedCheckbox().getLabel() == "Add") {
                        result.addition(first, second);
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Subtract") {
                        result.subtract(first, second);
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Multiply") {
                        result.multiply(first, second);
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Divide"){
                        if(second.getElements().get(0).getCoef() ==0)
                        {
                            lRem.setText("Can't div by 0");
                            lResult.setText("");
                        }
                        else {
                            rem.setElements(result.Divide(pol1.getText(), pol2.getText()));
                            lResult.setText("Q: " + result.formString());
                            lRem.setText("Rem: " + rem.formString());
                        }
                    }
                if (ops.getSelectedCheckbox().getLabel() == "Integrate") {
                    if(selectPol.getSelectedCheckbox().getLabel()=="Use P1:") {
                        first.Integrate();
                        result.setElements(first.getElements());
                    }
                    lResult.setText("Result: " + result.formString());
                    lRem.setText("");
                    if(selectPol.getSelectedCheckbox().getLabel()=="Use P2:") {
                        second.Integrate();
                        result.setElements(second.getElements());
                    }
                    lResult.setText("Result: " + result.formString());
                    lRem.setText("");
                }
                if (ops.getSelectedCheckbox().getLabel() == "Derivate") {
                    if(selectPol.getSelectedCheckbox().getLabel()=="Use P1:") {
                        first.Derivate();
                        result.setElements(first.getElements());
                    }
                    lResult.setText("Result: " + result.formString());
                    lRem.setText("");
                    if(selectPol.getSelectedCheckbox().getLabel()=="Use P2:") {
                        second.Derivate();
                        result.setElements(second.getElements());
                    }
                    lResult.setText("Result: " + result.formString());
                    lRem.setText("");
                }
            }
        });
        pol1.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) { }
            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {
                first.interpret(pol1.getText());
                second.interpret(pol2.getText());
                computedFirst.setText(first.formString());;
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    if (ops.getSelectedCheckbox().getLabel() == "Add") {
                        result.addition(first, second);
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Subtract") {
                        result.subtract(first, second);
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Multiply") {
                        result.multiply(first, second);
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Divide"){
                        if(second.getElements().get(0).getCoef() ==0)
                        {
                            lRem.setText("Can't div by 0");
                            lResult.setText("");
                        }
                        else {
                            rem.setElements(result.Divide(pol1.getText(), pol2.getText()));
                            lResult.setText("Q: " + result.formString());
                            lRem.setText("Rem: " + rem.formString());
                        }
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Integrate") {
                        if(selectPol.getSelectedCheckbox().getLabel()=="Use P1:") {
                            first.Integrate();
                            result.setElements(first.getElements());
                        }
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                        if(selectPol.getSelectedCheckbox().getLabel()=="Use P2:") {
                            second.Integrate();
                            result.setElements(second.getElements());
                        }
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Derivate") {
                        if(selectPol.getSelectedCheckbox().getLabel()=="Use P1:") {
                            first.Derivate();
                            result.setElements(first.getElements());
                        }
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                        if(selectPol.getSelectedCheckbox().getLabel()=="Use P2:") {
                            second.Derivate();
                            result.setElements(second.getElements());
                        }
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                }
            }
        });
        pol2.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                second.interpret(pol2.getText());
                first.interpret(pol1.getText());
                computedSecond.setText(second.formString());
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    if (ops.getSelectedCheckbox().getLabel() == "Add") {
                        result.addition(first, second);
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Subtract") {
                        result.subtract(first, second);
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Multiply") {
                        result.multiply(first, second);
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Divide"){
                        if(second.getElements().get(0).getCoef() ==0)
                        {
                            lRem.setText("Can't div by 0");
                            lResult.setText("");
                        }
                        else {
                            rem.setElements(result.Divide(pol1.getText(), pol2.getText()));
                            lResult.setText("Q: " + result.formString());
                            lRem.setText("Rem: " + rem.formString());
                        }
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Integrate") {
                        if(selectPol.getSelectedCheckbox().getLabel()=="Use P1:") {
                            first.Integrate();
                            result.setElements(first.getElements());
                        }
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                        if(selectPol.getSelectedCheckbox().getLabel()=="Use P2:") {
                            second.Integrate();
                            result.setElements(second.getElements());
                        }
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                    if (ops.getSelectedCheckbox().getLabel() == "Derivate") {
                        if(selectPol.getSelectedCheckbox().getLabel()=="Use P1:") {
                            first.Derivate();
                            result.setElements(first.getElements());
                        }
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                        if(selectPol.getSelectedCheckbox().getLabel()=="Use P2:") {
                            second.Derivate();
                            result.setElements(second.getElements());
                        }
                        lResult.setText("Result: " + result.formString());
                        lRem.setText("");
                    }
                }
            }
        });

        //Added elements
        operations.add(compute);
        resultsPanel.add(lResult);
        resultsPanel.add(lRem);
        p1Panel.add(pol1);
        p2Panel.add(pol2);
        p1Panel.add(computedFirst);
        p2Panel.add(computedSecond);
        p1Panel.setBackground(Color.lightGray);
        p2Panel.setBackground(Color.lightGray);
        resultsPanel.setBackground(Color.lightGray);
        left.add(p1Panel);
        left.add(p2Panel);
        left.add(resultsPanel);

        //Spacing pane design
        space.add(new Label("                                                   "));

        //Added panes
        add(left);
        add(space);
        add(operations);

        //Cancel button listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
