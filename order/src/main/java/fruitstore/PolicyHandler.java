package fruitstore;

import fruitstore.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryStarted_UpdateOrderStatus(@Payload DeliveryStarted deliveryStarted){

        if(!deliveryStarted.validate()) return;
        //아래 부분 로직 추가 policyhandler수정
        System.out.println("\n\n##### listener UpdateOrderStatus : " + deliveryStarted.toJson() + "\n\n");
        System.out.println("deliberyStarted 배송 시작");
        System.out.println("주문번호 : " + deliveryStarted.getOrderId());
        //System.out.println("배송번호 : " + deliveryStarted.getDeliveryId());
        Order order = new Order();
        //order.setOrderId(deliveryStarted.getOrderId());
        orderRepository.save(order);


    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryCanceled_UpdateOrderStatus(@Payload DeliveryCanceled deliveryCanceled){

        if(!deliveryCanceled.validate()) return;

        System.out.println("\n\n##### listener UpdateOrderStatus : " + deliveryCanceled.toJson() + "\n\n");



        // Sample Logic //
        // Order order = new Order();
        // orderRepository.save(order);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_UpdateOrderStatus(@Payload PaymentCanceled paymentCanceled){

        if(!paymentCanceled.validate()) return;

        System.out.println("\n\n##### listener UpdateOrderStatus : " + paymentCanceled.toJson() + "\n\n");



        // Sample Logic //
        // Order order = new Order();
        // orderRepository.save(order);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_UpdateOrderStatus(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener UpdateOrderStatus : " + paymentApproved.toJson() + "\n\n");



        // Sample Logic //
        // Order order = new Order();
        // orderRepository.save(order);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
