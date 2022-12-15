package com.sso.web.logic;


public class TextAdd extends Det {

    private double[] coeffs = new double[10];

    public TextAdd(double[] arr) {
        System.arraycopy(arr, 0, this.coeffs, 0, 10);
    }
    public String latexText(String text) {
        return "\\(" + text + "\\)";
    }
    private final String[] mults = {"x²", "y²", "z²", "xy", "xz", "yz", "x", "y", "z", " = 0"};
    private final String[] aLatex = {"a_{11}", "a_{22}", "a_{33}", "a_{12}",  "a_{13}", "a_{23}", "a_{1}", "a_{2}", "a_{3}", "a_{0}"};

    public String textK1(String[] arr) {
        return "\\(\\begin{vmatrix} " + arr[0] + " & " + arr[1] + " \\\\ " + arr[1] + " & " + arr[2] + " \\end{vmatrix} \\)";
    }

    public String equation() {
        StringBuilder text = new StringBuilder(this.coeffs[0] + "x²");
        for (int i = 1;i<this.coeffs.length;i++)
            text.append(valueEqu(this.coeffs[i], this.mults[i]));
        return text.toString();
    }
    public String allEquation() {
        String text = "Рівняння\\,поверхні:\\\\[0.1in]" + equation();
        return latexText(text);
    }

    public String posOrNeg(double number) {
        return (number < 0 ? "":"+") + number;
    }

    public String valueEqu(double coeff, String mult) {
        return posOrNeg(coeff) + mult;
    }


    public String nameValue(String name, double value) {
        return name + " = " +value + ",\\,";
    }

    public String coeff(){
        String text = "Виразимо\\,коефіцієнти:\\\\[0.1in]";
        for (int i = 0;i<this.coeffs.length-1;i++)
            text += nameValue(aLatex[i], this.coeffs[i]*((i > 2 && i < 9) ? 0.5:1.0));
        return latexText(text + " a_{0} = " + this.coeffs[this.coeffs.length-1]);
    }

    public String textI1(double I1) {
        String text = "Підрахуймо\\,ортогональні\\,інваріанти\\,I_{1},\\,I_{2},\\,I_{3}\\,та\\,K_{3}:\\\\[0.1in]"+"I_{1} = a_{11} + a_{22} + a_{33} = " + this.coeffs[0] + posOrNeg(this.coeffs[1]) + posOrNeg(this.coeffs[2]) + " = " + I1;
        return latexText(text);
    }


    public String textI2(double I2) {
        return "\\(I_{2} = \\)" + sumDet2(new String[]{"a_{11}", "a_{12}", "a_{22}", "a_{11}", "a_{13}", "a_{33}", "a_{22}", "a_{23}", "a_{33}"}) + " = " + sumDet2(new double[] {this.coeffs[0], this.coeffs[3]/2.0, this.coeffs[1], this.coeffs[0], this.coeffs[4]/2.0, this.coeffs[2], this.coeffs[1], this.coeffs[5]/2.0, this.coeffs[2]}) + " = \\(" + I2 + "\\)";
    }

    public String textI3(double I3) {
        return latexText("I_{3}\\,=\\,") + det3(new String[] {"a_{11}", "a_{12}", "a_{13}", "a_{22}", "a_{23}", "a_{33}"}) + latexText("\\,=\\,") + det3(new double[] {this.coeffs[0], this.coeffs[3] / 2.0, this.coeffs[4] / 2.0, this.coeffs[1], this.coeffs[5] / 2.0, this.coeffs[2]}) + latexText("\\,=\\,"+I3);
    }

    public String textK3(double K3) {
        return latexText("K_{3}\\,=\\,") + det4(new String[] {"a_{11}", "a_{12}", "a_{13}", "a_{1}", "a_{22}", "a_{23}", "a_{2}", "a_{33}", "a_{3}", "a_{0}"}) + latexText("\\,=\\,") + det4(new double[] {this.coeffs[0], this.coeffs[3] / 2.0, this.coeffs[4] / 2.0, this.coeffs[6] / 2.0, this.coeffs[1], this.coeffs[5] / 2.0, this.coeffs[7] / 2.0, this.coeffs[2], this.coeffs[8] / 2.0, this.coeffs[9]}) + latexText("\\,=\\," + K3);
    }

    public String spaceLatex(String text) {
        return text.replace(" ", "\\,");
    }

}
