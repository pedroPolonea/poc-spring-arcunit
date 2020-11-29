package com.psd.filter.entity.enums;

public enum ProductType {
    ELECTRONICS,
    HOME_APPLIANCES,
    KITCHEN,
    BATHROOM;

    public static String getName (ProductType productType){
        for (ProductType type : ProductType.values()) {
            if (type.equals(productType)){
                return type.name();
            }
        }
        return null;
    }

    public static ProductType getEnum (String productType){
        for (ProductType type : ProductType.values()) {
            if (productType.equals(type.name())){
                return type;
            }
        }
        return null;
    }

}
