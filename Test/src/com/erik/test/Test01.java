package com.erik.test;

public class Test01 {
    public static class Main {

        public static void sayHello(IHello iHello, String name) {
            iHello.sayHello(name);
        }

        public static void main(String[] args) {

            IHello iHello = name -> {
                String content = name + ": " + "happy new year everyone!";
                System.out.println(content);
            };

            // 这里可以把iHelo看成一个匿名实现类来传递参数
            sayHello(iHello, "mengday");

            // 如果去掉变量的接收，直接将Lambda表达式传递到参数中，此时Lambda表达式更像是一个函数
            // 也就是说JDK1.8竟然可以将一个函数作为参数传递到方法中，这是之前版本做不到的
            // 将函数作为方法的参数传递，一般用于回调函数，将回调函数传递到方法中
            sayHello(name -> {
                String content = name + ": " + "happy new year everyone!";
                System.out.println(content);
            }, "mengday");
        }
    }

}
