//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Students {
    public static ArrayList<Students> collection = new ArrayList<>();

    int crs;
    int wld;
    int shtraf;

    public Students(int crs) {
        this.crs = crs;
        collection.add(this);
    }

    public void start(ArrayList<Integer> inData, int time, int prmtr) {
        if (!inData.isEmpty() && inData.size() >= 2) {
            switch (prmtr) {
                case 2:
                    Collections.reverse(inData);
                    break;
                case 3:
                    Collections.sort(inData);
            }
            for (int vremia : inData) {
                if (time < 0) break;
                time -= vremia;
                this.wld++;
            }
            this.shtraf = time < 0 ? -time : 0;
        } else {
            System.out.println("А решать-то и нечего! Лист пустой! Где задание???");
        }
    }

    public static void getWinner(String pathOut) {
        StringBuilder stb = new StringBuilder();

        int win = 0;
        for (Students std : collection) win += std.wld;

        if (win == 0) {
            System.out.println(stb.append("Победителей нет. Ни кто ни чего не решал."));
        } else {
            int size = collection.size();
            // Сортировка по задачам
            for (int j = 0; j < size - 1; j++) {
                for (int i = 0; i < size - 1; i++) {
                    if (collection.get(i).wld < collection.get(i + 1).wld) {
                        Students temp = collection.get(i);
                        collection.set(i, collection.get(i + 1));
                        collection.set(i + 1, temp);
                    }
                }
            }
            // Сортировка по штрафам
            for (int j = 0; j < size - 1; j++) {
                for (int i = 0; i < size - 1; i++) {
                    if (collection.get(i).wld == collection.get(i + 1).wld) {
                        if (collection.get(i).shtraf > collection.get(i + 1).shtraf) {
                            Students temp = collection.get(i);
                            collection.set(i, collection.get(i + 1));
                            collection.set(i + 1, temp);
                        }
                    }
                }
            }
            // Сортировка по курсам
            for (int j = 0; j < size - 1; j++) {
                for (int i = 0; i < size - 1; i++) {
                    if ((collection.get(i).wld == collection.get(i + 1).wld) | (collection.get(i).shtraf == collection.get(i + 1).shtraf)) {
                        if (collection.get(i).crs > collection.get(i + 1).crs) {
                            Students temp = collection.get(i);
                            collection.set(i, collection.get(i + 1));
                            collection.set(i + 1, temp);
                        }
                    }
                }
            }
            int mesto = 1;
            System.out.print(stb.append("!!!Победители!!!\n\n"));
            for (Students std : collection) {
                String outString = "На " + (mesto++) + " месте студент (" +
                        std.crs + " курс) решил " + std.wld + " задач и набрал " +
                        std.shtraf + " штрафных баллов.\n";
                System.out.print(outString);
                stb.append(outString);
            }
        }
        try (FileWriter flr = new FileWriter(pathOut)){
            flr.write(stb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}