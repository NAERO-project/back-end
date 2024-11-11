package naero.naeroserver.entity.independence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_faq")
public class TblFaq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "faq_title", nullable = false, length = 50)
    private String faqTitle;

    @NotNull
    @Lob
    @Column(name = "faq_content", nullable = false)
    private String faqContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaqTitle() {
        return faqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }

    public String getFaqContent() {
        return faqContent;
    }

    public void setFaqContent(String faqContent) {
        this.faqContent = faqContent;
    }

}