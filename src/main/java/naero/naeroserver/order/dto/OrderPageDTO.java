package naero.naeroserver.order.dto;

import naero.naeroserver.member.dto.UserDTO;

import java.util.List;

public class OrderPageDTO {

    OrderDTO orderDTO;
    UserDTO userDTO;
    List<OrderPageProductDTO> orderPageProductDTOList;

    public OrderPageDTO() {
    }

    public OrderPageDTO(OrderDTO orderDTO, UserDTO userDTO, List<OrderPageProductDTO> orderDetailProductDTO) {
        this.orderDTO = orderDTO;
        this.userDTO = userDTO;
        this.orderPageProductDTOList = orderDetailProductDTO;
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public List<OrderPageProductDTO> getOrderPageProductDTOList() {
        return orderPageProductDTOList;
    }

    public void setOrderPageProductDTOList(List<OrderPageProductDTO> orderPageProductDTOList) {
        this.orderPageProductDTOList = orderPageProductDTOList;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "OrderPageDTO{" +
                "orderDTO=" + orderDTO +
                ", userDTO=" + userDTO +
                ", orderPageProductDTOList=" + orderPageProductDTOList +
                '}';
    }
}
