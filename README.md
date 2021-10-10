# Algorithm
## Java
### 배열 출력 방법
1차원 배열 - Arrays.toString(arr)   
다차원 배열 - Arrays.deepToString(arr)

### Long
답이 int 범위(약 20억)를 벗어나는 경우 long을 사용

## Dynamic Programming
### When
- 부분(하위) 문제로 나눌 수 있는 경우
- 이전 선택이 다음 선택에 영향을 미치는 경우
- 현재의 값은 최적의 값이라 생각

-> 점화식으로 나타내어 해결
  
### How
1. **Bottom-up**   
   반복문 이용   
   초기값 설정 후 값을 누적해서 채워나감   
   1차원 또는 2차원 배열 이용   
   인덱스를 이용해 항(구간) 표현
   
   
2. **Top-down**   
   재귀 이용   
   종료 조건으로 초기값 반환   
   파라미터를 이용해 항(구간) 표현   
   Memoization을 이용하여 재귀 호출을 줄일 수 있음(가지치기)
   
### Tip
- IndexError 주의
- index - 1을 하는 경우를 위해 인덱스는 1 ~ n으로 설정   
