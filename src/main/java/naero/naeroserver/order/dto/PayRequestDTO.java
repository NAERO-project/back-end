package naero.naeroserver.order.dto;

import java.util.List;
import java.util.Map;

public class PayRequestDTO {

    private OrderDTO orderDTO;
    private PaymentDTO paymentDTO;
    private Map<Integer, Integer> optionIds;

    public PayRequestDTO() {
    }

    public PayRequestDTO(OrderDTO orderDTO, PaymentDTO paymentDTO, Map<Integer, Integer> productId) {
        this.orderDTO = orderDTO;
        this.paymentDTO = paymentDTO;
        this.optionIds = productId;
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

    public Map<Integer, Integer> getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(Map<Integer, Integer> optionIds) {
        this.optionIds = optionIds;
    }

    @Override
    public String toString() {
        return "PayRequestDTO{" +
                "orderDTO=" + orderDTO +
                ", paymentDTO=" + paymentDTO +
                ", productID=" + optionIds +
                '}';
    }
}
