package gestion.km;

public class Error extends OwnResponse{
    ErrInfo info;

    public ErrInfo getInfo() {
        return info;
    }

    public void setInfo(ErrInfo info) {
        this.info = info;
    }
    
}
