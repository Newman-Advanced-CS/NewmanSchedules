import java.io.IOException;

public class User {
    int ID;
    String firstName;
    String lastName;
    int year;
    String password;

    enum UserType{
        Student,
        Teacher,
        Admin
    }
    UserType type;

    public static boolean CheckForUser()
    {
        return true;
    }

    // Constructor
    public User(int ID, String pass) throws IOException {
        this.ID = ID;

        // Use ID to get info
        String data = WebRequest.GET("/getUser.php?ID=" + ID + "&pass=" + pass);
        String[] dataArray = data.split(";");
        this.firstName = dataArray[0];
        this.lastName = dataArray[1];
        this.year = Integer.parseInt(dataArray[2]);
        this.type = UserType.valueOf(dataArray[3]);
        this.password = pass;
    }

    // Getters
    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYear() {
        return year;
    }

    public UserType getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }
}
