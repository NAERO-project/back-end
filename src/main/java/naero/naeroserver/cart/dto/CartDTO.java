package naero.naeroserver.cart.dto;

public class CartDTO {

    private Integer cartId;
    private Integer optionId;
    private Integer count;
    private Integer price;
    private Integer userId;

    public CartDTO() {
    }

    public CartDTO(Integer cartId, Integer optionId, Integer count, Integer price, Integer userId) {
        this.cartId = cartId;
        this.optionId = optionId;
        this.count = count;
        this.price = price;
        this.userId = userId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
