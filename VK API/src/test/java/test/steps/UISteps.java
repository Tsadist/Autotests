package test.steps;

import page.HomePage;
import page.MyProfilePage;
import page.PasswordPage;
import page.SideBarForm;

public class UISteps {

    public static void authorization(String login, String password) {

        HomePage homePage = new HomePage();
        homePage.setLogin(login);

        PasswordPage passwordPage = new PasswordPage();
        passwordPage.setPassword(password);
    }

    public static MyProfilePage enterMyPage() {

        SideBarForm sideBarForm = new SideBarForm();
        sideBarForm.clickMyPage();

        return new MyProfilePage();
    }
}
