package com.cm;

import java.util.concurrent.atomic.LongAdder;

public class Test1 {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.add(1);


        CounterSampl counterSampl = new CounterSampl();
        counterSampl.increase();
    }
}
