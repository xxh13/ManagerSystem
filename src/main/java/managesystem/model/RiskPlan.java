package managesystem.model;

import java.util.Date;

/**
 *
 * Created by XXH on 2016/11/17.
 */
public class RiskPlan {
    int rid;
    String name;
    Date time;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date date) {
        this.time = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RiskPlan(int rid, String name, Date date) {
        this.rid = rid;
        this.name = name;
        this.time = date;
    }

    public RiskPlan(String name, Date date) {
        this.name = name;
        this.time = date;
    }

}
