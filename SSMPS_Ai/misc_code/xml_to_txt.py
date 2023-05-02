import os
import xml.etree.ElementTree as ET
folder = r'C:\Users\freet\Documents\GitHub\SSMPS_minju\SSMPS_Ai\product\20_product\label'
txt_folder = r'C:\Users\freet\Documents\GitHub\SSMPS_minju\SSMPS_Ai\product\20_product\label_txt'
files = [f for f in os.listdir(folder)]

for file_name in files:
    # 라벨링된 XML 파일을 열어서 내용을 확인합니다.
    tree = ET.parse(f'{folder}/{file_name}')
    root = tree.getroot()
    # 추출한 정보를 쓰기 위한 텍스트 파일을 엽니다.
    with open(f'{txt_folder}/{file_name[:-4]}.txt', 'w') as f:
    
        # XML 파일에서 라벨링 정보가 들어있는 태그를 찾습니다.
        for obj in root.iter('object'):
            
            # 해당 태그에서 필요한 정보를 추출합니다.
            name = obj.find('name').text
            xmin = obj.find('bndbox/xmin').text
            ymin = obj.find('bndbox/ymin').text
            xmax = obj.find('bndbox/xmax').text
            ymax = obj.find('bndbox/ymax').text
            
            # 추출한 정보를 텍스트 파일에 쓰는 코드를 작성합니다.
            line = f"{name} {xmin} {ymin} {xmax} {ymax}\n"
            f.write(line)

        # 변환된 파일을 저장합니다.
        f.close()
