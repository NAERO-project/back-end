package naero.naeroserver.member.dto;

public class UserGradeDTO {
    private int gradeId;
    private String gradeName;
    private int critExp;

    public UserGradeDTO() {
    }

    public UserGradeDTO(int gradeId, String gradeName, int critExp) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.critExp = critExp;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getCritExp() {
        return critExp;
    }

    public void setCritExp(int critExp) {
        this.critExp = critExp;
    }

    @Override
    public String toString() {
        return "UserGradeDTO{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                ", critExp=" + critExp +
                '}';
    }
}
