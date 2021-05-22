package com.notes.study.test;

import com.notes.study.enums.SpotDepthTypeEnum;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
public class DealWith {

    private static SpotDepthTypeEnum spotDepthTypeEnum;

    private static BigDecimal price;

    private static Integer priceScale;

    private static Integer scale;

    public DealWith() {
    }

    public DealWith(SpotDepthTypeEnum spotDepthTypeEnum, BigDecimal price, Integer priceScale) {
        this.spotDepthTypeEnum = spotDepthTypeEnum;
        this.priceScale = priceScale;
        this.price = price;
    }


    public static DealWith DepthEnum(SpotDepthTypeEnum spotDepthTypeEnum, BigDecimal price, Integer priceScale) {
        return new DealWith(spotDepthTypeEnum, price, priceScale);
    }


    /**
     * step 处理
     */
    public void step() {
        getScale();
        if (ifNeedMove()) {
             needMove();
        } else {
             notNeedMove();
        }
    }

    /**
     * 获取保留的小数点
     * @return
     */
    public Integer getScale() {
        switch (spotDepthTypeEnum) {
            case STEP0:
                scale = 0;
                break;
            case STEP1:
            case STEP2:
            case STEP3:
                scale = (int) Math.log10(Integer.valueOf(spotDepthTypeEnum.getValue()));
                break;
            case STEP4:
            case STEP5:
                scale = (int) Math.log10(Integer.valueOf(spotDepthTypeEnum.getValue()));
                scale = scale - 1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + spotDepthTypeEnum);
        }
        return scale;
    }

    /**
     * 需要移动小数点
     * @return
     */
    public BigDecimal needMove() {
        BigDecimal bigDecimal1 = price.movePointLeft(getIntScale()).setScale(0, RoundingMode.UP);

        bigDecimal1 = roundUp5(bigDecimal1.intValue(), isStep4());

        BigDecimal afterPrice = bigDecimal1.movePointRight(getIntScale()).setScale(1, RoundingMode.DOWN);

        log.info("needMove price:{}, afterPrice:{}",price,afterPrice);
        return afterPrice;
    }

    /**
     * 不需要移动小数点
     * @return
     */
    public BigDecimal notNeedMove() {
        BigDecimal afterPrice = price.setScale(getIntScale(), RoundingMode.UP);
        afterPrice = roundUpPlaces5(afterPrice, isStep4());
        log.info("notNeedMove price:{}, afterPrice:{}",price,afterPrice);
        return afterPrice;
    }


    /**
     * 是否需要移动小数点
     * @return
     */
    public boolean ifNeedMove() {
        log.info("step:{} scale:{},priceScale:{}",spotDepthTypeEnum.getCode(), scale,priceScale);
        return scale >= priceScale;
    }


    /**
     * 值向上取整为5的倍数
     * @param n
     * @param flag
     * @return
     */
    public BigDecimal roundUp5(int n, boolean flag) {
        if (!flag) {
            return new BigDecimal(n);
        }
        return new BigDecimal((n + 4) / 5 * 5);
    }

    /**
     * 小数位过多时 处理5的倍数
     * @param n
     * @param flag
     * @return
     */
    public BigDecimal roundUpPlaces5(BigDecimal n, boolean flag) {
        if (!flag) {
            return n;
        }
        int s = n.movePointRight(getIntScale()).intValue();
        return new BigDecimal((s + 4) / 5 * 5).movePointLeft(getIntScale());
    }

    /**
     * 获取移动的小数位
     * @return
     */
    public Integer getIntScale() {
        return new BigDecimal(priceScale).subtract(new BigDecimal(scale)).abs().intValue();
    }

    /**
     * 是否是 step4
     * @return
     */
    public boolean isStep4() {
        return SpotDepthTypeEnum.STEP4.equals(spotDepthTypeEnum);
    }

}
