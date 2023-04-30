import cv2
import time
import torch
from models.experimental import attempt_load
from utils.datasets import letterbox
from utils.general import non_max_suppression, scale_coords
from utils.torch_utils import select_device

def detect_video(source=0, weights_path="yolov7-face.pt", conf_threshold=0.25):
    # Yolov7 model 초기화
    device = select_device('')
    model = attempt_load(weights_path, map_location=device)
    stride = int(model.stride.max())
    names = model.module.names if hasattr(model, 'module') else model.names

    # 영상소스 설정
    cap = cv2.VideoCapture(source)
    if not cap.isOpened():
        print("Error: 영상 소스를 열 수 없음")
        return

    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break

        # detection 시작
        start_time = time.time()
        img = letterbox(frame, new_shape=640, auto=False)[0]
        img = img[..., ::-1].transpose((2, 0, 1))
        img = torch.from_numpy(img.copy()).to(device)
        img = img.float()
        img /= 255.0 
        if img.ndimension() == 3:
            img = img.unsqueeze(0)

        pred = model(img, augment=False)[0]
        pred = non_max_suppression(pred, conf_threshold, 0.4, agnostic=False)
        end_time = time.time()
        fps = 1 / (end_time - start_time)

        # 바운딩 박스 그리기
        for i, det in enumerate(pred):
            if len(det):
                det[:, :4] = scale_coords(img.shape[2:], det[:, :4], frame.shape).round()
                for *xyxy, conf, cls in det:
                    label = f"{names[int(cls)]}: {conf:.2f}"
                    cv2.rectangle(frame, (int(xyxy[0]), int(xyxy[1])), (int(xyxy[2]), int(xyxy[3])), (255, 0, 0), 2)
                    cv2.putText(frame, label, (int(xyxy[0]), int(xyxy[1]) - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 0, 0), 2)

            # 화면 출력
            cv2.putText(frame, f"FPS: {fps:.2f}", (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 0.6, (0,0, 255), 2)
            cv2.imshow("YOLOv7 Object Detection", frame)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    cap.release()
    cv2.destroyAllWindows()

if __name__ == "__main__":
    detect_video(source="mask2.mp4")
