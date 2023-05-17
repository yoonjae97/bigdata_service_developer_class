
nums = [1, 3, 2, 4, 5, 8, 10, 11, 13, 19, 22, 27]

evenList = [i for i in nums if i % 2 == 0]
print(evenList)

nums2 = nums
nums2[0] = 100

print(nums)
print(nums2)

from copy import deepcopy
nums = [1, 3, 2, 4, 5, 8, 10, 11, 13, 19, 22, 27]
nums2 = deepcopy(nums)
nums2[0] = 100

print(nums)
print(nums2)

nums3 = [num for num in nums] # 축약을 이용한 깊은 복사
nums3[0] = 1
print(nums)
print(nums2)
print(nums3)

dataList = [
    {'name':'홍길동','age':12},
    {'name':'임꺽정','age':11},
    {'name':'장길산','age':13},
    {'name':'홍명희','age':14},
    {'name':'홍경래','age':15}
]

# 나이가 13살 이상인 사람만
age13 = [i for i in dataList if i['age'] >=13]
print(age13)

hong = [i for i in dataList if ('경') in i['name']]
print(hong)







