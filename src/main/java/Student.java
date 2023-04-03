import java.sql.*;
import java.util.ArrayList;

public class Student {

    private String name;
    private String surname;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



    public static void main(String... args) throws SQLException {

        Connection con;
        Statement stat;

        con = DriverManager.getConnection("jdbc:mysql://localhost/newdb?" + "user='root'&password=Sql123.");

        stat = con.createStatement();

        String que = "CREATE VIEW italian_students AS " +
                     "SELECT first_name, last_name FROM students " +
                     "WHERE country = 'Italy';";

        stat.execute(que);


        que =   "CREATE VIEW german_students AS" +
                "SELECT first_name, last_name FROM students" +
                "WHERE country = 'Germany';";

        stat.execute(que);


        ArrayList<Student> italianStudents = new ArrayList<>();

        ResultSet italianResultSet = stat.executeQuery("SELECT * FROM italian_students");

             while (italianResultSet.next()) {

                String name = italianResultSet.getString("first_name");
                String surname = italianResultSet.getString("last_name");

                italianStudents.add(new Student(name, surname));
             }


        ArrayList<Student> germanStudents = new ArrayList<>();

        ResultSet germanResultSet = stat.executeQuery("SELECT * FROM german_students");

            while (germanResultSet.next()) {

                String name = germanResultSet.getString("first_name");
                String surname = germanResultSet.getString("last_name");

                germanStudents.add(new Student(name, surname));
            }



    }


}
