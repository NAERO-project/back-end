package naero.naeroserver.product.dto;

import java.util.Map;

public class ProductSearchDTO {
    private Map<String, String> filter;
    private Map<String, String> keyword;
    private boolean asc;
    private String critColum;

    public ProductSearchDTO(Map<String, String> filter, Map<String, String> keyword, boolean asc, String critColum) {
        this.filter = filter;
        this.keyword = keyword;
        this.asc = asc;
        this.critColum = critColum;
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

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String getCritColum() {
        return critColum;
    }

    public void setCritColum(String critColum) {
        this.critColum = critColum;
    }

    @Override
    public String toString() {
        return "ProductSearchDTO{" +
                "filter=" + filter +
                ", keyword=" + keyword +
                ", asc=" + asc +
                ", critColum='" + critColum + '\'' +
                '}';
    }
}