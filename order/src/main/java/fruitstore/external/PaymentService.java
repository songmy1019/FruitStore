package fruitstore.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

// FeignClient 마이크로 서비스간 Request-Response 호출에 있어 대상 서비스를 FeignClient로 callback옵션 주기
// ordercanceled 시에도 이렇게 처리해야하는지 확인 필요
//@FeignClient(name="payments", url="http://localhost:8082", fallback = PaymentServiceFallback.class)

@FeignClient(name="payments", url="http://payment:8080")
public interface PaymentService {
    @RequestMapping(method= RequestMethod.GET, path="/payments")
    public void payment(@RequestBody Payment payment);

}

