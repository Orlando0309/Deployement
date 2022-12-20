package gestion.km;

public class ErrInfo {
    Integer code;
    String message;
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ErrInfo(){}
    public ErrInfo(Integer code, String message){
        this.code=code;
        this.message=message;
    }
}
