package hexlet.code;

public class User {

    private Long id;
    private String username;
    private String number;


    public User(String username, String number) {
        this.username = username;
        this.number = number;
    }

    public User() { }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNumber() {
        return number;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
