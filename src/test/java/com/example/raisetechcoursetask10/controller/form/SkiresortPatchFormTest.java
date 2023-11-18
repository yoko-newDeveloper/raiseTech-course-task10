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
    public static void setValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class NullTest {
        @Test
        public void nameがnullの時にバリデーションエラーとなること() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm(null, "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);

            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrCustomerEvaluation",
                                    "name, area, customerEvaluationのいずれかが空白です"
                            )
                    );
        }

        @Test
        public void areaがnullの時にバリデーションエラーとなること() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", null, "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrCustomerEvaluation",
                                    "name, area, customerEvaluationのいずれかが空白です"
                            )
                    );
        }

        @Test
        public void customerEvaluationがnullの時にバリデーションエラーとなること() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", "Canada", null);

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrCustomerEvaluation",
                                    "name, area, customerEvaluationのいずれかが空白です")
                    );
        }
    }

    @Nested
    class EmptyTest {

        @Test
        public void nameが空文字の時にバリデーションエラーとなること() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("", "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);

            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrCustomerEvaluation",
                                    "name, area, customerEvaluationのいずれかが空白です")
                    );
        }

        @Test
        public void areaが空文字の時にバリデーションエラーとなること() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", "", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);

            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrCustomerEvaluation",
                                    "name, area, customerEvaluationのいずれかが空白です")
                    );
        }

        @Test
        public void customerEvaluationが空文字の時にバリデーションエラーとなること() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", "Canada", "");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);

            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrCustomerEvaluation",
                                    "name, area, customerEvaluationのいずれかが空白です")
                    );

        }
    }
}
