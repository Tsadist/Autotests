package pages.enums;

import java.util.ArrayList;
import java.util.List;

public enum CheckBoxTwoCard {

    Ponies("Ponies"),
    Polo("Polo"),
    Dough("Dough"),
    Snails("Snails"),
    Balls("Balls"),
    Post_its("Post-its"),
    Faucets("Faucets"),
    Enveloppes("Enveloppes"),
    Cables("Cables"),
    Questions("Questions"),
    Squares("Squares"),
    Purple("Purple"),
    Cotton("Cotton"),
    Dry_wall("Dry-wall"),
    Closets("Closets"),
    Tires("Tires"),
    Windows("Windows"),
    Mullets("Mullets"),
    Cinnamon("Cinnamon"),
    Unselect_all("Unselect all");

    private final String value;

    CheckBoxTwoCard(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List <CheckBoxTwoCard> RandomValuesList(int numberRandomValues){
        List <CheckBoxTwoCard> checkBoxTwoCardList = new ArrayList<>();
        CheckBoxTwoCard checkBox;
        for (int i = 0; i < numberRandomValues; i++) {
            checkBox = CheckBoxTwoCard.values()[(int) (Math.random() * CheckBoxTwoCard.values().length)];
            if (checkBox == CheckBoxTwoCard.Unselect_all || checkBoxTwoCardList.contains(checkBox)){
                i--;
            } else {
                checkBoxTwoCardList.add(checkBox);
            }
        }
        return checkBoxTwoCardList;
    }
}
