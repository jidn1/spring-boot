package com.notes.study.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import io.netty.util.Timeout;
import lombok.extern.log4j.Log4j2;
import net.openhft.affinity.AffinityStrategies;
import net.openhft.affinity.AffinityThreadFactory;

import java.util.Timer;
import java.util.TimerTask;

@Log4j2
public class DisruptorTest {

    public static void main(String[] args) {
        new DisruptorTest().initDisruptor();
    }


    private Disruptor disruptor;


    public void initDisruptor() {
        disruptor = new Disruptor(
                // event factory
                new RbCmdFactory(),

                // ring buffer size 2^N  13544980 & (1024 - 1)
                1024,

                // 线程池
                new AffinityThreadFactory("aft_core", AffinityStrategies.ANY),

                // 1个生产者线程
                ProducerType.SINGLE,

                //消费者等待策略
                // busySpinWainStrategy   while(true)
                //yieldWait 轮询完成 使用yield 让出 cpu资源 等待重新唤醒线程 折中

                // block Wait
                //TimeOutBlockWait

                new BlockingWaitStrategy()
        );

        // 设置全局异常处理器 在代码内部使用 try-catch 非常不合适

        final DisruptorExceptionHandler disruptorExceptionHandler = new DisruptorExceptionHandler(
                "disruptor-1",
                (ex, seq) -> {
                    log.error("Exception throw on seq{}", seq, ex);

                }
        );

        disruptor.setDefaultExceptionHandler(disruptorExceptionHandler);

        ConsumerA consumerA = new ConsumerA();
        ConsumerB  consumerB = new ConsumerB();

        disruptor.handleEventsWith(consumerA).then(consumerB);
        disruptor.start();

        //发布数据
        new Timer().schedule(new ProducerTask(),2000,1000);

    }

    private static final EventTranslatorOneArg<RbCmd, RbData> PUB_TRANSLATOR =
            (rbCmd, seq, rbData) -> {
                rbCmd.code = rbData.code;
                rbCmd.msg = rbData.msg;
            };

    int index = 0;
    private class ProducerTask extends TimerTask {

        @Override
        public void run() {
            disruptor.getRingBuffer().publishEvent(PUB_TRANSLATOR, new RbData(index, ""));
            index++;
        }

    }

    private class ConsumerA implements EventHandler<RbCmd> {

        @Override
        public void onEvent(RbCmd rbCmd, long l, boolean b) throws Exception {
            log.info("ConsumerA rev:{}", rbCmd);
        }
    }

    private class ConsumerB implements EventHandler<RbCmd> {

        @Override
        public void onEvent(RbCmd rbCmd, long l, boolean b) throws Exception {
            log.info("ConsumerB rev:{}", rbCmd);
        }
    }
}
