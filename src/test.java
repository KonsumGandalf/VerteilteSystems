import java.lang.reflect.Constructor;

public class test{
    public static String name;
    public test(){
        name = "felix";
    }

    public void dummy(){
        System.out.printf("My name is %s", this.name);
    }

    public static void main(String[] args){
        System.out.printf("My name is %s", name);

    }
}


