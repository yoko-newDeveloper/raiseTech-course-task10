package com.example.raisetechcoursetask10.controller.form;

import jakarta.validation.constraints.AssertTrue;

public class SkiresortPatchForm {

    private final String name;
    private final String area;
    private final String customerEvaluation;

    public SkiresortPatchForm(String name, String area, String customerEvaluation) {
        this.name = name;
        this.area = area;
        this.customerEvaluation = customerEvaluation;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getCustomerEvaluation() {
        return customerEvaluation;
    }

    @AssertTrue(message = "name, area, customerEvaluationのいずれかを入力してください")
    public boolean isNameOrAreaOrCustomerEvaluation() {
        // name,area,customerEvaluationのすべてがnull又は空文字または半角スペースの時にfalse(バリデーションエラー)を返す
        if ((name == null || name.isBlank()) && (area == null || area.isBlank()) && (customerEvaluation == null || customerEvaluation.isBlank())) {
            return false;
        } else {
            return true;
        }
    }
}
