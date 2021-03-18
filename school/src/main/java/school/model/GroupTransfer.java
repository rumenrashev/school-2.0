package school.model;

public class GroupTransfer {

    private Long id;
    private String number;
    private String letter;

    public GroupTransfer() {
    }

    public Long getId() {
        return id;
    }

    public GroupTransfer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public GroupTransfer setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getLetter() {
        return letter;
    }

    public GroupTransfer setLetter(String letter) {
        this.letter = letter;
        return this;
    }
}
