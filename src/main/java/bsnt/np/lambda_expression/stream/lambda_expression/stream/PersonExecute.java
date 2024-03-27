package bsnt.np.lambda_expression.stream.lambda_expression.stream;

import java.util.ArrayList;
import java.util.List;

public class PersonExecute {
    public static void main(String[] args) {
        List<Person> personList = personList();
        personList.stream()
                .filter(person -> person.getAddress().startsWith("S"))
                .forEach(person -> System.out.println(person.getFirstName()));
    }

    private static List<Person> personList(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1,"Tilak","Basnet","Nepal",33));
        personList.add(new Person(2,"Raphel","Lauda","Sudan",20));
        personList.add(new Person(3,"Kanchan","India","Nepal",34));
        personList.add(new Person(4,"Kshatish","Basnet","Nepal",9));
        personList.add(new Person(5,"Bhagiram","Oli","Nepal",33));
        return personList;
    }
}
