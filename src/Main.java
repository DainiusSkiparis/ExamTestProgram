import commands.AdminCommands;
import commands.Commands;
import commands.UserCommands;
import configs.SessionFactoryMaker;

import static configs.TestData.uploadTestData;

public class Main {
    public static void main(String[] args) {

        SessionFactoryMaker.getFactory();
        System.out.println("DB connection successfully!!!");

        //create new database if not exists
        //configs.CreateDB.create();

        //upload test data to database
        uploadTestData();

        //Skip steps configuration
        int admin = 1;
        int user = 2;
        int start = 0;

        int skipTo = start;

        if (skipTo == 1) {
            AdminCommands.loginToAdminCMD();
        } else if (skipTo == 2) {
            UserCommands.loginToUserCMD();
        } else Commands.runProgramCMD();
    }

}
