package ru.geekbrains.java;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    private ArrayList<T> fruits;

    public Box(T...fruits) {
        this.fruits = new ArrayList<>(Arrays.asList(fruits));
    }

    public void add(T fruit){
        this.fruits.add(fruit);
    }

    public float getWeight(){
        float weight=0f;
        for (T fruit : fruits) {
            weight+=fruit.getWeight();
        }
    return weight;
    }

    public boolean compare(Box box){
        return this.getWeight()==box.getWeight();
    }

    public  void sprinkle(Box<T> box){
        box.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

}
