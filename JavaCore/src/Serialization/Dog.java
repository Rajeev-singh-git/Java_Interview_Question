package Serialization;

import java.io.*;

class Dog implements Serializable {
    int i = 10;
    int j = 20;
}

class SerializableDemo {
    public static void main(String[] args) throws Exception {
        Dog d1 = new Dog();
        System.out.println("Serialization started");

        FileOutputStream fos = new FileOutputStream("dogdata.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(d1);
        System.out.println("Serialization ended");

        System.out.println("Deserialization started");
        FileInputStream fis = new FileInputStream("dogdata.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Dog d2 = (Dog) ois.readObject();
        System.out.println("Deserialization ended");

        System.out.println(d2.i + "................" + d2.j);
    }
}
