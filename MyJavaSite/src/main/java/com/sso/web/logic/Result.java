package com.sso.web.logic;

import org.springframework.ui.ModelMap;

public class Result {
    private final Butler butler = new Butler();
    private final HideSeekType hsk = new HideSeekType();
    private final TextAdd txtAdd = new TextAdd();
    private final Det dets = new Det();
    public void answerHTML(ModelMap model, double[] arr) {
        String[] types = {"Еліпсоїд", "Уявний\\,еліпсоїд", "Уявний\\,конус", "Однопорожнинний\\,гіперболоїд", "Двопорожнинний\\,гіперболоїд", "Конус", "Еліптичний\\,параболоїд", "Гіперболічний\\,параболоїд", "Еліптичний\\,циліндр", "Уявний\\,еліптичний\\,циліндр", "Пара\\,уявних\\,площин,\\,що\\,перетинаються", "Гіперболічний\\,циліндр", "Пара\\,площин,\\,що\\,перетинаються", "Пара\\,паралельних\\,площин", "Пара\\,уявних\\,паралельних\\,площин", "Пара\\,площин,\\,що\\,збігаються", "Параболічний\\,циліндр"};
        int numberType = (int) model.getAttribute("numberOfType");
        String typeSSO = types[numberType-1];
        String answerText = "За\\,таблицею\\,(див.нижче)\\,дізнаємося,\\,що\\,рівняння\\,задає\\,\\textcolor{red}{" + typeSSO + "},\\,оскільки\\,І_{3}",
                textK2 = "",
                textK1 = "",
                answerK2 = "",
                answerK1 = "";
        double  I1 = butler.convertToDouble(model, "I1"),
                I2 = butler.convertToDouble(model, "I2"),
                I3 = butler.convertToDouble(model, "I3"),
                K1 = butler.convertToDouble(model, "K1"),
                K2 = butler.convertToDouble(model, "K2"),
                K3 = butler.convertToDouble(model, "K3");
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
                textK2 = txtAdd.latexText("K_{2}\\,=\\,") + dets.sumDet3(new String[] {"a_{11}", "a_{12}", "a_{1}", "a_{22}", "a_{2}", "a_{0}", "a_{13}", "a_{33}", "a_{3}", "a_{23}"}) + txtAdd.latexText("\\,=\\,") + dets.sumDet3(new double[] {arr[0], arr[3] / 2.0, arr[6] / 2.0, arr[1], arr[7] / 2.0, arr[9], arr[4] / 2.0, arr[2], arr[8] / 2.0, arr[5] / 2.0}) + txtAdd.latexText("\\,=\\,"+K2);
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
                        textK1 = txtAdd.latexText("K_{1}\\,=\\,") + dets.sumDet2(new String[] {"a_{11}", "a_{1}", "a_{0}", "a_{22}", "a_{2}", "a_{0}", "a_{33}", "a_{3}", "a_{0}"}) + txtAdd.latexText("\\,=\\,") + dets.sumDet2(new double[] {arr[0], arr[6] / 2.0, arr[9], arr[1], arr[7] / 2.0, arr[9], arr[2], arr[8] / 2.0, arr[9]}) + txtAdd.latexText("\\,=\\,"+K1);
                        answerText += ",\\,K_{2}=0\\,та\\,K_{1}" + (K1 > 0 ? ">":K1 < 0 ? "<":"=") + "0";
                    }
                }
            }
        }
        finalAnswer(model, new String[]{answerText, textK2, answerK2, textK1, answerK1, typeSSO});
    }

    private void finalAnswer(ModelMap model, String[] arr) {
        model.addAttribute("why", txtAdd.latexText(arr[0]));
        model.addAttribute("textK2", arr[1]);
        model.addAttribute("answerK2", txtAdd.latexText(arr[2]));
        model.addAttribute("textK1", arr[3]);
        model.addAttribute("answerK1", txtAdd.latexText(arr[4]));
        model.addAttribute("answer", txtAdd.latexText("Відповідь:\\,\\underline{" + arr[5] + "}"));
        model.addAttribute("typeSSO", arr[5]);
    }

    public void solution(ModelMap model, double[] a) {
        butler.addAtrs(model, a);
        dets.myDets(model, a);
        hsk.typeSSO(model);
        txtAdd.textK1(model, new String[] {"a_{11}", "a_{12}", "a_{22}"});
        txtAdd.allEquation(model, a);
        txtAdd.gequation(model, "Загальне\\,рівняння\\,поверхні:\\\\[0.1in]" + "a_{11}x²\\,+\\,a_{22}y²\\,+\\,a_{33}z²\\,+\\,2a_{12}xy\\,+\\,2a_{13}xz\\,+\\,2a_{23}yz\\,+\\,2a_{1}x\\,+\\,2a_{2}y\\,+\\,2a_{3}z\\,+\\,a_{0}\\,=\\,0");
        txtAdd.coeff(model, a);
        txtAdd.textI1(model, new double[] {a[0], a[1], a[2]});
        txtAdd.textI2(model, a);
        txtAdd.textI3(model, a);
        txtAdd.textK3(model, a);
        answerHTML(model, a);
    }
}
