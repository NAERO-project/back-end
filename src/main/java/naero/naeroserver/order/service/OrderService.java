package naero.naeroserver.order.service;

import naero.naeroserver.member.repository.UserRepository;
import naero.naeroserver.order.dto.PaymentDTO;
import naero.naeroserver.order.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
//    private final ModelMapper modelMapper;
//    private final OrderRepository orderRepository;
//    private final UserRepository userRepository;
//
//    public Object insertOrder(PaymentDTO paymentDTO) {
//    }
}
