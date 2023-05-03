import os


path = r'C:\Users\freet\Documents\GitHub\SSMPS_minju\SSMPS_Ai\product\20_product_2'
l = os.listdir(f'{path}/train/images')
l2 = []

for i in l:
    if i[:5] not in l2:
        l2.append(i[:5])

f = open(f"{path}/data.yaml", 'w')
f.write("train: \n")
f.write("val: \n")
f.write("test: \n")
f.write(f"nc: {len(l2)}\n")
f.write("names:")
f.write("[")
for i in l2[:-1]:
    f.write(f"'{i}',\n")
f.write(f"'{l2[-1]}']")

f.close()

print(l2)