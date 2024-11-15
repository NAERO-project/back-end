package naero.naeroserver.order.dto;

public class PayRequestDTO {

    private OrderDTO orderDTO;
    private PaymentDTO paymentDTO;
    private int optionId;

    public PayRequestDTO() {
    }

    public PayRequestDTO(OrderDTO orderDTO, PaymentDTO paymentDTO, int productId) {
        this.orderDTO = orderDTO;
        this.paymentDTO = paymentDTO;
        this.optionId = productId;
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

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    @Override
    public String toString() {
        return "PayRequestDTO{" +
                "orderDTO=" + orderDTO +
                ", paymentDTO=" + paymentDTO +
                ", productID=" + optionId +
                '}';
    }
}
