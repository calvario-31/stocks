package stocks.model;

import java.util.List;

public class Data {
    private final List<SingleData> listData;
    private Result result;

    public Data(List<SingleData> listData) {
        this.listData = listData;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<SingleData> getListData() {
        return listData;
    }

    public Result getResult() {
        return result;
    }
}
