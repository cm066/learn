package rocketmqandseata;


import com.cm.business.entity.Msg;
import com.cm.business.producer.SpringTransactionProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@SpringBootTest()
class BuisnessApplicationTests {

    @Autowired
    private SpringTransactionProducer springTransactionProducer;
    @Test
    void contextLoads() {
    }
    @Test
    public void test()
    {
        Msg msg = new Msg();
        msg.setProductId(1L);
        msg.setCurrentUse(1);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        msg.setUuid(uuid);
        springTransactionProducer.sendMsg("mss1",msg);
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){
        System.out.println(UUID.randomUUID());
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(11);
        objects.remove(1);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1,1);
        objectObjectHashMap.remove(1);
//        Collections.shuffle();
    }

}
