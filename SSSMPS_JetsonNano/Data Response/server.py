
import socket, threading
import json


def binder(client_socket, addr):
    print('연결됨 : ', addr)
    try:
        while True:
            data = client_socket.recv(4)
            length = int.from_bytes(data, "little")
            data = client_socket.recv(length)
            msg = data.decode()
            if msg == "":
                continue
            print('반환 값 : ', addr, msg)

            try:
                msg_dict = json.loads(msg)
            except json.JSONDecodeError:
                print("전달된 문자열이 JSON 형식이 아님")
                continue

            if msg_dict.get("type") == "start":
                print("start 됨")
                msg = "ok"
            else:
                print("other")
                msg = "echo : " + msg

            data = msg.encode()
            length = len(data)
            client_socket.sendall(length.to_bytes(4, byteorder='little'))
            client_socket.sendall(data)
    except Exception as e:
        print("except : ", addr, e)
    finally:
        client_socket.close()


server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# 소켓 레벨과 데이터 형태를 설정한다.
server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

server_socket.bind(('1.234.5.119', 12345))

server_socket.listen(1)

try:
    while True:
        client_socket, addr = server_socket.accept()
        th = threading.Thread(target=binder, args=(client_socket, addr))
        th.start()
except:
    print("server")
finally:
    server_socket.close()
