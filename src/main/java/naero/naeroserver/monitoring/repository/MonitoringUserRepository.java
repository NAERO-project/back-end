package naero.naeroserver.monitoring.repository;

import naero.naeroserver.entity.user.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface MonitoringUserRepository extends JpaRepository<TblUser, Integer> {
    @Query("SELECT COUNT(u.username) " +
            "FROM TblUser u " +
            "WHERE u.enrollDate >= :yesterday")
    Integer findOneDayAgoRegistMembers(@Param("yesterday") Instant yesterday);

    @Query("SELECT COUNT(u.username) " +
            "FROM TblUser u " +
            "WHERE u.enrollDate BETWEEN :twoDaysAgo AND :yesterday")
    Integer findTwoDaysAgoRegistMembers(@Param("twoDaysAgo") Instant twoDaysAgo, @Param("yesterday") Instant yesterday);
}
