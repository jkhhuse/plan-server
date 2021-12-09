package test;

import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

class People {
    String name;
    int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public People(String name, int age){
        this.name = name;
        this.age = age;
    }
    //将对象实例化，并给它赋值。且用一个list集合装起来
    public static void main(String[] args) {
        People p1 = new People("杨华",26);
        List list = new ArrayList();
        list.add(p1);

        //将ArrayList泛型改成ListIterrator,以使用hasNext(),next()方法正向迭代。
        ListIterator<People> l = list.listIterator();
        //正向迭代，并重新将迭代出的对象设置到list中，再下面调用hasPrevious(),previous()方法，逆向迭代
        //注意：在使用此操作时候 一定要注意：一定要先进行由前向后输出，之后才能进行由后向前输出
        while(l.hasNext()){
            People p = l.next();
            //重新设值.set(); .add();
            System.out.print(p.getAge());
            System.out.print("\r\n");
        }
//        //判断若前一个元素存在，则取出来。从而
//        while(l.hasPrevious()){
//            People p = l.previous();
//            System.out.print(p.getAge());
//        }

    }
}