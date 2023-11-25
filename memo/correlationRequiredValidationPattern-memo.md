# バリデーションパターンを整理する

### 全てのテストパターン

NGパターン

|       value        |  NG  | NG | NG  | 
|:------------------:|:----:|:--:|:---:|
|        name        | null | "" | " " | 
|        area        | null | "" | " " |
| customerEvaluation | null | "" | " " | 

- nameOKパターン

|       value        |  OK   |   OK   |  OK   |  OK   |   OK   |  OK   |  OK   |   OK   |  OK   |  OK   |  OK   |  OK   |  OK   |  OK   |  OK   |
|:------------------:|:-----:|:------:|:-----:|:-----:|:------:|:-----:|:-----:|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|        name        | goryu | goryu  | goryu | goryu | goryu  | goryu | goryu | goryu  | goryu | goryu | goryu | goryu | goryu | goryu | goryu |
|        area        | null  | nagano | null  |  ""   | nagano |  ""   |  " "  | nagano |  " "  | null  |  ""   | null  |  " "  |  ""   |  " "  |
| customerEvaluation | null  |  null  | good  |  ""   |   ""   | good  |  " "  |  " "   | good  |  ""   | null  |  " "  | null  |  " "  |  ""   |

- areaOKパターン

|       value        |   OK   |   OK   |   OK   |   OK   |   OK   |   OK   |   OK   |   OK   |   OK   |   OK   |   OK   |   OK   |   OK   | OK | OK |
|:------------------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:--:|:--:|
|        name        |  null  |  null  |   ""   |   ""   |  " "   |  " "   |  null  |   ""   |  null  |  " "   |   ""   |  " "   |        |    |    |
|        area        | nagano | nagano | nagano | nagano | nagano | nagano | nagano | nagano | nagano | nagano | nagano | nagano | nagano |
| customerEvaluation |  null  |  good  |   ""   |  good  |  " "   |  good  |   ""   |  null  |  " "   |  null  |  " "   |   ""   |

- customerEvaluationOKパターン

|       value        |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |  OK  |
|:------------------:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|
|        name        | null |  ""  | " "  | null |  ""  | " "  | null |  ""  | " "  |
|        area        | null |  ""  | " "  |  ""  | null | null | " "  | " "  |  ""  |
| customerEvaluation | good | good | good | good | good | good | good | good | good |

### 実装パターン

|       value        |  NG  | NG | NG  |  OK   |   OK   |   OK   |  OK   |   OK   |   OK   |  OK   |   OK   |  OK   |   OK   |  OK  |
|:------------------:|:----:|:--:|:---:|:-----:|:------:|:------:|:-----:|:------:|:------:|:-----:|:------:|:-----:|:------:|:----:|
|        name        | null | "" | " " | goryu | goryu  |   ""   | goryu | goryu  |  " "   | goryu | goryu  | goryu |  null  |  ""  |
|        area        | null | "" | " " | null  | nagano | nagano |  ""   | nagano | nagano |  " "  | nagano | null  | nagano | " "  |
| customerEvaluation | null | "" | " " | good  |  null  |  good  | good  |   ""   |  good  | good  |  " "   |  ""   |  " "   | good |

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
