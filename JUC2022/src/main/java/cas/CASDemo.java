package cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc: // describe the class information
 * @Date: 2022-07-19 20:58
 * @Author: paul
 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);

        atomicInteger.compareAndSet(5, 5);

    }

}
