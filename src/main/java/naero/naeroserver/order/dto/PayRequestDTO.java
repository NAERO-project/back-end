package naero.naeroserver.order.dto;

public class PayRequestDTO {

    private OrderDTO orderDTO;
    private PaymentDTO paymentDTO;
    private int productId;

    public PayRequestDTO() {
    }

    public PayRequestDTO(OrderDTO orderDTO, PaymentDTO paymentDTO, int productId) {
        this.orderDTO = orderDTO;
        this.paymentDTO = paymentDTO;
        this.productId = productId;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "PayRequestDTO{" +
                "orderDTO=" + orderDTO +
                ", paymentDTO=" + paymentDTO +
                ", productID=" + productId +
                '}';
    }
}
