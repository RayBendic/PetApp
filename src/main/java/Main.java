import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import security.domain.SecretKey;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

@ComponentScan
@EnableAspectJAutoProxy
public class Main {

    public void main(String[] args) {
        new Test().doStuff();
    }

    public class Test {
        public void doStuff() {
            System.out.println("YAY");
        }

    }

    class Builder {
        private Student student;
    }


}

class Student {

    int id;
    String name;
    String auth;
    String makesMeFeelGood;

}
