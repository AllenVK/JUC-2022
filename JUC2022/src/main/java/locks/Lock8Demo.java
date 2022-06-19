package locks;

import javax.swing.event.TreeSelectionListener;
import java.util.concurrent.TimeUnit;

/**
 * @Desc: 8锁案例编码
 * @Date: 2022-06-13 22:31
 * @Author: paul
 */

// 资源类
class phone {

    public synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("-----------sendSMS");
    }

    public void hello() {
        System.out.println("-----------hello");
    }

}

/**
 * 题目：谈谈你对多线程锁的理解，使用8锁案例说明
 * 口诀：线程 -> 操作 -> 资源类
 * 8锁案例说明
 * 1. 标准访问a、b两个线程，请问先打印邮件还是短信
 * 2. sendEmail方法中加入睡眠3秒，请问先打印邮件还是短信
 * 3. 添加一个普通的hello方法，请问先打印邮件还是hello
 * 4. 有两部手机，请问先打印邮件还是短信
 * 5. 有两个静态同步方法，有1部手机，请问先打印邮件还是短信
 * 6. 有两个静态同步方法，有2部手机，请问先打印邮件还是短信
 * 7. 有一个静态同步方法，有一个普通同步方法，有1部手机，请问先打印邮件还是短信
 * 8. 有一个静态同步方法，有一个普通同步方法，有2部手机，请问先打印邮件还是短信
 */
public class Lock8Demo {

    // 所有程序的入口
    public static void main(String[] args) {

        phone phone = new phone();

        new Thread(() -> {
            // TODO:do something
            phone.sendEmail();
        }, "a").start();

        // 暂停200毫秒，保证a线程先启动
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.sendSMS();
        }, "b").start();

    }

}
