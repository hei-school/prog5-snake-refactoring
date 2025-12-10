package snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public final int SCREEN_HEIGHT = 20;
    public final int SCREEN_WIDTH = 40;
    public final int TICK_DELAY_MS = 120;

    private Snake snake;
    private Food food;

    private int score = 0;

    int sh = 20; // Screen Height
    int sw = 40; // Screen width


    Random r = new Random();
    int[] f = new int[]{r.nextInt(sh - 2) + 1, r.nextInt(sw - 2) + 1};  // 'f' = food?

    String d = "R";
    int sc = 0; // Score


    public static int[] mv(int[] h, String d) {
        int[] k = new int[]{h[0], h[1]};
        if (d.equals("L")) k[1]--;
        else if (d.equals("R")) k[1]++;
        else if (d.equals("U")) k[0]--;
        else if (d.equals("D")) k[0]++;
        return k;
    }

    public void start() throws Exception {
        // Snake
        List<int[]> s = new ArrayList<>();
        s.add(new int[]{10, 10});
        s.add(new int[]{10, 9});
        s.add(new int[]{10, 8});

        while (true) {
            // Navigation
            if (System.in.available() > 0) {
                char c = (char) System.in.read(); // Character
                if (c == 'a' && !d.equals("R")) d = "L";
                else if (c == 'd' && !d.equals("L")) d = "R";
                else if (c == 'w' && !d.equals("D")) d = "U";
                else if (c == 's' && !d.equals("U")) d = "D";
            }

            // Position tête serpent
            int[] hd = mv(s.get(0), d);
            if (hd[0] <= 0 || hd[0] >= sh - 1 || hd[1] <= 0 || hd[1] >= sw - 1) {
                System.out.println("GAME OVER - SCORE = " + sc);
                return;
            }

            // Vérification de collision en O(n) — on pourrait utiliser un Set pour obtenir du O(1)
            for (int i = 0; i < s.size(); i++) {
                int[] b = s.get(i);
                if (hd[0] == b[0] && hd[1] == b[1]) {
                    System.out.println("GAME OVER - SCORE = " + sc);
                    return;
                }
            }

            if (hd[0] == f[0] && hd[1] == f[1]) {
                sc++;
                f = new int[]{r.nextInt(sh - 2) + 1, r.nextInt(sw - 2) + 1};
            } else {
                s.remove(s.size() - 1);
            }

            s.add(0, hd);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sh; i++) {
                for (int j = 0; j < sw; j++) {
                    boolean drawn = false;

                    if (i == f[0] && j == f[1]) {
                        sb.append("*");
                        drawn = true;
                    }

                    for (int[] px : s) { // Pixel
                        if (px[0] == i && px[1] == j) {
                            sb.append("#");
                            drawn = true;
                            break;
                        }
                    }

                    if (!drawn) {
                        if (i == 0 || j == 0 || i == sh - 1 || j == sw - 1)
                            sb.append("X");
                        else
                            sb.append(" ");
                    }
                }
                sb.append("\n");
            }

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(sb.toString());
            System.out.println("Score: " + sc);

            Thread.sleep(120);
        }
    }
}
