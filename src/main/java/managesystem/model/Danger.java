package managesystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cristph on 2016/11/6.
 */

@Table(name = "danger")
@Entity
public class Danger {

    @Id
    private int did;

    private String content;
    private String possiblity;
    private String effect;
    private String threshold;
    private String poster;
    private String tracer;
    private String conditiondescription;
    private String description;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPossiblity() {
        return possiblity;
    }

    public void setPossiblity(String possiblity) {
        this.possiblity = possiblity;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTracer() {
        return tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    public String getConditiondescription() {
        return conditiondescription;
    }

    public void setConditiondescription(String conditiondescription) {
        this.conditiondescription = conditiondescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Danger{" +
                "did=" + did +
                ", content='" + content + '\'' +
                ", possiblity='" + possiblity + '\'' +
                ", effect='" + effect + '\'' +
                ", threshold='" + threshold + '\'' +
                ", poster='" + poster + '\'' +
                ", tracer='" + tracer + '\'' +
                ", conditiondescription='" + conditiondescription + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
