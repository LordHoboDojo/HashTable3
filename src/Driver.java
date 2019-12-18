import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("put.txt"));
        HashTable table = new HashTable(101);
        while(sc.hasNext())
        {
            sc.next();
            sc.next();
            int key = sc.nextInt();
            String val = sc.next()+ " " + sc.next();
            table.put(key,val);
        }

        sc = new Scanner(new File("remove.txt"));
        while(sc.hasNext())
        {
            sc.next();
            sc.next();
            int key = sc.nextInt();
            String val = sc.next()+ " " + sc.next();
            table.remove(key);
        }


         sc = new Scanner(new File("override.txt"));
        while(sc.hasNext())
        {
            sc.next();
            sc.next();
            int key = sc.nextInt();
            String val = sc.next()+ " " + sc.next();
            table.put(key,val);
        }

        sc = new Scanner(new File("put2.txt"));
        while(sc.hasNext())
        {
            sc.next();
            sc.next();
            int key = sc.nextInt();
            String val = sc.next()+ " " + sc.next();
            table.put(key,val);
        }
        System.out.println(table);
    }
}
