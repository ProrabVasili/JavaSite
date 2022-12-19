package com.sso.web.logic;


public class HideSeekType {
    private double[] invariants;

    public HideSeekType() throws Exception {
        this(new double[]{1.0, 2.0});
    }
    public HideSeekType(double[] arr) throws Exception{
        if (arr.length != 6)
            throw new Exception("Access denied - Array must be 6 in length");
        this.invariants = new double[6];
        System.arraycopy(arr, 0, this.invariants, 0, 6);
    }

    public EnumType typeSSO() {
        double  I1 = this.invariants[0],
                I2 = this.invariants[1],
                I3 = this.invariants[2],
                K1 = this.invariants[3],
                K2 = this.invariants[4],
                K3 = this.invariants[5];

        if (I3 != 0) {
            if (I2 > 0 && I1*I3 > 0)
                return ellipticalType(K3);
            else if (I2 <= 0 || I1*I3 <= 0)
                return hyperbolicType(K3);
        }
        return parabolicType(I1, I2, K1, K2, K3);
    }

    private EnumType ellipticalType(double K3) {
        if (K3 < 0)
            return EnumType.Ellipsoid;
        else if (K3 > 0)
            return EnumType.ImaginaryEllipsoid;
        else
            return EnumType.ImaginaryConicalSurface;
    }

    private EnumType hyperbolicType(double K3) {
        if (K3 > 0)
            return EnumType.OneSheetHyperboloid;
        else if (K3 < 0)
            return EnumType.TwoSheetHyperboloid;
        else
            return EnumType.ConicalSurface;
    }

    private EnumType parabolicType(double I1, double I2, double K1, double K2, double K3) {
        if (K3 < 0)
            return EnumType.EllipticParaboloid;
        else if (K3 > 0)
            return EnumType.HyperbolicParaboloid;
        else {
            if (I2 > 0)
                return I2GreaterZero(I1, K2);
            else if (I2 < 0)
                return I2LessZero(K2);
            else {
                if (K2 == 0)
                    return K2Zero(K1);
                else
                    return EnumType.ParabolicCylinder;
            }
        }
    }

    private EnumType I2GreaterZero(double I1, double K2) {
        if (I1*K2 < 0)
            return EnumType.EllipticCylinder;
        else if (I1*K2 > 0)
            return EnumType.ImaginaryEllipticCylinder;
        else
            return EnumType.PairImaginaryIntersectingPlanes;
    }

    private EnumType I2LessZero(double K2) {
        if (K2 != 0)
            return EnumType.HyperbolicCylinder;
        else
            return EnumType.PairIntersectingPlanes;
    }

    private EnumType K2Zero(double K1) {
        if (K1 < 0)
            return EnumType.PairParallelPlanes;
        else if (K1 > 0)
            return EnumType.PairImaginaryParallelPlanes;
        else
            return EnumType.PairCoincidentPlanes;
    }
}
