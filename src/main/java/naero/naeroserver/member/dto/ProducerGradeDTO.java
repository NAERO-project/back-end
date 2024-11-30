package naero.naeroserver.member.dto;

public class ProducerGradeDTO {
    private int pgradeId;
    private String pgradeName;
    private int critSales;
    private int critReview;

    public ProducerGradeDTO(int pgradeId, String pgradeName, int critSales, int critReview) {
        this.pgradeId = pgradeId;
        this.pgradeName = pgradeName;
        this.critSales = critSales;
        this.critReview = critReview;
    }

    public int getPgradeId() {
        return pgradeId;
    }

    public void setPgradeId(int pgradeId) {
        this.pgradeId = pgradeId;
    }

    public String getPgradeName() {
        return pgradeName;
    }

    public void setPgradeName(String pgradeName) {
        this.pgradeName = pgradeName;
    }

    public int getCritSales() {
        return critSales;
    }

    public void setCritSales(int critSales) {
        this.critSales = critSales;
    }

    public int getCritReview() {
        return critReview;
    }

    public void setCritReview(int critReview) {
        this.critReview = critReview;
    }

    @Override
    public String toString() {
        return "ProducerGradeDTO{" +
                "pgradeId=" + pgradeId +
                ", pgradeName='" + pgradeName + '\'' +
                ", critSales=" + critSales +
                ", critReview=" + critReview +
                '}';
    }
}
