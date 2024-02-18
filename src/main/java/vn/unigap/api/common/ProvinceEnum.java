package vn.unigap.api.common;

import java.util.HashMap;
import java.util.Map;


public enum ProvinceEnum {
    VungTau(790000, "Vung Tau"),
    HoChMinh(700000, "Thanh pho Ho Chi Minh"),
    HaNoi(100000, "Ha Noi"),
    DaNang(550000, "Da Nang" ),
    CanTho(900000, "Can Tho" );

    private final int provinceId;

    private final String provinceName;


    ProvinceEnum(int provinceId, String provinceName) {
        this.provinceName = provinceName;
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public static Map<Integer, String> getProvinceMap(){
        Map<Integer , String> provinceMap = new HashMap<>();
        for(ProvinceEnum province : ProvinceEnum.values()){
            provinceMap.put(province.getProvinceId(), province.getProvinceName() );
        }
        return provinceMap;
    }
}
