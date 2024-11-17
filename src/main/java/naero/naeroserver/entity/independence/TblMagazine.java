package naero.naeroserver.entity.independence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_magazine")
public class TblMagazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "magazine_id", nullable = false)
    private Integer magazineId;

    @Size(max = 20)
    @NotNull
    @Column(name = "magazine_title", nullable = false, length = 20)
    private String magazineTitle;

    @NotNull
    @Lob
    @Column(name = "magazine_content", nullable = false)
    private String magazineContent;

    @Size(max = 255)
    @Column(name = "magazine_photo")
    private String magazinePhoto;

    public Integer getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(Integer id) {
        this.magazineId = id;
    }

    public String getMagazineTitle() {
        return magazineTitle;
    }

    public void setMagazineTitle(String magazineTitle) {
        this.magazineTitle = magazineTitle;
    }

    public String getMagazineContent() {
        return magazineContent;
    }

    public void setMagazineContent(String magazineContent) {
        this.magazineContent = magazineContent;
    }

    public String getMagazinePhoto() {
        return magazinePhoto;
    }

    public void setMagazinePhoto(String magazinePhoto) {
        this.magazinePhoto = magazinePhoto;
    }

}