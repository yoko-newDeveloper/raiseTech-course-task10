# バリデーションパターンを整理する

|       value        |  NG  | NG | NG  |   OK   |    OK    |  OK  |
|:------------------:|:----:|:--:|:---:|:------:|:--------:|:----:| 
|        name        | null | "" | " " | niseko |    ""    | null |
|        area        | null | "" | " " |  null  | hokkaido |  ""  |
| customerEvaluation | null | "" | " " |   ""   |   " "    | good |

- 論理演算子：||（論理和）
    - true || false:どちらかがtrueであれば全体がtrue
    - false || false:どちらもfalseならfalse
    - true || true || false:最初のtrueで評価終了、つまりtrue

```
// name、area、customerEvaluationがすべてnullまたは空文字列または半角スペースである場合にfalseを返す->各変数がnullまたは空文字列または半角スペースである場合に成立
if ((name == null || name.isBlank()) && (area == null || area.isBlank()) && (customerEvaluation == null || customerEvaluation.isBlank())) {
    return false;
    
// name、area、customerEvaluationのうち少なくとも1つが空文字列または半角スペースである場合にもfalseを返す->いずれかの変数が空文字列または半角スペースである場合に条件が成立    
} else if (name.isBlank() || area.isBlank() || customerEvaluation.isBlank()) {
    return false;
}

// どちらにも当てはまらない場合にtrueを返す->全ての変数がnull,空文字,半角スペースではない場合
return true;
```

```
.extracting(
    violation -> violation.getPropertyPath().toString(), // プロパティのパスを文字列に変換
    ConstraintViolation::getMessage // 制約違反のメッセージを取得
)
.containsExactlyInAnyOrder(
    tuple(
        "nameOrAreaOrCustomerEvaluation", // 期待されるプロパティのパス
        "name, area, customerEvaluationのいずれかを入力してください" // 期待されるエラーメッセージ
    )
);
```
