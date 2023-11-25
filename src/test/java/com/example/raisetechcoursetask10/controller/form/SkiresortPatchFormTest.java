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
    class NullTest {

        @Test
        public void nameとareaとcustomerEvaluationの全てがnullの時にバリデーションエラーとなること() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm(null, null, null);

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).hasSize(1)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrCustomerEvaluation",
                                    "name, area, customerEvaluationのいずれかを入力してください")
                    );
        }

        @Test
        public void nameのみがnullの時にバリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("", "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void areaのみがnullの時にバリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", null, "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void customerEvaluationの時にバリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", "Canada", null);

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }
    }

    @Nested
    class EmptyStringTest {

        @Test
        public void nameとareaとcustomerEvaluationがすべて空文字である時バリデーションエラーとなること() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("", "", "");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).hasSize(1);
            // 制約違反(ConstraintViolation)情報で、どのプロパティに関連しているか、エラーメッセージが何かを検証する
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrCustomerEvaluation",
                                    "name, area, customerEvaluationのいずれかを入力してください")
                    );
        }

        @Test
        public void nameのみが空文字の時バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("", "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void areaのみが空文字の時バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Louise", "", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void customerEvaluationのみが空文字の時バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("Lake Loise", "Canada", "");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }
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
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("goryu", " ", "Ski the World Heritage Site of the Canadian Rockies");

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
        void customerEvaluation以外が半角スペースと空文字の場合バリデーションエラーとならないこと() {
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm("", " ", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }
    }
}
