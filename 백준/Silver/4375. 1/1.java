import java.io.*;

class Main {
    static int n, target, targetSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            n = Integer.parseInt(br.readLine());
            target = 1;
            targetSize = 1;

            while (target % n != 0) {
                target = (target % n) * 10 + 1;
                targetSize++;
            }

            bw.append(String.valueOf(targetSize));
            bw.newLine();

            if (!br.ready()) break;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}