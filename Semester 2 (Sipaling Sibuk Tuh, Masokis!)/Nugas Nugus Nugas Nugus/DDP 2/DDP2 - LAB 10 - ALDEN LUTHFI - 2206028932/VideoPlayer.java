import java.util.NoSuchElementException;
import java.util.Scanner;

public class VideoPlayer {                                                      /* main driver program                */

    private static VideoList<Movie> movieList;
    private static VideoList<DDPTubeVideo> ddpTubeVideoList;
    private static final Scanner in = new Scanner(System.in);

    private static void outln(Object objects) {                                 /* Print shorthand/macro              */
        System.out.println(objects);
    }

    private static void out(Object objects) {
        System.out.print(objects);
    }

    public static void main(String[] args) {                                    /* main method                        */
        movieList = new VideoList<Movie>();
        ddpTubeVideoList = new VideoList<DDPTubeVideo>();

        outln("Selamat datang di DEDEPE Player!");
        mainloop: while (true) {                                                /* main program loop                  */
            try {
                printMainMenu();
                int menu = Integer.parseInt(in.nextLine());
                switch (menu) {                                                 /* menu selection                     */
                    case 1 -> addVideo();
                    case 2 -> nextVideo();
                    case 3 -> deleteVideo();
                    case 4 -> printVideoList();
                    default -> {
                        break mainloop;
                    }
                }
            } catch (IllegalArgumentException e) {                              /* error handlers                     */
                outln("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                outln("WRONG INPUT FORMAT!!\nError code: 401");
                outln("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            } catch (NoSuchElementException e) {
                outln("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                outln("NO VIDEO FOUND!!\nError code: 402");
                outln("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            } catch (Exception e) {
                outln("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                outln("UNKNOWN ERROR!!\nError code: 444");
                outln("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            }
        }
        outln("Terima kasih sudah menggunakan DEDEPE Player!");
        in.close();
    }

    public static void addVideo() {                                             /* add video to corresponding list    */
        outln("---------------TAMBAH VIDEO----------------");
        outln("Tambah Video Baru");
        out("Masuk di (1) paling depan atau (2) paling belakang: ");
        int pos = Integer.parseInt(in.nextLine());
        out("Jenis: ");
        String type = in.nextLine();
        out("Judul: ");
        String title = in.nextLine();
        out("Durasi (dalam menit): ");
        int duration = Integer.parseInt(in.nextLine());

        if (duration <= 0)
            throw new IllegalArgumentException();

        Video newVideo;

        if (type.equalsIgnoreCase("Movie")) {
            out("Sutradara: ");
            String director = in.nextLine();
            out("Rating: ");
            Double rating = Double.valueOf(in.nextLine());

            if (rating > 5.0)
                throw new IllegalArgumentException();

            newVideo = new Movie(title, duration, director, rating);

            movieList.insertVideo((Movie) newVideo, pos == 1);
        } else if (type.equalsIgnoreCase("DDPTube")) {
            out("Creator: ");
            String creator = in.nextLine();

            newVideo = new DDPTubeVideo(title, duration, creator);

            ddpTubeVideoList.insertVideo((DDPTubeVideo) newVideo, pos == 1);

        } else {
            outln("Tipe video tidak diketahui !!!");
        }
    }

    public static void nextVideo() {                                            /* plays the next video               */
        out("Putar (1) movie atau (2) DDPTube video selanjutnya? ");
        int type = Integer.parseInt(in.nextLine());

        switch (type) {
            case 1 -> {
                Movie movie = movieList.deleteVideo();
                movieList.insertVideo(movie, false);
            }
            case 2 -> {
                DDPTubeVideo ddpTubeVideo = ddpTubeVideoList.deleteVideo();
                ddpTubeVideoList.insertVideo(ddpTubeVideo, false);
            }
        }

    }

    public static void deleteVideo() {                                          /* delete from corresponding list     */
        outln("---------------HAPUS VIDEO-----------------");

        out("Hapus (1) movie atau (2) DDPTube video? ");
        int type = Integer.parseInt(in.nextLine());

        Video video = null;
        switch (type) {
            case 1 -> {
                video = movieList.deleteVideo();
            }
            case 2 -> {
                video = ddpTubeVideoList.deleteVideo();
            }
        }
        outln(video.getTitle() + " - " + video.getDuration() + " dihapus!");
        Video.videoAmount--;
    }

    public static void printVideoList() {                                       /* I/O methods                        */
        outln("---------------DAFTAR VIDEO----------------");

        int counter = 0;

        outln("Movie anda: ");
        for (Video video : movieList.getVideoList()) {
            outln(++counter + ". " + video.getTitle() + " - " + video.getDuration());
        }
        if (counter < 1) {
            outln("List movie anda kosong");
        }

        counter = 0;
        outln("DDPTube Video anda: ");
        for (Video video : ddpTubeVideoList.getVideoList()) {
            outln(++counter + ". " + video.getTitle() + " - " + video.getDuration());
        }
        if (counter < 1) {
            outln("List DDPTube video anda kosong");
        }
    }

    public static void printMainMenu() {
        outln("-------------------------------------------");
        outln("Total jumlah video sekarang: " + Video.videoAmount);
        playMovie();
        playDdpTubeVideo();
        outln("-------------------------------------------");
        out("""
                Silakan pilih menu:\s
                1. Tambah video
                2. Putar video selanjutnya
                3. Hapus video
                4. Lihat daftar video
                5. Keluar
                Pilihan:\s""");
    }

    public static void playMovie() {
        try {
            Video current = movieList.getFirst();
            outln("Movie sekarang: \n" + ((current == null) ? "Tidak ada" : current));
        } catch (Exception e) {
            outln("Movie sekarang: \nTidak ada");
        }
    }

    public static void playDdpTubeVideo() {
        try {
            Video current = ddpTubeVideoList.getFirst();
            outln("DDPTube video sekarang: \n" + ((current == null) ? "Tidak ada" : current));
        } catch (Exception e) {
            outln("DDPTube video sekarang: \nTidak ada");
        }
    }
}
