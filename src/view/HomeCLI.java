package view;

import database.UserModel;
import database.UserSQL;

public class HomeCLI {


    public static void main(String[] args) {

        final var SEPARATOR = "\n----------------------------------------------------------------\n";

        UserSQL sql = new UserSQL();
        final var students = sql.findAll();

        for (UserModel student : students) {
            System.out.println(student);
            System.out.println(SEPARATOR);
        }

    }
}
