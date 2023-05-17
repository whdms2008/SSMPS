import cv2
import os
size = (416, 416)
path = r"C:\Users\freet\Documents\GitHub\SSMPS_minju\SSMPS_Ai\product\20_product\two_augmentation\product_image\product_image_yolo\train\images"
img_list = os.listdir(path)
for img_l in img_list:
    # print(f"{path}/{img_l}")
    img = cv2.imread(f"{path}/{img_l}")
    img = cv2.resize(img, size)
    cv2.imwrite(f"{path}/{img_l}", img)
    # cv2.imshow("img", img)
    # cv2.waitKey(0)