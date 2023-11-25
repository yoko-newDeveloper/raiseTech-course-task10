package com.example.raisetechcoursetask10.controller.form;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class SkiresortPatchFormTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class BlankTest {

        @Test
        public void nameとareaとcustomerEvaluationがすべて半角スペースである時バリデーションエラーとなること() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm(" ", " ", " ");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrCustomerEvaluation",
                                    "name, area, customerEvaluationのいずれかを入力してください")
                    );
        }

        @Test
        void nameのみが半角スペースの時バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm(" ", "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        void areaのみが半角スペースの時バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", " ", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        void customerEvaluationのみが半角スペースの時バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", "Canada", " ");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }
    }
}
