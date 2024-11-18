package naero.naeroserver.cart.dto;

public class CartDTO {

    private int cartId;
    private int optionId;
    private int count;
    private int price;
    private int userId;

    public CartDTO() {
    }

    public CartDTO(int cartId, int optionId, int count, int price, int userId) {
        this.cartId = cartId;
        this.optionId = optionId;
        this.count = count;
        this.price = price;
        this.userId = userId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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
                "cartId=" + cartId +
                ", optionId=" + optionId +
                ", count=" + count +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}
