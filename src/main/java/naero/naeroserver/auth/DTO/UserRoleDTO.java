package naero.naeroserver.auth.DTO;

public class UserRoleDTO {
    private int userRoleId;
    private int userId;
    private int roleId;

    public UserRoleDTO() {
    }

    public UserRoleDTO(int userRoleId, int userId, int roleId) {
        this.userRoleId = userRoleId;
        this.userId = userId;
        this.roleId = roleId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String  toString() {
        return "UserRoleDTO{" +
                "userRoleId=" + userRoleId +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
