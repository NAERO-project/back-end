package naero.naeroserver.manage.DTO;

public class ManageUserDTO {
    private int userId;
    private String username;
    private String userFullname;
    private String producerName;
    private char withStatus;
    private String gradeName;

    public ManageUserDTO() {
    }

    public ManageUserDTO(int userId, String username, String userFullname, String producerName, char withStatus, String gradeName) {
        this.userId = userId;
        this.username = username;
        this.userFullname = userFullname;
        this.producerName = producerName;
        this.withStatus = withStatus;
        this.gradeName = gradeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }


    public char getWithStatus() {
        return withStatus;
    }

    public void setWithStatus(char withStatus) {
        this.withStatus = withStatus;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        return "ManageUserDTO{" +
                "userId=" + userId +
                ", userFullname='" + userFullname + '\'' +
                ", producerName='" + producerName + '\'' +
                ", withStatus='" + withStatus + '\'' +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
