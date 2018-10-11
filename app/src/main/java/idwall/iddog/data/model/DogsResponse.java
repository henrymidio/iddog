package idwall.iddog.data.model;

import java.util.List;

public class DogsResponse {
    private String category;
    private List<String> list;

    public DogsResponse(String category, List<String> list) {
        this.category = category;
        this.list = list;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

}
