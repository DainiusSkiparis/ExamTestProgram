import commands.*;
import configs.*;

public class Main {

    public static void main(String[] args) {

        SessionFactoryMaker.getFactory();
        System.out.println("DB connection successfully!!!");

        //Skip steps // 0 = login; 1 = admin, 2 = user,
        int skipTo = 0;

        if (skipTo == 1) {
            TestData.uploadTestData();
            AdminCommands.loginToAdminCMD();
        } else if (skipTo == 2) {
            TestData.uploadTestData();
            UserCommands.loginToUserCMD();
        } else {
            TestData.uploadTestData();
            Commands.runProgramCMD();
        }
    }
}
