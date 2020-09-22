package test.java;

import org.junit.jupiter.api.Assertions;
import main.java.Op_Polynomials.Structures.Pol;

class PolTest {

    @org.junit.jupiter.api.Test
    void addition() {
        Pol F =new Pol("3x^4 + 7x^3 - 5x^2 + 4x + 2");
        Pol S =new Pol("2x^3 + x^2 - 5x +7");
        Pol REZ =new Pol("");
        REZ.addition(F,S);
        Assertions.assertEquals("3x^4+9x^3-4x^2-x+9", REZ.formString());
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        Pol F =new Pol("3x^4 + 7x^3 - 5x^2 + 4x + 2");
        Pol S =new Pol("2x^3 + x^2 - 5x +7");
        Pol REZ =new Pol("");
        Pol Expect = new Pol ("3x^4+5x^3-6x^2+9x-5");
        REZ.subtract(F,S);
        Assertions.assertEquals(Expect.formString(), REZ.formString());
    }

    @org.junit.jupiter.api.Test
    void multiply() {
        Pol F =new Pol("3x^4 + 7x^3 - 5x^2 + 4x + 2");
        Pol S =new Pol("2x^3 + x^2 - 5x +7");
        Pol REZ =new Pol("");
        REZ.multiply(F,S);
        Assertions.assertEquals("6x^7+17x^6-18x^5-11x^4+82x^3-53x^2+18x+14", REZ.formString());
    }

    @org.junit.jupiter.api.Test
    void divide() {
        Pol REZ =new Pol("");
        REZ.Divide("3x^4 + 7x^3 - 5x^2 + 4x + 2","2x^3 + x^2 - 5x +7");
        Assertions.assertEquals("1.5x+2.75", REZ.formString());
    }

    @org.junit.jupiter.api.Test
    void integrate() {
        Pol F =new Pol("2x^3 + x^2 - 5x +7");
        F.Integrate();
        Assertions.assertEquals("0.5x^4+0.33x^3-2.5x^2+7x", F.formString());
    }

    @org.junit.jupiter.api.Test
    void derivate() {
        Pol F =new Pol("2x^3 + x^2 - 5x +7");
        F.Derivate();
        Assertions.assertEquals("6x^2+2x-5", F.formString());
    }
}