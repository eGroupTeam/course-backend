# course-backend

## 第十一章 利用 postman 進行測試

### 測試

- [Writing tests](https://learning.postman.com/docs/writing-scripts/test-scripts/)
- [Parsing response body data](https://learning.postman.com/docs/writing-scripts/script-references/test-examples/#parsing-response-body-data)
- [Postman: Automating Rest APIs](https://medium.com/geekculture/automating-rest-apis-with-postman-c740049b56dd)
- [Postman: JSON Schema Validation](https://medium.com/geekculture/postman-json-schema-validation-ed09b3532a39)
- [Free Online JSON to JSON Schema Converter](https://www.liquid-technologies.com/online-json-to-schema-converter)

#### 測試是否回傳狀態及回覆速度

    pm.test("Status test", function () {
      pm.response.to.have.status(200);
    });

    pm.test("Status test", function () {
      pm.response.to.have.status(404);
    });

    pm.test("Response time is less than 200ms", () => {
      pm.expect(pm.response.responseTime).to.be.below(200);
    });

#### 測試回傳內容

    pm.test("response must be valid and have a body", function () {
      pm.response.to.be.ok;
      pm.response.to.be.withBody;
      pm.response.to.be.json;
    });

    pm.test("response must be error and have a body", function () {
      pm.response.to.be.error;
      pm.response.to.be.withBody;
      pm.response.to.be.json;
    });

#### 測試資料內容

    pm.test("order 1", () => {
      const responseJson = pm.response.json();
      pm.expect(responseJson.id).to.eql(1);
      pm.expect(responseJson.customerId).to.eql(2);
      pm.expect(responseJson.items[0].id).to.eql(1);
      pm.expect(responseJson.items[0].orderId).to.eql(1);
      pm.expect(responseJson.items[0].productId).to.eql(1);
      pm.expect(responseJson.items[0].amount).to.eql(2);
      pm.expect(responseJson.items[0].price).to.eql(8000);
    });


    const expectedObject = {
        "id": 1,
        "customerId": 2,
        "items": [
            {
                "id": 1,
                "orderId": 1,
                "productId": 1,
                "amount": 2,
                "price": 8000
            }
        ]
      };
    pm.test("test order with object", () => {
      pm.expect(pm.response.json()).to.deep.equal(expectedObject);
    });

#### 測試資料的 schema

    const errorSchema = {
      "type": "object",
      "properties": {
        "timestamp": {
          "type": "string"
        },
        "status": {
          "type": "integer"
        },
        "error": {
          "type": "string"
        },
        "message": {
          "type": "string"
        },
        "path": {
          "type": "string"
        }
      },
      "required": [
        "timestamp",
        "status",
        "error",
        "message",
        "path"
      ]
    }
    pm.test('Schema is valid', function() {
      pm.response.to.have.jsonSchema(errorSchema);
    });


    const itemSchema = {
        "properties": {
            "id":{"type":"number"},
            "orderId":{"type":"number"},
            "productId":{"type":"number"},
            "amount":{"type":"number"},
            "price":{"type":"number"},

        }

    }
    const orderSchema = {
      "properties": {
        "id": {
          "type": "number"
        },
        "customerId":{
            "type":"number"
        },
        "items":{
            "type":"array",
            "items":[
                itemSchema
            ]


        }

      }
    };
    const schema = {
        "type":"array",
                "items":[
                orderSchema
            ]
    }

    pm.test('Schema is valid', function() {
      pm.response.to.have.jsonSchema(schema);
    });

注意，當有語法有問題時，其實還是會回傳 success...可以利用 console.log 確保程式真的執行

    pm.test('there are values'), function(){
    console.log("here4");

    };

### 作業:完成所有 REST service 的對應測試，修完 bug....

請將 postman 的測試腳本，Export Json，放到目錄 Push 到 github
