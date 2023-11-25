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

class SkiresortPatchFormTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class OkPatternTest {

        @Test
        void name以外がnullと空文字の場合バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", null, "");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        void area以外がnullと半角スペースの場合バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm(null, "Canada", " ");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        void customerEvaluation以外が空文字と半角スペースの場合バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("", " ", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }
    }
}
