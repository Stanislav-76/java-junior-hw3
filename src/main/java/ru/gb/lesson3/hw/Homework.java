package ru.gb.lesson3.hw;

import ru.gb.lesson3.SerializablePerson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class Homework {

    /**
     * Написать класс с двумя методами:
     * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл. Название файл - class.getName() + "_" + UUID.randomUUID().toString()
     * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект из файла и удаляет этот файл.
     * <p>
     * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Car car = new Car("BMW X5", 2017);
        System.out.println(car);
        String fileName = car.getClass().getName() + "_" + UUID.randomUUID().toString();

        saveObjectToFile(car, fileName);

        Object car1 = loadFileToObject(fileName);
        System.out.println(car1);

    }

    public static void saveObjectToFile(Object o, String filename) throws IOException {
        Path path = Path.of(filename);
        OutputStream outputStream = Files.newOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(o);
        objectOutputStream.close();
    }

    public static Object loadFileToObject(String file) {
        Path path = Path.of(file);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
            Object deserializedObj = objectInputStream.readObject();
            Files.delete(path);
            return deserializedObj;
        } catch (IOException e) {
            throw new RuntimeException("Файл не найден или отсутствует(поврежден)");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found");
        }
    }


}
