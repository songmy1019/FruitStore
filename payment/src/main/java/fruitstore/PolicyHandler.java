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
    @Autowired PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_CancelPayment(@Payload OrderCanceled orderCanceled){

        if(!orderCanceled.validate()) return;
        // println내용 수정/추가, paystatus 셋팅
        System.out.println("\n\n##### listener CancelPayment : " + orderCanceled.toJson() + "\n\n");
        System.out.println("결재취소");
        Payment payment = new Payment();
        //Payment.setPayStatus("결재취소");
        paymentRepository.save(payment);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
