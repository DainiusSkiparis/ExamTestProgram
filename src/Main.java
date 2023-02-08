import commands.AdminCommands;
import commands.Commands;
import commands.UserCommands;
import configs.SessionFactoryMaker;
import configs.TestData;

public class Main {
    public static void main(String[] args) {
        SessionFactoryMaker.getFactory();
        System.out.println("DB connection successfully!!!");

        //Skip steps
        int skipTo = 1; // 0 = login; 1 = admin, 2 = user,

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
