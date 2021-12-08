public class MeteoStation extends Observable {

    @Override
    public Observer[] getSubscribers() {
        Observer[] obsArr = new Observer[subscribers.size()];
        for(int i=0; i<subscribers.size(); i++){
            obsArr[i] = subscribers.get(i);
        }
        return obsArr;
    }

    @Override
    public void broadcast(Pair<String, Integer> pair) {
        for(int i=0; i<subscribers.size(); i++){
            if(subscribers.get(i).equals(pair)){
                subscribers.get(i).update(pair);
            }
        }
    }

    @Override
    public void addObserver(Observer sub) {
        if(!subscribers.contains(sub))
            subscribers.add(sub);
    }

    @Override
    public boolean removeObserver(Observer sub) {
        if(sub instanceof Observer)
            return subscribers.remove(sub);
        return false;
    }

    @Override
    public void setAlert(String alert, int zone) {
        for(Observer o : subscribers){
            if(o.getZone()==zone){
                o.update(alert);
            }
        }
    }
}