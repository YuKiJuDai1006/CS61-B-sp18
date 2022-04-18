import static org.junit.Assert.*;
import org.junit.Test;

import javax.annotation.processing.SupportedSourceVersion;

public class TestArrayDequeGold {
    @Test
    public void testwxy() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> happy = new ArrayDequeSolution<>();
        String res = new String();
        for (int i = 0; i < 100; i += 1) {
            double num1 = StdRandom.uniform();
            double num2 = StdRandom.uniform();
            if (num1 > 0.5) {
                happy.addFirst(i);
                sad.addFirst(i);
                res += "addFirst(" + i + ")\n";
            } else {
                happy.addLast(i);
                sad.addLast(i);
                res += "addLast(" + i + ")\n";
            }
            if (num2 < 0.3) {
                Integer expect = happy.removeFirst();
                Integer actual = sad.removeFirst();
                res += "removeFirst()\n";
                assertEquals(res, expect, actual);
            } else {
                if (num2 > 0.7) {
                    Integer expect = happy.removeLast();
                    Integer actual = sad.removeLast();
                    res += "removeLast()\n";
                    assertEquals(res, expect, actual);
                }
            }
        }
    }
}

