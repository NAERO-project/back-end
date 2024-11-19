package naero.naeroserver.member.dto;

import java.util.Map;

public class ManageSearchDTO {
    private Map<String, String> filter;
    private Map<String, String> keyword;
    private boolean asc;
    private String critColum;

    public ManageSearchDTO() {
    }

    public ManageSearchDTO(Map<String, String> filter, Map<String, String> keyword, boolean asc, String critColum) {
        this.filter = filter;
        this.keyword = keyword;
        this.asc = asc;
        this.critColum = critColum;
    }

    public String getCritColum() {
        return critColum;
    }

    public void setCritColum(String critColum) {
        this.critColum = critColum;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public Map<String, String> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, String> filter) {
        this.filter = filter;
    }

    public Map<String, String> getKeyword() {
        return keyword;
    }

    public void setKeyword(Map<String, String> keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "ManageSearchDTO{" +
                "filter=" + filter +
                ", keyword=" + keyword +
                ", asc=" + asc +
                ", critColum='" + critColum + '\'' +
                '}';
    }
}
