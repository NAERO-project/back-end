package naero.naeroserver.member.dto;

import java.util.Arrays;

public class ManageSearchDTO {
    private String[] filter;
    private String keyword;

    public ManageSearchDTO() {
    }

    public ManageSearchDTO(String[] filter, String keyword) {
        this.filter = filter;
        this.keyword = keyword;
    }

    public String[] getFilter() {
        return filter;
    }

    public void setFilter(String[] filter) {
        this.filter = filter;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "ManageSearchDTO{" +
                "filter=" + Arrays.toString(filter) +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
