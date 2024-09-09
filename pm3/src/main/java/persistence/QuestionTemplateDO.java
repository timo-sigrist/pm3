package persistence;

import javax.persistence.*;

/**
 * DO for a question template
 */
@Entity
@Table(name = "tquestion_templates")
public class QuestionTemplateDO {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "question_templates_id", nullable = false)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;
    @ManyToOne
    private UserDO userDo;
    @ManyToOne
    @JoinColumn(name = "question_catalog_id", nullable = false)
    private QuestionCatalogDO questionCatalogDo;

    public QuestionTemplateDO() { }

    /**
     * @param userDo DO for the assigned user
     * @param questionCatalogDo DO for the assigned question dialog
     */
    public QuestionTemplateDO(UserDO userDo, QuestionCatalogDO questionCatalogDo) {
        this.userDo = userDo;
        this.questionCatalogDo = questionCatalogDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDO getUserDo() {
        return userDo;
    }

    public void setUserDo(UserDO userDo) {
        this.userDo = userDo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionCatalogDO getQuestionCatalogDo() {
        return questionCatalogDo;
    }

    public void setQuestionCatalogDo(QuestionCatalogDO questionCatalogDo) {
        this.questionCatalogDo = questionCatalogDo;
    }
}
