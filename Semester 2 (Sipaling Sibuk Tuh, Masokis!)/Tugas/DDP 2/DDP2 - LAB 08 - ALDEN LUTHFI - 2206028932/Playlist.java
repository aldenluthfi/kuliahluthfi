import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Playlist {

    private static final Playlist playlist = new Playlist();                    /* Class Instance                     */

    class InvalidPlaylistException extends RuntimeException {                   /* Custom exception                   */
        public InvalidPlaylistException() {
            super();
        }
    }

    private void validasiFile(String[] isiFile)                                 /* Validasi isi file                  */
    throws InvalidPlaylistException {
        String pattern = "[^\\|]{1}[^(\\|\\|)]*\\|{2}[^(\\|\\|)]*[^\\|]{1}";
        for (String line : isiFile) if (!line.matches(pattern)) {
            throw new InvalidPlaylistException();
        }
    }

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {

            System.out.print("File playlist pertama: ");                        /* Input file input                   */
            Scanner file1 = new Scanner(new File(in.nextLine()))
            .useDelimiter("\\Z");
            String[] isiFile1 = file1.next().strip().split("\n");
            playlist.validasiFile(isiFile1);

            System.out.print("File playlist kedua: ");                          /* Input file input                   */
            Scanner file2 = new Scanner(new File(in.nextLine()))
            .useDelimiter("\\Z");
            String[] isiFile2 = file2.next().strip().split("\n");
            playlist.validasiFile(isiFile2);

            System.out.print("File playlist output: ");                         /* Input file output                  */
            PrintWriter out = new PrintWriter(new File(in.nextLine()));

            LinkedHashSet<String> finalPlaylist= new LinkedHashSet<>();

            for (String lagu : isiFile1) finalPlaylist.add(lagu);               /* Memasukkan kedalam set             */
            for (String lagu : isiFile2) finalPlaylist.add(lagu);               /* Memasukkan kedalam set             */
            for (String lagu : finalPlaylist) out.println(lagu);                /* Menulis set ke output file         */

            System.out.println(
                "Berhasil menimpa playlist, jumlah lagu adalah: %d"
                .formatted(finalPlaylist.size())
            );

            out.close();

        } catch (FileNotFoundException e) {                                     /* handle exceptions                  */
            System.out.println("File tidak ditemukan!");
        } catch (InvalidPlaylistException e){
            System.out.println("Playlist tidak valid!");
        }
    }
}
