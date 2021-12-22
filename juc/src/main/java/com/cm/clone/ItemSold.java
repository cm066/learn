package com.cm.clone;

import java.util.List;

/**
 * @author Administrator
 */
public class ItemSold implements Cloneable{
    private Long itemId;
    private Long sold;
    private List<SkuSold> skuSolds;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public List<SkuSold> getSkuSolds() {
        return skuSolds;
    }

    public void setSkuSolds(List<SkuSold> skuSolds) {
        this.skuSolds = skuSolds;
    }
}
