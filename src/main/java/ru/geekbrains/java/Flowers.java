package ru.geekbrains.java;

import java.io.*;
import java.util.Arrays;

public class Flowers {

    private  String[] header;
    private String[][] flowers;

    public Flowers() {

    }

    public Flowers(String[][] flowers) {
        this.flowers = flowers;
       this.header  = new String[]{"Name", "Height, cm", "Flowering season"};;

    }

    public void printFlowers() {
        System.out.println(Arrays.toString(this.header));
        for (int i = 0; i < flowers.length; i++) {
            for (int j = 0; j < flowers[i].length; j++) {
                System.out.print(flowers[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void overwriteFile(File file) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            String header = String.join(";", this.header);
            bw.write(header);
            bw.newLine();
            bw.flush();
            for (int i = 0; i < flowers.length; i++) {
                String text = String.join(";", this.flowers[i]);
                bw.write(text);
                bw.newLine();
                bw.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFromFile(File file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
           String[] rowHeader= br.readLine().split(";");
            this.header=new String[rowHeader.length];
            for (int i=0; i<rowHeader.length;i++) {
                this.header[i]=rowHeader[i];
            }
            this.flowers=new String[countLines(file)-1][];
           int countLines=0;

            while(true) {
                String rowFile = br.readLine();
                if(rowFile==null) break;
               String[] arrData=rowFile.split(";");
             this.flowers[countLines]=new String[arrData.length];
                for (int i=0; i<arrData.length;i++) {
                    this.flowers[countLines][i] =arrData[i];
                }
              countLines++;
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    private int countLines(File file){
        try (LineNumberReader  lnr = new LineNumberReader (new FileReader(file))) {
            int countLines=0;
                while(lnr.readLine()  !=null) {

                countLines++;
            }
            return countLines;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public String[] getHeader() {
        return header;
    }

    public String[][] getFlowers() {
        return flowers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flowers flowers1 = (Flowers) o;
        return Arrays.equals(header, flowers1.header) &&
                Arrays.deepEquals(flowers, flowers1.flowers);
    }

}




