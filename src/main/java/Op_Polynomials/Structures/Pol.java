package main.java.Op_Polynomials.Structures;

import java.text.DecimalFormat;
import java.util.*;

public class Pol
{
    private ArrayList<Mon> elements;

    public ArrayList<Mon> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Mon> elements) {
        this.elements = elements;
    }

    //constructor
    public Pol (String fromInput)
    {
        this.elements = new ArrayList<Mon>();
        interpret(fromInput);
    }
    public Pol(ArrayList<Mon> elements)
    {
        this.elements =elements;
    }

    //interpret the coef and exp of a monome
    public void readMonomeCases(String[] co_ex,Mon build)
    {        //I used nested ifs instead of switch to avoid redundant verifications
        try {//I'll put the more probable cases first
            if (co_ex.length != 1) {
                if (co_ex[0].equals("-"))//-x...
                    build.setCoef(-1.0f);
                else if (co_ex[0].equals("+"))//+x...
                    build.setCoef(+1.0f);
                else build.setCoef(Float.parseFloat(co_ex[0]));//co*x...
                if (!co_ex[1].equals("x"))// ...x^ex
                        build.setExp(Integer.parseInt(co_ex[1].substring(2)));
            } else {
                if (!co_ex[0].equals("x")) //default "x"
                {
                    if (co_ex[0].startsWith("x")) // "x^ex"
                    {
                        build.setExp(Integer.parseInt(co_ex[0].substring(2)));
                    } else {// "co"
                        build.setExp(0);
                        build.setCoef(Float.parseFloat(co_ex[0]));
                    }
                }
            }
        }catch (Exception e){return;}
    }

    //interpret the given string
    public void interpret ( String given ) {
        elements.clear();//Special cases
        given = given.replaceAll("[ *]", "");
        given = given.replaceAll("[qwertyuiopasdfghjklzcvbnmQWERTYUIOPASDFGHJKLZCVBNMX]", "x");
        if (given.equals("")) {
            elements.add(new Mon(0, 0));
            return;
        }
        String[] Mons = given.split("(?=[+-])");//The string is separated into monome strings
        for (String oneMon : Mons) {
            Mon build = new Mon(1, 1);
            boolean key = false;
            String[] co_ex = oneMon.split("(?=[x])");//to identify easier coefs and exps.
            if ((co_ex[0].equals("-") || co_ex[0].equals("+")) && (co_ex.length == 1 || !co_ex[1].startsWith("x")))
                break;//Special cases that are wrongly interpreted and pass through try-catch
            readMonomeCases(co_ex, build);
            for (Mon existent : elements) {
                if (build.getExp() == existent.getExp()) {
                    existent.changeCoef(build.getCoef());
                    key = true;
                    break;
                }
            }
            if (key == false) {
                elements.add(new Mon(build.getCoef(), build.getExp()));
            }
        }
        elements.removeIf(n -> (n.getCoef() == 0));
        elements.sort(new Comp());
    }


    public String formString() {//toString for polynomials
        DecimalFormat format = new DecimalFormat("#.##");
        StringBuilder computed = new StringBuilder();
        for(Mon monomes: elements) {//All cases
            boolean check=true;
            if(monomes.getCoef() ==0) {
                continue;
            }if(monomes.getCoef() ==-1) {
                computed.append("-");
                check=false;
            }
            else if(monomes.getCoef() ==1) {
                computed.append("+");
                check=false;
            } else {
                if(monomes.getCoef() >0)
                    computed.append("+");
                computed.append(format.format(monomes.getCoef()));
            }
            if(monomes.getExp() ==0) {
                if(!check) {
                    computed.append("1");
                }
                continue;
            }
            if(monomes.getExp() ==1) {
                computed.append("x");
                continue;
            }
            computed.append("x^").append(Integer.toString(monomes.getExp()));
        }
        if(computed.toString().equals("")) {
            return "0";
        }
        if(computed.toString().startsWith("+")) {
            return computed.substring(1);
        }
        return computed.toString();
    }

    //addition
    public void addition(Pol first,Pol second)
    {
        this.elements.clear();
        this.elements.addAll(first.elements);
        this.elements.addAll(second.elements);

        for(int search = 0; search< elements.size(); search++)
        {
            float aux1 = 0;
            if(elements.get(search).getCoef() !=0)
            {

                for (int add = 0; add< elements.size(); add++)
                {
                    if (elements.get(search).getExp() == elements.get(add).getExp())
                    {
                        aux1 = (aux1 + elements.get(add).getCoef());
                        elements.get(add).setCoef(0);
                    }
                }

                elements.get(search).setCoef(aux1);
            }
        }
        elements.removeIf(n ->(n.getCoef() ==0));
        return ;
    }


    //substraction
    public void subtract(Pol first, Pol second)
    {
        for(Mon aux: second.elements)
        {
            aux.setCoef(aux.getCoef() * -1);
        }
        this.addition(first,second);
    }

    //multiplication
    public  void multiply (Pol first, Pol second)
    {
        elements.clear();
        for(Mon auxF : first.elements)
        {
            for(Mon auxS: second.elements)
            {
                elements.add(new Mon(auxF.getCoef() * auxS.getCoef(), auxF.getExp() + auxS.getExp()));
            }
        }
        for(Mon search: this.elements)
        {
            if(search.getCoef() !=0)
            {
                float aux1 = 0;
                for (Mon add : elements)
                {
                    if (search.getExp() == add.getExp())
                    {
                        aux1 += add.getCoef();
                        add.setCoef(0);
                    }
                }
                search.setCoef(aux1);
            }
        }
        elements.removeIf(n ->(n.getCoef() ==0));
    }

    //clone() for Pol's
    private void PolClone(Pol cloneThis)
    {
        this.elements.clear();
        for(Mon aux : cloneThis.elements)
        {
            this.elements.add(new Mon(aux.getCoef(), aux.getExp()));
        }
    }

    //Division
    public ArrayList<Mon> Divide (String firstGen,String SecondGen)
    {
        this.elements =null;
        Pol first= new Pol(firstGen);
        Pol first2=new Pol("");
        Pol second = new Pol(SecondGen);
        Pol second2=new Pol("");
        Pol q =new Pol("");
        Pol q2=new Pol("");
        while ((first.elements.size()!=0)&&(first.elements.get(0).getExp() >= second.elements.get(0).getExp()))
        {
            q2.PolClone(q);//Clones are necesary because the current implementation destroys the input pol's
            first2.PolClone(first);
            second2.PolClone(second);
            Float co = first.elements.get(0).getCoef() / second.elements.get(0).getCoef();
            int ex = first.elements.get(0).getExp() - second.elements.get(0).getExp();
            Pol T = new Pol(co + "x^" + ex);
            Pol aux = new Pol("");
            aux.multiply(T,second2);
            q.addition(q2, T);
            first.subtract(first2,aux);
        }
        this.elements =q.elements;
        return first.elements;
    }

    //Integration
    public void Integrate ()
    {
        for(Mon aux: elements)
        {
            aux.setExp(aux.getExp()+1);
            aux.setCoef(aux.getCoef() / (aux.getExp() * 1.0f));
        }
        return;
    }

    //Derivation
    public void Derivate()
    {
        for(Mon aux: elements)
        {
            aux.setCoef(aux.getCoef() * aux.getExp());
            aux.setExp(aux.getExp()-1);

        }
    }
}