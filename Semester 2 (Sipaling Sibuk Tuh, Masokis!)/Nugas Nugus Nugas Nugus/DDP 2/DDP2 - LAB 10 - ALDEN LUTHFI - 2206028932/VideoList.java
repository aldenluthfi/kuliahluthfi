import java.util.ArrayDeque;

public class VideoList <T extends Video> {
    ArrayDeque<T> videoList;

    public VideoList() {
        videoList = new ArrayDeque<T>();                                        /* Membuat object baru                */
    }

    public void insertVideo(T newVideo, boolean isFront) {                      /* memasukkan video depa atau belakang*/
        if (isFront) {
            videoList.addFirst(newVideo);
        } else {
            videoList.addLast(newVideo);
        }
    }

    public ArrayDeque<T> getVideoList() {
        return videoList;
    }

    public T deleteVideo() {                                                    /* menghapus video paling depan       */
        return videoList.pop();
    }

    public T getFirst() {                                                       /* Mengambil video paling depan       */
        return videoList.peek();
    }
}
