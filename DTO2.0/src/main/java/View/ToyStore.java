package View;

import Mapping.ToyDTO;
import com.sun.jdi.Type;
import services.ToyStoreInt;
import services.implement.ToyServiceImpl;

import java.util.Scanner;

public class ToyStore {
    public static void main(String[] args) throws Exception {
        String op = "1";
        ToyStoreInt impl = new ToyServiceImpl();

        do {
            Scanner s = new Scanner(System.in);
            System.out.println(" MENU ");
            System.out.println("1. Add toy");
            System.out.println("2. List toys");
            System.out.println("3. Max toy");
            System.out.println("4. Min toy");
            System.out.println("5. Sort toys");
            System.out.println("6. Show by Types");
            System.out.println("7. Show Toys Above");
            System.out.println("8. Search");
            System.out.println("9. Show total amount of Toys");
            System.out.println("10. Increase");
            System.out.println("11. Decrease");
            System.out.println("12. Show total all toys price");
            System.out.println("13. Exit");

            op = s.next();
            switch (op) {
                case "1" -> {
                    System.out.println("~~~ADD TOY~~~");
                    System.out.println("Enter the name");
                    String name = s.next();
                    System.out.println("Choose the type: \n0.Female\n1.Male\n2.Unisex");
                        Type type = Type.getTypeByValue(Integer.parseInt(s.next()));
                    System.out.println("Enter the price");
                    Integer prize = Integer.valueOf(s.next());
                    impl.addToy(new ToyDTO(name, type, prize, +1));
                }
                case "2" -> {
                    System.out.println("~~~LIST TOYS~~~");
                    if (!impl.listToys().isEmpty()) {
                        System.out.println(impl.listToys());
                    } else {
                        System.out.println("There is not toys yet");
                    }
                }
                case "3" -> {
                    System.out.println("~~~MAX TOY~~~");
                    System.out.println(impl.maxToy());
                }
                case "4" -> {
                    System.out.println("~~~MIN TOY~~~");
                    System.out.println(impl.minToy());
                }
                case "5" -> {
                    System.out.println("~~~SORT TOYS~~~");
                    System.out.println(impl.sort());

                }
                case "6" -> {
                    System.out.println("~~~SHOW BY TYPES~~~");
                    System.out.println(impl.showByType());
                }
                case "7" -> {
                    System.out.println("~~~SHOW TOYS ABOVE~~~");
                    System.out.println("Enter the number");
                    double number = Integer.parseInt(s.next());
                    System.out.println(impl.showToysAbove(number));
                }
                case "8" -> {
                    System.out.println("~~~SEARCH~~~");
                    System.out.println("Enter the toy name");
                    String name = s.next();
                    System.out.println(impl.search(name));
                }
                case "9"->{
                    System.out.println("~~~SHOW TOTAL AMOUNT OF TOYS~~~");
                    System.out.println("In the store are "+impl.totalToys());
                }
                case "10"->{
                    System.out.println("~~~INCREASE~~~");
                    System.out.println("Enter the toy name");
                    String name = s.next();
                    System.out.println("Enter the amount");
                    int amount = Integer.parseInt(s.next());
                    ToyDTO toyStoreDTO = impl.search(name);
                    System.out.println(impl.increase(toyStoreDTO, amount));
                }
                case "11"->{
                    System.out.println("~~~DECREASE~~~");
                    System.out.println("Enter the toy name");
                    String name = s.next();
                    System.out.println("Enter the amount");
                    int amount = Integer.parseInt(s.next());
                    ToyDTO toyStoreDTO = impl.search(name);
                    System.out.println(impl.decrease(toyStoreDTO,amount));
                }
                case "12"->{
                    System.out.println("~~~SHOW TOTAL ALL TOYS PRICE~~~");
                    System.out.println(impl.totalPriceAllToys());
                }
            }
        } while (!op.equals("13")) ;
    }

}
