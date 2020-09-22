package main.java.Op_Polynomials.Structures;
//Comparator class needed for sorting
import main.java.Op_Polynomials.Structures.Mon;

import java.util.Comparator;
        public class Comp implements Comparator <Mon> {
            @Override
            public int compare(Mon a, Mon b) {
                if (a.getExp() > b.getExp())
                    return -1;
                if (b.getExp() > a.getExp())
                    return 1;

                return 0;

    }
}


