package fruitstore.external;

import org.springframework.web.bind.annotation.RequestBody;

public class PaymentServiceFallback implements PaymentService{
    @Override
    public void payment(fruitstore.external.Payment payment) {
        // TODO Auto-generated method stub
        System.out.println("Circuit breaker has been opened. Fallback returned instead.");
        System.out.println("paymentservicefallback 메시지");
        
    }
}
