# issue
1. face detection
2. product detection

# member
1. face: 진민주, 조은
2. product: 진민주

# explain
1. face folder: face detection
2. product folder: product detection
3. misc_code: image_processing codes
```
1. mv: 카테고리 별로 폴더에 들어가 있는 이미지와 라벨링을 한 폴더에 넣기
(원본 이미지에서 Train Val 나눠진 폴더는 합쳐야 하고, mv를 돌리고 나서 카테고리별 폴더는 삭제)

2. del_not_meta: 일반 xml에는 item_no attribute가 없어서 삭제
(label_txt 폴더는 직접 만들어야함)

3. xml_to_txt: _meta.xml에서 txt 라벨링 파일로 변경

4. found: image의 라벨링 파일과 txt가 맞게 있는지 확인

5. dataset_split: 카테고리 별로 데이터셋 split

6. create_yaml: data.yaml 파일 만들기(이미지 number로 라벨링)
```