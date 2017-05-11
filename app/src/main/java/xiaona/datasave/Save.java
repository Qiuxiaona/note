package xiaona.datasave;


public class Save {
    private String title;
    private String content;
    private String times;
    private int ids;
    public Save(String title1,int id1,String content1,String time1){
        this.title=title1;
        this.ids=id1;
        this.content=content1;
        this.times=time1;
    }
    public Save(String title2,String content2,String time2){
        this.title=title2;
        this.content=content2;
        this.times=time2;
    }
    public Save(int id3,String title3,String time3){
        this.ids=id3;
        this.title=title3;
        this.times=time3;
    }
    public Save(String title4,String content4){
        this.title=title4;
        this.content=content4;
    }
    public int getIds() {
        return ids;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getTimes() {
        return times;
    }
}
