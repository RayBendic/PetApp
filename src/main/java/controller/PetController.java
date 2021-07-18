package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

@RestController("/pets")
public class PetController {

    @GetMapping("/cute-puppies")
    public String getCutePuppies(){

        Student s1 = new Student();
        Student s2 = new NullStudent();


        System.out.println(s1.toString());
        System.out.println(s2.toString());

        return null;
    }

}

class Student{

    public void elo(){

    }

    @Override
    public String toString() {
        return "Student{}";
    }
}

class NullStudent extends Student{

    @Override
    public String toString() {
        return "Null";
    }

}