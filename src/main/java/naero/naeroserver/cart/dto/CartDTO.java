package naero.naeroserver.cart.dto;

public class CartDTO {

    private int id;
    private int optionId;
    private int count;
    private int price;
    private int userId;

    public CartDTO() {
    }

    public CartDTO(int id, int optionId, int count, int price, int userId) {
        this.id = id;
        this.optionId = optionId;
        this.count = count;
        this.price = price;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartId=" + id +
                ", optionId=" + optionId +
                ", count=" + count +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}
