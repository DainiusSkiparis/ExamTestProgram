import commands.*;
import configs.*;

public class Main {

    public static void main(String[] args) {
        SessionFactoryMaker.getFactory();
        System.out.println("DB connection successfully!!!");
        //TestData.uploadTestData();
        Commands.runProgramCMD();
    }
}
