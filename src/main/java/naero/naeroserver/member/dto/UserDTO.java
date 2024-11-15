package naero.naeroserver.member.dto;

import naero.naeroserver.auth.DTO.UserRoleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UserDTO implements UserDetails {
    private int userId;
    private String userFullName;
    private String username;
    private String password;
    private String userEmail;
    private String userPhone;
    private int userPoint;
    private Date enrollDate;
    private char withStatus ='N';
    private UserGradeDTO grade;
    private List<UserRoleDTO> userRole;

    public UserDTO() {
    }


    public UserDTO(int userId, String userFullName, String username, String password, String userEmail, String userPhone, int userPoint, Date enrollDate, char withStatus, UserGradeDTO grade) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPoint = userPoint;
        this.enrollDate = enrollDate;
        this.withStatus = withStatus;
        this.grade = grade;
    }

    public UserDTO(int userId, String userFullName, String username, String password, String userEmail, String userPhone, int userPoint, Date enrollDate, char withStatus, UserGradeDTO grade, List<UserRoleDTO> userRole) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userPoint = userPoint;
        this.enrollDate = enrollDate;
        this.withStatus = withStatus;
        this.grade = grade;
        this.userRole = userRole;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public char getWithStatus() {
        return withStatus;
    }

    public void setWithStatus(char withStatus) {
        this.withStatus = withStatus;
    }

    public UserGradeDTO getGrade() {
        return grade;
    }

    public void setGrade(UserGradeDTO grade) {
        this.grade = grade;
    }

    public List<UserRoleDTO> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<UserRoleDTO> userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userFullName='" + userFullName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPoint='" + userPoint + '\'' +
                ", enrollDate=" + enrollDate +
                ", withStatus=" + withStatus +
                ", grade=" + grade +
                '}';
    }

    private Collection<GrantedAuthority> authorities;

    /* 설명.
     *  해당 사용자에 권한 목록을 설정할 때 사용할 setter()를 추가해야 한다.
     *  Spring Security는 인증된 사용자가 요청하는 특정 자원에 대한 권한이 있는지를 이 권한 목록을 통해 판단한다.
     * */
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /* 설명.
     *  현재 사용자가 부여받은 권한 컬렉션을 반환한다.(상속받은 UserDetails 인터페이스 사용)
     *  Spring Security는 이 정보(권한 컬렉션)를 사용해 접근 제어 결정을 판단한다.
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /* 설명.
     *  사용자의 비밀번호를 반환한다.
     *  (입력받은 사용자 비밀번호와 데이터베이스에 저장된 비밀번호를 비교하여 인증하는 데 사용)
     * */
    @Override
    public String getPassword() {
        return this.password;
    }

    /* 설명.
     *  사용자의 아이디(username)를 반환한다.
     *  (입력받은 사용자 아이디를 가지고 사용자를 검색하는데 사용)
     * */
    @Override
    public String getUsername() {
        return this.username;
    }

    /* 설명. 이하 추상 메소드들은 그대로 두고 사용하지 않는다. */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
