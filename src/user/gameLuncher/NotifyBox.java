package user.gameLuncher;

import java.util.Observable;

public class NotifyBox extends Observable {
    String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
        setChanged();
    }
}
