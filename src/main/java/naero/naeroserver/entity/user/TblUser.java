package naero.naeroserver.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import naero.naeroserver.entity.auth.TblUserRole;
import naero.naeroserver.entity.cart.TblCart;
import naero.naeroserver.entity.coupon.TblCouponList;
import naero.naeroserver.entity.independence.TblAlarm;
import naero.naeroserver.entity.inquiry.TblAnswer;
import naero.naeroserver.entity.inquiry.TblInquiry;
import naero.naeroserver.entity.inquiry.TblQuestion;
import naero.naeroserver.entity.inquiry.TblReview;
import naero.naeroserver.entity.liked.TblLikedProduct;
import naero.naeroserver.entity.liked.TblLikedSeller;
import naero.naeroserver.entity.order.TblAddress;
import naero.naeroserver.entity.order.TblOrder;
import naero.naeroserver.entity.product.TblCategoryLarge;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
public class TblUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Size(max = 20)
    @Column(name = "user_fullname", length = 20)
    private String userFullname;

    @Size(max = 20)
    @NotNull
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @Column(name = "user_email")
    private String userEmail;

    @Size(max = 20)
    @Column(name = "user_phone", length = 20)
    private String userPhone;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "user_point", nullable = false)
    private Integer userPoint;

    @NotNull
    @Column(name = "enroll_date", nullable = false)
    private LocalDate enrollDate;

    @Size(max = 1)
    @ColumnDefault("'N'")
    @Column(name = "with_status", length = 1)
    private String withStatus;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("1")
    @JoinColumn(name = "grade_id", nullable = false)
    private TblGrade grade;

    @OneToMany(mappedBy = "user")
    private Set<TblAddress> tblAddresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TblAlarm> tblAlarms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "answerEmp")
    private Set<TblAnswer> tblAnswers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "approver")
    private Set<TblCategoryLarge.TblBanner> tblBanners = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TblCart> tblCarts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TblCouponList> tblCouponLists = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TblInquiry> tblInquiries = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TblLikedProduct> tblLikedProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TblLikedSeller> tblLikedSellers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TblOrder> tblOrders = new LinkedHashSet<>();

    @OneToOne(mappedBy = "producer")
    private TblProducer tblProducers;

    @OneToMany(mappedBy = "user")
    private Set<TblQuestion> tblQuestions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TblReview> tblReviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TblUserRole> tblUserRoles = new LinkedHashSet<>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getWithStatus() {
        return withStatus;
    }

    public void setWithStatus(String withStatus) {
        this.withStatus = withStatus;
    }

    public TblGrade getGrade() {
        return grade;
    }

    public void setGrade(TblGrade grade) {
        this.grade = grade;
    }

    public Set<TblAddress> getTblAddresses() {
        return tblAddresses;
    }

    public void setTblAddresses(Set<TblAddress> tblAddresses) {
        this.tblAddresses = tblAddresses;
    }

    public Set<TblAlarm> getTblAlarms() {
        return tblAlarms;
    }

    public void setTblAlarms(Set<TblAlarm> tblAlarms) {
        this.tblAlarms = tblAlarms;
    }

    public Set<TblAnswer> getTblAnswers() {
        return tblAnswers;
    }

    public void setTblAnswers(Set<TblAnswer> tblAnswers) {
        this.tblAnswers = tblAnswers;
    }

    public Set<TblCategoryLarge.TblBanner> getTblBanners() {
        return tblBanners;
    }

    public void setTblBanners(Set<TblCategoryLarge.TblBanner> tblBanners) {
        this.tblBanners = tblBanners;
    }

    public Set<TblCart> getTblCarts() {
        return tblCarts;
    }

    public void setTblCarts(Set<TblCart> tblCarts) {
        this.tblCarts = tblCarts;
    }

    public Set<TblCouponList> getTblCouponLists() {
        return tblCouponLists;
    }

    public void setTblCouponLists(Set<TblCouponList> tblCouponLists) {
        this.tblCouponLists = tblCouponLists;
    }

    public Set<TblInquiry> getTblInquiries() {
        return tblInquiries;
    }

    public void setTblInquiries(Set<TblInquiry> tblInquiries) {
        this.tblInquiries = tblInquiries;
    }

    public Set<TblLikedProduct> getTblLikedProducts() {
        return tblLikedProducts;
    }

    public void setTblLikedProducts(Set<TblLikedProduct> tblLikedProducts) {
        this.tblLikedProducts = tblLikedProducts;
    }

    public Set<TblLikedSeller> getTblLikedSellers() {
        return tblLikedSellers;
    }

    public void setTblLikedSellers(Set<TblLikedSeller> tblLikedSellers) {
        this.tblLikedSellers = tblLikedSellers;
    }

    public Set<TblOrder> getTblOrders() {
        return tblOrders;
    }

    public void setTblOrders(Set<TblOrder> tblOrders) {
        this.tblOrders = tblOrders;
    }

    public TblProducer getTblProducers() {
        return tblProducers;
    }

    public void setTblProducers(TblProducer tblProducers) {
        this.tblProducers = tblProducers;
    }

    public Set<TblQuestion> getTblQuestions() {
        return tblQuestions;
    }

    public void setTblQuestions(Set<TblQuestion> tblQuestions) {
        this.tblQuestions = tblQuestions;
    }

    public Set<TblReview> getTblReviews() {
        return tblReviews;
    }

    public void setTblReviews(Set<TblReview> tblReviews) {
        this.tblReviews = tblReviews;
    }

    public Set<TblUserRole> getTblUserRoles() {
        return tblUserRoles;
    }

    public void setTblUserRoles(Set<TblUserRole> tblUserRoles) {
        this.tblUserRoles = tblUserRoles;
    }

}