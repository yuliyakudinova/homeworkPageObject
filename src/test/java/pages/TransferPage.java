package pages;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import data.DataHelper.CardInfo;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.*;

public class TransferPage {
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement transferAmount = $("[data-test-id='amount'] input");
    private SelenideElement transferTo = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public DashboardPage makeTransfer(CardInfo cardInfo,String amountToTransfer){
        transferAmount.setValue(amountToTransfer);
        transferTo.setValue(cardInfo.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }
}

