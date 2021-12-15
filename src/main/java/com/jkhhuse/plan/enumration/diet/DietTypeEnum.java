package com.jkhhuse.plan.enumration.diet;

public enum DietTypeEnum {
    /**
     * 特奶
     */
    SPECIAL_MILK(0),

    /**
     * 母乳
     */
    BREAST_MILK(1),

    /**
     * 蔬菜
     */
    VEGETABLES(2),

    /**
     * 肉食
     */
    MEAT(3),

    /**
     * 水果
     */
    FRUIT(4),

    /**
     * 主食
     */
    MAIN_FOOD(5),

    /**
     * 加工食品
     */
    PROCESSED_FOOD(6);

    private int index;

    private DietTypeEnum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
