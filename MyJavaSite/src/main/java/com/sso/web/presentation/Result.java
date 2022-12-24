package com.sso.web.presentation;


import com.sso.web.logic.Det;
import com.sso.web.logic.EnumType;
import com.sso.web.logic.HideSeekType;
import com.sso.web.logic.TextAdd;
import org.springframework.ui.ModelMap;


public class Result extends Det {
    private final double[] coeffs = new double[10];

    public Result() throws Exception {
        this(new double[] {1.0, 2.0});
    }
    public Result(double[] arr) throws Exception{
        if (arr.length != 10)
            throw new Exception("Access denied - Array must be 10 in length");
        System.arraycopy(arr, 0, this.coeffs, 0, 10);
    }
    private final String[] namesAtr = {"a11", "a22", "a33", "a12", "a13", "a23", "a1", "a2", "a3", "a0"};
    public void answerHTML(double[] invariants, ModelMap model) throws Exception {
        TextAdd txtAdd = new TextAdd(this.coeffs);
        double  I1 = invariants[0],
                I2 = invariants[1],
                I3 = invariants[2],
                K1 = invariants[3],
                K2 = invariants[4],
                K3 = invariants[5];
        HideSeekType hsk = new HideSeekType(invariants);
        EnumType enumType = hsk.typeSSO();
        String typeSSO = enumType.getKey();
        String answerText = "За\\,таблицею\\,(див.нижче)\\,дізнаємося,\\,що\\,рівняння\\,задає\\,\\textcolor{red}{" + txtAdd.spaceLatex(typeSSO) + "},\\,оскільки\\,І_{3}",
                textK2 = "",
                textK1 = "",
                answerK2 = "",
                answerK1 = "";

        int numberType = enumType.getValue();
        if (I3 != 0) {
            answerText += "≠0,\\,";
            if (numberType < 4)
                answerText += "І_{2}>0,\\,І_{1}⋅І_{3}>0";
            else
                answerText += (I2 <= 0 ? "І_{2}≤0" : "І_{1}⋅І_{3}≤0");
            answerText += "\\,та\\,K_{3}\\," + ((numberType == 2 || numberType == 4) ? ">" : (numberType == 5 || numberType == 1) ? "<" : "=") + "\\,0";
        }
        else {
            answerText += "=0";
            if (K3 != 0)
                answerText += "\\,та\\,K_{3}"+(K3 > 0 ? ">":"<")+"0";
            else {
                answerText += ",\\,K_{3}=0,\\,";
                answerK2 = "Оскільки\\,І_{3}=0\\,та\\,K_{3}=0,\\,тому\\,порахуємо\\,ще\\,ортогональний\\,семіінваріант\\,K_{2}:\\\\[0.1in]";
                textK2 = txtAdd.latexText("K_{2}\\,=\\,") + sumDet3(new String[] {"a_{11}", "a_{12}", "a_{1}", "a_{22}", "a_{2}", "a_{0}", "a_{13}", "a_{33}", "a_{3}", "a_{23}"}) + txtAdd.latexText("\\,=\\,") + sumDet3(new double[] {this.coeffs[0], this.coeffs[3] / 2.0, this.coeffs[6] / 2.0, this.coeffs[1], this.coeffs[7] / 2.0, this.coeffs[9], this.coeffs[4] / 2.0, this.coeffs[2], this.coeffs[8] / 2.0, this.coeffs[5] / 2.0}) + txtAdd.latexText("\\,=\\,"+K2);
                if (I2 > 0) {
                    answerText += "I_{2}>0\\,та\\,";
                    if (K2 == 0)
                        answerText += "K_{2}=0";
                    else
                        answerText += "I_{1}*K_{2}"+((I1*K2 > 0) ? ">":"<") + "0";
                }
                else if (I2 < 0)
                    answerText += "I_{2}<0\\,та\\,K_{2}" + (K2 != 0 ? "≠":"=") + "0";
                else {
                    answerText += "I_{2}=0";
                    if (K2 != 0)
                        answerText += "\\,та\\,K_{2}≠0";
                    else {
                        answerK1 = "Оскільки\\,І_{3}=0,\\,K_{3}=0,\\,I_{2}=0\\,та\\,K_{2}=0,\\,тому\\,порахуємо\\,ще\\,ортогональний\\,семіінваріант\\,K_{1}:\\\\[0.1in]";
                        textK1 = txtAdd.latexText("K_{1}\\,=\\,") + sumDet2(new String[] {"a_{11}", "a_{1}", "a_{0}", "a_{22}", "a_{2}", "a_{0}", "a_{33}", "a_{3}", "a_{0}"}) + txtAdd.latexText("\\,=\\,") + sumDet2(new double[] {this.coeffs[0], this.coeffs[6] / 2.0, this.coeffs[9], this.coeffs[1], this.coeffs[7] / 2.0, this.coeffs[9], this.coeffs[2], this.coeffs[8] / 2.0, this.coeffs[9]}) + txtAdd.latexText("\\,=\\,"+K1);
                        answerText += ",\\,K_{2}=0\\,та\\,K_{1}" + (K1 > 0 ? ">":K1 < 0 ? "<":"=") + "0";
                    }
                }
            }
        }
        finalAnswer(model, new String[]{answerText, textK2, answerK2, textK1, answerK1, typeSSO});
    }

    private void finalAnswer(ModelMap model, String[] arr) throws Exception {
        TextAdd txtAdd = new TextAdd(this.coeffs);
        model.addAttribute("why", txtAdd.latexText(arr[0]));
        model.addAttribute("textK2", arr[1]);
        model.addAttribute("answerK2", txtAdd.latexText(arr[2]));
        model.addAttribute("textK1", arr[3]);
        model.addAttribute("answerK1", txtAdd.latexText(arr[4]));
        model.addAttribute("typeSSO", arr[5]);
        model.addAttribute("answer", txtAdd.latexText("Відповідь:\\,\\underline{" + txtAdd.spaceLatex(arr[5]) + "}"));
    }

    public void solution(ModelMap model) throws Exception {
        TextAdd txtAdd = new TextAdd(this.coeffs);
        addAtrs(model);
        double[] invariants = myDets(this.coeffs);
        model.addAttribute("textI1", txtAdd.textK1(new String[] {"a_{11}", "a_{12}", "a_{22}"}));
        model.addAttribute("equation", txtAdd.allEquation());
        model.addAttribute("gequation", latexText("Загальне\\,рівняння\\,поверхні:\\\\[0.1in]" + "a_{11}x²\\,+\\,a_{22}y²\\,+\\,a_{33}z²\\,+\\,2a_{12}xy\\,+\\,2a_{13}xz\\,+\\,2a_{23}yz\\,+\\,2a_{1}x\\,+\\,2a_{2}y\\,+\\,2a_{3}z\\,+\\,a_{0}\\,=\\,0"));
        model.addAttribute("coeff", txtAdd.coeff());
        model.addAttribute("textI1", txtAdd.textI1(invariants[0]));
        model.addAttribute("textI2", txtAdd.textI2(invariants[1]));
        model.addAttribute("textI3", txtAdd.textI3(invariants[2]));
        model.addAttribute("textK3", txtAdd.textK3(invariants[5]));
        answerHTML(invariants, model);
    }

    public void addAtrs(ModelMap model) {
        for (int i = 0;i<this.coeffs.length;i++)
            model.addAttribute(namesAtr[i], this.coeffs[i]);
    }

    public String latexText(String text) {
        return "\\(" + text + "\\)";
    }
}
