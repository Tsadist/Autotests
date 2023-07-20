package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pages.enums.CheckBoxTwoCard;
import utils.UploadFile;

import java.util.List;

public class AvatarAndInterests extends Form {

    private final static String CHECKBOX_TEMPLATE = "//*[text()='%s']//preceding-sibling::*";

    private final ILink uploadPhotoLink = getElementFactory().getLink(By.xpath("//*[contains(@class, 'avatar-and-interests__upload-button')]"), "Upload_photo_link");
    private final IButton buttonNext = getElementFactory().getButton(By.xpath("//*[contains(@class, 'button') and text()='Next']"), "Button_Next");

    public AvatarAndInterests() {
        super(By.xpath("//*[contains(@class, 'avatar-and-interests__avatar-section')]"), "Unique_element");
    }

    public void uploadPhoto (String patchFile) {
        UploadFile.uploadFile(uploadPhotoLink, patchFile);
    }

    public void selectCheckBoxes(List<CheckBoxTwoCard> checkBoxes) {
        for (CheckBoxTwoCard checkBox : checkBoxes) {
            selectOneCheckBox(checkBox);
        }
    }

    public void selectOneCheckBox(CheckBoxTwoCard checkBox) {
        getElementFactory().getCheckBox(By.xpath(String.format(CHECKBOX_TEMPLATE, checkBox.getValue())), "Checkbox_two_card").check();
    }

    public void clickNextButton() {
        buttonNext.click();
    }
}
