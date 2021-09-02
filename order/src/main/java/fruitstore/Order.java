package fruitstore;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Date;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderId;
    private Long prodId;
    private Long userId;
    private Integer qty;
    private Integer price;
    private String address;
    private String orderStatus;

    @PostPersist
    public void onPostPersist(){

        /*
        //log보기시작
        System.out.println("\nfruitStore.order\n#####################################################");
        System.out.println("orderId : " + getOrderId() ) ;    
        System.out.println("userId : " + getUserId() ) ;     
        System.out.println("prodId : " + getProdId() ) ;  
        System.out.println("qty : " + getQty() ) ;  
        System.out.println("price : " + getPrice() ) ;  
        System.out.println("#########################################################################\n");
        //log종료  
        //주문 신청시 payment 값 셋팅 order.java -> paymentservice로
        fruitstore.external.Payment payment = new fruitstore.external.Payment();
        payment.setOrderId(getOrderId());
        payment.setPayId(getOrderId()+1000);
        payment.setOrderStatus("주문신청됨");
        payment.setPayStatus("결재요청됨");
        payment.setProdId(getProdId());
        payment.setPrice(getPrice());
        payment.setQty(getQty());
        payment.setUserId(getUserId());
        OrderApplication.applicationContext.getBean(fruitstore.external.PaymentService.class)
        .payment(payment);    
        //payment or update? 테스트 필요
        //
        */

        OrderPlaced orderPlaced = new OrderPlaced();
        BeanUtils.copyProperties(this, orderPlaced);
        orderPlaced.publishAfterCommit();

       // 원래 있던 부분인데 위에 변수 자동 셋팅해주는 것으로 수정함. 이 부분 테스트 필요
       // fruitstore.external.Payment payment = new fruitstore.external.Payment();
       // OrderApplication.applicationContext.getBean(fruitstore.external.PaymentService.class)
       //     .payment(payment);

       // OrderCanceled orderCanceled = new OrderCanceled();
       // BeanUtils.copyProperties(this, orderCanceled);
       // orderCanceled.publishAfterCommit();

    }
    // 위에 초기 셋팅 제외하고 추가 한 부분

    @PreRemove
    public void onPreRemove(){
        
        System.out.println("onPreRemove");
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();


    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }




}