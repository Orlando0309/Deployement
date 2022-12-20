package gestion.km;

public class Success extends OwnResponse{
    Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public Success(){}
    public Success(Object data){
        this.data = data;
    }
}

