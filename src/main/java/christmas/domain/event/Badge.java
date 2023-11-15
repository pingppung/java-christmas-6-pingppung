package christmas.domain.event;

public class Badge {
    public static final int BADGE_STAR_LIMIT = 5_000;
    public static final int BADGE_TREE_LIMIT = 10_000;
    public static final int BADGE_SANTA_LIMIT = 20_000;
    private final String badgeType;

    public Badge(int totalDiscountAmount) {
        this.badgeType = determineBadge(totalDiscountAmount);
    }

    private String determineBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= BADGE_SANTA_LIMIT) {
            return "산타";
        }
        if (totalBenefitAmount >= BADGE_TREE_LIMIT) {
            return "트리";
        }
        if (totalBenefitAmount >= BADGE_STAR_LIMIT) {
            return "별";
        }
        return "없음";
    }

    public String getBadgeType() {
        return badgeType;
    }
}
