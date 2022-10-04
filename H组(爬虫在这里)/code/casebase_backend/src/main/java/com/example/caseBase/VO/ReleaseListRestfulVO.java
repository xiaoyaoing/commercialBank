package com.example.caseBase.VO;

import java.util.List;

public class ReleaseListRestfulVO {

    List<String> ids;
    int state;

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
