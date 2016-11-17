package managesystem.service;

import managesystem.model.Danger;

import java.util.Date;
import java.util.List;

/**
 * Created by cristph on 2016/11/6.
 */
public interface DangerService {

    public String save(Danger danger);

    public String deleteDanger(int did);

    public List<Danger> getDangersByTime(Date d1,Date d2);

    public List<Danger> getDangersByRid(int rid);

    public Danger getDangerByDid(int did);

    public String update(Danger danger);


}
