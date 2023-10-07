フロント側ですり抜けたエラーをバックエンドで拾うことがある->Javaでのバリデーションは必要

## バリデーションの目的

- 入力内容や記述内容が要件を満たしているか、妥当性を確認すること
- 001-create-table-and-load-data.sqlで定義されているバリデーションよりも優先的にエラーチェックをする(Mapperの処理より先にエラーチェックを行う)

|           | Null | 空文字 | 空白 | 全角スペース |
|:---------:|:----:|:---:|:--:|:------:|
| @NotNull  |  ×   |  ○  | ○  |   ○    | 
| @NotEmpty |  ×   |  ×  | ○  |   ○    |
| @NotBlanc |  ×   |  ×  | ×  |   ○    |

- @Not Null：変数やメソッドの引数がnullであってはならない。変数や引数には必ず値が入っていなければならない。
- @Not Blanc：文字列が空ではないこと。文字列を少なくとも1つ以上持っていること（名前が空白のままでは送信できないなど）
- @Not Empty：コレクション（リスト、セットなど）が空ではないこと。1つ以上の要素を持っていること。

- 空文字:文字列が0のこと。
- null:箱も中身もない。何も存在しない。
- blanc:箱はあるけど何も入ってない。半角スペース、全角スペースのこと。