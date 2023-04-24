import requests
import json

url = "" # 주소
data = {
    "key": "value",
    "key2": "value2"
}

headers = {
    "Content-Type": "application/json"
}

response = requests.post(url, data=json.dumps(data), headers=headers)

if response.status_code == 201:
    print("데이터 전송 완료")
else:
    print("에러 : ", response.status_code)
