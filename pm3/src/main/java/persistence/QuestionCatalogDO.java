package persistence;

import javax.persistence.*;
import java.util.Set;

/**
 * DO for a question catalog
 */
@Entity
@Table(name = "tquestion_catalog")
public class QuestionCatalogDO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "question_catalog_id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "questionCatalogDo", orphanRemoval = true)
    private Set<QuestionTemplateDO> questionTemplatesDo;
    @ManyToOne
    private UserDO userDo;

    public QuestionCatalogDO() { }

    /**
     * @param userDo DO for the assigned user
     */
    public QuestionCatalogDO(UserDO userDo) {
        this.userDo = userDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDO getUserDo() {
        return userDo;
    }

    public void setUserDo(UserDO userDo) {
        this.userDo = userDo;
    }

    public Set<QuestionTemplateDO> getQuestionTemplatesDo() {
        return questionTemplatesDo;
    }

    public void addQuestionTemplateDo(QuestionTemplateDO questionTemplateDo) {
        this.questionTemplatesDo.add(questionTemplateDo);
    }
}
