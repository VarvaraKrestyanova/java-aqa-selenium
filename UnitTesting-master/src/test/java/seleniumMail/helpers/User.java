package seleniumMail.helpers;

public class User {

    String name;
    String position;
    String office;

    public User(String name, String position, String office) {
        this.name = name;
        this.position = position;
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", office='" + office + '\'' +
                '}';
    }

}
