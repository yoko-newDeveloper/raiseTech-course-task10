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
            SkiresortPatchForm skiresortPatchForm = new SkiresortPatchForm(null, "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(skiresortPatchForm);
            assertThat(violations).isEmpty();
        }
    }
}
