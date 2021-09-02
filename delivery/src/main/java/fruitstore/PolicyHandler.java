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
    @Autowired DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentCanceled_CancleDelivery(@Payload PaymentCanceled paymentCanceled){

        if(!paymentCanceled.validate()) return;

        System.out.println("\n\n##### listener CancleDelivery : " + paymentCanceled.toJson() + "\n\n");
        System.out.println("\n\n##### 결재 취소됨");
        Delivery delivery = new Delivery();
        deliveryRepository.save(delivery);


        // Sample Logic //
        // Delivery delivery = new Delivery();
        // deliveryRepository.save(delivery);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaymentApproved_AcceptDelivery(@Payload PaymentApproved paymentApproved){

        if(!paymentApproved.validate()) return;

        System.out.println("\n\n##### listener AcceptDelivery : " + paymentApproved.toJson() + "\n\n");
        System.out.println("\n\n##### 결재 승인됨");
        Delivery delivery = new Delivery();
        deliveryRepository.save(delivery);


        // Sample Logic //
        // Delivery delivery = new Delivery();
        // deliveryRepository.save(delivery);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
