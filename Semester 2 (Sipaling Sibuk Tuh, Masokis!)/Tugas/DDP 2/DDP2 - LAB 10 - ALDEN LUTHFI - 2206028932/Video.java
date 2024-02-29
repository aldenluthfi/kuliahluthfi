public class Video {                                                            /* yang ini gausah di dokum yah       */
    private String title;
    private int duration;
    public static int videoAmount;

    public Video(String title, int duration){                                   /* pretty self-explanatory            */
        this.title = title;
        this.duration = duration;
        videoAmount++;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return this.getTitle() + " - " + this.getDuration() + " menit";
    }
}
