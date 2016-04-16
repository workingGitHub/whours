package com.instance.working.whours.model;

import java.util.UUID;

/**
 * Created by Administrator on 2016/4/16 0016.
 */
public abstract class ProjectInterface {
    public abstract void UpdateCostTime(long NewCostTime,long OldCostTime);
    public abstract UUID getProjectId();

}
