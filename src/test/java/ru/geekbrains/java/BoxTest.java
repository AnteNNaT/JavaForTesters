package ru.geekbrains.java;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/*
○	Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
○	Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
    поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
○	Для хранения фруктов внутри коробки можно использовать ArrayList;
○	Сделать метод getWeight(), который высчитывает вес коробки. Задать вес одного фрукта и их количество:
    вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
○	Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
    которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
    Можно сравнивать коробки с яблоками и апельсинами;
○	Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов:
    нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается,
    а в другую перекидываются объекты, которые были в первой;
○	Не забываем про метод добавления фрукта в коробку
 */

public class BoxTest {

    @Test
    public void checkBox(){

        Box<Apple> boxWithApples=new Box<>(new Apple(), new Apple());
        Box<Apple> anotherBoxWithApples = new Box<>(new Apple(), new Apple(), new Apple(),new Apple());
        Box<Orange> boxWithOrange=new Box<>(new Orange(), new Orange(), new Orange());

        Assert.assertEquals(2.0f, boxWithApples.getWeight(),0.001f);
        Assert.assertEquals(4.5f, boxWithOrange.getWeight(), 0.001f);

        boxWithApples.add(new Apple());
        Assert.assertFalse(boxWithApples.compare(boxWithOrange));

        boxWithApples.sprinkle(anotherBoxWithApples);
        Assert.assertEquals(0.0f,boxWithApples.getWeight(), 0.001f);
        Assert.assertEquals(7.0f,anotherBoxWithApples.getWeight(), 0.001f);

    }


}