/*
Трое студентов, пятикурсник, третьекурсник и первокурсник, живут в одной комнате общежития и
любят участвовать в соревнованиях по программированию по правилам ACM. У каждого из них свой
подход к решению задач. Пятикурсник решает все задачи строго по порядку - сначала первую,
затем вторую, и так до последней. Третьекурсник решает задачи строго в обратном порядке – сначала последнюю, затем предпоследнюю, и так до первой. А первокурсник сначала решает самую простую задачу, затем – самую простую из оставшихся задач, и так до самой сложной. Сложность задачи определяется временем, необходимым для её решения. Для решения одной и той же задачи наши студенты тратят одинаковое количество времени.

Ваша задача – по описанию соревнований по программированию определить, кто из студентов победит.
Напомним, что по правилам ACM побеждает участник, за 300 минут решивший больше всего задач, а при
равенстве количества задач – набравший меньше штрафного времени.

Наши студенты – очень сильные программисты, и при решении задач они не делают неправильных попыток.
Поэтому за задачу начисляется штраф в размере количества минут от начала соревнования до её посылки
на проверку. Если же и количество штрафного времени совпадает – то студент со старшего курса уступает
победу студенту с младшего курса.
Формат ввода

Входной файл input.txt содержит натуральное число N (N ≤ 10) – количество задач. Во второй строке
записаны N натуральных чисел – количество минут, необходимое для решения каждой задачи. Время решения задачи не превосходит 300 минут.

Входной файл
10
23 98 64 38 157 51 46 23 24 89

*/

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String pathIn = "zadanie.txt";       // Файл с задачами
        String pathOut = "winners.txt";      // Файл с победителями
        int time = 400;                                                 // Время для решения задач

        Students s1 = new Students(5);
        Students s2 = new Students(3);
        Students s3 = new Students(1);

        s1.start(getdata(pathIn), time, 1);
        s2.start(getdata(pathIn), time, 2);
        s3.start(getdata(pathIn), time, 3);

        Students.getWinner(pathOut);
    }

    public static ArrayList<Integer> getdata(String path) {
        ArrayList<Integer> zadachi = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();

        try (FileReader flr = new FileReader(path);
             Scanner scn = new Scanner(flr)){
            for(int i = 0; i < 2; ++i) {
                if (!scn.hasNextLine()) {
                    errorData(1);
                    lines.clear();
                    break;
                }
                lines.add(scn.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (lines.size() < 2) {
            errorData(2);
        } else {
            Scanner scn = new Scanner(lines.get(0));
            if (!scn.hasNextInt()) {
                errorData(3);
            } else {
                int zdchN = scn.nextInt();
                if (zdchN > 0 && zdchN <= 10) {
                    scn = new Scanner(lines.get(1));
                    for(int i = 0; i < zdchN; ++i) {
                        if (!scn.hasNextInt()) {
                            errorData(4);
                            zadachi.clear();
                            zdchN = 0;
                            break;
                        }
                        int temp = scn.nextInt();
                        if (temp >= 1 && temp <= 300) {
                            zadachi.add(temp);
                        } else {
                            errorData(5);
                            zadachi.clear();
                            zdchN = 0;
                            break;
                        }
                    }
                } else {
                    errorData(6);
                    zdchN=0;
                }
            }
            scn.close();
        }
        return zadachi;
    }
    public static void errorData(int prmtr) {
        switch (prmtr) {
            case 1 -> System.out.println("Ошибка чтения линий из фала");
            case 2 -> System.out.println("Пустой список либо строк менее двух");
            case 3 -> System.out.println("Ошибка чтения первой линии списка");
            case 4 -> System.out.println("Ошибка чтения второй линии списка");
            case 5 -> System.out.println("Ошибка в условиях второй линии 1 <= x <= 300");
            case 6 -> System.out.println("Ошибка в условиях первой линии 1 <= x <= 10");
        }
    }
}
