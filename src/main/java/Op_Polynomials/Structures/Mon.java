package main.java.Op_Polynomials.Structures;

public class Mon{
    private float coef;
    private int exp;
    public Mon(float coef,int exp)
    {
    this.coef=coef;
    this.exp=exp;
    }
    public  void setCoef(float coef ){this.coef=coef;}
    public  void setExp(int exp ){this.exp=exp;}
    public  float getCoef(){return coef;}
    public  int getExp(){return exp;}
    public void changeCoef (float addThis)
    {
        this.coef=this.coef+ addThis;
    }
}
