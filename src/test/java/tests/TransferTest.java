package tests;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    @BeforeEach
    public void setUp(){
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    public void shouldTransferMaxAmount(){
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage =  loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);
        var firstCardInfo = DataHelper.getFirstCard();
        var secondCardInfo = DataHelper.getSecondCard();
        int amount = 10000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPage = dashboardPage.cardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeTransfer(firstCardInfo, String.valueOf(amount));
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
    @Test
    public void shouldTransferZeroAmount(){
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage =  loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);
        var firstCardInfo = DataHelper.getFirstCard();
        var secondCardInfo = DataHelper.getSecondCard();
        int amount = 0;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPage = dashboardPage.cardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeTransfer(firstCardInfo, String.valueOf(amount));
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
    @Test
    public void shouldTransferMinAmount(){
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage =  loginPage.validLogin(authInfo);
        var verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verifyInfo);
        var firstCardInfo = DataHelper.getFirstCard();
        var secondCardInfo = DataHelper.getSecondCard();
        int amount = 1;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPage = dashboardPage.cardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeTransfer(firstCardInfo, String.valueOf(amount));
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
}