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
    private String possibility;
    private String effect;
    private String threshold;
    private String poster;
    private String condition;
    private String description;
    private String time;
    private String rid;

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
                ", possibility='" + possibility + '\'' +
                ", effect='" + effect + '\'' +
                ", threshold='" + threshold + '\'' +
                ", poster='" + poster + '\'' +
                ", condition='" + condition + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getPossibility() {
        return possibility;
    }

    public void setPossibility(String possibility) {
        this.possibility = possibility;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
