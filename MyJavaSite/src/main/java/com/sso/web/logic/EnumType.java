package com.sso.web.logic;

public enum EnumType {
    Ellipsoid("Еліпсоїд", 1),
    ImaginaryEllipsoid("Уявний еліпсоїд", 2),
    ImaginaryConicalSurface("Уявний конус", 3),
    OneSheetHyperboloid("Однопорожнинний гіперболоїд", 4),
    TwoSheetHyperboloid("Двопорожнинний гіперболоїд", 5),
    ConicalSurface("Конус", 6),
    EllipticParaboloid("Еліптичний параболоїд", 7),
    HyperbolicParaboloid("Гіперболічний параболоїд", 8),
    EllipticCylinder("Еліптичний циліндр", 9),
    ImaginaryEllipticCylinder("Уявний еліптичний циліндр", 10),
    PairImaginaryIntersectingPlanes("Пара уявних площин, що перетинаються", 11),
    HyperbolicCylinder("Гіперболічний циліндр", 12),
    PairIntersectingPlanes("Пара площин, що перетинаються", 13),
    PairParallelPlanes("Пара паралельних площин", 14),
    PairImaginaryParallelPlanes("Пара уявних паралельних площин", 15),
    PairCoincidentPlanes("Пара площин, що збігаються", 16),
    ParabolicCylinder("Параболічний циліндр", 17);

    private final String key;
    private final Integer value;

    EnumType(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }
    public int getValue() {
        return this.value;
    }
}
