package pages.enums;

public enum DomainPointOneCard {

    org(".org"),
    co_uk(".co.uk"),
    net(".net"),
    gov(".gov"),
    de(".de"),
    fr(".fr"),
    nl(".nl"),
    com(".com"),
    be(".be"),
    jpg(".jpg");

    private final String value;

    DomainPointOneCard(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DomainPointOneCard randomValues(){
        return DomainPointOneCard.values()[(int) (Math.random() * DomainPointOneCard.values().length)];
    }
}
