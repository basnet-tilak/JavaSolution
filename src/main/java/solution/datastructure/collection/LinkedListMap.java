package solution.datastructure.collection;

import java.util.LinkedHashMap;

public class LinkedListMap {
    public static LinkedHashMap<String,String> listOfCountryWithCapitals() {
        LinkedHashMap<String, String> country = new LinkedHashMap<>();
        country.put("Afghanistan", "Kabul");
        country.put("Albania", "Tirana");
        country.put("Algeria", "Algiers");
        country.put("Andorra","Andorra la Vella");
        country.put("Angola","Luanda");
        country.put("Antigua and Barbuda", "Saint John's");
        country.put("Argentina","Buenos Aires");
        country.put("Armenia","Yerevan");
        country.put("Australia","Canberra");
        country.put("Austria","Vienna");
        country.put("Azerbaijan","Baku");
        country.put("Bahamas","Nassau");
        country.put("Bahrain","Manama");
        country.put("Bangladesh","Dhaka");
        country.put("Barbados","Bridgetown");
        country.put("Belarus","Minsk");
        country.put("Belgium","Brussels");
        country.put("Belize","Belmopan");
        country.put("Benin","Porto-Novo");
        country.put("Bhutan","Thimphu");
        country.put("Bolivia","Sucre, La Paz");
        country.put("Brazil","Brasilia");
        country.put("Cambodia","Phenom Pen");
        country.put("Cameroon","Yaounde");
        country.put("Canada","Ottawa");
        return country;
    }

    public static void main(String[] args) {
        LinkedHashMap<String, String> country = listOfCountryWithCapitals();

        country.forEach(((s, s2) -> System.out.println(s + "--->" + s2)));

        country.forEach((s, s2) -> {
            if(s.startsWith("B")){
                // it will print the all the country name and capital of country which start with "B"
                System.out.println(s+ " " +s2);
            }
        });
    }
}
