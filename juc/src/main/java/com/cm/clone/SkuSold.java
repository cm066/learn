package com.cm.clone;

/**
 * @author Administrator
 */
public class SkuSold {
    private Long skuId;
    private Long skuSold;

    public SkuSold(Long skuId, Long skuSold) {
        this.skuId = skuId;
        this.skuSold = skuSold;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSkuSold() {
        return skuSold;
    }

    public void setSkuSold(Long skuSold) {
        this.skuSold = skuSold;
    }
}
