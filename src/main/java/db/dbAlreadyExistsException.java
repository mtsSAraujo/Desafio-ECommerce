package db;

public class dbAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public dbAlreadyExistsException(String msg){
        super(msg);
    }
}

