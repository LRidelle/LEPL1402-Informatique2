public class Client extends Observer {

    public Client(int zone){
        this.news = "";
        this.zone = zone;
    }

    @Override
    public void update(Object o) {
        if(o instanceof String)
            this.news = (String) o;
    }

    @Override
    public int getZone() {
        return this.zone;
    }

    @Override
    public String getNews() {
        return this.news;
    }
}